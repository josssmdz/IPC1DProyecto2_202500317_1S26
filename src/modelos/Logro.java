package modelos;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

 //Representa un logro desbloqueable del sistema de gamificación.
public class Logro {

    private String nombre;
    private String descripcion;
    private int xpRecompensa;
    private boolean desbloqueado;
    private String fechaDesbloqueo;

    public Logro(String nombre, String descripcion, int xpRecompensa) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.xpRecompensa = xpRecompensa;
        this.desbloqueado = false;
        this.fechaDesbloqueo = "";
    }

    public void desbloquear(Usuario u) {
        if (!this.desbloqueado) {
            this.desbloqueado = true;
            this.fechaDesbloqueo = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
            u.otorgarXP(this.xpRecompensa);
        }
    }

    public boolean verificar(Usuario u) {
        if (desbloqueado) return false;

        switch (nombre) {
            case "Primera Compra":
                return u.getXp() >= 50;
            case "Coleccionista Novato":
                return u.getXp() >= 500;
            case "Coleccionista Experto":
                return u.getXp() >= 600;
            case "Taquillero":
                return u.getXp() >= 450;
            case "Alta Rareza":
                return u.getXp() >= 200;
            case "Gamer Dedicado":
                return u.getXp() >= 1000;
            case "Leyenda Viviente":
                return u.getNivel() >= 5;
            case "Gran Gastador":
                return u.getXp() >= 2000;
            default:
                return false;
        }
    }

    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    public int getXpRecompensa() { return xpRecompensa; }
    public boolean isDesbloqueado() { return desbloqueado; }
    public String getFechaDesbloqueo() { return fechaDesbloqueo; }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public void setXpRecompensa(int xpRecompensa) { this.xpRecompensa = xpRecompensa; }
    public void setDesbloqueado(boolean desbloqueado) { this.desbloqueado = desbloqueado; }
    public void setFechaDesbloqueo(String fechaDesbloqueo) { this.fechaDesbloqueo = fechaDesbloqueo; }

    @Override
    public String toString() {
        return nombre + "|" + descripcion + "|" + xpRecompensa + "|" +
               desbloqueado + "|" + fechaDesbloqueo;
    }
}