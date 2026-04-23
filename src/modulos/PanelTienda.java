package modulos;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;
import modelos.*;
import estructuras.*;

//Panel de la tienda de videojuegos. Módulo 1 — Catálogo, carrito e historial de compras

public class PanelTienda extends JPanel {

    private VentanaPrincipal ventana;
    private ListaSimple catalogo;
    private ListaSimple carrito;
    private ListaSimple historial;
    private int contadorCompras;

    private JTextField txtBuscar;
    private JComboBox<String> cmbGenero;
    private JComboBox<String> cmbPlataforma;
    private JPanel panelTarjetas;
    private JScrollPane scrollCatalogo;

    private JTable tablaCarrito;
    private DefaultTableModel modeloCarrito;
    private JLabel lblTotal;
    private JButton btnConfirmar;
    private JButton btnEliminar;

    private JTable tablaHistorial;
    private DefaultTableModel modeloHistorial;

    private static final Color COLOR_FONDO = new Color(15, 15, 35);
    private static final Color COLOR_PANEL = new Color(26, 26, 58);
    private static final Color COLOR_BOTON = new Color(26, 115, 232);
    private static final Color COLOR_TEXTO = Color.WHITE;
    private static final Color COLOR_ACENTO = new Color(144, 202, 249);

    public PanelTienda(VentanaPrincipal ventana) {
        this.ventana = ventana;
        this.catalogo = new ListaSimple();
        this.carrito = new ListaSimple();
        this.historial = new ListaSimple();
        this.contadorCompras = 0;
        setLayout(new BorderLayout());
        setBackground(COLOR_FONDO);
        inicializarComponentes();
        agregarComponentes();
        agregarEventos();
        cargarCatalogo();
        cargarHistorial();
    }


