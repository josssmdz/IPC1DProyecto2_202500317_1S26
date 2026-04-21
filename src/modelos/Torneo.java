package modelos;

 //Representa un torneo de videojuegos disponible en la plataforma.
 
public class Torneo {
    private String id;
    private String nombre;
    private String juego;
    private String fecha;
    private String hora;
    private double precioTicket;
    private int ticketsDisponibles;

    public Torneo(String id, String nombre, String juego,
                  String fecha, String hora, double precioTicket, int ticketsDisponibles) {
        this.id = id;
        this.nombre = nombre;
        this.juego = juego;
        this.fecha = fecha;
        this.hora = hora;
        this.precioTicket = precioTicket;
        this.ticketsDisponibles = ticketsDisponibles;
    }

    public boolean hayTickets() {
        return this.ticketsDisponibles > 0;
    }

    public synchronized void reducirTicket() {
        if (this.ticketsDisponibles > 0) {
            this.ticketsDisponibles--;
        }
    }

    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public String getJuego() { return juego; }
    public String getFecha() { return fecha; }
    public String getHora() { return hora; }
    public double getPrecioTicket() { return precioTicket; }
    public int getTicketsDisponibles() { return ticketsDisponibles; }

    public void setId(String id) { this.id = id; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setJuego(String juego) { this.juego = juego; }
    public void setFecha(String fecha) { this.fecha = fecha; }
    public void setHora(String hora) { this.hora = hora; }
    public void setPrecioTicket(double precioTicket) { this.precioTicket = precioTicket; }
    public void setTicketsDisponibles(int ticketsDisponibles) { this.ticketsDisponibles = ticketsDisponibles; }

    @Override
    public String toString() {
        return id + "|" + nombre + "|" + juego + "|" +
               fecha + "|" + hora + "|" + precioTicket + "|" + ticketsDisponibles;
    }
}