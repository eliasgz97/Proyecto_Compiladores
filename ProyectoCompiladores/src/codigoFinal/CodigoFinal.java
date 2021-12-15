package codigoFinal;

import java.util.ArrayList;
import comprobacionTipos.TablaSimbolos;
import codigoIntermedio.TablaCuadruplos;
import codigoIntermedio.Cuadruplo;
import comprobacionTipos.Simbolo;
import java.util.Stack;

public class CodigoFinal {

    TablaSimbolos tabla_simbolos;
    TablaCuadruplos cuadruplos;
    ArrayList<String> lineas = new ArrayList();
    ArrayList<Simbolo> variables;
    ArrayList<String> msjs;
    ArrayList<Stack<String>> registros_s = new ArrayList();
    ArrayList<String> locales = new ArrayList();

    public CodigoFinal(TablaSimbolos tabla_simbolos, TablaCuadruplos cuadruplos) {
        this.tabla_simbolos = tabla_simbolos;
        this.cuadruplos = cuadruplos;
        msjs = cuadruplos.getMensajes();
        variables = tabla_simbolos.getVariablesGlobales();
        inicializarRegistros(temporales);
        inicializarRegistros(registros_S);
        inicializarRegistros(registros_A);
//        inicializarRegistros(registros_V);
//        initPilaRegistros();
    }

    public ArrayList<String> getCodigoFinal() {
        return lineas;
    }

    public void generarData() {
        lineas.add("\t .data");
        for (Simbolo s : variables) {
            if (s.tipoVariable.toLowerCase().equals("integer")) {
                lineas.add("_" + s.nombre + ":\t .word 0");
            }
            if (s.tipoVariable.toLowerCase().equals("boolean")) {
                lineas.add("_" + s.nombre + ":\t .byte 0");
            }
            if (s.tipoVariable.toLowerCase().equals("float")) {
                lineas.add("_" + s.nombre + ":\t .float 0.0");
            }
        }
        int contador = 1;
        for (String mesgs : msjs) {
            lineas.add("_mesg" + contador + ":\t .asciiz " + mesgs);
            contador++;

        }
        lineas.add("");
        lineas.add("\t .text");
        lineas.add("\t .globl _main");
        /*lineas.add("_true:        .asciiz \"true\\n\"");
        lineas.add("_false:        .asciiz \"false\\n\"");
        lineas.add("bool:   .space 8");
        lineas.add("");
        lineas.add("");
         */

    }

    public void llamadoMetodos() {
        generarData();
        generarCodigo();
    }

