package estructuras;

 //Nodo de la cola de espera de participantes en torneos

public class NodoCola {

    Object dato;
    NodoCola siguiente;

    public NodoCola(Object dato) {
        this.dato = dato;
        this.siguiente = null;
    }

    public Object getDato() { return dato; }
    public NodoCola getSiguiente() { return siguiente; }

    public void setDato(Object dato) { this.dato = dato; }
    public void setSiguiente(NodoCola siguiente) { this.siguiente = siguiente; }
}
