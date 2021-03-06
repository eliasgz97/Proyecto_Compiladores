/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigoIntermedio;

import java.util.ArrayList;

/**
 *
 * @author EliasGZ
 */
public class TablaCuadruplos {

    ArrayList<Cuadruplo> Cuadruplos;
    private String temporal;
    private String etiqueta;

    public TablaCuadruplos() {
        Cuadruplos = new ArrayList<Cuadruplo>();
        temporal = "t0";
        etiqueta = "ETIQ0";
    }

    public ArrayList<Cuadruplo> getCuadruplos() {
        return Cuadruplos;
    }

    public void setCuadruplos(ArrayList<Cuadruplo> cuadruplos) {
        Cuadruplos = cuadruplos;
    }

    public void generarCuadruplo(String operador, String argumento1, String argumento2, String respuesta) {
        Cuadruplos.add(new Cuadruplo(operador, argumento1, argumento2, respuesta));
    }

    private int getNextTemporal() {
        return Integer.parseInt(temporal.substring(1)) + 1;
    }

    private int getLastTemporal() {
        return Integer.parseInt(temporal.substring(1)) - 1;
    }

    public String temporalNuevo() {
        temporal = temporal.substring(0, 1) + getNextTemporal();
        return temporal;
    }

    public String getLastTemp() {
        return temporal;
    }

    public String temporalAnterior() {
        return temporal.substring(0, 1) + (getLastTemporal());
    }
    
    private int getNextEtiqueta() {
        return Integer.parseInt(etiqueta.substring(4)) + 1;
    }

    public String etiquetaNueva() {
        etiqueta = etiqueta.substring(0, 4) + getNextEtiqueta();
        return etiqueta;
    }
    
    public ArrayList<String> getMensajes () {
        ArrayList<String> mensajes = new ArrayList();
        for (int i = 0; i < Cuadruplos.size(); i++) {
            if (Cuadruplos.get(i).getOperator().equals("print") && Cuadruplos.get(i).getArgs1().contains("\"")) {
                mensajes.add(Cuadruplos.get(i).getArgs1());
            }
        }
        return mensajes;
    }

}
