package estructuras;

 //Nodo de la lista enlazada simple

public class NodoSimple {
    
    Object dato;
    NodoSimple siguiente;

    public NodoSimple(Object dato) {
        this.dato = dato;
        this.siguiente = null;
    }

    public Object getDato() { return dato; }
    public NodoSimple getSiguiente() { return siguiente; }

    public void setDato(Object dato) { this.dato = dato; }
    public void setSiguiente(NodoSimple siguiente) { this.siguiente = siguiente; }
}
