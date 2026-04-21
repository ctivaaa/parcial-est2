
package parcial;

class Caja {
    int valor;
    Caja izquierda;
    Caja derecha;

    public Caja(int valor) {
        this.valor = valor;
        this.izquierda = null;
        this.derecha = null;
    }
}

class Arbol {
    Caja primero;

    public Caja poner(Caja actual, int numero) {
        if (actual == null) {
            return new Caja(numero);
        }

        if (numero < actual.valor) {
            actual.izquierda = poner(actual.izquierda, numero);
        } else {
            actual.derecha = poner(actual.derecha, numero);
        }

        return actual;
    }

    public void mostrarOrdenado(Caja actual) {
        if (actual != null) {
            mostrarOrdenado(actual.izquierda);
            System.out.print(actual.valor + " ");
            mostrarOrdenado(actual.derecha);
        }
    }

    public int contarFinales(Caja actual) {
        if (actual == null) {
            return 0;
        }

        if (actual.izquierda == null && actual.derecha == null) {
            return 1;
        }

        return contarFinales(actual.izquierda) +
               contarFinales(actual.derecha);
    }

    public Caja borrar(Caja actual, int numero) {
        if (actual == null) {
            return null;
        }

        if (numero < actual.valor) {
            actual.izquierda = borrar(actual.izquierda, numero);
        } 
        else if (numero > actual.valor) {
            actual.derecha = borrar(actual.derecha, numero);
        } 
        else {
            if (actual.izquierda == null && actual.derecha == null) {
                return null;
            }

            if (actual.izquierda == null) {
                return actual.derecha;
            }
            if (actual.derecha == null) {
                return actual.izquierda;
            }

            int menor = buscarMasPequeno(actual.derecha);
            actual.valor = menor;
            actual.derecha = borrar(actual.derecha, menor);
        }

        return actual;
    }

    private int buscarMasPequeno(Caja actual) {
        while (actual.izquierda != null) {
            actual = actual.izquierda;
        }
        return actual.valor;
    }
}

public class Programa {
    public static void main(String[] args) {

        Arbol miArbol = new Arbol();

        int[] numeros = {
            55, 25, 75, 15, 35, 65, 8555,
            25, 75, 15, 35, 65, 85
        };

        for (int n : numeros) {
            miArbol.primero = miArbol.poner(miArbol.primero, n);
        }

        System.out.print("Numeros en orden: ");
        miArbol.mostrarOrdenado(miArbol.primero);
        System.out.println();

        System.out.println("Cantidad de hojas: " +
                miArbol.contarFinales(miArbol.primero));

        miArbol.primero = miArbol.poner(miArbol.primero, 30);
        System.out.print("Despues de agregar 30: ");
        miArbol.mostrarOrdenado(miArbol.primero);
        System.out.println();

        miArbol.primero = miArbol.borrar(miArbol.primero, 65);
        System.out.print("Despues de borrar 65: ");
        miArbol.mostrarOrdenado(miArbol.primero);
        System.out.println();
    }
}