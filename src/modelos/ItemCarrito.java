package modelos;

 // Representa un juego dentro del carrito de compras con su cantidad.
 
public class ItemCarrito {

    private Juego juego;
    private int cantidad;

    public ItemCarrito(Juego juego, int cantidad) {
        this.juego = juego;
        this.cantidad = cantidad;
    }

    public double getSubtotal() {
        return juego.getPrecio() * cantidad;
    }

    public void aumentarCantidad() {
        this.cantidad++;
    }

    public void disminuirCantidad() {
        if (this.cantidad > 1) {
            this.cantidad--;
        }
    }

    public Juego getJuego() { return juego; }
    public int getCantidad() { return cantidad; }

    public void setJuego(Juego juego) { this.juego = juego; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    @Override
    public String toString() {
        return juego.getNombre() + " x" + cantidad + 
               " — Q" + String.format("%.2f", getSubtotal());
    }
}