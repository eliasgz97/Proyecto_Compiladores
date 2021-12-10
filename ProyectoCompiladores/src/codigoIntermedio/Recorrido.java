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
    Nodo etiqfunct = new Nodo("funciones");

    public Recorrido() {
        cuadruplos = new TablaCuadruplos();
    }

    public TablaCuadruplos getCuadruplos() {
        return cuadruplos;
    }
    
    public void recorrerfunct() {
        for (Nodo hoja : etiqfunct.getHijos()) {
            hoja.setVisitado2(false);
           if (hoja.getNombre().equals("Procedure")) {
                    cuadruplos.generarCuadruplo("ETIQ", "FUN_" + hoja.getValor(), "", "");
                     for (int i = 0; i < hoja.getHijos().size(); i++) {
                        hoja.getHijos().get(i).setVisitado2(false);
                    }
                    recorrer(hoja);
                }
                if (hoja.getNombre().equals("declaracion_funcion")) {
                    cuadruplos.generarCuadruplo("ETIQ", "FUN_" + hoja.getHijos().get(0).getValor(), "", "");
                     for (int i = 0; i < hoja.getHijos().size(); i++) {
                        hoja.getHijos().get(i).setVisitado2(false);
                    }
                    recorrer(hoja);
                } 
        }
    }

    public void recorrer(Nodo padre) {
        for (Nodo hoja : padre.getHijos()) {
            if ((!hoja.isVisitado2())) {
                hoja.setVisitado2(true);
                //System.out.println(hoja.verdadera + hoja.getNombre());
                if (hoja.getNombre().equals("Asignacion")) {
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
                                if (padre.getHijos().get(0).getNombre().equals(",")) {
                                    recorrerRepeticion(padre.getHijos().get(0), hoja.getHijos().get(0).getValor());
                                } else {
                                    cuadruplos.generarCuadruplo("=", hoja.getHijos().get(0).getValor(),
                                            "", padre.getHijos().get(0).getValor());
                                }
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
                                //System.out.println("entra num int " + hoja.getHijos().get(0).getNombre());
                                cuadruplos.generarCuadruplo("=", hoja.getHijos().get(0).getValor(),
                                        "", "print");
                                break;
                            case "numfloat":
                                cuadruplos.generarCuadruplo("=", hoja.getHijos().get(0).getValor(),
                                        "", "print");
                                break;
                            case "boolean":
                                cuadruplos.generarCuadruplo("=", hoja.getHijos().get(0).getValor(),
                                        "", "print");
                                break;
                            case "id":
                                cuadruplos.generarCuadruplo("=", hoja.getHijos().get(0).getValor(),
                                        "", "print");
                                break;
                            default:
                                recorrerAsignacion(hoja.getHijos().get(0));
                                cuadruplos.generarCuadruplo("=", cuadruplos.getLastTemp(),
                                        "", "print");
                                hoja.getHijos().get(0).setVisitado2(true);
                                break;
                        }
                    } else if (padre.getNombre().equals("get")) {
                        cuadruplos.generarCuadruplo("=", hoja.getHijos().get(0).getValor(),
                                "", "get");
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
                } else if (hoja.getNombre().equals("put_string")) {
                    cuadruplos.generarCuadruplo("=", hoja.getValor(),
                            "", "print");
                }
                if (hoja.getNombre().equals("if-then")) {
                    hoja.siguiente = cuadruplos.etiquetaNueva();
                    genCuadruploIF(hoja);
                    cuadruplos.generarCuadruplo("ETIQ", hoja.siguiente, "", "");
                }
                if (hoja.getNombre().equals("loop")) {
                    //System.out.println("entra loop");
                    hoja.siguiente = cuadruplos.etiquetaNueva();
                    genCuadruploExit_When(hoja);
                    cuadruplos.generarCuadruplo("ETIQ", hoja.siguiente, "", "");
                }
                if (hoja.getNombre().equals("exit-when")) {
                    //System.out.println(hoja.verdadera + "algooo");
                    //System.out.println(verdadera + " verdadera");
                    hoja.getHijos().get(0).verdadera = verdadera;
                    hoja.getHijos().get(0).falsa = falsa;
                    generarE(hoja.getHijos().get(0));
                }
                if (hoja.getNombre().equals("while")) {
                    hoja.siguiente = cuadruplos.etiquetaNueva();
                    genCuadruploWhile(hoja);
                    cuadruplos.generarCuadruplo("ETIQ", hoja.siguiente, "", "");
                }
                if (hoja.getNombre().equals("for")) {
                    hoja.siguiente = cuadruplos.etiquetaNueva();
                    genCuadruploFor(hoja);
                    cuadruplos.generarCuadruplo("ETIQ", hoja.siguiente, "", "");
                    hoja.getHijos().get(1).setVisitado2(true);
                    hoja.getHijos().get(2).setVisitado2(true);
                }
                if (hoja.getNombre().equals("Procedure")) {
                    etiqfunct.addHijo((Nodo)hoja);
                    //cuadruplos.generarCuadruplo("ETIQ", "FUN_" + hoja.getValor(), "", "");
                    for (int i = 0; i < hoja.getHijos().size(); i++) {
                        hoja.getHijos().get(i).setVisitado2(true);
                    }
                }
                if (hoja.getNombre().equals("declaracion_funcion")) {
                    etiqfunct.addHijo((Nodo)hoja);
                    //cuadruplos.generarCuadruplo("ETIQ", "FUN_" + hoja.getHijos().get(0).getValor(), "", "");
                     for (int i = 0; i < hoja.getHijos().size(); i++) {
                        hoja.getHijos().get(i).setVisitado2(true);
                    }
                }
                recorrer(hoja);
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
        recorrer(n.getHijos().get(0));
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
        recorrer(n.getHijos().get(3));
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
        recorrer(n.getHijos().get(1));
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
        recorrer(n.getHijos().get(1));
        if (n.getHijos().size() > 2) {
            //if true then
            if (n.getHijos().get(2).getNombre().equals("else")) {
                cuadruplos.generarCuadruplo("GOTO", n.siguiente, "", "");
                cuadruplos.generarCuadruplo("ETIQ", n.getHijos().get(0).falsa, "", "");
                n.getHijos().get(2).getHijos().get(0).siguiente = n.siguiente;
                recorrer(n.getHijos().get(2).getHijos().get(0));
            } else {
                cuadruplos.generarCuadruplo("GOTO", n.siguiente, "", "");
                cuadruplos.generarCuadruplo("ETIQ", n.getHijos().get(0).falsa, "", "");
                n.getHijos().get(2).getHijos().get(0).siguiente = n.siguiente;
                n.getHijos().get(2).siguiente = n.siguiente;
                genCuadruploIF(n.getHijos().get(2));
                recorrer(n.getHijos().get(2).getHijos().get(0));
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
