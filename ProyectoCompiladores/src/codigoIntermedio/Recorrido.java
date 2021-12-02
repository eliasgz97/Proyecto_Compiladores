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
                //System.out.println(hoja.getNombre() + " nodo");
                //perales(5+9*7, true, 8.9)
                if (hoja.getNombre().equals("Asignacion")) {
                    if (!padre.getNombre().equals("put") && !padre.getNombre().equals("get")) {
                        switch (hoja.getHijos().get(0).getNombre()) {
                            case "num_int":
                                //System.out.println("entra num int " + hoja.getHijos().get(0).getNombre());
                                cuadruplos.generarCuadruplo("=", hoja.getHijos().get(0).getValor(),
                                        "", padre.getHijos().get(0).getValor());
                                break;
                            case "numfloat":
                                cuadruplos.generarCuadruplo("=", hoja.getHijos().get(0).getValor(),
                                        "", padre.getHijos().get(0).getValor());
                                break;
                            case "boolean":
                                cuadruplos.generarCuadruplo("=", hoja.getHijos().get(0).getValor(),
                                        "", padre.getHijos().get(0).getValor());
                                break;
                            case "id":
                                cuadruplos.generarCuadruplo("=", hoja.getHijos().get(0).getValor(),
                                        "", padre.getHijos().get(0).getValor());
                                break;
                            default:
                                recorrerAsignacion(hoja.getHijos().get(0));
                                cuadruplos.generarCuadruplo("=", cuadruplos.getLastTemp(),
                                        "", padre.getHijos().get(0).getValor());
                                hoja.getHijos().get(0).setVisitado2(true);
                                break;
                        }
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
