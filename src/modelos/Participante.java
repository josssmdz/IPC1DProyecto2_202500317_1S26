package modelos;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

 //Representa un participante inscrito a un torneo.

public class Participante {

    private String nombre;
    private String torneo;
    private String timestamp;

    public Participante(String nombre, String torneo) {
        this.nombre = nombre;
        this.torneo = torneo;
        this.timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
    }

    public String getNombre() { return nombre; }
    public String getTorneo() { return torneo; }
    public String getTimestamp() { return timestamp; }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setTorneo(String torneo) { this.torneo = torneo; }
    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }

    @Override
    public String toString() {
        return nombre + "|" + torneo + "|" + timestamp;
    }
}
