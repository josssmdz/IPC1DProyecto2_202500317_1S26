package modulos;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.io.*;
import java.util.Scanner;
import modelos.*;
import estructuras.*;

public class PanelGamificacion extends JPanel {

    private VentanaPrincipal ventana;
    private Usuario[] leaderboard;
    private int totalUsuarios;

    private JLabel lblNivel;
    private JLabel lblRango;
    private JLabel lblXP;
    private JProgressBar progressBar;
    private JPanel panelLogros;
    private JTable tablaLeaderboard;
    private DefaultTableModel modeloLeaderboard;

    private static final Color COLOR_FONDO = new Color(15, 15, 35);
    private static final Color COLOR_PANEL = new Color(26, 26, 58);
    private static final Color COLOR_BOTON = new Color(26, 115, 232);
    private static final Color COLOR_TEXTO = Color.WHITE;
    private static final Color COLOR_ACENTO = new Color(144, 202, 249);
    private static final Color COLOR_ORO = new Color(255, 215, 0);
    private static final Color COLOR_PLATA = new Color(192, 192, 192);
    private static final Color COLOR_BRONCE = new Color(205, 127, 50);

    public PanelGamificacion(VentanaPrincipal ventana) {
        this.ventana = ventana;
        this.leaderboard = new Usuario[10];
        this.totalUsuarios = 0;
        setLayout(new BorderLayout());
        setBackground(COLOR_FONDO);
        inicializarComponentes();
        agregarComponentes();
        cargarLeaderboard();
    }