    public void generarCodigo() {
        ArrayList<Cuadruplo> cuadruplosRecorrer = this.cuadruplos.getCuadruplos();
        for (int i = 0; i < cuadruplosRecorrer.size(); i++) {
            if (cuadruplosRecorrer.get(i).getOperator().equals("print")) {
                generarPrint(cuadruplosRecorrer.get(i));
            }
            if (cuadruplosRecorrer.get(i).getOperator().equals("=")) {
                generarAsignacion(cuadruplosRecorrer.get(i));
            }
            if (cuadruplosRecorrer.get(i).getOperator().equals("+")) {
                generarOperacion(cuadruplosRecorrer.get(i), "add");
            }
            if (cuadruplosRecorrer.get(i).getOperator().equals("*")) {
                generarOperacion(cuadruplosRecorrer.get(i), "mul");
            }
            if (cuadruplosRecorrer.get(i).getOperator().equals("/")) {
                generarOperacion(cuadruplosRecorrer.get(i), "div");
            }
            if (cuadruplosRecorrer.get(i).getOperator().equals("-")) {
                generarOperacion(cuadruplosRecorrer.get(i), "sub");
            }
            if (cuadruplosRecorrer.get(i).getOperator().contains("IF")) {
                String oprel = cuadruplos.getCuadruplos().get(i).getOperator().substring(2);
                if (oprel.equals("<")) {
                    generarIf(cuadruplos.getCuadruplos().get(i), "blt");
                } else if (oprel.equals("<=")) {
                    generarIf(cuadruplos.getCuadruplos().get(i), "ble");
                } else if (oprel.equals(">")) {
                    generarIf(cuadruplos.getCuadruplos().get(i), "bgt");
                } else if (oprel.equals(">=")) {
                    generarIf(cuadruplos.getCuadruplos().get(i), "bge");
                } else if (oprel.equals("=")) {
                    generarIf(cuadruplos.getCuadruplos().get(i), "beq");
                } else if (oprel.equals("<>")) {
                    generarIf(cuadruplos.getCuadruplos().get(i), "bne");
                }
            }
            if (cuadruplos.getCuadruplos().get(i).getOperator().contains("GOTO")) {
                lineas.add("\t b _" + cuadruplos.getCuadruplos().get(i).getArgs1());
            }
            if (cuadruplos.getCuadruplos().get(i).getOperator().equals("ETIQ")) {
                lineas.add("");
                lineas.add("_" + cuadruplos.getCuadruplos().get(i).getArgs1() + ":");
            }
            if (cuadruplos.getCuadruplos().get(i).getOperator().equals("PARAM")) {
                insertParam(cuadruplos.getCuadruplos().get(i));
            }
            if (cuadruplos.getCuadruplos().get(i).getOperator().equals("CALL")) {
                //inicializarRegistros(registros_A);
//                if (contCallFunc > -1) {
//                    String t = getTempDisponible();
//                    lineas.add("        move $" + t + ", $v0");
//                    temporales[Integer.parseInt(t.substring(1))] = returnSave;
//                }
//                saveTempsUsados();
                lineas.add("        jal _" + cuadruplos.getCuadruplos().get(i).getArgs1());
                //resetTemps();
//                if (!(tabla_simbolos.get_id(cuadruplos.getCuadruplos().get(i).getArgs1()).getTipo()).toLowerCase().equals("void")) {
//                    contCallFunc++;
//                }
                returnSave = "RET" + i;

            }
            if (cuadruplosRecorrer.get(i).getArgs1().contains("FUN_")) {
                String nombreFuncion = cuadruplosRecorrer.get(i).getArgs1();
                actualFuncion = nombreFuncion.substring(4, nombreFuncion.length());
                lineas.add("\t sw $fp, -4($sp)");
                lineas.add("\t sw $ra, -8($sp)");
                lineas.add("\t move $fp, $sp");
                offsetLocales = 8;
                ingresarVariablesLocales();
            }
            if(cuadruplosRecorrer.get(i).getArgs1().equals("fin_proc")){
                lineas.add("\t move $sp, $fp");
                lineas.add("\t lw $fp, -4($sp)");
                lineas.add("\t lw $ra, -8($sp)");
                lineas.add("\t jr $ra");
            }
        }
    }

    private void ingresarVariablesLocales() {
        ArrayList<Simbolo> varlocales = tabla_simbolos.getVariablesLocales(actualFuncion);
        int offto = 8;
        for (int i = 0; i < varlocales.size(); i++) {
            if (varlocales.get(i).tipoVariable.equals("integer")) {
                offto += 4;
                locales.add(varlocales.get(i).getNombre());
                locales.add(offto + "");
            } else if (varlocales.get(i).tipoVariable.equals("boolean")) {
                offto += 1;
                locales.add(varlocales.get(i).getNombre());
                locales.add(offto + "");
            } else {
                locales.add(varlocales.get(i).getNombre());
                locales.add(offto + "");
                offto += 4;
            }
        }
        lineas.add("\t sub $sp, $sp, " + offto);
        lineas.add("");
        actualSP = offto;
    }

    private void ingresarParams() {
        ArrayList<Simbolo> parametros = tabla_simbolos.getParametros(actualFuncion);
        for (int i = 0; i < parametros.size(); i++) {
            lineas.add("        sw $s" + i + ", -" + offsetLocales + "($sp)");
            lineas.add("        move $s" + i + ", $a" + i);
            registros_s.get(i).push(registros_S[i]);
            registros_S[i] = parametros.get(i).getNombre();
            offsetLocales += 4;
        }
        lineas.add("        move $fp, $sp");
        if (parametros.size() > 0) {
            lineas.add("        sub $sp, $sp, " + (offsetLocales));
        }
        actualSP = offsetLocales;

    }

