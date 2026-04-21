package estructuras;

//Lista enlazada simple implementada desde cero. Usada para carrito, historial, logros y leaderboard

public class ListaSimple {

    private NodoSimple frente;
    private int tamanio;

    public ListaSimple() {
        this.frente = null;
        this.tamanio = 0;
    }

    public void insertar(Object dato) {
        NodoSimple nuevo = new NodoSimple(dato);
        if (frente == null) {
            frente = nuevo;
        } else {
            NodoSimple nodo = frente;
            while (nodo.getSiguiente() != null) {
                nodo = nodo.getSiguiente();
            }
            nodo.setSiguiente(nuevo);
        }
        tamanio++;
    }

    public void insertarAlInicio(Object dato) {
        NodoSimple nuevo = new NodoSimple(dato);
        nuevo.setSiguiente(frente);
        frente = nuevo;
        tamanio++;
    }

    public void eliminar(Object dato) {
        if (frente == null) return;
        if (frente.getDato().equals(dato)) {
            frente = frente.getSiguiente();
            tamanio--;
            return;
        }
        NodoSimple nodo = frente;
        while (nodo.getSiguiente() != null) {
            if (nodo.getSiguiente().getDato().equals(dato)) {
                nodo.setSiguiente(nodo.getSiguiente().getSiguiente());
                tamanio--;
                return;
            }
            nodo = nodo.getSiguiente();
        }
    }

    public NodoSimple buscar(Object criterio) {
        NodoSimple nodo = frente;
        while (nodo != null) {
            if (nodo.getDato().toString().contains(criterio.toString())) {
                return nodo;
            }
            nodo = nodo.getSiguiente();
        }
        return null;
    }

    public boolean estaVacia() {
        return frente == null;
    }

    public int getTamanio() {
        return tamanio;
    }

    public Object[] toArray() {
        Object[] arreglo = new Object[tamanio];
        NodoSimple nodo = frente;
        int i = 0;
        while (nodo != null) {
            arreglo[i] = nodo.getDato();
            nodo = nodo.getSiguiente();
            i++;
        }
        return arreglo;
    }

    public void limpiar() {
        frente = null;
        tamanio = 0;
    }
    
    public NodoSimple getFrente() { return frente; }
}