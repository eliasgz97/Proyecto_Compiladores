/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comprobacionTipos;

/**
 *
 * @author EliasGZ
 */
public class Simbolo {
    public String nombre;
    public String tipoVariable;
    public Object valor;
    public Boolean tipoConstante;
    public Boolean isFunction;
    public Boolean isProcedure;
    public String ambito;
    public int offset;

    public Simbolo(String nombre, String tipoVariable, Object valor, Boolean tipoConstante, Boolean isFunction, Boolean isProcedure, String ambito, int offset) {
        this.nombre = nombre;
        this.tipoVariable = tipoVariable;
        this.valor = valor;
        this.tipoConstante = tipoConstante;
        this.isFunction = isFunction;
        this.isProcedure = isProcedure;
        this.ambito = ambito;
        this.offset = offset;
    }

    public Simbolo() {

    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }
    
    public String getNombre() {
        return this.nombre;
    }

    public String getTipoVariable() {
        return this.tipoVariable;
    }

    public Object getValor() {
        return this.valor;
    }

    public Boolean getTipoConstante() {
        return this.tipoConstante;
    }

    public Boolean getIsFunction() {
        return this.isFunction;
    }

    public String getAmbito() {
        return ambito;
    }

    public void setAmbito(String ambito) {
        this.ambito = ambito;
    }
}
