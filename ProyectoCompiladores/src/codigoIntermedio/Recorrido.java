package codigoIntermedio;

import proyectocompiladores.Nodo;

/**
 *
 * @author EliasGZ
 */
public class Recorrido {

    TablaCuadruplos cuadruplos;
    String verdadera = "";
    String falsa = "";
    int contador;
    int semaforo = 0;
    Nodo etiqfunct = new Nodo("funciones");

    public Recorrido() {
        cuadruplos = new TablaCuadruplos();
    }

    public TablaCuadruplos getCuadruplos() {
        return cuadruplos;
    }

    public void recorrerfunct() {
        cuadruplos.generarCuadruplo("FIN_MAIN", "fin_main", "", "");
        for (Nodo hoja : etiqfunct.getHijos()) {
            hoja.setVisitado2(false);
            if (hoja.getNombre().equals("Procedure")) {
                cuadruplos.generarCuadruplo("ETIQ", "FUN_" + hoja.getValor(), "", "");
                recorrer(hoja, 0);
                cuadruplos.generarCuadruplo("FINP", "fin_proc", "void", "");
            }
            if (hoja.getNombre().equals("declaracion_funcion")) {
                cuadruplos.generarCuadruplo("ETIQ", "FUN_" + hoja.getHijos().get(0).getValor(), "", "");
                recorrer(hoja, 0);
                cuadruplos.generarCuadruplo("FINF", "fin_func", "", "");
            }
        }
    }

