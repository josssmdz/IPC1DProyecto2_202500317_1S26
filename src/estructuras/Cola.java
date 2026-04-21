package estructuras;

//Cola implementada desde cero con nodos propios. Usada para la fila de espera de participantes en torneos

public class Cola {

    private NodoCola frente;
    private NodoCola fin;
    private int tamanio;

    public Cola() {
        this.frente = null;
        this.fin = null;
        this.tamanio = 0;
    }

    public void encolar(Object dato) {
        NodoCola nuevo = new NodoCola(dato);
        if (fin == null) {
            frente = nuevo;
            fin = nuevo;
        } else {
            fin.setSiguiente(nuevo);
            fin = nuevo;
        }
        tamanio++;
    }

    public synchronized Object desencolar() {
        if (estaVacia()) return null;
        Object dato = frente.getDato();
        frente = frente.getSiguiente();
        if (frente == null) fin = null;
        tamanio--;
        return dato;
    }

    public Object peek() {
        if (estaVacia()) return null;
        return frente.getDato();
    }

    public boolean estaVacia() {
        return frente == null;
    }

    public int tamanio() {
        return tamanio;
    }

    public Object[] toArray() {
        Object[] arreglo = new Object[tamanio];
        NodoCola nodo = frente;
        int i = 0;
        while (nodo != null) {
            arreglo[i] = nodo.getDato();
            nodo = nodo.getSiguiente();
            i++;
        }
        return arreglo;
    }

    public NodoCola getFrente() { return frente; }
    public NodoCola getFin() { return fin; }
}
