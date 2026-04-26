package modulos;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;
import modelos.*;
import estructuras.*;

///Panel de eventos especiales y torneos. Módulo 3 — Cola, threads concurrentes y venta de tickets
///
public class PanelTorneos extends JPanel {

    private VentanaPrincipal ventana;
    private ListaSimple torneos;
    private Cola colaEspera;
    private ListaSimple ticketsVendidos;
    private Torneo torneoActual;
    private boolean ventaActiva;

    private JTable tablaTorneos;
    private DefaultTableModel modeloTorneos;
    private JTextArea txtLogTaquillas;
    private JLabel lblTaquilla1;
    private JLabel lblTaquilla2;
    private JLabel lblColaRestante;
    private JButton btnInscribirse;
    private JButton btnIniciarVenta;
    private JButton btnVolver;
    private static final Color COLOR_FONDO = new Color(15, 15, 35);
    private static final Color COLOR_PANEL = new Color(26, 26, 58);
    private static final Color COLOR_BOTON = new Color(26, 115, 232);
    private static final Color COLOR_TEXTO = Color.WHITE;
    private static final Color COLOR_ACENTO = new Color(144, 202, 249);

    public PanelTorneos(VentanaPrincipal ventana) {
        this.ventana = ventana;
        this.torneos = new ListaSimple();
        this.colaEspera = new Cola();
        this.ticketsVendidos = new ListaSimple();
        this.ventaActiva = false;
        setLayout(new BorderLayout());
        setBackground(COLOR_FONDO);
        inicializarComponentes();
        agregarComponentes();
        agregarEventos();
        cargarTorneos();
        cargarTickets();
    }

    private void inicializarComponentes() {
        String[] cols = {"ID", "Nombre", "Juego", "Fecha",
                         "Hora", "Precio", "Tickets"};
        modeloTorneos = new DefaultTableModel(cols, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaTorneos = new JTable(modeloTorneos);
        tablaTorneos.setBackground(new Color(26, 26, 58));
        tablaTorneos.setForeground(COLOR_TEXTO);
        tablaTorneos.setGridColor(new Color(50, 50, 90));
        tablaTorneos.setSelectionBackground(new Color(21, 101, 192));
        tablaTorneos.getTableHeader().setBackground(COLOR_PANEL);
        tablaTorneos.getTableHeader().setForeground(COLOR_ACENTO);
        tablaTorneos.setRowHeight(28);

        // Log de taquillas
        txtLogTaquillas = new JTextArea();
        txtLogTaquillas.setBackground(new Color(10, 10, 25));
        txtLogTaquillas.setForeground(new Color(129, 199, 132));
        txtLogTaquillas.setFont(new Font("Monospaced", Font.PLAIN, 12));
        txtLogTaquillas.setEditable(false);

        // Labels de estado
        lblTaquilla1 = new JLabel("Taquilla 1: Libre");
        lblTaquilla1.setForeground(new Color(129, 199, 132));
        lblTaquilla1.setFont(new Font("Arial", Font.BOLD, 13));

        lblTaquilla2 = new JLabel("Taquilla 2: Libre");
        lblTaquilla2.setForeground(new Color(129, 199, 132));
        lblTaquilla2.setFont(new Font("Arial", Font.BOLD, 13));

        lblColaRestante = new JLabel("Cola: 0 personas");
        lblColaRestante.setForeground(COLOR_ACENTO);
        lblColaRestante.setFont(new Font("Arial", Font.BOLD, 13));

        btnInscribirse = crearBoton("+ Inscribirse", COLOR_BOTON);
        btnIniciarVenta = crearBoton("Iniciar Venta", 
            new Color(46, 125, 50));
    }

    private void agregarComponentes() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(13, 71, 161));
        header.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        JLabel lblTitulo = new JLabel("Eventos Especiales — Torneos");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setForeground(COLOR_TEXTO);

        btnVolver = new JButton("← Menú");
        btnVolver.setBackground(new Color(21, 101, 192));
        btnVolver.setForeground(COLOR_TEXTO);
        btnVolver.setFocusPainted(false);
        btnVolver.setBorderPainted(false);
        btnVolver.setCursor(new Cursor(Cursor.HAND_CURSOR));

        header.add(lblTitulo, BorderLayout.WEST);
        header.add(btnVolver, BorderLayout.EAST);

        JPanel panelIzq = new JPanel(new BorderLayout());
        panelIzq.setBackground(COLOR_FONDO);
        panelIzq.setPreferredSize(new Dimension(550, 0));

        JLabel lblTorneos = new JLabel("Torneos Disponibles");
        lblTorneos.setForeground(COLOR_ACENTO);
        lblTorneos.setFont(new Font("Arial", Font.BOLD, 14));
        lblTorneos.setBorder(BorderFactory.createEmptyBorder(8, 8, 4, 0));