    private void inicializarComponentes() {
        txtBuscar = new JTextField();
        txtBuscar.setBackground(new Color(40, 40, 80));
        txtBuscar.setForeground(COLOR_TEXTO);
        txtBuscar.setCaretColor(COLOR_TEXTO);

        String[] generos = {"Todos", "Accion", "RPG", "Estrategia",
                            "Deportes", "Terror", "Aventura"};
        String[] plataformas = {"Todas", "PC", "PlayStation",
                                "Xbox", "Nintendo Switch"};

        cmbGenero = new JComboBox<>(generos);
        cmbPlataforma = new JComboBox<>(plataformas);
        cmbGenero.setBackground(new Color(40, 40, 80));
        cmbGenero.setForeground(COLOR_TEXTO);
        cmbPlataforma.setBackground(new Color(40, 40, 80));
        cmbPlataforma.setForeground(COLOR_TEXTO);

        panelTarjetas = new JPanel(new GridLayout(0, 3, 10, 10));
        panelTarjetas.setBackground(COLOR_FONDO);
        scrollCatalogo = new JScrollPane(panelTarjetas);
        scrollCatalogo.setBackground(COLOR_FONDO);
        scrollCatalogo.getViewport().setBackground(COLOR_FONDO);

        String[] colsCarrito = {"Juego", "Precio", "Cantidad", "Subtotal"};
        modeloCarrito = new DefaultTableModel(colsCarrito, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaCarrito = new JTable(modeloCarrito);
        tablaCarrito.setBackground(new Color(26, 26, 58));
        tablaCarrito.setForeground(COLOR_TEXTO);
        tablaCarrito.setGridColor(new Color(50, 50, 90));
        tablaCarrito.getTableHeader().setBackground(COLOR_PANEL);
        tablaCarrito.getTableHeader().setForeground(COLOR_ACENTO);

        lblTotal = new JLabel("Total: Q0.00");
        lblTotal.setForeground(COLOR_ACENTO);
        lblTotal.setFont(new Font("Arial", Font.BOLD, 16));

        btnConfirmar = new JButton("Confirmar Compra");
        btnConfirmar.setBackground(new Color(46, 125, 50));
        btnConfirmar.setForeground(COLOR_TEXTO);
        btnConfirmar.setFocusPainted(false);
        btnConfirmar.setBorderPainted(false);
        btnConfirmar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnEliminar = new JButton("Eliminar Item");
        btnEliminar.setBackground(new Color(183, 28, 28));
        btnEliminar.setForeground(COLOR_TEXTO);
        btnEliminar.setFocusPainted(false);
        btnEliminar.setBorderPainted(false);
        btnEliminar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        String[] colsHistorial = {"ID", "Fecha", "Total"};
        modeloHistorial = new DefaultTableModel(colsHistorial, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaHistorial = new JTable(modeloHistorial);
        tablaHistorial.setBackground(new Color(26, 26, 58));
        tablaHistorial.setForeground(COLOR_TEXTO);
        tablaHistorial.setGridColor(new Color(50, 50, 90));
        tablaHistorial.getTableHeader().setBackground(COLOR_PANEL);
        tablaHistorial.getTableHeader().setForeground(COLOR_ACENTO);
    }

    private void agregarComponentes() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(13, 71, 161));
        header.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        JLabel lblTitulo = new JLabel("Tienda de Videojuegos");
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

        JPanel panelIzq = new JPanel(new BorderLayout());
        panelIzq.setBackground(COLOR_FONDO);
        panelIzq.setPreferredSize(new Dimension(580, 0));

        JPanel panelFiltros = new JPanel(new GridLayout(2, 2, 8, 8));
        panelFiltros.setBackground(COLOR_PANEL);
        panelFiltros.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel lblBuscar = new JLabel("Buscar:");
        lblBuscar.setForeground(COLOR_TEXTO);
        JLabel lblGenero = new JLabel("Género:");
        lblGenero.setForeground(COLOR_TEXTO);
        JLabel lblPlataforma = new JLabel("Plataforma:");
        lblPlataforma.setForeground(COLOR_TEXTO);

        panelFiltros.add(lblBuscar);
        panelFiltros.add(txtBuscar);
        panelFiltros.add(lblGenero);
        panelFiltros.add(cmbGenero);

        JPanel panelFiltros2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelFiltros2.setBackground(COLOR_PANEL);
        panelFiltros2.add(lblPlataforma);
        panelFiltros2.add(cmbPlataforma);

        JPanel panelTopIzq = new JPanel(new BorderLayout());
        panelTopIzq.setBackground(COLOR_PANEL);
        panelTopIzq.add(panelFiltros, BorderLayout.CENTER);
        panelTopIzq.add(panelFiltros2, BorderLayout.SOUTH);

        panelIzq.add(panelTopIzq, BorderLayout.NORTH);
        panelIzq.add(scrollCatalogo, BorderLayout.CENTER);

        JPanel panelDer = new JPanel(new BorderLayout());
        panelDer.setBackground(COLOR_FONDO);

        JLabel lblCarrito = new JLabel("Carrito");
        lblCarrito.setForeground(COLOR_ACENTO);
        lblCarrito.setFont(new Font("Arial", Font.BOLD, 14));
        lblCarrito.setBorder(BorderFactory.createEmptyBorder(8, 8, 4, 0));

        JPanel panelBotonesCarrito = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBotonesCarrito.setBackground(COLOR_PANEL);
        panelBotonesCarrito.add(lblTotal);
        panelBotonesCarrito.add(btnEliminar);
        panelBotonesCarrito.add(btnConfirmar);

        JPanel panelCarrito = new JPanel(new BorderLayout());
        panelCarrito.setBackground(COLOR_PANEL);
        panelCarrito.add(lblCarrito, BorderLayout.NORTH);
        panelCarrito.add(new JScrollPane(tablaCarrito), BorderLayout.CENTER);
        panelCarrito.add(panelBotonesCarrito, BorderLayout.SOUTH);
        panelCarrito.setPreferredSize(new Dimension(0, 300));

        JLabel lblHistorial = new JLabel("Historial de Compras");
        lblHistorial.setForeground(COLOR_ACENTO);
        lblHistorial.setFont(new Font("Arial", Font.BOLD, 14));
        lblHistorial.setBorder(BorderFactory.createEmptyBorder(8, 8, 4, 0));

        JPanel panelHistorial = new JPanel(new BorderLayout());
        panelHistorial.setBackground(COLOR_PANEL);
        panelHistorial.add(lblHistorial, BorderLayout.NORTH);
        panelHistorial.add(new JScrollPane(tablaHistorial), BorderLayout.CENTER);

        panelDer.add(panelCarrito, BorderLayout.NORTH);
        panelDer.add(panelHistorial, BorderLayout.CENTER);

        add(header, BorderLayout.NORTH);
        add(panelIzq, BorderLayout.WEST);
        add(panelDer, BorderLayout.CENTER);
    }