    public void recorrer(Nodo padre, int semaforo) {
        for (Nodo hoja : padre.getHijos()) {
            if ((!hoja.isVisitado2())) {
                hoja.setVisitado2(true); //visitamos el nodo para que no lo recorra otra vez
                if (hoja.getNombre().equals("Asignacion")) { 
                    if (semaforo != 1) { //este semaforo ayuda a generar el codigo intermedio de procedure y funciones después del main 
                        if (!padre.getNombre().equals("put") && !padre.getNombre().equals("get") && !(padre.getNombre().equals("nueva_linea") && padre.getHijos().get(0).getNombre().equals("return"))) {
                            switch (hoja.getHijos().get(0).getNombre()) {
                                case "num_int":
                                    if (padre.getHijos().get(0).getNombre().equals(",")) {
                                        recorrerRepeticion(padre.getHijos().get(0), hoja.getHijos().get(0).getValor());
                                    } else {
                                        cuadruplos.generarCuadruplo("=", hoja.getHijos().get(0).getValor(),
                                                "", padre.getHijos().get(0).getValor());
                                    }
                                    break;
                                case "numfloat":
                                    if (padre.getHijos().get(0).getNombre().equals(",")) {
                                        recorrerRepeticion(padre.getHijos().get(0), hoja.getHijos().get(0).getValor());
                                    } else {
                                        cuadruplos.generarCuadruplo("=", hoja.getHijos().get(0).getValor(),
                                                "", padre.getHijos().get(0).getValor());
                                    }
                                    break;
                                case "boolean":
                                    if (padre.getHijos().get(0).getNombre().equals(",")) {
                                        recorrerRepeticion(padre.getHijos().get(0), hoja.getHijos().get(0).getValor());
                                    } else {
                                        cuadruplos.generarCuadruplo("=", hoja.getHijos().get(0).getValor(),
                                                "", padre.getHijos().get(0).getValor());
                                    }
                                    break;
                                case "id":
                                    if (padre.getHijos().get(0).getNombre().equals(",")) { // si la declaración de variables tiene varios ids
                                        recorrerRepeticion(padre.getHijos().get(0), hoja.getHijos().get(0).getValor()); //llama a método que recorre repetición de id
                                    } else {
                                        cuadruplos.generarCuadruplo("=", hoja.getHijos().get(0).getValor(),
                                                "", padre.getHijos().get(0).getValor()); //genera el cuadruplo de asignacion
                                    }
                                    break;
                                case "llamado_funcion":
                                    if (hoja.getHijos().get(0).getHijos().size() > 1) { // si el size es mayor a l es porque la función tiene 1 o más parámetros
                                        if (hoja.getHijos().get(0).getHijos().get(1).getNombre().equals(",")) {
                                            recorrerLlamadoFuncion(hoja.getParams());
                                        } else {
                                            switch (hoja.getValorParam().getNombre()) {
                                                //getHijos().get(0).getHijos().get(1).getHijos().get(0)
                                                case "num_int":
                                                    cuadruplos.generarCuadruplo("PARAM", hoja.getValorParam().getValor(), "", "");
                                                    System.out.println("sale generar cuadruplos");
                                                    break;
                                                case "numfloat":
                                                     cuadruplos.generarCuadruplo("PARAM", hoja.getValorParam().getValor(), "", "");
                                                    break;
                                                case "boolean":
                                                     cuadruplos.generarCuadruplo("PARAM", hoja.getValorParam().getValor(), "", "");
                                                    break;
                                                case "id":
                                                    cuadruplos.generarCuadruplo("PARAM", hoja.getValorParam().getValor(), "", "");
                                                    break;
                                                default:
                                                    recorrerAsignacion(hoja.getHijos().get(0).getHijos().get(1).getHijos().get(0));
                                                    System.out.println("sale recorrer asignacion");
                                                    System.out.println(cuadruplos.getLastTemp() + " temporal");
                                                     cuadruplos.generarCuadruplo("PARAM", cuadruplos.getLastTemp(), "", "");
                                                    break;
                                                    
                                            }
                                        }
                                        cuadruplos.generarCuadruplo("CALL", hoja.getFunctionId().getValor(), "", "");
                                    } else if (hoja.getHijos().get(0).getHijos().size() == 1) { // si el size es 1 es porque no tiene parámetros
                                        cuadruplos.generarCuadruplo("CALL", hoja.getFunctionId().getValor(), "", "");
                                    }
                                    hoja.getHijos().get(0).setVisitado2(true);
                                    cuadruplos.generarCuadruplo("=", "RET", "", padre.getHijos().get(0).getValor());
                                    break;
                                default:
                                    recorrerAsignacion(hoja.getHijos().get(0));
                                    if (padre.getHijos().get(0).getNombre().equals(",")) {
                                        recorrerRepeticion(padre.getHijos().get(0), cuadruplos.getLastTemp());
                                    } else {
                                        cuadruplos.generarCuadruplo("=", cuadruplos.getLastTemp(),
                                                "", padre.getHijos().get(0).getValor());
                                    }
                                    hoja.getHijos().get(0).setVisitado2(true);
                                    break;
                            }
                        } else if (padre.getNombre().equals("put")) {
                            switch (hoja.getHijos().get(0).getNombre()) {
                                case "num_int":
                                    cuadruplos.generarCuadruplo("print", hoja.getHijos().get(0).getValor(),
                                            "", "");
                                    break;
                                case "numfloat":
                                    cuadruplos.generarCuadruplo("print", hoja.getHijos().get(0).getValor(),
                                            "", "");
                                    break;
                                case "boolean":
                                    cuadruplos.generarCuadruplo("print", hoja.getHijos().get(0).getValor(),
                                            "", "");
                                    break;
                                case "id":
                                    cuadruplos.generarCuadruplo("print", hoja.getHijos().get(0).getValor(),
                                            "", "");
                                    break;
                                default:
                                    recorrerAsignacion(hoja.getHijos().get(0));
                                    cuadruplos.generarCuadruplo("print", cuadruplos.getLastTemp(),
                                            "", "");
                                    hoja.getHijos().get(0).setVisitado2(true);
                                    break;
                            }
                        } else if (padre.getNombre().equals("get")) {
                            cuadruplos.generarCuadruplo("get", hoja.getHijos().get(0).getValor(),
                                    "", "");
                            hoja.getHijos().get(0).setVisitado2(true);
                        } else if (padre.getNombre().equals("nueva_linea") && padre.getHijos().get(0).getNombre().equals("return")) {
                            switch (hoja.getHijos().get(0).getNombre()) {
                                case "num_int":
                                    //System.out.println("entra num int " + hoja.getHijos().get(0).getNombre());
                                    cuadruplos.generarCuadruplo("RET", hoja.getHijos().get(0).getValor(),
                                            "", "");
                                    
                                    break;
                                case "numfloat":
                                    cuadruplos.generarCuadruplo("RET", hoja.getHijos().get(0).getValor(),
                                            "", "");
                                    break;
                                case "boolean":
                                    cuadruplos.generarCuadruplo("RET", hoja.getHijos().get(0).getValor(),
                                            "", "");
                                    break;
                                case "id":
                                    cuadruplos.generarCuadruplo("RET", hoja.getHijos().get(0).getValor(),
                                            "", "");
                                    break;
                                default:
                                    recorrerAsignacion(padre.getHijos().get(1).getHijos().get(0));
                                    cuadruplos.generarCuadruplo("RET", cuadruplos.getLastTemp(),
                                            "", "");
                                    
                                    hoja.getHijos().get(0).setVisitado2(true);
                                    break;
                            }
                        }
                    } else {
                        hoja.setVisitado2(false);
                    }
                    recorrer(hoja, semaforo);
                } else if (hoja.getNombre().equals("put_string")) {
                    if (semaforo != 1) { //este semaforo ayuda a generar el codigo intermedio de procedure y funciones después del main
                        cuadruplos.generarCuadruplo("print", '"' + hoja.getValor() + '"',
                                "", "");
                    } else {
                        hoja.setVisitado2(false);
                    }
                    recorrer(hoja, semaforo);
                } else if (hoja.getNombre().equals("if-then")) {
                    if (semaforo != 1) { //este semaforo ayuda a generar el codigo intermedio de procedure y funciones después del main
                        hoja.siguiente = cuadruplos.etiquetaNueva();
                        genCuadruploIF(hoja);
                        cuadruplos.generarCuadruplo("ETIQ", hoja.siguiente, "", "");
                    } else {
                        hoja.setVisitado2(false);
                    }
                    recorrer(hoja, semaforo);
                } else if (hoja.getNombre().equals("loop")) {
                    //System.out.println("entra loop");
                    if (semaforo != 1) { //este semaforo ayuda a generar el codigo intermedio de procedure y funciones después del main
                        hoja.siguiente = cuadruplos.etiquetaNueva();
                        genCuadruploExit_When(hoja);
                        cuadruplos.generarCuadruplo("ETIQ", hoja.siguiente, "", "");
                    } else {
                        hoja.setVisitado2(false);
                    }
                    recorrer(hoja, semaforo);
                } else if (hoja.getNombre().equals("exit-when")) {
                    //System.out.println(hoja.verdadera + "algooo");
                    //System.out.println(verdadera + " verdadera");
                    if (semaforo != 1) { //este semaforo ayuda a generar el codigo intermedio de procedure y funciones después del main
                        hoja.getHijos().get(0).verdadera = verdadera;
                        hoja.getHijos().get(0).falsa = falsa;
                        generarE(hoja.getHijos().get(0));
                    } else {
                        hoja.setVisitado2(false);
                    }
                    recorrer(hoja, semaforo);
                } else if (hoja.getNombre().equals("while")) {
                    if (semaforo != 1) { //este semaforo ayuda a generar el codigo intermedio de procedure y funciones después del main
                        hoja.siguiente = cuadruplos.etiquetaNueva();
                        genCuadruploWhile(hoja);
                        cuadruplos.generarCuadruplo("ETIQ", hoja.siguiente, "", "");
                    } else {
                        hoja.setVisitado2(false);
                    }
                    recorrer(hoja, semaforo);
                } else if (hoja.getNombre().equals("for")) {
                    if (semaforo != 1) { //este semaforo ayuda a generar el codigo intermedio de procedure y funciones después del main
                        hoja.siguiente = cuadruplos.etiquetaNueva();
                        genCuadruploFor(hoja);
                        cuadruplos.generarCuadruplo("ETIQ", hoja.siguiente, "", "");
                        hoja.getHijos().get(1).setVisitado2(true);
                        hoja.getHijos().get(2).setVisitado2(true);
                    } else {
                        hoja.setVisitado2(false);
                    }
                    recorrer(hoja, semaforo);
                } else if (hoja.getNombre().equals("Procedure")) {
                    etiqfunct.addHijo(hoja);
                    recorrer(hoja, 1);
                } else if (hoja.getNombre().equals("declaracion_funcion")) {
                    etiqfunct.addHijo(hoja);
                    recorrer(hoja, 1);
                } else if (hoja.getNombre().equals("llamado_funcion")) {
                    if (semaforo != 1) { //este semaforo ayuda a generar el codigo intermedio de procedure y funciones después del main
                        if (hoja.getHijos().size() > 1) { // es porque hay 1 o más parámetros
                            if (hoja.getHijos().get(1).getNombre().equals(",")) { // si es coma hay más de 2 parámetros 
                                recorrerLlamadoFuncion(hoja.getHijos().get(1));
                                hoja.getHijos().get(1).setVisitado2(true);
                            } else { //si no, solo hay un parámetro
                                switch (hoja.getParamDerecha().getNombre()) {
                                    case "num_int":
                                        cuadruplos.generarCuadruplo("PARAM", hoja.getParamDerecha().getValor(), "", "");
                                        break;
                                    case "numfloat":
                                        cuadruplos.generarCuadruplo("PARAM", hoja.getParamDerecha().getValor(), "", "");
                                        break;
                                    case "boolean":
                                        cuadruplos.generarCuadruplo("PARAM", hoja.getParamDerecha().getValor(), "", "");
                                        break;
                                    case "id":
                                        cuadruplos.generarCuadruplo("PARAM", hoja.getParamDerecha().getValor(), "", "");
                                        break;
                                    default:
                                        recorrerAsignacion(hoja.getParamDerecha());
                                        cuadruplos.generarCuadruplo("PARAM", cuadruplos.getLastTemp(), "", "");
                                        break;
                                }
                                hoja.getHijos().get(1).setVisitado2(true);
                            }
                            cuadruplos.generarCuadruplo("CALL", hoja.getHijos().get(0).getValor(), "", ""); //se genera el llamado a la función
                        } else if (hoja.getHijos().size() == 1) { // es porque no tiene parámetros
                            cuadruplos.generarCuadruplo("CALL", hoja.getHijos().get(0).getValor(), "", ""); // solo se genera el call porque no hay parametros
                        }
                        hoja.setVisitado2(true);
                    } else {
                        hoja.setVisitado2(false); //si el semaforo es 1, el visitado se vuelve false para generar el código intermedio después que termina de recorrer
                    }
                    recorrer(hoja, semaforo); //se llama recursivamente en caso de que exista más cuerpo de código
                } else {
                    hoja.setVisitado2(false);
                    recorrer(hoja, semaforo); //si es cualquier otra cosa se recorre
                }
            }
        }
    }