        JPanel panelBotonesTorneos = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelBotonesTorneos.setBackground(COLOR_PANEL);
        panelBotonesTorneos.add(btnInscribirse);
        panelBotonesTorneos.add(btnIniciarVenta);

        panelIzq.add(lblTorneos, BorderLayout.NORTH);
        panelIzq.add(new JScrollPane(tablaTorneos), BorderLayout.CENTER);
        panelIzq.add(panelBotonesTorneos, BorderLayout.SOUTH);

        JPanel panelDer = new JPanel(new BorderLayout());
        panelDer.setBackground(COLOR_PANEL);
        panelDer.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel lblTituloTaquillas = new JLabel("Estado de Taquillas");
        lblTituloTaquillas.setForeground(COLOR_ACENTO);
        lblTituloTaquillas.setFont(new Font("Arial", Font.BOLD, 14));

        JPanel panelEstado = new JPanel(new GridLayout(3, 1, 5, 5));
        panelEstado.setBackground(COLOR_PANEL);
        panelEstado.add(lblTaquilla1);
        panelEstado.add(lblTaquilla2);
        panelEstado.add(lblColaRestante);

        JLabel lblLog = new JLabel("Log de Tickets Vendidos");
        lblLog.setForeground(COLOR_ACENTO);
        lblLog.setFont(new Font("Arial", Font.BOLD, 13));
        lblLog.setBorder(BorderFactory.createEmptyBorder(8, 0, 4, 0));

        panelDer.add(lblTituloTaquillas, BorderLayout.NORTH);
        panelDer.add(panelEstado, BorderLayout.CENTER);

        JPanel panelLog = new JPanel(new BorderLayout());
        panelLog.setBackground(COLOR_PANEL);
        panelLog.add(lblLog, BorderLayout.NORTH);
        panelLog.add(new JScrollPane(txtLogTaquillas), BorderLayout.CENTER);

        JPanel panelDerTotal = new JPanel(new BorderLayout());
        panelDerTotal.setBackground(COLOR_PANEL);
        panelDerTotal.add(panelDer, BorderLayout.NORTH);
        panelDerTotal.add(panelLog, BorderLayout.CENTER);