    public void generarOperacion(Cuadruplo operacion, String op) {
        String temp = getTempDisponible();
        temporales[Integer.parseInt(temp.substring(1))] = operacion.getResult();
        if (operacion.getArgs1().matches("[0-9]+")) {
            boolean isreg = false;
            String temp1 = getTempDisponible();
            lineas.add("\t li $" + temp1 + ", " + operacion.getArgs1());
            temporales[Integer.parseInt(temp1.substring(1))] = operacion.getArgs1();
            String temp2 = getTempDisponible();
            if (operacion.getArgs2().matches("[0-9]+")) {
                temporales[Integer.parseInt(temp2.substring(1))] = operacion.getArgs2();
                lineas.add("\t li $" + temp2 + ", " + operacion.getArgs2());
            } else if (estaEnVariablesGlobales(operacion.getArgs2()) != -1) {
                temporales[Integer.parseInt(temp2.substring(1))] = operacion.getArgs2();
                lineas.add("\t lw $" + temp2 + ", _" + operacion.getArgs2());
            } else {
                String tem = BuscarTemporal(operacion.getArgs2());
                isreg = true;
                lineas.add("\t " + op + " $" + temp + ", $" + temp1 + ", $" + tem);
                setTempDisponible(tem);
            }
            if (!isreg) {
                lineas.add("\t " + op + " $" + temp + ", $" + temp1 + ", $" + temp2);

            }
            setTempDisponible(temp1);
            setTempDisponible(temp2);
        } else if (estaEnVariablesGlobales(operacion.getArgs1()) != -1) {
            boolean isreg = false;
            int index = estaEnVariablesGlobales(operacion.getArgs1());
            String temp1 = getTempDisponible();
            lineas.add("\t lw $" + temp1 + ", _" + variables.get(index).getNombre());
            temporales[Integer.parseInt(temp1.substring(1))] = operacion.getArgs1();
            String temp2 = getTempDisponible();
            if (operacion.getArgs2().matches("[0-9]+")) {
                temporales[Integer.parseInt(temp2.substring(1))] = operacion.getArgs2();
                lineas.add("\t li $" + temp2 + ", " + operacion.getArgs2());
            } else {
                String tem = BuscarTemporal(operacion.getArgs2());
                isreg = true;
                lineas.add("\t " + op + " $" + temp + ", $" + temp1 + ", $" + tem);
                setTempDisponible(tem);
            }
            if (!isreg) {
                lineas.add("\t " + op + " $" + temp + ", $" + temp1 + ", $" + temp2);
            }
            setTempDisponible(temp1);
            setTempDisponible(temp2);
        } else {
            String temp1 = BuscarTemporal(operacion.getArgs1());
            boolean isreg = false;
            String temp2 = getTempDisponible();
            if (operacion.getArgs2().matches("[0-9]+")) {
                temporales[Integer.parseInt(temp2.substring(1))] = operacion.getArgs2();
                lineas.add("\t li $" + temp2 + ", " + operacion.getArgs2());
            } else if (estaEnVariablesGlobales(operacion.getArgs2()) != -1) {
                int index = estaEnVariablesGlobales(operacion.getArgs2());
                temporales[Integer.parseInt(temp2.substring(1))] = operacion.getArgs2();
                lineas.add("\t lw $" + temp2 + ", _" + operacion.getArgs2());

            } else {
                String tem1 = BuscarTemporal(operacion.getArgs2());
                isreg = true;
                lineas.add("\t " + op + " $" + temp + ", $" + temp1 + ", $" + tem1);
                setTempDisponible(tem1);
            }
            if (!isreg) {
                lineas.add("\t " + op + " $" + temp + ", $" + temp1 + ", $" + temp2);
            }
            setTempDisponible(temp1);
            setTempDisponible(temp2);
        }
    }