    public void recorrerLlamadoFuncion(Nodo valor) {
        //System.out.println("entra llamadoFuncion");
        if (valor.getHijos().get(1).getNombre().equals(",")) { // recorre recursivamente si encuentra una coma
            if (valor.getParamIzquierda().getNombre().contains("num")
                    || valor.getParamIzquierda().getNombre().contains("bool") || valor.getParamIzquierda().getNombre().equals("id")) {
                cuadruplos.generarCuadruplo("PARAM", valor.getParamIzquierda().getValor(), "", "");
            } else {
                recorrerAsignacion(valor.getParamIzquierda());
                cuadruplos.generarCuadruplo("PARAM", cuadruplos.getLastTemp(), "", "");
            }
            recorrerLlamadoFuncion(valor.getHijos().get(1));
        } else if (valor.getHijos().get(1).getNombre().equals("Asignacion")) { //cuando encuentra una asignacion a la derecha
            if (valor.getParamIzquierda().getNombre().contains("num")
                    || valor.getParamIzquierda().getNombre().contains("bool") || valor.getParamIzquierda().getNombre().equals("id")) {
                cuadruplos.generarCuadruplo("PARAM", valor.getParamIzquierda().getValor(), "", "");
            } else {
                recorrerAsignacion(valor.getParamIzquierda());
                cuadruplos.generarCuadruplo("PARAM", cuadruplos.getLastTemp(), "", "");
            }
            if (valor.getParamDerecha().getNombre().contains("num")
                    || valor.getParamDerecha().getNombre().contains("bool") || valor.getParamDerecha().getNombre().equals("id")) {
                cuadruplos.generarCuadruplo("PARAM", valor.getParamDerecha().getValor(), "", "");
            } else {
                recorrerAsignacion(valor.getParamDerecha());
                cuadruplos.generarCuadruplo("PARAM", cuadruplos.getLastTemp(), "", "");
            }
        }
    }

