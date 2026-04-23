package modulos;

import javax.swing.*;
import java.awt.*;
import reportes.GeneradorReportes;

//Panel de generación de reportes HTML. Módulo 5 — 4 tipos de reportes con CSS embebido

public class PanelReportes extends JPanel {

    private VentanaPrincipal ventana;

    private static final Color COLOR_FONDO = new Color(15, 15, 35);
    private static final Color COLOR_PANEL = new Color(26, 26, 58);
    private static final Color COLOR_TEXTO = Color.WHITE;
    private static final Color COLOR_ACENTO = new Color(144, 202, 249);

    public PanelReportes(VentanaPrincipal ventana) {
        this.ventana = ventana;
        setLayout(new BorderLayout());
        setBackground(COLOR_FONDO);
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(13, 71, 161));
        header.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        JLabel lblTitulo = new JLabel("Generación de Reportes HTML");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setForeground(COLOR_TEXTO);

        JButton btnVolver = new JButton("Menú");
        btnVolver.setBackground(new Color(21, 101, 192));
        btnVolver.setForeground(COLOR_TEXTO);
        btnVolver.setFocusPainted(false);
        btnVolver.setBorderPainted(false);
        btnVolver.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnVolver.addActionListener(e ->
            ventana.mostrarPanel(VentanaPrincipal.MENU));

        header.add(lblTitulo, BorderLayout.WEST);
        header.add(btnVolver, BorderLayout.EAST);

        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(
            new BoxLayout(panelCentral, BoxLayout.Y_AXIS));
        panelCentral.setBackground(COLOR_FONDO);
        panelCentral.setBorder(
            BorderFactory.createEmptyBorder(40, 200, 40, 200));

        JLabel lblDesc = new JLabel(
            "Selecciona el tipo de reporte a generar:",
            SwingConstants.CENTER);
        lblDesc.setForeground(COLOR_ACENTO);
        lblDesc.setFont(new Font("Arial", Font.PLAIN, 16));
        lblDesc.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Botones de reportes
        JButton btnInventario = crearBtnReporte(
            "Reporte de Inventario",
            "Lista todos los juegos con stock y precios",
            new Color(13, 71, 161));

        JButton btnVentas = crearBtnReporte(
            "Reporte de Ventas",
            "Historial de compras con totales",
            new Color(27, 94, 32));

        JButton btnAlbum = crearBtnReporte(
            "Reporte del Álbum",
            "Estado actual del álbum de cartas",
            new Color(74, 20, 140));

        JButton btnTorneos = crearBtnReporte(
            "Reporte de Torneos",
            "Lista de torneos y tickets vendidos",
            new Color(230, 81, 0));

        btnInventario.addActionListener(e ->
            GeneradorReportes.generarInventario(
                ventana.getPanelTienda().getCatalogo()));

        btnVentas.addActionListener(e ->
            GeneradorReportes.generarVentas(
                ventana.getPanelTienda().getHistorial()));

        btnAlbum.addActionListener(e ->
            GeneradorReportes.generarAlbum(
                ventana.getPanelAlbum().getMalla()));

        btnTorneos.addActionListener(e ->
            GeneradorReportes.generarTorneos(
                ventana.getPanelTorneos().getTorneos(),
                ventana.getPanelTorneos().getTicketsVendidos()));

        panelCentral.add(lblDesc);
        panelCentral.add(Box.createVerticalStrut(30));
        panelCentral.add(btnInventario);
        panelCentral.add(Box.createVerticalStrut(15));
        panelCentral.add(btnVentas);
        panelCentral.add(Box.createVerticalStrut(15));
        panelCentral.add(btnAlbum);
        panelCentral.add(Box.createVerticalStrut(15));
        panelCentral.add(btnTorneos);

        add(header, BorderLayout.NORTH);
        add(panelCentral, BorderLayout.CENTER);
    }

    private JButton crearBtnReporte(String titulo,
                                     String descripcion, Color color) {
        JButton btn = new JButton(
            "<html><b>" + titulo + "</b><br>" +
            "<small>" + descripcion + "</small></html>");
        btn.setBackground(color);
        btn.setForeground(COLOR_TEXTO);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) {
                btn.setBackground(color.darker());
            }
            public void mouseExited(java.awt.event.MouseEvent e) {
                btn.setBackground(color);
            }
        });

        return btn;
    }
}