    private void generarIf(Cuadruplo cr, String oprel) {
        String etiq = cr.getResult().substring(5);
        if (cr.getArgs1().matches("[0-9]+")) {
            boolean isreg = false;
            String temp1 = getTempDisponible();
            lineas.add("\t li $" + temp1 + ", " + cr.getArgs1());
            temporales[Integer.parseInt(temp1.substring(1))] = cr.getArgs1();
            String temp2 = getTempDisponible();
            if (cr.getArgs2().matches("[0-9]+")) {
                temporales[Integer.parseInt(temp2.substring(1))] = cr.getArgs2();
                lineas.add("\t li $" + temp2 + ", " + cr.getArgs2());
            } else if (estaEnVariablesGlobales(cr.getArgs2()) != -1) {
                int index = estaEnVariablesGlobales(cr.getArgs2());
                temporales[Integer.parseInt(temp2.substring(1))] = cr.getArgs2();
                if (variables.get(index).getTipoVariable().toLowerCase().equals("integer")) {
                    lineas.add("\t lw $" + temp2 + ", _" + cr.getArgs2());
                } else if (variables.get(index).getTipoVariable().toLowerCase().equals("boolean")) {
                    lineas.add("\t lb $" + temp2 + ", _" + cr.getArgs2());
                } else {
                    lineas.add("\t lwcl $" + temp2 + ", _" + cr.getArgs2());
                }
            } else {
                String tem = BuscarTemporal(cr.getArgs2());
                isreg = true;
                lineas.add("\t " + oprel + " $" + temp1 + ", $" + tem + ", _" + etiq);
                setTempDisponible(tem);
            }
            if (!isreg) {
                lineas.add("\t " + oprel + " $" + temp1 + ", $" + temp2 + ", _" + etiq);
            }
            setTempDisponible(temp1);
            setTempDisponible(temp2);
        } else if (estaEnVariablesGlobales(cr.getArgs1()) != -1) {
            boolean isreg = false;
            int index = estaEnVariablesGlobales(cr.getArgs1());
            String temp1 = getTempDisponible();
            lineas.add("\t lw $" + temp1 + ", _" + variables.get(index).getNombre());
            temporales[Integer.parseInt(temp1.substring(1))] = cr.getArgs1();
            String temp2 = getTempDisponible();
            if (cr.getArgs2().matches("[0-9]+")) {
                temporales[Integer.parseInt(temp2.substring(1))] = cr.getArgs2();
                lineas.add("\t li $" + temp2 + ", " + cr.getArgs2());
            } else if (estaEnVariablesGlobales(cr.getArgs2()) != -1) {
                int index1 = estaEnVariablesGlobales(cr.getArgs2());
                temporales[Integer.parseInt(temp2.substring(1))] = cr.getArgs2();
                if (variables.get(index1).getTipoVariable().toLowerCase().equals("integer")) {
                    lineas.add("\t lw $" + temp2 + ", _" + cr.getArgs2());
                } else if (variables.get(index1).getTipoVariable().toLowerCase().equals("boolean")) {
                    lineas.add("\t lb $" + temp2 + ", _" + cr.getArgs2());
                } else {
                    lineas.add("\t lwcl $" + temp2 + ", _" + cr.getArgs2());
                }
            } else {
                String tem = BuscarTemporal(cr.getArgs2());
                isreg = true;
                lineas.add("\t " + oprel + " $" + temp1 + ", $" + tem + ", _" + etiq);
                setTempDisponible(tem);
            }
            if (!isreg) {
                lineas.add("\t " + oprel + " $" + temp1 + ", $" + temp2 + ", _" + etiq);
            }
            setTempDisponible(temp1);
            setTempDisponible(temp2);
//        } else if (estaEnParametros(cr.getArgs1()) != null) {
//            String s = buscarEnS(cr.getArgs1());
//            boolean isreg = false;
//            String temp2 = getTempDisponible();
//            if (cr.getArgs2().equals("true") || cr.getArgs2().equals("false")) {
//                String bool1 = "0";
//                if (cr.getArgs2().toLowerCase().equals("true")) {
//                    bool1 = "1";
//                }
//                temporales[Integer.parseInt(temp2.substring(1))] = bool1;
//                lineas.add("        li $" + temp2 + ", " + bool1);
//            } else if (cr.getArgs2().matches("[0-9]+")) {
//                temporales[Integer.parseInt(temp2.substring(1))] = cr.getArgs2();
//                lineas.add("        li $" + temp2 + ", " + cr.getArgs2());
//            } else if (estaEnVariablesGlobales(cr.getArgs2()) != -1) {
//                int index = estaEnVariablesGlobales(cr.getArgs2());
//                temporales[Integer.parseInt(temp2.substring(1))] = cr.getArgs2();
//                if (variables.get(index).getTipoVariable().toLowerCase().equals("integer")) {
//                    lineas.add("        lw $" + temp2 + ", _" + cr.getArgs2());
//                } else if (variables.get(index).getTipoVariable().toLowerCase().equals("boolean")) {
//                    lineas.add("        lb $" + temp2 + ", _" + cr.getArgs2());
//                }
//            } else {
//                String tem = BuscarTemporal(cr.getArgs2());
//                isreg = true;
//                lineas.add("        " + oprel + " $" + s + ", $" + tem + ", _" + etiq);
//                setTempDisponible(tem);
//            }
//            if (!isreg) {
//                lineas.add("        " + oprel + " $" + s + ", $" + temp2 + ", _" + etiq);
//            }
//            setTempDisponible(temp2);
        } else {
            String temp1 = BuscarTemporal(cr.getArgs1());
            boolean isreg = false;
            String temp2 = getTempDisponible();
            if (cr.getArgs2().matches("[0-9]+")) {
                temporales[Integer.parseInt(temp2.substring(1))] = cr.getArgs2();
                lineas.add("\t li $" + temp2 + ", " + cr.getArgs2());
            } else if (estaEnVariablesGlobales(cr.getArgs2()) != -1) {
                int index = estaEnVariablesGlobales(cr.getArgs2());
                temporales[Integer.parseInt(temp2.substring(1))] = cr.getArgs2();
                if (variables.get(index).getTipoVariable().toLowerCase().equals("integer")) {
                    lineas.add("\t lw $" + temp2 + ", _" + cr.getArgs2());
                } else if (variables.get(index).getTipoVariable().toLowerCase().equals("boolean")) {
                    lineas.add("\t lb $" + temp2 + ", _" + cr.getArgs2());
                } else {
                    lineas.add("\t lwcl $" + temp2 + ", _" + cr.getArgs2());
                }

            } else if (buscarEnS(cr.getArgs2()) != null) {
                String s1 = buscarEnS(cr.getArgs2());
                isreg = true;
                lineas.add("\t " + oprel + " $" + temp1 + ", $" + s1 + ", _" + etiq);

            } else {
                String tem1 = BuscarTemporal(cr.getArgs2());
                isreg = true;
                lineas.add("\t " + oprel + " $" + temp1 + ", $" + tem1 + ", _" + etiq);
                setTempDisponible(tem1);
            }
            if (!isreg) {
                lineas.add("\t " + oprel + " $" + temp1 + ", $" + temp2 + ", _" + etiq);
            }
            setTempDisponible(temp1);
            setTempDisponible(temp2);
        }
    }

