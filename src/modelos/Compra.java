package modelos;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import estructuras.ListaSimple;
import estructuras.NodoSimple;


 //Representa una compra confirmada en la tienda.
 
public class Compra {

    private String id;
    private String fecha;
    private ListaSimple items;
    private double total;

    public Compra(String id, ListaSimple items) {
        this.id = id;
        this.items = items;
        this.fecha = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        this.total = calcularTotal();
    }

    private double calcularTotal() {
        double suma = 0;
        NodoSimple nodo = items.getFrente();
        while (nodo != null) {
            ItemCarrito item = (ItemCarrito) nodo.getDato();
            suma += item.getSubtotal();
            nodo = nodo.getSiguiente();
        }
        return suma;
    }

    public String getResumen() {
        return "Compra #" + id + " | " + fecha + " | Total: Q" + 
               String.format("%.2f", total);
    }

    public String getId() { return id; }
    public String getFecha() { return fecha; }
    public ListaSimple getItems() { return items; }
    public double getTotal() { return total; }

    public void setId(String id) { this.id = id; }
    public void setFecha(String fecha) { this.fecha = fecha; }
    public void setItems(ListaSimple items) { this.items = items; }
    public void setTotal(double total) { this.total = total; }

    @Override
    public String toString() {
        return id + "|" + fecha + "|" + total;
    }
}