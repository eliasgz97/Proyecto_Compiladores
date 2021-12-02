package codigoIntermedio;

import proyectocompiladores.Nodo;

/**
 *
 * @author EliasGZ
 */
public class Recorrido {

    TablaCuadruplos cuadruplos;

    public Recorrido() {
        cuadruplos = new TablaCuadruplos();
    }

    public void recorrer(Nodo padre) {
        for (Nodo hoja : padre.getHijos()) {
            if (!hoja.isVisitado2()) {
                hoja.setVisitado2(true);
                System.out.println(hoja.getNombre() + " nodo");
                if (hoja.getNombre().equals("Asignacion")) {
                    if ((!hoja.getHijos().get(0).getNombre().equals("num_int")) && (!hoja.getHijos().get(0).getNombre().equals("numfloat"))
                            && (!hoja.getHijos().get(0).getNombre().equals("boolean")) && (!hoja.getHijos().get(0).getNombre().equals("id"))) {
                        recorrerAsignacion(hoja.getHijos().get(0));
                        hoja.getHijos().get(0).setVisitado(true);
                    }
                }
                recorrer(hoja);
            }
        }
    }

    public void recorrerAsignacion(Nodo padre) {
        if ((padre.getHijos().get(0).getNombre().equals("num_int") || padre.getHijos().get(0).getNombre().equals("numfloat")
                || padre.getHijos().get(0).getNombre().equals("id"))
                && (padre.getHijos().get(1).getNombre().equals("num_int") || padre.getHijos().get(1).getNombre().equals("numfloat")
                || padre.getHijos().get(1).getNombre().equals("id"))) {
            cuadruplos.generarCuadruplo(padre.getNombre(), padre.getHijos().get(0).getValor(), padre.getHijos().get(1).getValor(), cuadruplos.temporalNuevo());
            //System.out.println(cuadruplos.getLastTemp() + "caso base");
        }
        if (!(padre.getHijos().get(0).getNombre().equals("num_int") || padre.getHijos().get(0).getNombre().equals("numfloat")
                || padre.getHijos().get(0).getNombre().equals("id")) //si el de la izquierda no es un número
                && (padre.getHijos().get(1).getNombre().equals("num_int") || padre.getHijos().get(1).getNombre().equals("numfloat")
                || padre.getHijos().get(1).getNombre().equals("id"))) {
            recorrerAsignacion(padre.getHijos().get(0));
        }
        if ((padre.getHijos().get(0).getNombre().equals("num_int") || padre.getHijos().get(0).getNombre().equals("numfloat")
                || padre.getHijos().get(0).getNombre().equals("id"))
                && !(padre.getHijos().get(1).getNombre().equals("num_int") || padre.getHijos().get(1).getNombre().equals("numfloat")
                || padre.getHijos().get(1).getNombre().equals("id"))) {
            recorrerAsignacion(padre.getHijos().get(1));
        }
        if (!(padre.getHijos().get(0).getNombre().equals("num_int") || padre.getHijos().get(0).getNombre().equals("numfloat")//si el de la izquierda no es un número
                || padre.getHijos().get(0).getNombre().equals("id"))
                && !(padre.getHijos().get(1).getNombre().equals("num_int") || padre.getHijos().get(1).getNombre().equals("numfloat")
                || padre.getHijos().get(1).getNombre().equals("id"))) {
            recorrerAsignacion(padre.getHijos().get(0));
            recorrerAsignacion(padre.getHijos().get(1));
        }
    }

    public void imprimirTabla() {
        System.out.println("\nTABLA CUADRUPLOS:");
        System.out.println("============================================================:");
        int contador  = 0;
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