    private void inicializarComponentes() {
        lblNivel = new JLabel("Nivel 1 — Aprendiz");
        lblNivel.setForeground(COLOR_ORO);
        lblNivel.setFont(new Font("Arial", Font.BOLD, 22));

        lblRango = new JLabel("Rango: Aprendiz");
        lblRango.setForeground(COLOR_ACENTO);
        lblRango.setFont(new Font("Arial", Font.PLAIN, 14));

        lblXP = new JLabel("XP: 0 / 500");
        lblXP.setForeground(COLOR_TEXTO);
        lblXP.setFont(new Font("Arial", Font.PLAIN, 14));

        progressBar = new JProgressBar(0, 100);
        progressBar.setValue(0);
        progressBar.setStringPainted(true);
        progressBar.setForeground(new Color(26, 115, 232));
        progressBar.setBackground(new Color(40, 40, 80));
        progressBar.setPreferredSize(new Dimension(0, 25));

        panelLogros = new JPanel(new GridLayout(0, 2, 10, 10));
        panelLogros.setBackground(COLOR_PANEL);
        panelLogros.setBorder(
            BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String[] cols = {"#", "Usuario", "XP", "Nivel", "Rango"};
        modeloLeaderboard = new DefaultTableModel(cols, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaLeaderboard = new JTable(modeloLeaderboard) {
            @Override
            public Component prepareRenderer(
                    javax.swing.table.TableCellRenderer r, int row, int col) {
                Component c = super.prepareRenderer(r, row, col);
                if (row == 0) c.setBackground(new Color(80, 60, 0));
                else if (row == 1) c.setBackground(new Color(60, 60, 60));
                else if (row == 2) c.setBackground(new Color(70, 45, 20));
                else c.setBackground(new Color(26, 26, 58));
                c.setForeground(COLOR_TEXTO);
                return c;
            }
        };
        tablaLeaderboard.setBackground(COLOR_PANEL);
        tablaLeaderboard.setForeground(COLOR_TEXTO);
        tablaLeaderboard.setGridColor(new Color(50, 50, 90));
        tablaLeaderboard.setRowHeight(30);
        tablaLeaderboard.getTableHeader().setBackground(COLOR_PANEL);
        tablaLeaderboard.getTableHeader().setForeground(COLOR_ACENTO);
    }

    private void agregarComponentes() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(13, 71, 161));
        header.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        JLabel lblTitulo = new JLabel("Recompensas y Leaderboard");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setForeground(COLOR_TEXTO);

        JButton btnVolver = new JButton("Menu");
        btnVolver.setBackground(new Color(21, 101, 192));
        btnVolver.setForeground(COLOR_TEXTO);
        btnVolver.setFocusPainted(false);
        btnVolver.setBorderPainted(false);
        btnVolver.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnVolver.addActionListener(e ->
            ventana.mostrarPanel(VentanaPrincipal.MENU));

        JButton btnRefrescar = new JButton("Actualizar");
        btnRefrescar.setBackground(new Color(0, 131, 143));
        btnRefrescar.setForeground(COLOR_TEXTO);
        btnRefrescar.setFocusPainted(false);
        btnRefrescar.setBorderPainted(false);
        btnRefrescar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnRefrescar.addActionListener(e -> actualizar());

        JPanel panelHeaderBtns = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelHeaderBtns.setBackground(new Color(13, 71, 161));
        panelHeaderBtns.add(btnRefrescar);
        panelHeaderBtns.add(btnVolver);

        header.add(lblTitulo, BorderLayout.WEST);
        header.add(panelHeaderBtns, BorderLayout.EAST);

        JPanel panelIzq = new JPanel(new BorderLayout());
        panelIzq.setBackground(COLOR_FONDO);
        panelIzq.setPreferredSize(new Dimension(420, 0));

        JPanel panelXP = new JPanel();
        panelXP.setLayout(new BoxLayout(panelXP, BoxLayout.Y_AXIS));
        panelXP.setBackground(COLOR_PANEL);
        panelXP.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        lblNivel.setAlignmentX(Component.LEFT_ALIGNMENT);
        lblRango.setAlignmentX(Component.LEFT_ALIGNMENT);
        lblXP.setAlignmentX(Component.LEFT_ALIGNMENT);
        progressBar.setAlignmentX(Component.LEFT_ALIGNMENT);

        panelXP.add(lblNivel);
        panelXP.add(Box.createVerticalStrut(8));
        panelXP.add(lblRango);
        panelXP.add(Box.createVerticalStrut(5));
        panelXP.add(lblXP);
        panelXP.add(Box.createVerticalStrut(10));
        panelXP.add(progressBar);

        JLabel lblLogros = new JLabel("Logros");
        lblLogros.setForeground(COLOR_ACENTO);
        lblLogros.setFont(new Font("Arial", Font.BOLD, 14));
        lblLogros.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 0));

        panelIzq.add(panelXP, BorderLayout.NORTH);
        panelIzq.add(lblLogros, BorderLayout.CENTER);
        panelIzq.add(new JScrollPane(panelLogros), BorderLayout.SOUTH);

        JPanel panelDer = new JPanel(new BorderLayout());
        panelDer.setBackground(COLOR_PANEL);

        JPanel panelPodio = new JPanel(new GridLayout(1, 3, 10, 0));
        panelPodio.setBackground(COLOR_PANEL);
        panelPodio.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelPodio.setPreferredSize(new Dimension(0, 100));

        JPanel lugar1 = crearLugarPodio("1°", COLOR_ORO);
        JPanel lugar2 = crearLugarPodio("2°", COLOR_PLATA);
        JPanel lugar3 = crearLugarPodio("3°", COLOR_BRONCE);

        panelPodio.add(lugar1);
        panelPodio.add(lugar2);
        panelPodio.add(lugar3);

        JLabel lblLeaderboard = new JLabel("Top 10 — Leaderboard");
        lblLeaderboard.setForeground(COLOR_ACENTO);
        lblLeaderboard.setFont(new Font("Arial", Font.BOLD, 14));
        lblLeaderboard.setBorder(
            BorderFactory.createEmptyBorder(8, 8, 4, 0));

        panelDer.add(panelPodio, BorderLayout.NORTH);
        panelDer.add(lblLeaderboard, BorderLayout.CENTER);
        panelDer.add(new JScrollPane(tablaLeaderboard), BorderLayout.SOUTH);

        add(header, BorderLayout.NORTH);
        add(panelIzq, BorderLayout.WEST);
        add(panelDer, BorderLayout.CENTER);
    }

    public void actualizar() {
        Usuario u = ventana.getUsuario();

        lblNivel.setText("Nivel " + u.getNivel() +
            " — " + u.getRango());
        lblRango.setText("Rango: " + u.getRango());
        lblXP.setText("XP: " + u.getXp() +
            " / " + u.getXPSiguienteNivel());
        progressBar.setValue((int)(u.getProgreso() * 100));
        progressBar.setString(u.getXp() + " XP");

        actualizarPanelLogros();

        actualizarLeaderboard();
    }

    private void actualizarPanelLogros() {
        panelLogros.removeAll();
        Usuario u = ventana.getUsuario();
        NodoSimple nodo = u.getLogros().getFrente();

        while (nodo != null) {
            Logro logro = (Logro) nodo.getDato();
            JPanel panelLogro = crearPanelLogro(logro);
            panelLogros.add(panelLogro);
            nodo = nodo.getSiguiente();
        }

        panelLogros.revalidate();
        panelLogros.repaint();
    }

    private JPanel crearPanelLogro(Logro logro) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(logro.isDesbloqueado() ?
            new Color(27, 60, 27) : new Color(40, 40, 70));
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(
                logro.isDesbloqueado() ?
                new Color(76, 175, 80) : new Color(60, 60, 100), 1),
            BorderFactory.createEmptyBorder(8, 8, 8, 8)
        ));

        String icono = logro.isDesbloqueado() ? "Desbloqueado" : "Bloqueado";
        JLabel lblIcono = new JLabel(icono, SwingConstants.CENTER);
        lblIcono.setFont(new Font("Arial", Font.PLAIN, 20));
        lblIcono.setPreferredSize(new Dimension(35, 35));

        JLabel lblNombreLogro = new JLabel(logro.getNombre());
        lblNombreLogro.setForeground(logro.isDesbloqueado() ?
            new Color(129, 199, 132) : new Color(150, 150, 180));
        lblNombreLogro.setFont(new Font("Arial", Font.BOLD, 11));

        JLabel lblDescLogro = new JLabel(
            "<html>" + logro.getDescripcion() + "</html>");
        lblDescLogro.setForeground(new Color(180, 180, 210));
        lblDescLogro.setFont(new Font("Arial", Font.PLAIN, 10));

        JPanel panelTexto = new JPanel(new GridLayout(2, 1));
        panelTexto.setOpaque(false);
        panelTexto.add(lblNombreLogro);
        panelTexto.add(lblDescLogro);

        panel.add(lblIcono, BorderLayout.WEST);
        panel.add(panelTexto, BorderLayout.CENTER);

        return panel;
    }

    private JPanel crearLugarPodio(String titulo, Color color) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(COLOR_PANEL);
        panel.setBorder(BorderFactory.createLineBorder(color, 2));

        JLabel lblTitulo = new JLabel(titulo, SwingConstants.CENTER);
        lblTitulo.setForeground(color);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel lblUsuario = new JLabel("---", SwingConstants.CENTER);
        lblUsuario.setForeground(COLOR_TEXTO);
        lblUsuario.setFont(new Font("Arial", Font.PLAIN, 12));

        panel.add(lblTitulo, BorderLayout.NORTH);
        panel.add(lblUsuario, BorderLayout.CENTER);

        return panel;
    }

    private void actualizarLeaderboard() {
        Usuario u = ventana.getUsuario();
        boolean existe = false;
        for (int i = 0; i < totalUsuarios; i++) {
            if (leaderboard[i].getNombre().equals(u.getNombre())) {
                leaderboard[i] = u;
                existe = true;
                break;
            }
        }
        if (!existe && totalUsuarios < 10) {
            leaderboard[totalUsuarios] = u;
            totalUsuarios++;
        }

        for (int i = 1; i < totalUsuarios; i++) {
            Usuario key = leaderboard[i];
            int j = i - 1;
            while (j >= 0 && leaderboard[j].getXp() < key.getXp()) {
                leaderboard[j + 1] = leaderboard[j];
                j--;
            }
            leaderboard[j + 1] = key;
        }

        modeloLeaderboard.setRowCount(0);
        for (int i = 0; i < totalUsuarios; i++) {
            modeloLeaderboard.addRow(new Object[]{
                "#" + (i + 1),
                leaderboard[i].getNombre(),
                leaderboard[i].getXp() + " XP",
                "Nivel " + leaderboard[i].getNivel(),
                leaderboard[i].getRango()
            });
        }
    }

    private void cargarLeaderboard() {
        try {
            File archivo = new File("leaderboard.txt");
            if (!archivo.exists()) {
                actualizar();
                return;
            }
            Scanner sc = new Scanner(archivo);
            while (sc.hasNextLine() && totalUsuarios < 10) {
                String linea = sc.nextLine().trim();
                if (!linea.isEmpty()) {
                    String[] p = linea.split("\\|");
                    if (p.length == 4) {
                        Usuario u = new Usuario(p[0], p[1]);
                        u.setXp(Integer.parseInt(p[2]));
                        u.setNivel(Integer.parseInt(p[3]));
                        leaderboard[totalUsuarios] = u;
                        totalUsuarios++;
                    }
                }
            }
            sc.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        actualizar();
    }

    public void guardarDatos() {
        try (PrintWriter pw = new PrintWriter(
                new FileWriter("leaderboard.txt"))) {
            for (int i = 0; i < totalUsuarios; i++) {
                pw.println(leaderboard[i].toString());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
