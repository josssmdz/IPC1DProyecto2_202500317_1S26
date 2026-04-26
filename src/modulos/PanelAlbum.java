package modulos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;
import modelos.*;
import estructuras.*;

// Panel del álbum de cartas coleccionables. Módulo 2 — Malla ortogonal, visualización y gestión del álbum

public class PanelAlbum extends JPanel {

    private VentanaPrincipal ventana;
    private MallaOrtogonal malla;
    private static final int FILAS = 4;
    private static final int COLUMNAS = 6;
    private NodoMatriz nodoSeleccionado;
    private int filaSeleccionada = -1;
    private int colSeleccionada = -1;

    private JPanel panelMalla;
    private JPanel panelDetalles;
    private JTextField txtBuscar;
    private JLabel lblNombre;
    private JLabel lblTipo;
    private JLabel lblRareza;
    private JLabel lblAtaque;
    private JLabel lblDefensa;
    private JLabel lblPs;
    private JButton btnAgregar;
    private JButton btnIntercambiar;
    private JButton btnBuscar;

    private static final Color COLOR_FONDO = new Color(15, 15, 35);
    private static final Color COLOR_PANEL = new Color(26, 26, 58);
    private static final Color COLOR_BOTON = new Color(26, 115, 232);
    private static final Color COLOR_TEXTO = Color.WHITE;
    private static final Color COLOR_ACENTO = new Color(144, 202, 249);
    private static final Color COLOR_VACIA = new Color(40, 40, 70);

    public PanelAlbum(VentanaPrincipal ventana) {
        this.ventana = ventana;
        this.malla = new MallaOrtogonal(FILAS, COLUMNAS);
        setLayout(new BorderLayout());
        setBackground(COLOR_FONDO);
        inicializarComponentes();
        agregarComponentes();
        agregarEventos();
        cargarAlbum();
    }

    private void inicializarComponentes() {
        panelMalla = new JPanel(new GridLayout(FILAS, COLUMNAS, 6, 6));
        panelMalla.setBackground(COLOR_FONDO);
        panelMalla.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        txtBuscar = new JTextField();
        txtBuscar.setBackground(new Color(40, 40, 80));
        txtBuscar.setForeground(COLOR_TEXTO);
        txtBuscar.setCaretColor(COLOR_TEXTO);

        lblNombre = crearLabel("Selecciona una carta");
        lblTipo = crearLabel("");
        lblRareza = crearLabel("");
        lblAtaque = crearLabel("");
        lblDefensa = crearLabel("");
        lblPs = crearLabel("");

        btnAgregar = crearBoton("Agregar Carta", COLOR_BOTON);
        btnIntercambiar = crearBoton("Intercambiar", new Color(123, 31, 162));
        btnBuscar = crearBoton("Buscar", new Color(0, 131, 143));
    }

    private void agregarComponentes() {
        // ---- HEADER ----
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(13, 71, 161));
        header.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        JLabel lblTitulo = new JLabel("Álbum de Cartas Coleccionables");
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

        JScrollPane scrollMalla = new JScrollPane(panelMalla);
        scrollMalla.setBackground(COLOR_FONDO);
        scrollMalla.getViewport().setBackground(COLOR_FONDO);

