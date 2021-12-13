package codigoFinal;

import java.util.ArrayList;
import comprobacionTipos.TablaSimbolos;
import codigoIntermedio.TablaCuadruplos;
import codigoIntermedio.Cuadruplo;
import comprobacionTipos.Simbolo;

public class CodigoFinal {

    TablaSimbolos tabla_simbolos;
    TablaCuadruplos cuadruplos;
    ArrayList<String> lineas = new ArrayList();
    ArrayList<Simbolo> variables;
    ArrayList<String> msjs;

    public CodigoFinal(TablaSimbolos tabla_simbolos, TablaCuadruplos cuadruplos) {
        this.tabla_simbolos = tabla_simbolos;
        this.cuadruplos = cuadruplos;
        msjs = cuadruplos.getMensajes();
        variables = tabla_simbolos.getVariablesGlobales();
//        inicializarRegistros(temporales);
//        inicializarRegistros(registros_S);
//        inicializarRegistros(registros_A);
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
        lineas.add("\t .globl main");
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
        lineas.add("main:");
        for (int i = 0; i < cuadruplosRecorrer.size(); i++) {

            if (cuadruplosRecorrer.get(i).getOperator().equals("print")) {
                generarPrint(cuadruplosRecorrer.get(i));
            }
            if (cuadruplosRecorrer.get(i).getOperator().equals("=")) {
                generarAsignacion(cuadruplosRecorrer.get(i));
            }
            /*if () {
                 
             }*/
            if (cuadruplosRecorrer.get(i).getArgs1().contains("FUN_")) {
                lineas.add(cuadruplosRecorrer.get(i).getArgs1() + ":");
            }
        }
    }

    public void generarPrint(Cuadruplo print) { // Metodo para realizar prints en codigo ensamblador
        if (print.getArgs1().contains("\"")) {
            int msg = msjs.indexOf(print.getArgs1()) + 1;
            lineas.add("\t li $v0, 4");
            lineas.add("\t li $a0, _mesg" + msg);
            lineas.add("\t syscall");
            lineas.add("");
        } else if (print.getArgs1().matches("[0-9]+")) {
            lineas.add("\t li $v0, 1");
            lineas.add("\t li $a0, " + print.getArgs1());
            lineas.add("\t syscall");
            lineas.add("");
        } else {
            lineas.add("\t li $v0, 1");
            lineas.add("\t lw $a0, " + "_" + print.getArgs1());
            lineas.add("\t syscall");
            lineas.add("");
        }
    }

    public void generarAsignacion(Cuadruplo asignacion) {
        if (asignacion.getArgs1().charAt(0)!='t' && asignacion.getArgs2().equals("")) {
        lineas.add("\t li $t0," + asignacion.getArgs1());
        lineas.add("\t sw $t0,_" + asignacion.getResult());
        }
        
    }
}