    private void agregarEventos() {
        txtBuscar.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                filtrarCatalogo();
            }
        });

        cmbGenero.addActionListener(e -> filtrarCatalogo());
        cmbPlataforma.addActionListener(e -> filtrarCatalogo());

        btnConfirmar.addActionListener(e -> confirmarCompra());
        btnEliminar.addActionListener(e -> eliminarDelCarrito());
    }

    private void cargarCatalogo() {
        try {
            File archivo = new File("catalogo.txt");
            if (!archivo.exists()) {
                cargarCatalogoDefault();
                return;
            }
            Scanner sc = new Scanner(archivo);
            while (sc.hasNextLine()) {
                String linea = sc.nextLine().trim();
                if (!linea.isEmpty()) {
                    String[] partes = linea.split("\\|");
                    if (partes.length == 7) {
                        Juego j = new Juego(
                            partes[0], partes[1], partes[2],
                            partes[4], Double.parseDouble(partes[3]),
                            Integer.parseInt(partes[5]), partes[6]
                        );
                        catalogo.insertar(j);
                    }
                }
            }
            sc.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                "Error al cargar catálogo: " + ex.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
        mostrarTarjetas(catalogo);
    }

    private void cargarCatalogoDefault() {
        catalogo.insertar(new Juego("J001", "Zelda: Tears of the Kingdom",
            "Aventura", "Nintendo Switch", 350.00, 10,
            "Explora Hyrule en una nueva aventura épica"));
        catalogo.insertar(new Juego("J002", "God of War Ragnarok",
            "Acción", "PlayStation", 420.00, 8,
            "La batalla épica de Kratos continúa"));
        catalogo.insertar(new Juego("J003", "Elden Ring",
            "RPG", "PC", 380.00, 15,
            "Un mundo abierto lleno de desafíos"));
        catalogo.insertar(new Juego("J004", "FIFA 25",
            "Deportes", "PlayStation", 300.00, 20,
            "El fútbol más realista de la saga"));
        catalogo.insertar(new Juego("J005", "Halo Infinite",
            "Acción", "Xbox", 290.00, 12,
            "El regreso del Jefe Maestro"));
        catalogo.insertar(new Juego("J006", "Resident Evil 4",
            "Terror", "PC", 310.00, 7,
            "El remake del clásico survival horror"));
        mostrarTarjetas(catalogo);
    }

    private void mostrarTarjetas(ListaSimple lista) {
        panelTarjetas.removeAll();
        NodoSimple nodo = lista.getFrente();
        while (nodo != null) {
            Juego j = (Juego) nodo.getDato();
            panelTarjetas.add(crearTarjeta(j));
            nodo = nodo.getSiguiente();
        }
        panelTarjetas.revalidate();
        panelTarjetas.repaint();
    }

    private JPanel crearTarjeta(Juego juego) {
        JPanel tarjeta = new JPanel(new BorderLayout());
        tarjeta.setBackground(COLOR_PANEL);
        tarjeta.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(50, 50, 90), 1),
            BorderFactory.createEmptyBorder(8, 8, 8, 8)
        ));

        JLabel lblNombre = new JLabel(juego.getNombre());
        lblNombre.setForeground(COLOR_TEXTO);
        lblNombre.setFont(new Font("Arial", Font.BOLD, 12));

        JLabel lblGenero = new JLabel(juego.getGenero() +
            " | " + juego.getPlataforma());
        lblGenero.setForeground(COLOR_ACENTO);
        lblGenero.setFont(new Font("Arial", Font.PLAIN, 11));

        JLabel lblPrecio = new JLabel("Q" +
            String.format("%.2f", juego.getPrecio()));
        lblPrecio.setForeground(new Color(129, 199, 132));
        lblPrecio.setFont(new Font("Arial", Font.BOLD, 13));

        JLabel lblStock = new JLabel("Stock: " + juego.getStock());
        lblStock.setForeground(juego.getStock() > 0 ?
            new Color(129, 199, 132) : new Color(229, 115, 115));
        lblStock.setFont(new Font("Arial", Font.PLAIN, 11));

        JButton btnAgregar = new JButton("+ Agregar");
        btnAgregar.setBackground(COLOR_BOTON);
        btnAgregar.setForeground(COLOR_TEXTO);
        btnAgregar.setFocusPainted(false);
        btnAgregar.setBorderPainted(false);
        btnAgregar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnAgregar.addActionListener(e -> agregarAlCarrito(juego));

        JPanel panelInfo = new JPanel(new GridLayout(4, 1, 2, 2));
        panelInfo.setBackground(COLOR_PANEL);
        panelInfo.add(lblNombre);
        panelInfo.add(lblGenero);
        panelInfo.add(lblPrecio);
        panelInfo.add(lblStock);

        tarjeta.add(panelInfo, BorderLayout.CENTER);
        tarjeta.add(btnAgregar, BorderLayout.SOUTH);

        return tarjeta;
    }

    private void filtrarCatalogo() {
        String busqueda = txtBuscar.getText().toLowerCase();
        String genero = (String) cmbGenero.getSelectedItem();
        String plataforma = (String) cmbPlataforma.getSelectedItem();

        ListaSimple filtrada = new ListaSimple();
        NodoSimple nodo = catalogo.getFrente();

        while (nodo != null) {
            Juego j = (Juego) nodo.getDato();
            boolean coincideBusqueda = busqueda.isEmpty() ||
                j.getNombre().toLowerCase().contains(busqueda) ||
                j.getCodigo().toLowerCase().contains(busqueda);
            boolean coincideGenero = genero.equals("Todos") ||
                j.getGenero().equals(genero);
            boolean coincidePlataforma = plataforma.equals("Todas") ||
                j.getPlataforma().equals(plataforma);

            if (coincideBusqueda && coincideGenero && coincidePlataforma) {
                filtrada.insertar(j);
            }
            nodo = nodo.getSiguiente();
        }
        mostrarTarjetas(filtrada);
    }

    private void agregarAlCarrito(Juego juego) {
        if (juego.getStock() <= 0) {
            JOptionPane.showMessageDialog(this,
                "No hay stock disponible para " + juego.getNombre(),
                "Sin stock", JOptionPane.WARNING_MESSAGE);
            return;
        }

        NodoSimple nodo = carrito.getFrente();
        while (nodo != null) {
            ItemCarrito item = (ItemCarrito) nodo.getDato();
            if (item.getJuego().getCodigo().equals(juego.getCodigo())) {
                item.aumentarCantidad();
                actualizarTablaCarrito();
                return;
            }
            nodo = nodo.getSiguiente();
        }

        carrito.insertar(new ItemCarrito(juego, 1));
        actualizarTablaCarrito();
    }

    private void actualizarTablaCarrito() {
        modeloCarrito.setRowCount(0);
        double total = 0;

        NodoSimple nodo = carrito.getFrente();
        while (nodo != null) {
            ItemCarrito item = (ItemCarrito) nodo.getDato();
            modeloCarrito.addRow(new Object[]{
                item.getJuego().getNombre(),
                "Q" + String.format("%.2f", item.getJuego().getPrecio()),
                item.getCantidad(),
                "Q" + String.format("%.2f", item.getSubtotal())
            });
            total += item.getSubtotal();
            nodo = nodo.getSiguiente();
        }
        lblTotal.setText("Total: Q" + String.format("%.2f", total));
    }

    private void eliminarDelCarrito() {
        int fila = tablaCarrito.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(this,
                "Selecciona un item para eliminar",
                "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Object[] items = carrito.toArray();
        carrito.eliminar(items[fila]);
        actualizarTablaCarrito();
    }

    private void confirmarCompra() {
        if (carrito.estaVacia()) {
            JOptionPane.showMessageDialog(this,
                "El carrito esta vacío",
                "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        StringBuilder sinStock = new StringBuilder();
        NodoSimple nodo = carrito.getFrente();

        while (nodo != null) {
            ItemCarrito item = (ItemCarrito) nodo.getDato();
            if (item.getJuego().getStock() < item.getCantidad()) {
                sinStock.append("- ").append(item.getJuego().getNombre())
                        .append("\n");
            }
            nodo = nodo.getSiguiente();
        }

        if (sinStock.length() > 0) {
            JOptionPane.showMessageDialog(this,
                "Stock insuficiente para:\n" + sinStock,
                "Stock insuficiente", JOptionPane.WARNING_MESSAGE);
            return;
        }

        nodo = carrito.getFrente();
        while (nodo != null) {
            ItemCarrito item = (ItemCarrito) nodo.getDato();
            item.getJuego().reducirStock(item.getCantidad());
            nodo = nodo.getSiguiente();
        }

        contadorCompras++;
        String idCompra = "C" + String.format("%03d", contadorCompras);
        Compra compra = new Compra(idCompra, carrito);

        historial.insertarAlInicio(compra);
        ventana.getUsuario().otorgarXP(carrito.getTamanio() * 50);

        Logro logro = ventana.getUsuario().verificarLogros();
        if (logro != null) mostrarNotificacionLogro(logro);

        actualizarTablaHistorial();
        carrito.limpiar();
        actualizarTablaCarrito();
        mostrarTarjetas(catalogo);

        JOptionPane.showMessageDialog(this,
            "¡Compra confirmada! " + compra.getResumen(),
            "Compra exitosa", JOptionPane.INFORMATION_MESSAGE);
    }

    private void cargarHistorial() {
        try {
            File archivo = new File("historial.txt");
            if (!archivo.exists()) return;
            Scanner sc = new Scanner(archivo);
            while (sc.hasNextLine()) {
                String linea = sc.nextLine().trim();
                if (!linea.isEmpty()) {
                    String[] partes = linea.split("\\|");
                    if (partes.length == 3) {
                        Compra c = new Compra(partes[0], new ListaSimple());
                        c.setFecha(partes[1]);
                        c.setTotal(Double.parseDouble(partes[2]));
                        historial.insertarAlInicio(c);
                    }
                }
            }
            sc.close();
            actualizarTablaHistorial();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                "Error al cargar historial: " + ex.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actualizarTablaHistorial() {
        modeloHistorial.setRowCount(0);
        NodoSimple nodo = historial.getFrente();
        while (nodo != null) {
            Compra c = (Compra) nodo.getDato();
            modeloHistorial.addRow(new Object[]{
                c.getId(),
                c.getFecha(),
                "Q" + String.format("%.2f", c.getTotal())
            });
            nodo = nodo.getSiguiente();
        }
    }

    private void mostrarNotificacionLogro(Logro logro) {
        JOptionPane.showMessageDialog(this, "¡Logro desbloqueado!\n\n" +
            logro.getNombre() + "\n" + logro.getDescripcion(), "Nuevo logro", JOptionPane.INFORMATION_MESSAGE);
    }

    public void guardarDatos() {
        try (PrintWriter pw = new PrintWriter(new FileWriter("catalogo.txt"))) {
            NodoSimple nodo = catalogo.getFrente();
            while (nodo != null) {
                pw.println(nodo.getDato().toString());
                nodo = nodo.getSiguiente();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        try (PrintWriter pw = new PrintWriter(new FileWriter("historial.txt"))) {
            NodoSimple nodo = historial.getFrente();
            while (nodo != null) {
                pw.println(nodo.getDato().toString());
                nodo = nodo.getSiguiente();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public ListaSimple getCatalogo() {
    return catalogo;
    }

    public ListaSimple getHistorial() {
    return historial;
    }

}
