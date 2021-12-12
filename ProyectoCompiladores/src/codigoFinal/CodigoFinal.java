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
        lineas.add("            .data");
        for (Simbolo s : variables) {
            if (s.tipoVariable.toLowerCase().equals("integer")) {
                lineas.add("_" + s.nombre + ":        .word 0");
            }
            if (s.tipoVariable.toLowerCase().equals("boolean")) {
                lineas.add("_" + s.nombre + ":        .byte 0");
            }
            if (s.tipoVariable.toLowerCase().equals("float")) {
                lineas.add("_" + s.nombre + ":        .float 0.0");
            }
        }
        int contador = 1;
        for (String mesgs : msjs) {
            lineas.add("_mesg" + contador + ":     .asciiz " + mesgs);
            contador++;

        }
        lineas.add("");
        lineas.add("            .text");
        lineas.add("            .globl main");
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
             /*if () {
                 
             }*/
             if (cuadruplosRecorrer.get(i).getArgs1().contains("FUN_")) {
                 lineas.add(cuadruplosRecorrer.get(i).getArgs1()+ ":");
             }
        }
    }

    public void generarPrint(Cuadruplo print) {
        if (print.getArgs1().contains("\"")) {
            int msg = msjs.indexOf(print.getArgs1()) + 1;
            lineas.add("            li $v0, 4");
            lineas.add("            li $a0, _mesg" + msg);
            lineas.add("            syscall");
            lineas.add("");
        } else if (print.getArgs1().matches("[0-9]+")) {
            lineas.add("        li $v0, 1");
            lineas.add("        li $a0, " + print.getArgs1());
            lineas.add("        syscall");
            lineas.add("");
        } else {
            lineas.add("        li $v0, 1");
            lineas.add("        lw $a0, " + "_" +print.getArgs1());
            lineas.add("        syscall");
            lineas.add("");
        }
    }
}