    public void genCuadruploExit_When(Nodo n) {
        //System.out.println("entra exit");
        n.getHijos().get(0).getHijos().get(1).falsa = cuadruplos.etiquetaNueva();
        cuadruplos.generarCuadruplo("ETIQ", n.getHijos().get(0).getHijos().get(1).falsa, "", "");
        n.getHijos().get(0).getHijos().get(1).verdadera = n.siguiente;
        verdadera = n.getHijos().get(0).getHijos().get(1).verdadera;
        falsa = n.getHijos().get(0).getHijos().get(1).falsa;
        recorrer(n.getHijos().get(0), 0);
    }

    public void recorrerRepeticion(Nodo n, String valor) {
        if (n.getHijos().get(1).getNombre().equals(",")) { //caso base cuando encuentra un id
            String rep_id;
            rep_id = n.getHijos().get(0).getValor();
            cuadruplos.generarCuadruplo("=", valor, "", rep_id);
            recorrerRepeticion(n.getHijos().get(1), valor);
        } else if (n.getHijos().get(1).getNombre().equals("id")) { //caso cuando encuentra una coma
            String rep_id;
            rep_id = n.getHijos().get(0).getValor();
            cuadruplos.generarCuadruplo("=", valor, "", rep_id);
            rep_id = n.getHijos().get(1).getValor();
            cuadruplos.generarCuadruplo("=", valor, "", rep_id);
        }
    }

