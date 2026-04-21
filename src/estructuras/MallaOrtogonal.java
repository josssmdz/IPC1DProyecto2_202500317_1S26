package estructuras;

import modelos.Carta;

//Malla ortogonal de nodos doblemente enlazados. Representa el álbum de cartas coleccionables
 
public class MallaOrtogonal {

    private NodoMatriz origen;
    private int filas;
    private int columnas;

    public MallaOrtogonal(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        construir(filas, columnas);
    }

    public boolean construir(int filas, int columnas) {
        NodoMatriz[][] temp = new NodoMatriz[filas][columnas];
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                temp[i][j] = new NodoMatriz(null);
            }
        }
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (i > 0) temp[i][j].setArriba(temp[i-1][j]);
                if (i < filas - 1) temp[i][j].setAbajo(temp[i+1][j]);
                if (j > 0) temp[i][j].setIzquierda(temp[i-1][j]);
                if (j < columnas - 1) temp[i][j].setDerecha(temp[i+1][j]);
            }
        }

        origen = temp[0][0];
        return true;
    }

    public boolean insertarCarta(Carta carta) {
        NodoMatriz fila = origen;
        while (fila != null) {
            NodoMatriz nodo = fila;
            while (nodo != null) {
                if (nodo.getDato() == null) {
                    nodo.setDato(carta);
                    return true;
                }
                nodo = nodo.getDerecha();
            }
            fila = fila.getAbajo();
        }
        return false;
    }

    public NodoMatriz buscar(String criterio) {
        NodoMatriz fila = origen;
        while (fila != null) {
            NodoMatriz nodo = fila;
            while (nodo != null) {
                if (nodo.getDato() != null) {
                    Carta c = nodo.getDato();
                    if (c.getNombre().equalsIgnoreCase(criterio) ||
                        c.getTipo().equalsIgnoreCase(criterio) ||
                        c.getRareza().equalsIgnoreCase(criterio)) {
                        return nodo;
                    }
                }
                nodo = nodo.getDerecha();
            }
            fila = fila.getAbajo();
        }
        return null;
    }

    public void intercambiar(int f1, int c1, int f2, int c2) {
        NodoMatriz nodo1 = getNodo(f1, c1);
        NodoMatriz nodo2 = getNodo(f2, c2);
        if (nodo1 != null && nodo2 != null) {
            Carta temp = nodo1.getDato();
            nodo1.setDato(nodo2.getDato());
            nodo2.setDato(temp);
        }
    }

    public NodoMatriz getNodo(int fila, int columna) {
        NodoMatriz nodo = origen;
        for (int i = 0; i < fila; i++) {
            if (nodo == null) return null;
            nodo = nodo.getAbajo();
        }
        for (int j = 0; j < columna; j++) {
            if (nodo == null) return null;
            nodo = nodo.getDerecha();
        }
        return nodo;
    }

    public boolean filaCompleta(int fila) {
        NodoMatriz nodo = getNodo(fila, 0);
        while (nodo != null) {
            if (nodo.getDato() == null) return false;
            nodo = nodo.getDerecha();
        }
        return true;
    }

    public NodoMatriz getPrimerNodo() {
        return origen;
    }

    public NodoMatriz getOrigen() { return origen; }
    public int getFilas() { return filas; }
    public int getColumnas() { return columnas; }
}
