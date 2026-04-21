package modulos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

 //Panel del menú principal de GameZone Pro.
 
public class PanelMenu extends JPanel {

    private VentanaPrincipal ventana;

    private JLabel lblTitulo;
    private JLabel lblSubtitulo;
    private JButton btnTienda;
    private JButton btnAlbum;
    private JButton btnTorneos;
    private JButton btnGamificacion;
    private JButton btnReportes;
    private JButton btnEstudiante;
    private JButton btnSalir;

    private static final Color COLOR_FONDO = new Color(15, 15, 35);
    private static final Color COLOR_BOTON = new Color(26, 115, 232);
    private static final Color COLOR_BOTON_HOVER = new Color(21, 101, 192);
    private static final Color COLOR_SALIR = new Color(183, 28, 28);
    private static final Color COLOR_TEXTO = Color.WHITE;

    public PanelMenu(VentanaPrincipal ventana) {
        this.ventana = ventana;
        setLayout(new BorderLayout());
        setBackground(COLOR_FONDO);
        inicializarComponentes();
        agregarComponentes();
        agregarEventos();
    }

    private void inicializarComponentes() {
        // Título
        lblTitulo = new JLabel("GameZone Pro", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 42));
        lblTitulo.setForeground(COLOR_TEXTO);

        // Subtítulo
        lblSubtitulo = new JLabel("IPC1 — Proyecto 2", SwingConstants.CENTER);
        lblSubtitulo.setFont(new Font("Arial", Font.PLAIN, 16));
        lblSubtitulo.setForeground(new Color(144, 202, 249));

        // Botones
        btnTienda = crearBoton("Tienda de Videojuegos", COLOR_BOTON);
        btnAlbum = crearBoton("Album de Cartas", COLOR_BOTON);
        btnTorneos = crearBoton("Eventos Especiales", COLOR_BOTON);
        btnGamificacion = crearBoton("Recompensas y Leaderboard", COLOR_BOTON);
        btnReportes = crearBoton("Reportes", COLOR_BOTON);
        btnEstudiante = crearBoton("Ver Datos del Estudiante", COLOR_BOTON);
        btnSalir = crearBoton("Salir", COLOR_SALIR);
    }

    private JButton crearBoton(String texto, Color color) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Arial", Font.BOLD, 15));
        btn.setBackground(color);
        btn.setForeground(COLOR_TEXTO);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(320, 48));

        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(color.darker());
            }
            @Override
            public void mouseExited(MouseEvent e) {
                btn.setBackground(color);
            }
        });

        return btn;
    }

    private void agregarComponentes() {
        JPanel panelTitulo = new JPanel();
        panelTitulo.setLayout(new BoxLayout(panelTitulo, BoxLayout.Y_AXIS));
        panelTitulo.setBackground(COLOR_FONDO);
        panelTitulo.setBorder(BorderFactory.createEmptyBorder(50, 0, 30, 0));

        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblSubtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        panelTitulo.add(lblTitulo);
        panelTitulo.add(Box.createVerticalStrut(8));
        panelTitulo.add(lblSubtitulo);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.Y_AXIS));
        panelBotones.setBackground(COLOR_FONDO);

        btnTienda.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnAlbum.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnTorneos.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnGamificacion.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnReportes.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnEstudiante.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnSalir.setAlignmentX(Component.CENTER_ALIGNMENT);

        panelBotones.add(btnTienda);
        panelBotones.add(Box.createVerticalStrut(12));
        panelBotones.add(btnAlbum);
        panelBotones.add(Box.createVerticalStrut(12));
        panelBotones.add(btnTorneos);
        panelBotones.add(Box.createVerticalStrut(12));
        panelBotones.add(btnGamificacion);
        panelBotones.add(Box.createVerticalStrut(12));
        panelBotones.add(btnReportes);
        panelBotones.add(Box.createVerticalStrut(12));
        panelBotones.add(btnEstudiante);
        panelBotones.add(Box.createVerticalStrut(24));
        panelBotones.add(btnSalir);

        add(panelTitulo, BorderLayout.NORTH);
        add(panelBotones, BorderLayout.CENTER);
    }

    private void agregarEventos() {
        btnTienda.addActionListener(e ->
            ventana.mostrarPanel(VentanaPrincipal.TIENDA));
        btnAlbum.addActionListener(e ->
            ventana.mostrarPanel(VentanaPrincipal.ALBUM));
        btnTorneos.addActionListener(e ->
            ventana.mostrarPanel(VentanaPrincipal.TORNEOS));
        btnGamificacion.addActionListener(e ->
            ventana.mostrarPanel(VentanaPrincipal.GAMIFICACION));
        btnReportes.addActionListener(e ->
            ventana.mostrarPanel(VentanaPrincipal.REPORTES));
        btnEstudiante.addActionListener(e ->
            ventana.mostrarPanel(VentanaPrincipal.ESTUDIANTE));
        btnSalir.addActionListener(e -> ventana.salir());
    }
}
