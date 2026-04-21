package estructuras;

import modelos.Carta;

//Nodo de la malla ortogonal del álbum de cartas. Cada nodo tiene 4 referencias arriba, abajo, izquierda, derecha.

public class NodoMatriz {

    Carta dato;
    NodoMatriz arriba;
    NodoMatriz abajo;
    NodoMatriz izquierda;
    NodoMatriz derecha;

    public NodoMatriz(Carta dato) {
        this.dato = dato;
        this.arriba = null;
        this.abajo = null;
        this.izquierda = null;
        this.derecha = null;
    }

    public Carta getDato() { return dato; }
    public NodoMatriz getArriba() { return arriba; }
    public NodoMatriz getAbajo() { return abajo; }
    public NodoMatriz getIzquierda() { return izquierda; }
    public NodoMatriz getDerecha() { return derecha; }

    public void setDato(Carta dato) { this.dato = dato; }
    public void setArriba(NodoMatriz arriba) { this.arriba = arriba; }
    public void setAbajo(NodoMatriz abajo) { this.abajo = abajo; }
    public void setIzquierda(NodoMatriz izquierda) { this.izquierda = izquierda; }
    public void setDerecha(NodoMatriz derecha) { this.derecha = derecha; }
}
