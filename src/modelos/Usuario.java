package modelos;

 //Representa el usuario actual de la plataforma GameZone Pro.
 
public class Usuario {

    private String nombre;
    private String carne;
    private int xp;
    private int nivel;
    private ListaSimple logros;

    private static final int[] UMBRALES = {0, 500, 1500, 3500, 7000};
    private static final String[] RANGOS = {
        "Aprendiz", "Jugador", "Veterano", "Maestro", "Leyenda"
    };

    public Usuario(String nombre, String carne) {
        this.nombre = nombre;
        this.carne = carne;
        this.xp = 0;
        this.nivel = 1;
        this.logros = new ListaSimple();
        inicializarLogros();
    }

    private void inicializarLogros() {
        logros.insertar(new Logro("Primera Compra",
                "Realiza tu primera compra en la tienda", 10));
        logros.insertar(new Logro("Coleccionista Novato",
                "Añade 10 cartas a tu álbum", 20));
        logros.insertar(new Logro("Coleccionista Experto",
                "Completa una fila completa del álbum", 30));
        logros.insertar(new Logro("Taquillero",
                "Compra tickets para 3 torneos distintos", 25));
        logros.insertar(new Logro("Alta Rareza",
                "Obtén una carta de rareza Legendaria", 50));
        logros.insertar(new Logro("Gamer Dedicado",
                "Acumula 1000 XP", 50));
        logros.insertar(new Logro("Leyenda Viviente",
                "Alcanza el Nivel 5", 100));
        logros.insertar(new Logro("Gran Gastador",
                "Gasta más de Q2000 en la tienda", 30));
    }

    public void otorgarXP(int cantidad) {
        this.xp += cantidad;
        actualizarNivel();
    }

    private void actualizarNivel() {
        for (int i = UMBRALES.length - 1; i >= 0; i--) {
            if (this.xp >= UMBRALES[i]) {
                this.nivel = i + 1;
                break;
            }
        }
    }

    public String getRango() {
        return RANGOS[this.nivel - 1];
    }

    public int getXPSiguienteNivel() {
        if (this.nivel >= 5) return this.xp;
        return UMBRALES[this.nivel];
    }

    public float getProgreso() {
        if (this.nivel >= 5) return 1.0f;
        int xpNivelActual = UMBRALES[this.nivel - 1];
        int xpSiguiente = UMBRALES[this.nivel];
        return (float)(this.xp - xpNivelActual) / (xpSiguiente - xpNivelActual);
    }

    public Logro verificarLogros() {
        NodoSimple nodo = logros.getFrente();
        while (nodo != null) {
            Logro logro = (Logro) nodo.getDato();
            if (logro.verificar(this)) {
                logro.desbloquear(this);
                return logro;
            }
            nodo = nodo.getSiguiente();
        }
        return null;
    }

    public int getXPNivelActual() {
        return UMBRALES[this.nivel - 1];
    }

    public String getNombre() { return nombre; }
    public String getCarne() { return carne; }
    public int getXp() { return xp; }
    public int getNivel() { return nivel; }
    public ListaSimple getLogros() { return logros; }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setCarne(String carne) { this.carne = carne; }
    public void setXp(int xp) { this.xp = xp; }
    public void setNivel(int nivel) { this.nivel = nivel; }
    public void setLogros(ListaSimple logros) { this.logros = logros; }

    @Override
    public String toString() {
        return nombre + "|" + carne + "|" + xp + "|" + nivel;
    }
}