        panelDetalles = new JPanel();
        panelDetalles.setLayout(new BoxLayout(panelDetalles, BoxLayout.Y_AXIS));
        panelDetalles.setBackground(COLOR_PANEL);
        panelDetalles.setPreferredSize(new Dimension(220, 0));
        panelDetalles.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));

        JLabel lblTituloDetalles = new JLabel("Detalles");
        lblTituloDetalles.setForeground(COLOR_ACENTO);
        lblTituloDetalles.setFont(new Font("Arial", Font.BOLD, 16));
        lblTituloDetalles.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblBuscarTxt = new JLabel("Buscar carta:");
        lblBuscarTxt.setForeground(COLOR_TEXTO);
        lblBuscarTxt.setAlignmentX(Component.LEFT_ALIGNMENT);

        txtBuscar.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        btnBuscar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnAgregar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnIntercambiar.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblNombre.setAlignmentX(Component.LEFT_ALIGNMENT);
        lblTipo.setAlignmentX(Component.LEFT_ALIGNMENT);
        lblRareza.setAlignmentX(Component.LEFT_ALIGNMENT);
        lblAtaque.setAlignmentX(Component.LEFT_ALIGNMENT);
        lblDefensa.setAlignmentX(Component.LEFT_ALIGNMENT);
        lblPs.setAlignmentX(Component.LEFT_ALIGNMENT);

        panelDetalles.add(lblTituloDetalles);
        panelDetalles.add(Box.createVerticalStrut(15));
        panelDetalles.add(lblBuscarTxt);
        panelDetalles.add(Box.createVerticalStrut(5));
        panelDetalles.add(txtBuscar);
        panelDetalles.add(Box.createVerticalStrut(8));
        panelDetalles.add(btnBuscar);
        panelDetalles.add(Box.createVerticalStrut(20));
        panelDetalles.add(btnAgregar);
        panelDetalles.add(Box.createVerticalStrut(8));
        panelDetalles.add(btnIntercambiar);
        panelDetalles.add(Box.createVerticalStrut(20));
        panelDetalles.add(new JSeparator());
        panelDetalles.add(Box.createVerticalStrut(10));
        panelDetalles.add(lblNombre);
        panelDetalles.add(Box.createVerticalStrut(5));
        panelDetalles.add(lblTipo);
        panelDetalles.add(Box.createVerticalStrut(5));
        panelDetalles.add(lblRareza);
        panelDetalles.add(Box.createVerticalStrut(5));
        panelDetalles.add(lblAtaque);
        panelDetalles.add(Box.createVerticalStrut(5));
        panelDetalles.add(lblDefensa);
        panelDetalles.add(Box.createVerticalStrut(5));
        panelDetalles.add(lblPs);

        add(header, BorderLayout.NORTH);
        add(scrollMalla, BorderLayout.CENTER);
        add(panelDetalles, BorderLayout.EAST);
    }

    private void agregarEventos() {
        btnAgregar.addActionListener(e -> mostrarDialogoAgregarCarta());
        btnIntercambiar.addActionListener(e -> intercambiarCartas());
        btnBuscar.addActionListener(e -> buscarCarta());
    }

    private void renderizarMalla() {
        panelMalla.removeAll();
        NodoMatriz fila = malla.getOrigen();
        int f = 0;
        while (fila != null) {
            NodoMatriz nodo = fila;
            int c = 0;
            while (nodo != null) {
                final int fi = f;
                final int ci = c;
                final NodoMatriz nodoActual = nodo;
                panelMalla.add(crearCelda(nodoActual, fi, ci));
                nodo = nodo.getDerecha();
                c++;
            }
            fila = fila.getAbajo();
            f++;
        }
        panelMalla.revalidate();
        panelMalla.repaint();
    }

    private JPanel crearCelda(NodoMatriz nodo, int fila, int col) {
        JPanel celda = new JPanel(new BorderLayout());
        celda.setPreferredSize(new Dimension(110, 130));
        celda.setCursor(new Cursor(Cursor.HAND_CURSOR));

        if (nodo.getDato() == null) {
            celda.setBackground(COLOR_VACIA);
            celda.setBorder(BorderFactory.createLineBorder(
                new Color(60, 60, 100), 1));
            JLabel lblVacia = new JLabel("Vacía", SwingConstants.CENTER);
            lblVacia.setForeground(new Color(100, 100, 140));
            lblVacia.setFont(new Font("Arial", Font.ITALIC, 11));
            celda.add(lblVacia, BorderLayout.CENTER);
        } else {
            Carta carta = nodo.getDato();
            celda.setBackground(getColorTipo(carta.getTipo()));
            celda.setBorder(BorderFactory.createLineBorder(
                carta.esLegendaria() ?
                new Color(255, 215, 0) : new Color(80, 80, 120), 2));

            JLabel lblNombreCarta = new JLabel(
                "<html><center>" + carta.getNombre() +
                "</center></html>", SwingConstants.CENTER);
            lblNombreCarta.setForeground(COLOR_TEXTO);
            lblNombreCarta.setFont(new Font("Arial", Font.BOLD, 10));

            JLabel lblTipoCarta = new JLabel(
                carta.getTipo(), SwingConstants.CENTER);
            lblTipoCarta.setForeground(new Color(220, 220, 255));
            lblTipoCarta.setFont(new Font("Arial", Font.PLAIN, 9));

            JLabel lblRarezaCarta = new JLabel(
                carta.getRareza(), SwingConstants.CENTER);
            lblRarezaCarta.setForeground(carta.esLegendaria() ?
                new Color(255, 215, 0) : new Color(180, 180, 220));
            lblRarezaCarta.setFont(new Font("Arial", Font.ITALIC, 9));

            JPanel panelInfo = new JPanel(new GridLayout(3, 1));
            panelInfo.setOpaque(false);
            panelInfo.add(lblNombreCarta);
            panelInfo.add(lblTipoCarta);
            panelInfo.add(lblRarezaCarta);

            celda.add(panelInfo, BorderLayout.CENTER);
        }

        celda.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                seleccionarCelda(nodo, fila, col, celda);
            }
        });

        return celda;
    }

    private void seleccionarCelda(NodoMatriz nodo, int fila,
                                   int col, JPanel celda) {
        nodoSeleccionado = nodo;
        filaSeleccionada = fila;
        colSeleccionada = col;

        if (nodo.getDato() != null) {
            Carta c = nodo.getDato();
            lblNombre.setText(c.getNombre());
            lblTipo.setText("Tipo: " + c.getTipo());
            lblRareza.setText("Rareza: " + c.getRareza());
            lblAtaque.setText("ATQ: " + c.getAtaque());
            lblDefensa.setText("DEF: " + c.getDefensa());
            lblPs.setText("PS: " + c.getPs());
        } else {
            lblNombre.setText("Celda vacía");
            lblTipo.setText("");
            lblRareza.setText("");
            lblAtaque.setText("");
            lblDefensa.setText("");
            lblPs.setText("");
        }
    }

    private Color getColorTipo(String tipo) {
        switch (tipo) {
            case "Fuego":     return new Color(183, 28, 28);
            case "Agua":      return new Color(13, 71, 161);
            case "Planta":    return new Color(27, 94, 32);
            case "Eléctrico": return new Color(245, 127, 23);
            case "Psíquico":  return new Color(136, 14, 79);
            case "Oscuro":    return new Color(38, 50, 56);
            case "Acero":     return new Color(69, 90, 100);
            default:          return new Color(55, 71, 79);
        }
    }

    private void mostrarDialogoAgregarCarta() {
        JDialog dialogo = new JDialog(
            (Frame) SwingUtilities.getWindowAncestor(this),
            "Agregar Carta", true);
        dialogo.setSize(350, 420);
        dialogo.setLocationRelativeTo(this);
        dialogo.setLayout(new BorderLayout());
        dialogo.getContentPane().setBackground(COLOR_PANEL);

        JPanel panelForm = new JPanel(new GridLayout(9, 2, 8, 8));
        panelForm.setBackground(COLOR_PANEL);
        panelForm.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JTextField[] campos = new JTextField[7];
        String[] etiquetas = {"Código:", "Nombre:", "Tipo:", "Rareza:",
                              "Ataque:", "Defensa:", "PS:"};

        for (int i = 0; i < etiquetas.length; i++) {
            JLabel lbl = new JLabel(etiquetas[i]);
            lbl.setForeground(COLOR_TEXTO);
            campos[i] = new JTextField();
            campos[i].setBackground(new Color(40, 40, 80));
            campos[i].setForeground(COLOR_TEXTO);
            campos[i].setCaretColor(COLOR_TEXTO);
            panelForm.add(lbl);
            panelForm.add(campos[i]);
        }

        JButton btnConfirmar = crearBoton("Agregar", COLOR_BOTON);
        btnConfirmar.addActionListener(e -> {
            try {
                String codigo = campos[0].getText().trim();
                String nombre = campos[1].getText().trim();
                String tipo = campos[2].getText().trim();
                String rareza = campos[3].getText().trim();
                int ataque = Integer.parseInt(campos[4].getText().trim());
                int defensa = Integer.parseInt(campos[5].getText().trim());
                int ps = Integer.parseInt(campos[6].getText().trim());

                if (codigo.isEmpty() || nombre.isEmpty() ||
                    tipo.isEmpty() || rareza.isEmpty()) {
                    JOptionPane.showMessageDialog(dialogo,
                        "Todos los campos son obligatorios",
                        "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Carta carta = new Carta(codigo, nombre, tipo,
                                        rareza, ataque, defensa, ps, "");
                if (malla.insertarCarta(carta)) {
                    int xp = 0;
                    if (carta.esLegendaria()) {
                        ventana.getUsuario().otorgarXP(200);
                        xp = 200;
                    }

                    for (int f = 0; f < FILAS; f++) {
                        if (malla.filaCompleta(f)) {
                            ventana.getUsuario().otorgarXP(100);
                            xp += 100;
                        }
                    }

                    Logro logro = ventana.getUsuario().verificarLogros();
                    if (logro != null) {
                        JOptionPane.showMessageDialog(this,
                            "¡Logro desbloqueado!\n\n" +
                            logro.getNombre() + "\n" +
                            logro.getDescripcion(),
                            "Nuevo logro",
                            JOptionPane.INFORMATION_MESSAGE);
                    }

                    renderizarMalla();
                    dialogo.dispose();
                    JOptionPane.showMessageDialog(this,
                        "Carta agregada" +
                        (xp > 0 ? " — +" + xp + " XP" : ""),
                        "Éxito", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(dialogo,
                        "El álbum está lleno",
                        "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialogo,
                    "Ataque, Defensa y PS deben ser números enteros",
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        dialogo.add(panelForm, BorderLayout.CENTER);
        dialogo.add(btnConfirmar, BorderLayout.SOUTH);
        dialogo.setVisible(true);
    }

    private void intercambiarCartas() {
        if (filaSeleccionada < 0) {
            JOptionPane.showMessageDialog(this,
                "Primero selecciona la primera celda",
                "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String input = JOptionPane.showInputDialog(this,
            "Ingresa la posición de la segunda celda (fila,columna)\n" +
            "Usa numeración desde 1.\n" +
            "Ejemplo: 1,3");
        if (input == null || input.trim().isEmpty()) return;

        try {
            String[] partes = input.split(",");
            int f2 = Integer.parseInt(partes[0].trim()) - 1;
            int c2 = Integer.parseInt(partes[1].trim()) - 1;

            if (f2 < 0 || f2 >= FILAS || c2 < 0 || c2 >= COLUMNAS) {
                JOptionPane.showMessageDialog(this,
                    "Posición fuera del rango del álbum",
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            malla.intercambiar(filaSeleccionada, colSeleccionada, f2, c2);
            renderizarMalla();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                "Formato inválido. Usa fila,columna con numeración desde 1 (ej: 1,3)",
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void buscarCarta() {
        String criterio = txtBuscar.getText().trim();
        if (criterio.isEmpty()) {
            renderizarMalla();
            return;
        }

        panelMalla.removeAll();
        NodoMatriz fila = malla.getOrigen();
        int f = 0;
        while (fila != null) {
            NodoMatriz nodo = fila;
            int c = 0;
            while (nodo != null) {
                final int fi = f;
                final int ci = c;
                final NodoMatriz nodoActual = nodo;
                JPanel celda = crearCelda(nodoActual, fi, ci);

                if (nodoActual.getDato() != null) {
                    Carta carta = nodoActual.getDato();
                    if (carta.getNombre().toLowerCase()
                            .contains(criterio.toLowerCase()) ||
                        carta.getTipo().equalsIgnoreCase(criterio) ||
                        carta.getRareza().equalsIgnoreCase(criterio)) {
                        celda.setBorder(BorderFactory.createLineBorder(
                            new Color(255, 235, 59), 3));
                    }
                }

                panelMalla.add(celda);
                nodo = nodo.getDerecha();
                c++;
            }
            fila = fila.getAbajo();
            f++;
        }
        panelMalla.revalidate();
        panelMalla.repaint();
    }

    private void cargarAlbum() {
        try {
            File archivo = new File("album.txt");
            if (!archivo.exists()) {
                renderizarMalla();
                return;
            }
            Scanner sc = new Scanner(archivo);
            while (sc.hasNextLine()) {
                String linea = sc.nextLine().trim();
                if (!linea.isEmpty()) {
                    String[] p = linea.split("\\|");
                    if (p.length == 8) {
                        Carta carta = new Carta(p[0], p[1], p[2], p[3],
                            Integer.parseInt(p[4]),
                            Integer.parseInt(p[5]),
                            Integer.parseInt(p[6]), p[7]);
                        malla.insertarCarta(carta);
                    }
                }
            }
            sc.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                "Error al cargar álbum: " + ex.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
        renderizarMalla();
    }

    public void guardarDatos() {
        try (PrintWriter pw = new PrintWriter(new FileWriter("album.txt"))) {
            NodoMatriz fila = malla.getOrigen();
            while (fila != null) {
                NodoMatriz nodo = fila;
                while (nodo != null) {
                    if (nodo.getDato() != null) {
                        pw.println(nodo.getDato().toString());
                    }
                    nodo = nodo.getDerecha();
                }
                fila = fila.getAbajo();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private JLabel crearLabel(String texto) {
        JLabel lbl = new JLabel(texto);
        lbl.setForeground(COLOR_TEXTO);
        lbl.setFont(new Font("Arial", Font.PLAIN, 12));
        return lbl;
    }

    private JButton crearBoton(String texto, Color color) {
        JButton btn = new JButton(texto);
        btn.setBackground(color);
        btn.setForeground(COLOR_TEXTO);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 36));
        return btn;
    }

    public MallaOrtogonal getMalla() {
        return malla;
    }
}