    public void generarPrint(Cuadruplo print) { // Metodo para realizar prints en codigo ensamblador
        System.out.println(print.getArgs1() + ":::");
        if (print.getArgs1().contains("\"")) {
            int msg = msjs.indexOf(print.getArgs1()) + 1;
            lineas.add("\t li $v0, 4");
            lineas.add("\t la $a0, _mesg" + msg);
            lineas.add("\t syscall");
            lineas.add("");
        } else if (print.getArgs1().matches("[0-9]+")) {
            lineas.add("\t li $v0, 1");
            lineas.add("\t li $a0, " + print.getArgs1());
            lineas.add("\t syscall");
            lineas.add("");
        } else if (estaEnVariablesLocales(print.getArgs1()) != null) {
            Simbolo vartoken = estaEnVariablesLocales(print.getArgs1());
            if (vartoken.getTipoVariable().toLowerCase().equals("integer")) {
                offsetLocales += 4;
                lineas.add("\t li $v0, 1");
                lineas.add("\t lw $a0, -" + offsetLocales + "($fp)");
                lineas.add("\t syscall");
            } else if (vartoken.getTipoVariable().toLowerCase().equals("boolean")) {
                offsetLocales += 1;
                lineas.add("        lb $a0, -" + offsetLocales + "($fp)");
                lineas.add("        jal imprimirboolean");
            }
            lineas.add("");
        } else if (estaEnVariablesGlobales(print.getArgs1()) != -1) {
            int index = estaEnVariablesGlobales(print.getArgs1());
            if (variables.get(index).getTipoVariable().toLowerCase().equals("integer")) {
                lineas.add("        li $v0, 1");
                lineas.add("        lw $a0, " + "_" + variables.get(index).getNombre());
                lineas.add("        syscall");
            } else if (variables.get(index).getTipoVariable().toLowerCase().equals("boolean")) {
                lineas.add("        lb $a0, _" + variables.get(index).getNombre());
                lineas.add("        jal imprimirboolean");
            }
        }
    }

