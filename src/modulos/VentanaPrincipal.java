package modulos;

import javax.swing.*;
import java.awt.*;
import modelos.Usuario;

//JFrame principal de GameZone Pro. Contiene el CardLayout para navegar entre módulos

public class VentanaPrincipal extends JFrame {

    private CardLayout cardLayout;
    private JPanel panelContenido;
    private Usuario usuario;

    private PanelMenu panelMenu;
    private PanelTienda panelTienda;
    private PanelAlbum panelAlbum;
    private PanelTorneos panelTorneos;
    private PanelGamificacion panelGamificacion;
    private PanelReportes panelReportes;
    private PanelEstudiante panelEstudiante;

    public static final String MENU = "MENU";
    public static final String TIENDA = "TIENDA";
    public static final String ALBUM = "ALBUM";
    public static final String TORNEOS = "TORNEOS";
    public static final String GAMIFICACION = "GAMIFICACION";
    public static final String REPORTES = "REPORTES";
    public static final String ESTUDIANTE = "ESTUDIANTE";

    public VentanaPrincipal() {
        this.usuario = new Usuario("Josselyn Mendoza", "202500317");
        inicializarVentana();
        inicializarPaneles();
        agregarPaneles();
        mostrarPanel(MENU);
    }

    private void inicializarVentana() {
        setTitle("GameZone Pro — IPC1");
        setSize(1024, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        cardLayout = new CardLayout();
        panelContenido = new JPanel(cardLayout);
        add(panelContenido);
    }

    private void inicializarPaneles() {
        panelMenu = new PanelMenu(this);
        panelTienda = new PanelTienda(this);
        panelAlbum = new PanelAlbum(this);
        panelTorneos = new PanelTorneos(this);
        panelGamificacion = new PanelGamificacion(this);
        panelReportes = new PanelReportes(this);
        panelEstudiante = new PanelEstudiante(this);
    }

    private void agregarPaneles() {
        panelContenido.add(panelMenu, MENU);
        panelContenido.add(panelTienda, TIENDA);
        panelContenido.add(panelAlbum, ALBUM);
        panelContenido.add(panelTorneos, TORNEOS);
        panelContenido.add(panelGamificacion, GAMIFICACION);
        panelContenido.add(panelReportes, REPORTES);
        panelContenido.add(panelEstudiante, ESTUDIANTE);
    }

    public void mostrarPanel(String nombre) {
        cardLayout.show(panelContenido, nombre);
    }

    public void salir() {
        int confirm = JOptionPane.showConfirmDialog(
            this,
            "¿Estás seguro que deseas salir?",
            "Salir",
            JOptionPane.YES_NO_OPTION
        );
        if (confirm == JOptionPane.YES_OPTION) {
            guardarDatos();
            System.exit(0);
        }
    }

    private void guardarDatos() {
        panelTienda.guardarDatos();
        panelAlbum.guardarDatos();
        panelTorneos.guardarDatos();
        panelGamificacion.guardarDatos();
    }

    public Usuario getUsuario() { return usuario; }
    public PanelTienda getPanelTienda() { return panelTienda; }
    public PanelAlbum getPanelAlbum() { return panelAlbum; }
    public PanelTorneos getPanelTorneos() { return panelTorneos; }
    public PanelGamificacion getPanelGamificacion() { return panelGamificacion; }
}