    public void genCuadruploFor(Nodo n) {
        if (n.getHijos().get(1).getHijos().get(0).getNombre().equals("num_int")) {
            //System.out.println("entra num int for");
            cuadruplos.generarCuadruplo("=", n.getHijos().get(1).getHijos().get(0).getValor(),
                    "", n.getHijos().get(0).getValor());
            //n.getHijos().getsetVisitado2(true);
        } else {
            recorrerAsignacion(n);
            cuadruplos.generarCuadruplo("=", cuadruplos.getLastTemp(), "", n.getHijos().get(0).getValor());
            //n.setVisitado2(true);
        }
        n.comienzo = cuadruplos.etiquetaNueva();
        n.verdadera = cuadruplos.etiquetaNueva();
        cuadruplos.generarCuadruplo("ETIQ", n.comienzo, "", "");
        cuadruplos.generarCuadruplo("IF<=", n.getHijos().get(0).getValor(), n.getHijos().get(2).getHijos().get(0).getValor(), "GOTO " + n.verdadera);
        cuadruplos.generarCuadruplo("GOTO", n.siguiente, "", "");
        cuadruplos.generarCuadruplo("ETIQ", n.verdadera, "", "");
        n.getHijos().get(3).siguiente = cuadruplos.etiquetaNueva();
        recorrer(n.getHijos().get(3), 0);
        //n.getHijos().get(3).setVisitado2(true);
        cuadruplos.generarCuadruplo("+", n.getHijos().get(0).getValor(), "1", cuadruplos.temporalNuevo());
        cuadruplos.generarCuadruplo("=", cuadruplos.getLastTemp(), "", n.getHijos().get(0).getValor());
        cuadruplos.generarCuadruplo("GOTO", n.comienzo, "", "");
    }