    private void insertParam(Cuadruplo cr) {
        String a = getADisponible();
        if (cr.getArgs1().matches("[0-9]+|[0-9]+.[0-9]+")) {
            lineas.add("\t li $" + a + ", " + cr.getArgs1());
            registros_A[Integer.parseInt(a.substring(1))] = cr.getArgs1();
        } else if (estaEnVariablesGlobales(cr.getArgs1()) != -1) {
            int index = estaEnVariablesGlobales(cr.getArgs1());
            if (variables.get(index).getTipoVariable().toLowerCase().equals("integer")) {
                lineas.add("\t lw $" + a + ", _" + cr.getArgs1());
            } else if (variables.get(index).getTipoVariable().toLowerCase().equals("boolean")) {
                lineas.add("\t lb $" + a + ", _" + cr.getArgs1());
            } else {
                lineas.add("\t lw $" + a + ", _" + cr.getArgs1());
            }
            registros_A[Integer.parseInt(a.substring(1))] = cr.getArgs1();
        } else {
            String temp = BuscarTemporal(cr.getArgs1());
            lineas.add("\t move $" + a + ", $" + temp);
            setTempDisponible(temp);
        }
    }

    public void generarAsignacion(Cuadruplo asignacion) {
        if (estaEnVariablesGlobales(asignacion.getResult()) != -1) {
            int index = estaEnVariablesGlobales(asignacion.getResult());
            String forma = "sw";
            if (variables.get(index).getTipoVariable().toLowerCase().equals("boolean")) {
                forma = "sb";
            }
            if (BuscarTemporal(asignacion.getArgs1()) != null) {
                String temp = BuscarTemporal(asignacion.getArgs1());
                lineas.add("\t " + forma + " $" + temp + ", _" + variables.get(index).getNombre());
                setTempDisponible(temp);
                lineas.add("");
            } else if (asignacion.getArgs1().matches("RET[0-9]+")) {
                //contCallFunc--;
                lineas.add("\t " + forma + " $v0, _" + variables.get(index).getNombre());
                lineas.add("");
                //aquí iria si está en parametros
            } else if (estaEnVariablesLocales(asignacion.getArgs1()) != null) {
                String temp = getTempDisponible();
                String posicion = locateVarLocales(asignacion.getArgs1());
                lineas.add("\t lw $" + temp + ", " + "-" + posicion + "($fp)");
                lineas.add("\t sw $" + temp + ", " + "_" + asignacion.getResult());
            } else {
                String temp = getTempDisponible();
                lineas.add("\t li $" + temp + ", " + asignacion.getArgs1());
                temporales[Integer.parseInt(temp.substring(1))] = asignacion.getResult();
                lineas.add("\t " + forma + " $" + temp + ", _" + variables.get(index).getNombre());
                setTempDisponible(temp);
                lineas.add("");
            }
        } else if (estaEnVariablesLocales(asignacion.getResult()) != null) {
            Simbolo vartoken = estaEnVariablesLocales(asignacion.getResult());
            String forma = "sw";
            if (vartoken.getTipoVariable().toLowerCase().equals("boolean")) {
                forma = "sb";
            }
            if (BuscarTemporal(asignacion.getArgs1()) != null) {
                String temp = BuscarTemporal(asignacion.getArgs1());
                lineas.add("        " + forma + " $" + temp + ", -" + (offsetLocales + vartoken.getOffset()) + "($fp)");
                setTempDisponible(temp);
                lineas.add("");
//            } else if (asignacion.getArgs1().matches("RET[0-9]+")) {
//                //contCallFunc--;
//                lineas.add("        " + forma + " $v0, -" + (offsetLocales + vartoken.getOffset()) + "($fp)");
//                lineas.add("");
//                }else if (estaEnParametros(asignacion.getArgs1()) != null) {
//                String s = buscarEnS(asignacion.getArgs1());
//                lineas.add("        " + forma + " $" + s + ", -" + (offsetLocales + vartoken.getOffset()) + "($fp)");
//                lineas.add("");
            } else if (asignacion.getArgs1().matches("[0-9]+")) {
                String temp = getTempDisponible();
                lineas.add("        li $" + temp + ", " + asignacion.getArgs1());
                lineas.add("        " + forma + " $" + temp + ", -" + (offsetLocales + vartoken.getOffset()) + "($fp)");
                temporales[Integer.parseInt(temp.substring(1))] = asignacion.getResult();
            } //aqui iria else de está en parámetros
        } else {
            String temp = getTempDisponible();
            if (estaEnVariablesGlobales(asignacion.getArgs1()) != -1) {
                int index = estaEnVariablesGlobales(asignacion.getArgs1());
                if (variables.get(index).getTipoVariable().toLowerCase().equals("integer")) {
                    lineas.add("\t lw $" + temp + ", _" + variables.get(index).getNombre());
                } else if (variables.get(index).getTipoVariable().toLowerCase().equals("boolean")) {
                    lineas.add("\t lb $" + temp + ", _" + variables.get(index).getNombre());
                } else {
                    lineas.add("\t lw $" + temp + ", _" + variables.get(index).getNombre());
                }
                temporales[Integer.parseInt(temp.substring(1))] = asignacion.getResult();
            } else if (asignacion.getArgs1().matches("[0-9]+")) {
                lineas.add("\t li $" + temp + ", " + asignacion.getArgs1());
                temporales[Integer.parseInt(temp.substring(1))] = asignacion.getResult();
            } else if (estaEnVariablesLocales(asignacion.getArgs1()) != null) {
                Simbolo vartoken = estaEnVariablesLocales(asignacion.getArgs1());
                if (vartoken.getTipoVariable().toLowerCase().equals("integer")) {
                    lineas.add("        lw $" + temp + ", -" + (offsetLocales + vartoken.getOffset()) + "($fp)");
                } else if (vartoken.getTipoVariable().toLowerCase().equals("boolean")) {
                    lineas.add("        lb $" + temp + ", -" + (offsetLocales + vartoken.getOffset()) + "($fp)");
                }
                temporales[Integer.parseInt(temp.substring(1))] = asignacion.getResult();
            }
            lineas.add("");
        }
    }

