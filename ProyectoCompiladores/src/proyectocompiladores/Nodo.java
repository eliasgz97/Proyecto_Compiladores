/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectocompiladores;

import java.util.ArrayList;

/**
 *
 * @author eliasgz
 */
public class Nodo {

    private String ambito = "";

    public String getValCodigofinal() {
        return valCodigofinal;
    }

    public void setValCodigofinal(String valCodigofinal) {
        this.valCodigofinal = valCodigofinal;
    }
    private String valCodigofinal = "";
    //para 
    private String ambitoHijos = "";
    private String nombre;
    private int offset;
    private boolean esAsingacion = false;
    //en caso de que tengamos un Structure como tipo.
    private String nombreTipo = "";

    private ArrayList<Nodo> hijos;
    private String valor;
    private int numNodo;
    private boolean visitado = false;
    private Nodo padre = null;
    private int columna;
    private int fila;
    public String siguiente;
    public String verdadera;
    public String falsa;
    public String comienzo;

    public void setEsAsingacion(boolean esAsingacion) {
        this.esAsingacion = esAsingacion;
    }

    public boolean isEsAsingacion() {
        return esAsingacion;
    }

    ////////////////////////////////////////////////////////
    //este es para contar las sentencias a la izq
    public int contador_sentencias = 0;
    //este es para contar las declaraciones a la izq
    public int contador_decs = 0;

    public int getContador_sentencias() {
        return contador_sentencias;
    }

    public void setContador_sentencias(int contador_sentencias) {
        this.contador_sentencias = contador_sentencias;
    }

    public int getContador_decs() {
        return contador_decs;
    }

    public void setContador_decs(int contador_decs) {
        this.contador_decs = contador_decs;
    }
    /////////////////////////////////////////////////////

    public Nodo(String nombre, int columna, int fila) {
        this.nombre = nombre;
        this.columna = columna;
        this.fila = fila;
        siguiente = "";
        verdadera = "";
        falsa = "";
        setNombre(nombre);
        hijos = new ArrayList<>();
        setValor("");
        setNumNodo(0);
    }
    public Nodo(String nombre) {
        this.nombre = nombre;
        siguiente = "";
        verdadera = "";
        falsa = "";
        setNombre(nombre);
        hijos = new ArrayList<>();
        hijos.size();
        setValor("");
        setNumNodo(0);
    }
    public String getAmbito() {
        return ambito;
    }

    public void setAmbito(String ambito) {
        this.ambito = ambito;
    }

    public String getAmbitoHijos() {
        return ambitoHijos;
    }

    public void setAmbitoHijos(String ambitoHijos) {
        this.ambitoHijos = ambitoHijos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public String getNombreTipo() {
        return nombreTipo;
    }
    public void addHijo(Nodo hijo) {
        hijos.add(hijo);
    }
    public void setNombreTipo(String nombreTipo) {
        this.nombreTipo = nombreTipo;
    }

    public ArrayList<Nodo> getHijos() {
        return hijos;
    }

    public void setHijos(ArrayList<Nodo> hijos) {
        this.hijos = hijos;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public int getNumNodo() {
        return numNodo;
    }

    public void setNumNodo(int numNodo) {
        this.numNodo = numNodo;
    }

    public boolean isVisitado() {
        return visitado;
    }

    public void setVisitado(boolean visitado) {
        this.visitado = visitado;
    }

    public Nodo getPadre() {
        return padre;
    }

    public void setPadre(Nodo padre) {
        this.padre = padre;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public String getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(String siguiente) {
        this.siguiente = siguiente;
    }

    public String getVerdadera() {
        return verdadera;
    }

    public void setVerdadera(String verdadera) {
        this.verdadera = verdadera;
    }

    public String getFalsa() {
        return falsa;
    }

    public void setFalsa(String falsa) {
        this.falsa = falsa;
    }

    public String getComienzo() {
        return comienzo;
    }

    public void setComienzo(String comienzo) {
        this.comienzo = comienzo;
    }

    public Nodo() {

    }
}