    public void genCuadruploWhile(Nodo n) { //aquiiii
        n.comienzo = cuadruplos.etiquetaNueva();
        cuadruplos.generarCuadruplo("ETIQ", n.comienzo, "", "");
        n.getHijos().get(0).verdadera = cuadruplos.etiquetaNueva();
        n.getHijos().get(0).falsa = n.siguiente;
        generarE(n.getHijos().get(0));
        cuadruplos.generarCuadruplo("ETIQ", n.getHijos().get(0).verdadera, "", "");
        n.getHijos().get(1).siguiente = n.comienzo;
        recorrer(n.getHijos().get(1), 0);
        cuadruplos.generarCuadruplo("GOTO", n.comienzo, "", "");
    }

    public void genCuadruploIF(Nodo n) {
        n.getHijos().get(0).verdadera = cuadruplos.etiquetaNueva();
        if (n.getHijos().size() == 2) {
            (n).getHijos().get(0).falsa = n.siguiente;

        } else {
            n.getHijos().get(0).falsa = cuadruplos.etiquetaNueva();
        }
        generarE(n.getHijos().get(0));
        cuadruplos.generarCuadruplo("ETIQ", n.getHijos().get(0).verdadera, "", "");
        n.getHijos().get(1).siguiente = n.siguiente;
        recorrer(n.getHijos().get(1), 0);
        if (n.getHijos().size() > 2) {
            //if true then
            if (n.getHijos().get(2).getNombre().equals("else")) {
                cuadruplos.generarCuadruplo("GOTO", n.siguiente, "", "");
                cuadruplos.generarCuadruplo("ETIQ", n.getHijos().get(0).falsa, "", "");
                n.getHijos().get(2).getHijos().get(0).siguiente = n.siguiente;
                recorrer(n.getHijos().get(2).getHijos().get(0), 0);
            } else {
                cuadruplos.generarCuadruplo("GOTO", n.siguiente, "", "");
                cuadruplos.generarCuadruplo("ETIQ", n.getHijos().get(0).falsa, "", "");
                n.getHijos().get(2).getHijos().get(0).siguiente = n.siguiente;
                n.getHijos().get(2).siguiente = n.siguiente;
                genCuadruploIF(n.getHijos().get(2));
                recorrer(n.getHijos().get(2).getHijos().get(0), 0);
            }
        }
    }

    public void recorrerAsignacion(Nodo padre) {
        if ((padre.getHijos().get(0).getNombre().equals("num_int") || padre.getHijos().get(0).getNombre().equals("numfloat")
                || padre.getHijos().get(0).getNombre().equals("id"))
                && (padre.getHijos().get(1).getNombre().equals("num_int") || padre.getHijos().get(1).getNombre().equals("numfloat")
                || padre.getHijos().get(1).getNombre().equals("id"))) {
            cuadruplos.generarCuadruplo(padre.getNombre(), padre.getHijos().get(0).getValor(),
                    padre.getHijos().get(1).getValor(), cuadruplos.temporalNuevo());
            //System.out.println(cuadruplos.getLastTemp() + "caso base");
        }
        if (!(padre.getHijos().get(0).getNombre().equals("num_int") || padre.getHijos().get(0).getNombre().equals("numfloat")
                || padre.getHijos().get(0).getNombre().equals("id")) //si el de la izquierda no es un número
                && (padre.getHijos().get(1).getNombre().equals("num_int") || padre.getHijos().get(1).getNombre().equals("numfloat")
                || padre.getHijos().get(1).getNombre().equals("id"))) {
            recorrerAsignacion(padre.getHijos().get(0));
            cuadruplos.generarCuadruplo(padre.getNombre(), cuadruplos.getLastTemp(),
                    padre.getHijos().get(1).getValor(), cuadruplos.temporalNuevo());
        }
        if ((padre.getHijos().get(0).getNombre().equals("num_int") || padre.getHijos().get(0).getNombre().equals("numfloat")
                || padre.getHijos().get(0).getNombre().equals("id"))
                && !(padre.getHijos().get(1).getNombre().equals("num_int") || padre.getHijos().get(1).getNombre().equals("numfloat")
                || padre.getHijos().get(1).getNombre().equals("id"))) {
            recorrerAsignacion(padre.getHijos().get(1));
            cuadruplos.generarCuadruplo(padre.getNombre(), padre.getHijos().get(0).getValor(),
                    cuadruplos.getLastTemp(), cuadruplos.temporalNuevo());
        }
        if (!(padre.getHijos().get(0).getNombre().equals("num_int") || padre.getHijos().get(0).getNombre().equals("numfloat")//si el de la izquierda no es un número
                || padre.getHijos().get(0).getNombre().equals("id"))
                && !(padre.getHijos().get(1).getNombre().equals("num_int") || padre.getHijos().get(1).getNombre().equals("numfloat")
                || padre.getHijos().get(1).getNombre().equals("id"))) {
            recorrerAsignacion(padre.getHijos().get(0));
            String tmp = cuadruplos.getLastTemp();
            recorrerAsignacion(padre.getHijos().get(1));
            cuadruplos.generarCuadruplo(padre.getNombre(), tmp, cuadruplos.getLastTemp(), cuadruplos.temporalNuevo());
        }
    }

