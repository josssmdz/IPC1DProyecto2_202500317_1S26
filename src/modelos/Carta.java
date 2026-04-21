package modelos;

//Representa una carta coleccionable del álbum

public class Carta {
    
    private String codigo;
    private String nombre;
    private String tipo;
    private String rareza;
    private int ataque;
    private int defensa;
    private int ps;
    private String imagen;

    public Carta(String codigo, String nombre, String tipo,
                 String rareza, int ataque, int defensa, int ps, String imagen) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.tipo = tipo;
        this.rareza = rareza;
        this.ataque = ataque;
        this.defensa = defensa;
        this.ps = ps;
        this.imagen = imagen;
    }

    public boolean esLegendaria() {
        return this.rareza.equalsIgnoreCase("Legendaria");
    }

    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public String getTipo() { return tipo; }
    public String getRareza() { return rareza; }
    public int getAtaque() { return ataque; }
    public int getDefensa() { return defensa; }
    public int getPs() { return ps; }
    public String getImagen() { return imagen; }

    public void setCodigo(String codigo) { this.codigo = codigo; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public void setRareza(String rareza) { this.rareza = rareza; }
    public void setAtaque(int ataque) { this.ataque = ataque; }
    public void setDefensa(int defensa) { this.defensa = defensa; }
    public void setPs(int ps) { this.ps = ps; }
    public void setImagen(String imagen) { this.imagen = imagen; }

    @Override
    public String toString() {
        return codigo + "|" + nombre + "|" + tipo + "|" +
               rareza + "|" + ataque + "|" + defensa + "|" + ps + "|" + imagen;
    }
}