    public String locateVarLocales(String nombre) {
        String posicion = "";
        try {
            for (int i = 0; i < locales.size() - 1; i++) {
                if (locales.get(i).equals(nombre)) {
                    posicion = locales.get(i + 1);
                }
            }
        } catch (Exception e) {
        }
        return posicion;
    }

    private String getTempDisponible() {
        String dis = null;
        int index = -1;
        for (int i = 0; i < temporales.length; i++) {
            if (temporales[i].equals("")) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            dis = "t" + index;
        }
        return dis;
    }

    public int estaEnVariablesGlobales(String var) {
        int index = -1;
        for (int i = 0; i < variables.size(); i++) {
            if (variables.get(i).getNombre().equals(var)) {
                index = i;
                break;
            }
        }
        return index;
    }

    private String BuscarTemporal(String te) {
        String dis = null;
        int index = -1;
        for (int i = 0; i < temporales.length; i++) {
            if (temporales[i].equals(te)) {
                index = i;

                break;
            }
        }
        if (index != -1) {
            dis = "t" + index;
        }
        return dis;
    }

    private void inicializarRegistros(String[] r) {
        for (int i = 0; i < r.length; i++) {
            r[i] = "";
        }
    }

    public void setTempDisponible(String temp) {
        temporales[Integer.parseInt(temp.substring(1))] = "";
    }

    private String buscarEnS(String parametro) {
        String dis = null;
        int index = -1;
        for (int i = 0; i < registros_S.length; i++) {
            if (registros_S[i].equals(parametro)) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            dis = "s" + index;
        }
        return dis;
    }

    private String getADisponible() {
        String dis = null;
        int index = -1;
        for (int i = 0; i < registros_A.length; i++) {
            if (registros_A[i].equals("")) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            dis = "a" + index;
        }
        return dis;
    }

    private Simbolo estaEnVariablesLocales(String var) {
        return tabla_simbolos.getOffsetVarLocales(actualFuncion, var);
    }

    String[] registros_S = new String[8];
    String[] temporales = new String[10];
    String returnSave = "";
    String[] registros_A = new String[4];
    String actualFuncion = "";
    int offsetLocales = 0;
    int actualSP = 0;
}
