package comprobacionTipos;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import comprobacionTipos.Simbolo;

public class TablaSimbolos {

    static ArrayList<Simbolo> tablaSimbolos = new ArrayList<Simbolo>();
    static ArrayList<String> erroresSemanticos = new ArrayList<>();
    // static Map<String, Simbolo> tablaSimbolos = new HashMap();
    // static Stack<String> lista;
    // static ArrayList<Nodo> repeticiones;

    public static ArrayList<String> getErroresSemanticos() {
        return erroresSemanticos;
    }

    public static void setErroresSemanticos(ArrayList<String> erroresSemanticos) {
        TablaSimbolos.erroresSemanticos = erroresSemanticos;
    }

    static public ArrayList<Simbolo> getTablaSimbolos() {
        return tablaSimbolos;
    }

    static public void setTablaSimbolos(ArrayList<Simbolo> tablaSimbolos) {
        TablaSimbolos.tablaSimbolos = tablaSimbolos;
    }

    public static Logger log = Logger.getLogger(TablaSimbolos.class.getName());

    // public TablaSimbolos() {
    // tablaSimbolos = new HashMap<String, Simbolo>();
    // lista = new Stack<String>();
    // }
    static public String verificarTipoVariable(String nombre) {
        Simbolo s = buscar(nombre);
        return s.tipoVariable;
    }

    static public Boolean verificarTipoConstante(String nombre) {
        Simbolo s = buscar(nombre);
        return s.tipoConstante;
    }

    static public Boolean verificarIsFunction(String nombre) {
        Simbolo s = buscar(nombre);
        return s.isFunction;
    }

    static public void setAmbito(String nombre, String ambito) {
        Simbolo s = buscar(nombre);
        s.ambito = ambito;
        int pos = tablaSimbolos.indexOf(s);
        tablaSimbolos.set(pos, s);
    }

    static public void setTipoVariable(String nombre, String tipoVariable) {
        Simbolo s = buscar(nombre);
        s.tipoVariable = tipoVariable;
        int pos = tablaSimbolos.indexOf(s);
        tablaSimbolos.set(pos, s);
    }

    static public Simbolo insertar2(String nombre, String tipoVariable, Object valor, Boolean tipoConstante, Boolean tipoFuncion, String ambito) {
        Simbolo simbolo = null;
        boolean existe = false;
        for (Simbolo s : tablaSimbolos) {
            if (s.nombre.equals(nombre) && ambito.equals(s.ambito)) {
                existe = true;
                break;
            }
        }
        // La variable no existe
        if (!existe) {
            simbolo = new Simbolo(nombre, tipoVariable, valor.toString(), tipoConstante, tipoFuncion, ambito);
            System.out.println("Agregando a tabla de simbolos con nombre: " + nombre);
            tablaSimbolos.add(simbolo);
            // System.out.println("Variable creada exitosamente!!!");
            //imprimir();
            //System.out.println("Termino de imprimir");
            return simbolo;
        } else {
            System.out.println("No se agrego a la tabla de simbolos. " + nombre);
            erroresSemanticos.add("Id " + nombre + " duplicado, verifique el nombre");
            return null;
        }
    }

    static public String eliminar(String nombre) {
        //System.out.println("Eliminando variable: " + nombre);
        try {
            Simbolo s = buscar(nombre);
            tablaSimbolos.remove(s);
            return nombre;
        } catch (Exception e) {
            System.out.println("Error al eliminar una variable de la tabla de simbolos");
            return nombre;
        }
    }

    static public Simbolo crear(String nombre, String tipoVariable, Boolean constante, Boolean function) {
        Simbolo simbolo = null;
        // Validar si existe
        boolean existe = false;
        for (Simbolo s : tablaSimbolos) {
            if (s.nombre.equals(nombre)) {
                existe = true;
                break;
            }
        }
        // La variable no existe
        if (!existe) {
            simbolo = new Simbolo(nombre, tipoVariable, null, constante, function, "");
            System.out.println("Agregando a tabla de simbolos con nombre: " + nombre);
            tablaSimbolos.add(simbolo);
            // System.out.println("Variable creada exitosamente!!!");
            //imprimir();
            //System.out.println("Termino de imprimir");
            return simbolo;
        } else {
            System.out.println("No se agrego a la tabla de simbolos.");
            return null;
        }
    }

    static public Simbolo insertar(String nombre, Object valor, Boolean constante) {
        //System.out.println("\nIngreso a insertar valor a variable.");
        Simbolo simbolo = buscar(nombre);
        int pos = tablaSimbolos.indexOf(simbolo);
        // La variable existe
        if (simbolo != null) {
            // Actualizar el valor
            simbolo.valor = valor;
            simbolo.tipoConstante = constante;
            //tablaSimbolos.remove(nombre);//Elimino para actualizar
            //tablaSimbolos.put(nombre, simbolo);
            tablaSimbolos.set(pos, simbolo);
            //System.out.println("Variable actualizada");
            //imprimir();
            //System.out.println("Saliendo de insertar de TablaSimbolos\n");
            return simbolo;
        } else {
            return null;
        }
    }

    static public Simbolo buscar(String nombre) {
        // Simbolo n = (Simbolo)tablaSimbolos.get(nombre);
        // Busca si existe la variable en tabla de simbolos
        //System.out.println("buscando: "+nombre);
        Simbolo s = null;
        for (Simbolo simbolo : tablaSimbolos) {
            if (simbolo.nombre.equals(nombre)) {
                s = simbolo;
                break;
            }
        }
        return s;
    }

    static public String buscarTipo(String nombre, String ambito) {
        // Simbolo n = (Simbolo)tablaSimbolos.get(nombre);
        // Busca si existe la variable en tabla de simbolos
        //System.out.println("buscando: "+nombre);
        //Simbolo symbol = null;
        String tipo = "";
        for (Simbolo s : tablaSimbolos) {
            if (s.nombre.equals(nombre)) {
                if (ambito.contains(s.ambito)) {
                    tipo = s.tipoVariable;
                    System.out.println("tuanitoooo");
                    break;
                }
            } else {
                System.out.println("entra else");
                tipo = "error, la variable no ha sido encontrada";
            }
        }
        if (tipo.equals("error, la variable no ha sido encontrada")) {
            String tipoError = "error, la variable " + nombre + " no ha sido encontrada";
            erroresSemanticos.add(tipoError);
        }
        return tipo;
    }

    static public String buscarTipo(String nombre) {
        String tipo = "";
        for (Simbolo s : tablaSimbolos) {
            if (s.nombre.equals(nombre)) {
                tipo = s.tipoVariable;
            } else {
                tipo = "variable de asignación no encontrada";
            }
        }
        return tipo;
    }

    static public void imprimir() {
        System.out.println("\nTABLA DE SIMBOLOS:");
        System.out.println("============================================================:");
        for (Simbolo s : tablaSimbolos) {
            System.out.println(String.format(
                    "      " + "| Nombre: %s | valor: %s | tipoVariable: %s | tipoConstante: %s | Function: %s | Ambito: %s |",
                    s.nombre, s.valor, s.tipoVariable, s.tipoConstante, s.isFunction, s.ambito));
        }
        System.out.println("Saliendo de imprimir en TablaSimbolos");
        System.out.println("============================================================:\n");
    }

}