    String lastF = "";
    String lastV = "";

    public void generarE(Nodo n) {
        if (n.getNombre().toLowerCase().equals("and") && !n.getLeft().isVisitado2() && !n.getRight().isVisitado2()) {
            n.getLeft().verdadera = cuadruplos.etiquetaNueva();
            n.getLeft().falsa = n.falsa;
            generarE((n).getLeft());
            n.getLeft().setVisitado2(true);
            cuadruplos.generarCuadruplo("ETIQ", n.getLeft().verdadera, "", "");
            n.getRight().verdadera = n.verdadera;
            n.getRight().falsa = n.falsa;
            lastF = n.falsa;
            lastV = n.verdadera;
            generarE(n.getRight());
            n.getRight().setVisitado2(true);
        } else if (n.getNombre().toLowerCase().equals("or") && !n.getLeft().isVisitado2() && !n.getRight().isVisitado2()) {
            n.getLeft().verdadera = n.verdadera;
            n.getLeft().falsa = cuadruplos.etiquetaNueva();
            generarE(n.getLeft());
            n.getLeft().setVisitado2(true);
            cuadruplos.generarCuadruplo("ETIQ", n.getLeft().falsa, "", "");
            n.getRight().verdadera = n.verdadera;
            n.getRight().falsa = n.falsa;
            lastF = n.falsa;
            lastV = n.verdadera;
            generarE(n.getRight());
            n.getRight().setVisitado2(true);
        } else {
            n.setVisitado2(true);
            String etiquv = n.verdadera;
            String etiquf = n.falsa;
            //System.out.println(n.getNombre());
            cuadruplos.generarCuadruplo("IF" + n.getNombre(), n.getHijos().get(0).getValor(), n.getHijos().get(1).getValor(), "GOTO " + etiquv);
            cuadruplos.generarCuadruplo("GOTO", etiquf, "", "");
        }
    }

    public void imprimirTabla() {
        System.out.println("\nTABLA CUADRUPLOS:");
        System.out.println("============================================================:");
        int contador = 0;
        for (Cuadruplo cuadruplo : cuadruplos.getCuadruplos()) {
            System.out.println(String.format(
                    "      " + "| Indice: %d | Operador: %s | Argumento1: %s | Argumento2: %s | Resultado: %s |",
                    contador, cuadruplo.operator, cuadruplo.args1, cuadruplo.args2,
                    cuadruplo.result));
            contador++;
        }
        System.out.println("============================================================:");
    }

    public String imprimirCuadruplos() {
        String cuad = "\n";
        for (int i = 0; i < cuadruplos.getCuadruplos().size(); i++) {
            cuad += cuadruplos.getCuadruplos().get(i).prettyToString() + "\n";
        }
        return cuad;
    }
}
