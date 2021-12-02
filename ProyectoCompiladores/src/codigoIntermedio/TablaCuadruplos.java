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

}