        add(header, BorderLayout.NORTH);
        add(panelIzq, BorderLayout.WEST);
        add(panelDerTotal, BorderLayout.CENTER);
    }

    private void agregarEventos() {
        btnVolver.addActionListener(e -> {
            if (ventaActiva) {
                JOptionPane.showMessageDialog(this,
                    "Hay una venta en proceso, espera que finalice",
                    "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }
            ventana.mostrarPanel(VentanaPrincipal.MENU);
        });

        btnInscribirse.addActionListener(e -> inscribirParticipante());
        btnIniciarVenta.addActionListener(e -> iniciarVenta());
    }

    private void cargarTorneos() {
        try {
            File archivo = new File("torneos.txt");
            if (!archivo.exists()) {
                cargarTorneosDefault();
                return;
            }
            Scanner sc = new Scanner(archivo);
            while (sc.hasNextLine()) {
                String linea = sc.nextLine().trim();
                if (!linea.isEmpty()) {
                    String[] p = linea.split("\\|");
                    if (p.length == 7) {
                        torneos.insertar(new Torneo(p[0], p[1], p[2],
                            p[3], p[4], Double.parseDouble(p[5]),
                            Integer.parseInt(p[6])));
                    }
                }
            }
            sc.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                "Error al cargar torneos: " + ex.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
        actualizarTablaTorneos();
    }

    private void cargarTorneosDefault() {
        torneos.insertar(new Torneo("T001", "Copa Zelda",
            "Zelda: TotK", "28/04/2026", "14:00", 50.0, 10));
        torneos.insertar(new Torneo("T002", "Torneo God of War",
            "God of War", "29/04/2026", "16:00", 75.0, 8));
        torneos.insertar(new Torneo("T003", "Liga Elden Ring",
            "Elden Ring", "30/04/2026", "18:00", 100.0, 6));
        actualizarTablaTorneos();
    }

    private void actualizarTablaTorneos() {
        modeloTorneos.setRowCount(0);
        NodoSimple nodo = torneos.getFrente();
        while (nodo != null) {
            Torneo t = (Torneo) nodo.getDato();
            modeloTorneos.addRow(new Object[]{
                t.getId(), t.getNombre(), t.getJuego(),
                t.getFecha(), t.getHora(),
                "Q" + String.format("%.2f", t.getPrecioTicket()),
                t.getTicketsDisponibles()
            });
            nodo = nodo.getSiguiente();
        }
    }

    private void inscribirParticipante() {
        int fila = tablaTorneos.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(this,
                "Selecciona un torneo primero",
                "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Object[] torneosArr = torneos.toArray();
        torneoActual = (Torneo) torneosArr[fila];

        if (!torneoActual.hayTickets()) {
            JOptionPane.showMessageDialog(this,
                "No hay tickets disponibles para este torneo",
                "Sin tickets", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String nombre = JOptionPane.showInputDialog(this,
            "Ingresa tu nombre para inscribirte:",
            "Inscripción", JOptionPane.PLAIN_MESSAGE);

        if (nombre == null || nombre.trim().isEmpty()) return;

        Participante p = new Participante(
            nombre.trim(), torneoActual.getNombre());
        colaEspera.encolar(p);

        ventana.getUsuario().otorgarXP(150);
        Logro logro = ventana.getUsuario().verificarLogros();
        if (logro != null) {
            JOptionPane.showMessageDialog(this,
                "¡Logro desbloqueado!\n\n" +
                logro.getNombre() + "\n" + logro.getDescripcion(),
                "Nuevo logro", JOptionPane.INFORMATION_MESSAGE);
        }

        lblColaRestante.setText("Cola: " +
            colaEspera.tamanio() + " personas");
        agregarLog(nombre + " inscrito en " +
            torneoActual.getNombre());
    }

    private void iniciarVenta() {
        if (colaEspera.estaVacia()) {
            JOptionPane.showMessageDialog(this,
                "No hay participantes en la cola",
                "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (torneoActual == null) {
            JOptionPane.showMessageDialog(this,
                "Selecciona un torneo primero",
                "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        ventaActiva = true;
        btnIniciarVenta.setEnabled(false);
        btnInscribirse.setEnabled(false);

        Taquilla t1 = new Taquilla(1, colaEspera, torneoActual,
            ticketsVendidos, this);
        Taquilla t2 = new Taquilla(2, colaEspera, torneoActual,
            ticketsVendidos, this);

        t1.start();
        t2.start();

        new Thread(() -> {
            try {
                t1.join();
                t2.join();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            SwingUtilities.invokeLater(() -> {
                ventaActiva = false;
                btnIniciarVenta.setEnabled(true);
                btnInscribirse.setEnabled(true);
                actualizarTablaTorneos();
                agregarLog("— Venta cerrada —");
                JOptionPane.showMessageDialog(PanelTorneos.this,
                    "Venta finalizada. Tickets vendidos: " +
                    ticketsVendidos.getTamanio(),
                    "Venta cerrada",
                    JOptionPane.INFORMATION_MESSAGE);
            });
        }).start();
    }

    public void actualizarEstadoTaquilla(int numero, String estado) {
        SwingUtilities.invokeLater(() -> {
            if (numero == 1) {
                lblTaquilla1.setText("Taquilla 1: " + estado);
                lblTaquilla1.setForeground(estado.equals("Libre") ?
                    new Color(129, 199, 132) : new Color(255, 183, 77));
            } else {
                lblTaquilla2.setText("Taquilla 2: " + estado);
                lblTaquilla2.setForeground(estado.equals("Libre") ?
                    new Color(129, 199, 132) : new Color(255, 183, 77));
            }
            lblColaRestante.setText("Cola: " +
                colaEspera.tamanio() + " personas");
        });
    }

    public void agregarLog(String mensaje) {
        SwingUtilities.invokeLater(() -> {
            txtLogTaquillas.append(mensaje + "\n");
            txtLogTaquillas.setCaretPosition(
                txtLogTaquillas.getDocument().getLength());
        });
    }

    private void cargarTickets() {
        try {
            File archivo = new File("tickets_vendidos.txt");
            if (!archivo.exists()) return;
            Scanner sc = new Scanner(archivo);
            while (sc.hasNextLine()) {
                String linea = sc.nextLine().trim();
                if (!linea.isEmpty()) {
                    String[] p = linea.split("\\|");
                    if (p.length == 3) {
                        Participante part = new Participante(p[0], p[1]);
                        ticketsVendidos.insertar(part);
                    }
                }
            }
            sc.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void guardarDatos() {
        try (PrintWriter pw = new PrintWriter(
                new FileWriter("torneos.txt"))) {
            NodoSimple nodo = torneos.getFrente();
            while (nodo != null) {
                pw.println(nodo.getDato().toString());
                nodo = nodo.getSiguiente();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        try (PrintWriter pw = new PrintWriter(
                new FileWriter("tickets_vendidos.txt"))) {
            NodoSimple nodo = ticketsVendidos.getFrente();
            while (nodo != null) {
                pw.println(nodo.getDato().toString());
                nodo = nodo.getSiguiente();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private JButton crearBoton(String texto, Color color) {
        JButton btn = new JButton(texto);
        btn.setBackground(color);
        btn.setForeground(COLOR_TEXTO);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }
    
    public ListaSimple getTorneos() {
    return torneos;
    }
    
    public ListaSimple getTicketsVendidos() {
    return ticketsVendidos;
    }

}
