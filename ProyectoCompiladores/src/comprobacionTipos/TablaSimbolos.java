package comprobacionTipos;

import com.sun.org.apache.xerces.internal.util.SymbolTable;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import comprobacionTipos.Simbolo;

public class TablaSimbolos {

    static ArrayList<Simbolo> tablaSimbolos = new ArrayList<Simbolo>();
    static ArrayList<String> erroresSemanticos = new ArrayList<>();
    static String errorAmbito = "";
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

    static public Simbolo insertar2(String nombre, String tipoVariable, Object valor, Boolean tipoConstante, Boolean tipoFuncion, Boolean tipoProcedure, String ambito, int offset) {
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
            simbolo = new Simbolo(nombre, tipoVariable, valor.toString(), tipoConstante, tipoFuncion, tipoProcedure, ambito, offset);
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
    
    static public String get_ambit_hijos_func(String namefunc) {
        String ambitohijos = "";
        for (int j = 0; j < tablaSimbolos.size(); j++) {
            if (tablaSimbolos.get(j).getNombre().equals(namefunc)) {
                ambitohijos = tablaSimbolos.get(j).getAmbito();
                break;
            }
        }
        return ambitohijos;
    }
    
    static public Simbolo get_offsert_var_locales(String namefunc, String var) {
        Simbolo vartoken = null;
        String ambitohijos = get_ambit_hijos_func(namefunc);
        for (int j = 0; j < tablaSimbolos.size(); j++) {
            if (tablaSimbolos.get(j).getAmbito().equals(ambitohijos) && tablaSimbolos.get(j).getNombre().equals(var) && tablaSimbolos.get(j).getTipoConstante()) {
                vartoken = tablaSimbolos.get(j);
                break;
            }
        }
        return vartoken;
    }
    
    static public ArrayList<Simbolo> get_variables_locales(String namefunc) {

        ArrayList<Simbolo> listavars = new ArrayList();
        String ambitohijos = get_ambit_hijos_func(namefunc);
        ambitohijos += "." + namefunc;
        for (int j = 0; j < tablaSimbolos.size(); j++) {
            if (tablaSimbolos.get(j).getAmbito().equals(ambitohijos) && !tablaSimbolos.get(j).getTipoConstante()) {
                listavars.add(tablaSimbolos.get(j));
                System.out.println(tablaSimbolos.get(j).getNombre() + ":::");
            }
        }
        return listavars;

    }
    
    static public Simbolo crear(String nombre, String tipoVariable, Boolean constante, Boolean function, Boolean procedure, int offset) {
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
            simbolo = new Simbolo(nombre, tipoVariable, null, constante, function, procedure, "", offset);
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
                    //System.out.println("tuanitoooo");
                    break;
                }
            } else {
                tipo = "error, la variable no ha sido encontrada";
            }
        }
        if (tipo.equals("error, la variable no ha sido encontrada")) {
            String tipoError = "error, el identificador " + nombre + " no ha sido encontrado";
            erroresSemanticos.add(tipoError);
        }
        return tipo;
    }

    static public boolean esFuncion(String nombre, String ambito) {
        boolean isFuncion = false;
        for (Simbolo s : tablaSimbolos) {
            if (s.nombre.equals(nombre)) {
                if (ambito.contains(s.ambito)) {
                    isFuncion = s.isFunction;
                    break;
                }
            }
        }
        return isFuncion;
    }

    static public boolean esProcedure(String nombre, String ambito) {
        boolean isProcedure = false;
        String ambitoEncontrado = "";
        for (Simbolo s : tablaSimbolos) {
            if (s.nombre.equals(nombre)) {
                if (s.isProcedure) {
                    if (ambito.contains(s.ambito)) {
                        isProcedure = s.isProcedure;
                        ambitoEncontrado = "ambito encontrado";
                        break;
                    } else {
                        ambitoEncontrado = "ambito no encontrado";
                    }
                }
            }
        }
        if (ambitoEncontrado.equals("ambito no encontrado") || ambitoEncontrado.equals("")) {
            erroresSemanticos.add("error, el procedimiento " + nombre + " no ha sido encontrado");
        }
        return isProcedure;
    }

    static public String buscarDominio(String tipo) {
        //System.out.println(tipo+"tipo sin espacios");
        for (int i = 0; i < tipo.length(); i++) {
            if (tipo.charAt(i) == '-') {
                tipo = tipo.substring(0, i - 1);
                //System.out.println(tipo + "tipo Dominio");
                break;
            }
        }
        return tipo.trim();
    }

    static public String buscarRetorno(String tipo) {
        //System.out.println(tipo+"tipo sin espacios");
        for (int i = 0; i < tipo.length(); i++) {
            if (tipo.charAt(i) == '-') {
                tipo = tipo.substring(i + 2, tipo.length());
                //System.out.println(tipo + "tipo Dominio");
                break;
            }
        }
        return tipo.trim();
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
    
    static public ArrayList<Simbolo> getVariablesGlobales () {
        ArrayList<Simbolo> listavars = new ArrayList();
        for (int i = 0; i < tablaSimbolos.size(); i++) {
            if (!tablaSimbolos.get(i).getAmbito().contains(".") && (tablaSimbolos.get(i).getTipoVariable().equals("integer") 
                    || tablaSimbolos.get(i).getTipoVariable().equals("boolean") || tablaSimbolos.get(i).getTipoVariable().equals("float"))) {
                listavars.add(tablaSimbolos.get(i));
            }
        }
        return listavars;
    }
    
}
