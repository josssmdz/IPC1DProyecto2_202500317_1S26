package modulos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelEstudiante extends JPanel {

    private VentanaPrincipal ventana;

    private static final Color COLOR_FONDO = new Color(15, 15, 35);
    private static final Color COLOR_PANEL = new Color(26, 26, 58);
    private static final Color COLOR_TEXTO = Color.WHITE;
    private static final Color COLOR_ACENTO = new Color(144, 202, 249);

    public PanelEstudiante(VentanaPrincipal ventana) {
        this.ventana = ventana;
        setLayout(new BorderLayout());
        setBackground(COLOR_FONDO);
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(13, 71, 161));
        header.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        JLabel lblTitulo = new JLabel("Datos del Estudiante");
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

        JPanel panelInfo = new JPanel();
        panelInfo.setLayout(new BoxLayout(panelInfo, BoxLayout.Y_AXIS));
        panelInfo.setBackground(COLOR_PANEL);
        panelInfo.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        panelInfo.setPreferredSize(new Dimension(500, 320));

        panelInfo.add(crearLabelTitulo("Información académica"));
        panelInfo.add(Box.createVerticalStrut(15));
        panelInfo.add(crearLabel("Nombre completo: Josselyn Mendoza"));
        panelInfo.add(Box.createVerticalStrut(8));
        panelInfo.add(crearLabel("Carné: 202500317"));
        panelInfo.add(Box.createVerticalStrut(8));
        panelInfo.add(crearLabel("Correo: 2004348880101@usac.edu.gt"));
        panelInfo.add(Box.createVerticalStrut(8));
        panelInfo.add(crearLabel("Sección: D"));
        panelInfo.add(Box.createVerticalStrut(8));
        panelInfo.add(crearLabel("Semestre y año: 3S 2026"));
        panelInfo.add(Box.createVerticalStrut(20));
        panelInfo.add(crearLabelTitulo("Acerca de GameZone Pro"));
        panelInfo.add(Box.createVerticalStrut(15));
        panelInfo.add(crearLabelHtml(
            "Aplicación de escritorio en Java Swing que integra tienda "
            + "de videojuegos, álbum de cartas, torneos con hilos, "
            + "gamificación y reportes HTML."
        ));

        JPanel contenedor = new JPanel();
        contenedor.setBackground(COLOR_FONDO);
        contenedor.add(panelInfo);

        add(header, BorderLayout.NORTH);
        add(contenedor, BorderLayout.CENTER);
    }

    private JLabel crearLabel(String texto) {
        JLabel lbl = new JLabel(texto);
        lbl.setForeground(COLOR_TEXTO);
        lbl.setFont(new Font("Arial", Font.PLAIN, 15));
        lbl.setAlignmentX(Component.LEFT_ALIGNMENT);
        return lbl;
    }

    private JLabel crearLabelTitulo(String texto) {
        JLabel lbl = new JLabel(texto);
        lbl.setForeground(COLOR_ACENTO);
        lbl.setFont(new Font("Arial", Font.BOLD, 17));
        lbl.setAlignmentX(Component.LEFT_ALIGNMENT);
        return lbl;
    }

    private JLabel crearLabelHtml(String texto) {
        JLabel lbl = new JLabel("<html><body style='width:380px'>" + texto + "</body></html>");
        lbl.setForeground(COLOR_TEXTO);
        lbl.setFont(new Font("Arial", Font.PLAIN, 14));
        lbl.setAlignmentX(Component.LEFT_ALIGNMENT);
        return lbl;
    }
}

