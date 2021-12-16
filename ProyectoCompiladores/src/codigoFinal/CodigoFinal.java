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
    ArrayList<String> parametros = new ArrayList();

    public CodigoFinal(TablaSimbolos tabla_simbolos, TablaCuadruplos cuadruplos) {
        this.tabla_simbolos = tabla_simbolos;
        this.cuadruplos = cuadruplos;
        msjs = cuadruplos.getMensajes();
        variables = tabla_simbolos.getVariablesGlobales();
        inicializarRegistros(temporales);
        inicializarRegistros(registros_S);
        inicializarRegistros(registros_A);
        inicializarPilaRegistros();
    }

    private void inicializarPilaRegistros() {
        for (int i = 0; i < 8; i++) {
            registros_s.add(new Stack<String>());
        }
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
                lineas.add("_" + s.nombre + ":\t .word 0");
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
//            if (cuadruplosRecorrer.get(i).getOperator().equals("get")) {
//                //llamamos funcion que genera el read
//            }
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
                if (cuadruplosRecorrer.get(i).getArgs1().equals("main")) {
                    lineas.add("\t move $fp, $sp");
                }
            }
            if (cuadruplos.getCuadruplos().get(i).getOperator().equals("PARAM")) {
                insertParam(cuadruplos.getCuadruplos().get(i));
            }
            if (cuadruplos.getCuadruplos().get(i).getOperator().equals("CALL")) {
                inicializarRegistros(registros_A);
                lineas.add("\t jal _FUN" + "_" + cuadruplos.getCuadruplos().get(i).getArgs1());
                returnSave = "RET" + i;
            }
            if (cuadruplosRecorrer.get(i).getArgs1().contains("FUN_")) {
                String nombreFuncion = cuadruplosRecorrer.get(i).getArgs1();
                actualFuncion = nombreFuncion.substring(4, nombreFuncion.length());
                lineas.add("\t sw $fp, -4($sp)");
                lineas.add("\t sw $ra, -8($sp)");
                ingresarParams();
                lineas.add("\t move $fp, $sp");
                ingresarVariablesLocales();
            }
            if (cuadruplosRecorrer.get(i).getArgs1().equals("fin_proc")) {
                lineas.add("\t move $sp, $fp");
                lineas.add("\t lw $fp, -4($sp)");
                lineas.add("\t lw $ra, -8($sp)");
                finPrograma();
                lineas.add("\t jr $ra");
                locales.clear();
            }
            if (cuadruplosRecorrer.get(i).getArgs1().equals("fin_func")) {
                lineas.add("_FIN_" + actualFuncion + ":");
                lineas.add("\t move $sp, $fp");
                lineas.add("\t lw $fp, -4($sp)");
                lineas.add("\t lw $ra, -8($sp)");
                finPrograma();
                lineas.add("\t jr $ra");
                locales.clear();
            }
            if (cuadruplosRecorrer.get(i).getOperator().equals("RET")) {
                if (cuadruplosRecorrer.get(i).getArgs1().matches("[0-9]+")) {
                    lineas.add("\t li $v0, " + cuadruplosRecorrer.get(i).getArgs1());
                } else {
                    if (estaEnVariablesGlobales(cuadruplosRecorrer.get(i).getArgs1()) != -1) {
                        int index = estaEnVariablesGlobales(cuadruplosRecorrer.get(i).getArgs1());
                        lineas.add("\t lw $v0, _" + variables.get(index).getNombre());
                    } else if (estaEnVariablesLocales(cuadruplosRecorrer.get(i).getArgs1()) != null) {
                        String posicion = locateVarLocales(cuadruplosRecorrer.get(i).getArgs1());
                        lineas.add("\t lw $v0, -" + posicion + "($fp)");
                    } else { //else es parametro

                    }
                }
                lineas.add("\t b _FIN_" + actualFuncion);
            }
            if (cuadruplosRecorrer.get(i).getArgs1().equals("fin_main")) {
                lineas.add("\t li $v0, 10");
                lineas.add("\t syscall");
            }
        }
    }

    String[] contenidotemporales = new String[10];

    private void resetTemps() {
        for (int i = 9; i >= 0; i--) {
            if (!contenidotemporales[i].equals("")) {
                lineas.add("\t lw $t" + i + ", -" + (actualSP - 4) + "($fp)");
                lineas.add("\t add $sp, $sp, " + 4);
                temporales[i] = contenidotemporales[i];
                actualSP -= 4;
            }
            contenidotemporales[i] = "";
        }
    }

    private void saveTempsUsados() {
        for (int i = 0; i < temporales.length; i++) {
            contenidotemporales[i] = "";
            if (!temporales[i].equals("")) {
                contenidotemporales[i] = temporales[i];
                lineas.add("\t sub $sp, $sp, " + 4);
                lineas.add("\t sw $t" + i + ", -" + actualSP + "($fp)");
                actualSP += 4;
            }

        }

    }

    private void finPrograma() {
        ArrayList<Simbolo> parametros = tabla_simbolos.getParametros(actualFuncion);
        for (int i = parametros.size() - 1; i >= 0; i--) {
            lineas.add("\t lw $s" + i + ", -" + offsetLocales + "($sp)");
            registros_S[i] = registros_s.get(i).pop();
            offsetLocales -= 4;
        }
        inicializarRegistros(temporales);
    }

    private void ingresarVariablesLocales() {
        ArrayList<Simbolo> varlocales = tabla_simbolos.getVariablesLocales(actualFuncion);
        int offto = actualSP;
        for (int i = 0; i < varlocales.size(); i++) {
            offto += 4;
            locales.add(varlocales.get(i).getNombre());
            locales.add(offto + "");
        }
        lineas.add("\t sub $sp, $sp, " + offto);
        lineas.add("");
        actualSP = offto;
    }

    private void ingresarParams() {
        offsetLocales = 8;
        ArrayList<Simbolo> parametros = tabla_simbolos.getParametros(actualFuncion);
        for (int i = 0; i < parametros.size(); i++) {
            offsetLocales += 4;
            lineas.add("\t sw $s" + i + ", -" + offsetLocales + "($sp)");
            lineas.add("\t move $s" + i + ", $a" + i);
            registros_s.get(i).push(registros_S[i]);
            registros_S[i] = parametros.get(i).getNombre();
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
            } else if (estaEnVariablesLocales(operacion.getArgs2()) != null) {
                String posicion = locateVarLocales(operacion.getArgs2());
                temporales[Integer.parseInt(temp2.substring(1))] = operacion.getArgs2();
                lineas.add("\t lw $" + temp2 + ", -" + posicion + "($fp)");
                //else if de si esta en parametros
            } else if (estaEnParametros(operacion.getArgs2()) != null) {
                String s = buscarEnS(operacion.getArgs2());
                isreg = true;
                lineas.add("\t " + op + " $" + temp + ", $" + temp1 + ", $" + s);

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
            } else if (estaEnVariablesLocales(operacion.getArgs2()) != null) {
                String posicion = locateVarLocales(operacion.getArgs2());
                temporales[Integer.parseInt(temp2.substring(1))] = operacion.getArgs2();
                lineas.add("\t lw $" + temp2 + ", -" + posicion + "($fp)");

            } else if (estaEnVariablesGlobales(operacion.getArgs2()) != -1) {
                temporales[Integer.parseInt(temp2.substring(1))] = operacion.getArgs2();
                lineas.add("\t lw $" + temp2 + ", _" + operacion.getArgs2());
                //else if de parametros
            } else if (estaEnParametros(operacion.getArgs2()) != null) {
                String s = buscarEnS(operacion.getArgs2());
                isreg = true;
                lineas.add("\t " + op + " $" + temp + ", $" + temp1 + ", $" + s);

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
        } else if (estaEnVariablesLocales(operacion.getArgs1()) != null) {
            String temp1 = getTempDisponible();
            String pormientras = "\t lw $" + temp1 + ", -" + locateVarLocales(operacion.getArgs1()) + "($fp)";
            //aquiii
            boolean isreg = false;
            temporales[Integer.parseInt(temp1.substring(1))] = operacion.getArgs1();
            lineas.add(pormientras);
            String temp2 = getTempDisponible();
            if (operacion.getArgs2().matches("[0-9]+")) {
                temporales[Integer.parseInt(temp2.substring(1))] = operacion.getArgs2();
                lineas.add("\t li $" + temp2 + ", " + operacion.getArgs2());
            } else if (estaEnVariablesGlobales(operacion.getArgs2()) != -1) {
                temporales[Integer.parseInt(temp2.substring(1))] = operacion.getArgs2();
                lineas.add("\t lw $" + temp2 + ", _" + operacion.getArgs2());
            } else if (estaEnVariablesLocales(operacion.getArgs2()) != null) {
                temporales[Integer.parseInt(temp2.substring(1))] = operacion.getArgs2();
                String posicion = locateVarLocales(operacion.getArgs2());
                lineas.add("\t lw $" + temp2 + ", -" + posicion + "($fp)");
            } else if (estaEnParametros(operacion.getArgs2()) != null) {
                String s = buscarEnS(operacion.getArgs2());
                isreg = true;
                lineas.add("        " + op + " $" + temp + ", $" + temp1 + ", $" + s);

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
            //else if está en parametros
        } else if (estaEnParametros(operacion.getArgs1()) != null) {
            String s = buscarEnS(operacion.getArgs1());
            boolean isreg = false;
            String temp2 = getTempDisponible();
            if (operacion.getArgs2().matches("[0-9]+")) {
                temporales[Integer.parseInt(temp2.substring(1))] = operacion.getArgs2();
                lineas.add("\t li $" + temp2 + ", " + operacion.getArgs2());
            } else if (estaEnVariablesLocales(operacion.getArgs2()) != null) {
                String posicion = locateVarLocales(operacion.getArgs2());
                temporales[Integer.parseInt(temp2.substring(1))] = operacion.getArgs2();
                lineas.add("\t lw $" + temp2 + ", -" + posicion + "($fp)");

            } else if (estaEnVariablesGlobales(operacion.getArgs2()) != -1) {
                temporales[Integer.parseInt(temp2.substring(1))] = operacion.getArgs2();
                lineas.add("\t lw $" + temp2 + ", _" + operacion.getArgs2());

            } else if (estaEnParametros(operacion.getArgs2()) != null) {
                String s1 = buscarEnS(operacion.getArgs2());
                isreg = true;
                lineas.add("\t " + op + " $" + temp + ", $" + s + ", $" + s1);

            } else {
                String tem = BuscarTemporal(operacion.getArgs2());
                isreg = true;
                lineas.add("\t " + op + " $" + temp + ", $" + s + ", $" + tem);
                setTempDisponible(tem);
            }
            if (!isreg) {
                lineas.add("\t " + op + " $" + temp + ", $" + s + ", $" + temp2);
            }
            setTempDisponible(temp2);
        } else {
            String temp1 = BuscarTemporal(operacion.getArgs1());
            boolean isreg = false;
            String temp2 = getTempDisponible();
            if (operacion.getArgs2().matches("[0-9]+")) {
                temporales[Integer.parseInt(temp2.substring(1))] = operacion.getArgs2();
                lineas.add("\t li $" + temp2 + ", " + operacion.getArgs2());
            } else if (estaEnVariablesGlobales(operacion.getArgs2()) != -1) {
                temporales[Integer.parseInt(temp2.substring(1))] = operacion.getArgs2();
                lineas.add("\t lw $" + temp2 + ", _" + operacion.getArgs2());
            } else if (estaEnVariablesLocales(operacion.getArgs2()) != null) {
                String posicion = locateVarLocales(operacion.getArgs2());
                lineas.add("\t lw $" + temp2 + ", -" + posicion + "($fp)");
                //else if está en parametros
            } else if (estaEnParametros(operacion.getArgs2()) != null) {
                String s1 = buscarEnS(operacion.getArgs2());
                isreg = true;
                lineas.add("\t " + op + " $" + temp + ", $" + temp1 + ", $" + s1);

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
            } else if (estaEnVariablesLocales(cr.getArgs2()) != null) {
                String posicion = locateVarLocales(cr.getArgs2());
                temporales[Integer.parseInt(temp2.substring(1))] = cr.getArgs2();
                lineas.add("\t lw $" + temp2 + ", -" + posicion + "($fp)");
            } else if (estaEnParametros(cr.getArgs2()) != null) {
                String s = buscarEnS(cr.getArgs2());
                isreg = true;
                lineas.add("\t " + oprel + " $" + temp1 + ", $" + s + ", _" + etiq);
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
                temporales[Integer.parseInt(temp2.substring(1))] = cr.getArgs2();
                lineas.add("\t lw $" + temp2 + ", _" + cr.getArgs2());
            } else if (estaEnVariablesLocales(cr.getArgs2()) != null) {
                String posicion = locateVarLocales(cr.getArgs2());
                temporales[Integer.parseInt(temp2.substring(1))] = cr.getArgs2();
                lineas.add("\t lw $" + temp2 + ", -" + posicion);
            } else if (estaEnParametros(cr.getArgs2()) != null) {
                String s = buscarEnS(cr.getArgs2());
                isreg = true;
                lineas.add("\t " + oprel + " $" + temp1 + ", $" + s + ", _" + etiq);
            } else if (cr.getArgs2().equals("true") || cr.getArgs2().equals("false")) {
                String bool1 = "0";
                if (cr.getArgs2().toLowerCase().equals("true")) {
                    bool1 = "1";
                }
                temporales[Integer.parseInt(temp2.substring(1))] = bool1;
                lineas.add("\t li $" + temp2 + ", " + bool1);
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
        } else if (estaEnParametros(cr.getArgs1()) != null) {
            String s = buscarEnS(cr.getArgs1());
            boolean isreg = false;
            String temp2 = getTempDisponible();
            if (cr.getArgs2().equals("true") || cr.getArgs2().equals("false")) {
                String bool1 = "0";
                if (cr.getArgs2().toLowerCase().equals("true")) {
                    bool1 = "1";
                }
                temporales[Integer.parseInt(temp2.substring(1))] = bool1;
                lineas.add("\t li $" + temp2 + ", " + bool1);
            } else if (cr.getArgs2().matches("[0-9]+")) {
                temporales[Integer.parseInt(temp2.substring(1))] = cr.getArgs2();
                lineas.add("\t li $" + temp2 + ", " + cr.getArgs2());
            } else if (estaEnVariablesGlobales(cr.getArgs2()) != -1) {
                temporales[Integer.parseInt(temp2.substring(1))] = cr.getArgs2();
                lineas.add("\t lw $" + temp2 + ", _" + cr.getArgs2());
            } else if (estaEnVariablesLocales(cr.getArgs2()) != null) {
                String posicion = locateVarLocales(cr.getArgs2());
                temporales[Integer.parseInt(temp2.substring(1))] = cr.getArgs2();
                lineas.add("\t lw $" + temp2 + ", -" + posicion + "($fp)");
            } else if (estaEnParametros(cr.getArgs2()) != null) {
                String s1 = buscarEnS(cr.getArgs2());
                isreg = true;
                lineas.add("\t " + oprel + " $" + s + ", $" + s1 + ", _" + etiq);

            } else {
                String tem = BuscarTemporal(cr.getArgs2());
                isreg = true;
                lineas.add("\t " + oprel + " $" + s + ", $" + tem + ", _" + etiq);
                setTempDisponible(tem);
            }
            if (!isreg) {
                lineas.add("\t " + oprel + " $" + s + ", $" + temp2 + ", _" + etiq);
            }
            setTempDisponible(temp2);
        } else if (estaEnVariablesLocales(cr.getArgs1()) != null) {
            String posicion = locateVarLocales(cr.getArgs1());
            boolean isreg = false;
            String temp1 = getTempDisponible();
            lineas.add("\t lw $" + temp1 + ", -" + posicion + "($fp)");
            temporales[Integer.parseInt(temp1.substring(1))] = cr.getArgs1();
            String temp2 = getTempDisponible();
            if (cr.getArgs2().equals("true") || cr.getArgs2().equals("false")) {
                String bool1 = "0";
                if (cr.getArgs2().toLowerCase().equals("true")) {
                    bool1 = "1";
                }
                temporales[Integer.parseInt(temp2.substring(1))] = bool1;
                lineas.add("\t li $" + temp2 + ", " + bool1);
            } else if (cr.getArgs2().matches("[0-9]+")) {
                temporales[Integer.parseInt(temp2.substring(1))] = cr.getArgs2();
                lineas.add("\t li $" + temp2 + ", " + cr.getArgs2());
            } else if (estaEnVariablesLocales(cr.getArgs2()) != null) {
                String posicion1 = locateVarLocales(cr.getArgs2());
                temporales[Integer.parseInt(temp2.substring(1))] = cr.getArgs2();
                lineas.add("\t lw $" + temp2 + ", -" + posicion1 + "($fp)");
            } else if (estaEnVariablesGlobales(cr.getArgs2()) != -1) {
                temporales[Integer.parseInt(temp2.substring(1))] = cr.getArgs2();
                lineas.add("\t lw $" + temp2 + ", _" + cr.getArgs2());
            } else if (estaEnParametros(cr.getArgs2()) != null) {
                String s = buscarEnS(cr.getArgs2());
                isreg = true;
                lineas.add("\t " + oprel + " $" + temp1 + ", $" + s + ", _" + etiq);
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
            //else if de parametros
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

            } else if (estaEnVariablesLocales(cr.getArgs2()) != null) {
                String posicion = locateVarLocales(oprel);
                temporales[Integer.parseInt(temp2.substring(1))] = cr.getArgs2();
                lineas.add("\t lw $" + temp2 + ", -" + posicion + "($fp)");
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
            String posicion = locateVarLocales(print.getArgs1());
            lineas.add("\t li $v0, 1");
            lineas.add("\t lw $a0, -" + posicion + "($fp)");
            lineas.add("\t syscall");
            lineas.add("");
        } else if (estaEnVariablesGlobales(print.getArgs1()) != -1) {
            int index = estaEnVariablesGlobales(print.getArgs1());
            lineas.add("\t li $v0, 1");
            lineas.add("\t lw $a0, " + "_" + variables.get(index).getNombre());
            lineas.add("\t syscall");
        } else if (BuscarTemporal(print.getArgs1()) != null) {
            String temp = BuscarTemporal(print.getArgs1());
            lineas.add("\t li $v0, 1");
            lineas.add("\t move $a0, $" + temp);
            lineas.add("\t syscall");
            lineas.add("");
            setTempDisponible(temp);
        } else if (estaEnParametros(print.getArgs1()) != null) {
            String s = buscarEnS(print.getArgs1());
            lineas.add("        li $v0, 1");
            lineas.add("        move $a0, $" + s);
            lineas.add("        syscall");
            lineas.add("");
        }
    }

    public void generarRead(Cuadruplo cr) {
        if (estaEnVariablesLocales(cr.getArgs1()) != null) {
            String posicion = locateVarLocales(cr.getArgs1());
            lineas.add("\t li $v0, 5");
            lineas.add("\t syscall");
            lineas.add("\t sw $v0, -" + posicion + "($fp)");
            lineas.add("");
        } else if (estaEnVariablesGlobales(cr.getArgs1()) != -1) {
            int index = estaEnVariablesGlobales(cr.getArgs1());
            lineas.add("\t li $v0, 5");
            lineas.add("\t syscall");
            lineas.add("\t sw $v0, " + "_" + variables.get(index).getNombre());
            lineas.add("");
        } else if (estaEnParametros(cr.getArgs1()) != null) {
            String s = buscarEnS(cr.getArgs1());
            lineas.add("\t li $v0, 5");
            lineas.add("\t syscall");
            lineas.add("\t move $" + s + ", $v0");
            lineas.add("");
        }
    }

    private void insertParam(Cuadruplo cr) {
        String a = getADisponible();
        if (cr.getArgs1().matches("[0-9]+|[0-9]+.[0-9]+")) {
            lineas.add("\t li $" + a + ", " + cr.getArgs1());
            registros_A[Integer.parseInt(a.substring(1))] = cr.getArgs1();
        } else if (estaEnVariablesGlobales(cr.getArgs1()) != -1) {
            System.out.println("entra qui");
            int index = estaEnVariablesGlobales(cr.getArgs1());
            if (variables.get(index).getTipoVariable().toLowerCase().equals("integer")) {
                lineas.add("\t lw $" + a + ", _" + cr.getArgs1());
            } else if (variables.get(index).getTipoVariable().toLowerCase().equals("boolean")) {
                lineas.add("\t lw $" + a + ", _" + cr.getArgs1());
            } else {
                lineas.add("\t lw $" + a + ", _" + cr.getArgs1());
            }
            //setTempDisponible(a);
            registros_A[Integer.parseInt(a.substring(1))] = cr.getArgs1();
            System.out.println(getADisponible() + " disponible");
        } else if (cr.getArgs1().equals("true") || cr.getArgs1().equals("false")) {
            String bool = "0";
            if (cr.getArgs1().equals("true")) {
                bool = "1";
            }
            lineas.add("\t li $" + a + ", " + bool);
            registros_A[Integer.parseInt(a.substring(1))] = bool;
            //else if de parametros
        } else if (estaEnParametros(cr.getArgs1()) != null) {
            System.out.println("entra es un parametro");
            String s = buscarEnS(cr.getArgs1());
            lineas.add("\t move $" + a + ", $" + s);
        } else if (estaEnVariablesLocales(cr.getArgs1()) != null) {
            String posicion = locateVarLocales(cr.getArgs1());
            lineas.add("\t lw $" + a + ", -" + posicion + "($fp)");
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
            if (BuscarTemporal(asignacion.getArgs1()) != null) {
                String temp = BuscarTemporal(asignacion.getArgs1());
                lineas.add("\t " + forma + " $" + temp + ", _" + variables.get(index).getNombre());
                setTempDisponible(temp);
            } else if (asignacion.getArgs1().equals("RET")) {
                //contCallFunc--;
                lineas.add("\t " + forma + " $v0, _" + variables.get(index).getNombre());
                //aquí iria si está en parametros
            } else if (estaEnParametros(asignacion.getArgs1()) != null) {
                String s = buscarEnS(asignacion.getArgs1());
                lineas.add("\t " + forma + " $" + s + ", _" + asignacion.getArgs1());
            } else if (estaEnVariablesLocales(asignacion.getArgs1()) != null) {
                String temp = getTempDisponible();
                String posicion = locateVarLocales(asignacion.getArgs1());
                lineas.add("\t lw $" + temp + ", " + "-" + posicion + "($fp)");
                lineas.add("\t sw $" + temp + ", " + "_" + asignacion.getResult());
                setTempDisponible(temp);
            } else if (asignacion.getArgs1().equals("true") || asignacion.getArgs1().equals("false")) {
                String bool1 = "0";
                String temp = getTempDisponible();
                if (asignacion.getArgs1().toLowerCase().equals("true")) {
                    bool1 = "1";
                }
                lineas.add("\t li $" + temp + ", " + bool1);
                lineas.add("\t sw $" + temp + ", _" + variables.get(index).getNombre());
            } else {
                String temp = getTempDisponible();
                lineas.add("\t li $" + temp + ", " + asignacion.getArgs1());
                temporales[Integer.parseInt(temp.substring(1))] = asignacion.getResult();
                lineas.add("\t " + forma + " $" + temp + ", _" + variables.get(index).getNombre());
                setTempDisponible(temp);
                lineas.add("");
            }
        } else if (estaEnVariablesLocales(asignacion.getResult()) != null) {
            String forma = "sw";
            if (BuscarTemporal(asignacion.getArgs1()) != null) {
                String temp = BuscarTemporal(asignacion.getArgs1());
                String posicion = locateVarLocales(asignacion.getResult());
                lineas.add("\t " + forma + " $" + temp + ", -" + posicion + "($fp)");
                setTempDisponible(temp);
                lineas.add("");
            } else if (asignacion.getArgs1().equals("RET")) {
                //contCallFunc--;
                String posicion = locateVarLocales(asignacion.getResult());
                lineas.add("\t " + forma + " $v0, -" + posicion + "($fp)");
                lineas.add("");
            } else if (estaEnParametros(asignacion.getArgs1()) != null) {
                String s = buscarEnS(asignacion.getArgs1());
                String posicion = locateVarLocales(asignacion.getResult());
                lineas.add("\t " + forma + " $" + s + ", " + posicion + "($fp)");
                lineas.add("");
            } else if (asignacion.getArgs1().matches("[0-9]+")) {
                String temp = getTempDisponible();
                lineas.add("\t li $" + temp + ", " + asignacion.getArgs1());
                String posicion = locateVarLocales(asignacion.getResult());
                lineas.add("\t " + forma + " $" + temp + ", -" + posicion + "($fp)");//volver aqui
                //temporales[Integer.parseInt(temp.substring(1))] = asignacion.getResult();
                setTempDisponible(temp);
            } else if (asignacion.getArgs1().equals("true") || asignacion.getArgs1().equals("false")) {
                String bool1 = "0";
                String temp = getTempDisponible();
                String posicion = locateVarLocales(asignacion.getResult());
                if (asignacion.getArgs1().toLowerCase().equals("true")) {
                    bool1 = "1";
                }
                lineas.add("\t li $" + temp + ", " + bool1);
                lineas.add("\t sw $" + temp + ", -" + posicion + "($fp)");
                //aqui iria else if de parametros
            } else if (estaEnParametros(asignacion.getArgs1()) != null) {
                String s = buscarEnS(asignacion.getArgs1());
                String posicion = locateVarLocales(asignacion.getResult());
                lineas.add("        " + forma + " $" + s + ", -" + posicion + "($fp)");
                lineas.add("");
            } else {
                String temp = getTempDisponible();
                if (estaEnVariablesGlobales(asignacion.getArgs1()) != -1) {
                    int index = estaEnVariablesGlobales(asignacion.getArgs1());
                    lineas.add("\t lw $" + temp + ", _" + variables.get(index).getNombre());
                    temporales[Integer.parseInt(temp.substring(1))] = asignacion.getResult();
                } else if (asignacion.getArgs1().matches("[0-9]+")) {
                    lineas.add("\t li $" + temp + ", " + asignacion.getArgs1());
                    temporales[Integer.parseInt(temp.substring(1))] = asignacion.getResult();
                } else if (estaEnVariablesLocales(asignacion.getArgs1()) != null) {
                    String posicion = locateVarLocales(asignacion.getArgs1());
                    lineas.add("\t lw $" + temp + ", -" + posicion + "($fp)");
                    temporales[Integer.parseInt(temp.substring(1))] = asignacion.getResult();
                }
                lineas.add("");
            }
        } else if (estaEnParametros(asignacion.getResult()) != null) {
            String s = buscarEnS(asignacion.getResult());
            if (BuscarTemporal(asignacion.getArgs1()) != null) {
                String temp = BuscarTemporal(asignacion.getArgs1());
                lineas.add("\t move $" + s + ", $" + temp);
                setTempDisponible(temp);
                lineas.add("");
            } else if (asignacion.getArgs1().matches("RET[0-9]+")) {
                lineas.add("\t move $" + s + ", $v0");
                lineas.add("");
            } else if (estaEnParametros(asignacion.getArgs1()) != null) {
                String s1 = buscarEnS(asignacion.getArgs1());
                lineas.add("\t move $" + s1 + ", $" + s);
                lineas.add("");
            } else if (estaEnVariablesGlobales(asignacion.getArgs1()) != -1) {
                lineas.add("\t move $" + s + ", _" + asignacion.getArgs1());
                lineas.add("");
            }

        }
    }

    public String locateVarLocales(String nombre) {
        String posicion = "";
        try {
            for (int i = 0; i < locales.size() - 1; i++) {
                if (locales.get(i).equals(nombre)) {
                    posicion = locales.get(i + 1);
                    break;
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

    private Simbolo estaEnParametros(String var) {
        return tabla_simbolos.getSimbolParametros(actualFuncion, var);
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
