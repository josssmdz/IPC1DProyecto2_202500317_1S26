package modelos;

//Representa un videojuego del catálogo de la tienda

public class Juego {

    private String codigo;
    private String nombre;
    private String genero;
    private String plataforma;
    private double precio;
    private int stock;
    private String descripcion;

    public Juego(String codigo, String nombre, String genero, String plataforma, double precio, 
                 int stock, String descripcion) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.genero = genero;
        this.plataforma = plataforma;
        this.precio = precio;
        this.stock = stock;
        this.descripcion = descripcion;
    }

    public void reducirStock(int cantidad) {
        this.stock -= cantidad;
    }

    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public String getGenero() { return genero; }
    public String getPlataforma() { return plataforma; }
    public double getPrecio() { return precio; }
    public int getStock() { return stock; }
    public String getDescripcion() { return descripcion; }

    public void setCodigo(String codigo) { this.codigo = codigo; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setGenero(String genero) { this.genero = genero; }
    public void setPlataforma(String plataforma) { this.plataforma = plataforma; }
    public void setPrecio(double precio) { this.precio = precio; }
    public void setStock(int stock) { this.stock = stock; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    @Override
    public String toString() {
        return codigo + "|" + nombre + "|" + genero + "|" +
               precio + "|" + plataforma + "|" + stock + "|" + descripcion;
    }
}