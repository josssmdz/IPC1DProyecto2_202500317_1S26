package reportes;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.awt.Desktop;
import java.net.URI;
import estructuras.*;
import modelos.*;

//Genera los 4 tipos de reportes HTML con CSS embebido

public class GeneradorReportes {

    private static String generarNombre(String tipo) {
        return LocalDateTime.now()
            .format(DateTimeFormatter.ofPattern(
                "dd_MM_yyyy_HH_mm_ss")) + "_" + tipo + ".html";
    }

    private static void abrirEnNavegador(String nombreArchivo) {
        try {
            File archivo = new File(nombreArchivo);
            Desktop.getDesktop().browse(archivo.toURI());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static String getCSS() {
        return "<style>" +
            "body { font-family: Arial, sans-serif; " +
            "background: #0f0f23; color: #e0e0e0; margin: 0; padding: 20px; }" +
            "h1 { color: #90caf9; border-bottom: 2px solid #1a73e8; " +
            "padding-bottom: 10px; }" +
            "h2 { color: #90caf9; margin-top: 30px; }" +
            "table { width: 100%; border-collapse: collapse; " +
            "margin-top: 15px; }" +
            "th { background: #1a237e; color: #90caf9; " +
            "padding: 10px; text-align: left; }" +
            "td { padding: 8px 10px; border-bottom: " +
            "1px solid #2a2a4a; }" +
            "tr:hover { background: #1a1a3a; }" +
            ".legendaria { background: #3d2e00; color: #ffd700; " +
            "font-weight: bold; }" +
            ".vacia { background: #1a1a2e; color: #555; }" +
            ".badge { padding: 3px 8px; border-radius: 4px; " +
            "font-size: 12px; }" +
            ".badge-verde { background: #1b5e20; color: #a5d6a7; }" +
            ".badge-rojo { background: #b71c1c; color: #ef9a9a; }" +
            ".badge-azul { background: #0d47a1; color: #90caf9; }" +
            ".footer { margin-top: 40px; color: #555; " +
            "font-size: 12px; text-align: center; }" +
            "</style>";
    }

    public static void generarInventario(ListaSimple catalogo) {
        String nombre = generarNombre("Inventario");
        try (PrintWriter pw = new PrintWriter(
                new FileWriter(nombre))) {
            pw.println("<!DOCTYPE html><html lang='es'><head>");
            pw.println("<meta charset='UTF-8'>");
            pw.println("<title>Reporte de Inventario</title>");
            pw.println(getCSS());
            pw.println("</head><body>");
            pw.println("<h1>GameZone Pro — Reporte de Inventario</h1>");
            pw.println("<p>Generado: " + LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern(
                    "dd/MM/yyyy HH:mm:ss")) + "</p>");
            pw.println("<table>");
            pw.println("<tr><th>Código</th><th>Nombre</th>" +
                "<th>Género</th><th>Plataforma</th>" +
                "<th>Precio</th><th>Stock</th></tr>");

            NodoSimple nodo = catalogo.getFrente();
            while (nodo != null) {
                Juego j = (Juego) nodo.getDato();
                String badge = j.getStock() > 0 ?
                    "<span class='badge badge-verde'>Disponible</span>" :
                    "<span class='badge badge-rojo'>Agotado</span>";
                pw.println("<tr>" +
                    "<td>" + j.getCodigo() + "</td>" +
                    "<td>" + j.getNombre() + "</td>" +
                    "<td>" + j.getGenero() + "</td>" +
                    "<td>" + j.getPlataforma() + "</td>" +
                    "<td>Q" + String.format("%.2f",
                        j.getPrecio()) + "</td>" +
                    "<td>" + badge + " " +
                        j.getStock() + "</td>" +
                    "</tr>");
                nodo = nodo.getSiguiente();
            }

            pw.println("</table>");
            pw.println("<div class='footer'>" +
                "GameZone Pro</div>");
            pw.println("</body></html>");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        abrirEnNavegador(nombre);
    }

    public static void generarVentas(ListaSimple historial) {
        String nombre = generarNombre("Ventas");
        try (PrintWriter pw = new PrintWriter(
                new FileWriter(nombre))) {
            pw.println("<!DOCTYPE html><html lang='es'><head>");
            pw.println("<meta charset='UTF-8'>");
            pw.println("<title>Reporte de Ventas</title>");
            pw.println(getCSS());
            pw.println("</head><body>");
            pw.println("<h1>GameZone Pro — Reporte de Ventas</h1>");
            pw.println("<p>Generado: " + LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern(
                    "dd/MM/yyyy HH:mm:ss")) + "</p>");

            if (historial.estaVacia()) {
                pw.println("<p>No hay compras registradas.</p>");
            } else {
                pw.println("<table>");
                pw.println("<tr><th>ID</th><th>Fecha</th>" +
                    "<th>Total</th></tr>");

                NodoSimple nodo = historial.getFrente();
                double totalGeneral = 0;
                while (nodo != null) {
                    Compra c = (Compra) nodo.getDato();
                    pw.println("<tr>" +
                        "<td>" + c.getId() + "</td>" +
                        "<td>" + c.getFecha() + "</td>" +
                        "<td>Q" + String.format("%.2f",
                            c.getTotal()) + "</td>" +
                        "</tr>");
                    totalGeneral += c.getTotal();
                    nodo = nodo.getSiguiente();
                }

                pw.println("<tr><td colspan='2'>" +
                    "<strong>TOTAL GENERAL</strong></td>" +
                    "<td><strong>Q" +
                    String.format("%.2f", totalGeneral) +
                    "</strong></td></tr>");
                pw.println("</table>");
            }

            pw.println("<div class='footer'>" +
                "GameZone Pro</div>");
            pw.println("</body></html>");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        abrirEnNavegador(nombre);
    }

    public static void generarAlbum(MallaOrtogonal malla) {
        String nombre = generarNombre("Album");
        try (PrintWriter pw = new PrintWriter(
                new FileWriter(nombre))) {
            pw.println("<!DOCTYPE html><html lang='es'><head>");
            pw.println("<meta charset='UTF-8'>");
            pw.println("<title>Reporte de Álbum</title>");
            pw.println(getCSS());
            pw.println("</head><body>");
            pw.println("<h1>GameZone Pro — Reporte de Álbum</h1>");
            pw.println("<p>Generado: " + LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern(
                    "dd/MM/yyyy HH:mm:ss")) + "</p>");
            pw.println("<table>");
            pw.println("<tr><th>Posición</th><th>Código</th>" +
                "<th>Nombre</th><th>Tipo</th>" +
                "<th>Rareza</th><th>ATQ</th>" +
                "<th>DEF</th><th>PS</th></tr>");

            NodoMatriz fila = malla.getOrigen();
            int f = 0;
            while (fila != null) {
                NodoMatriz nodo = fila;
                int c = 0;
                while (nodo != null) {
                    if (nodo.getDato() == null) {
                        pw.println("<tr class='vacia'>" +
                            "<td>[" + f + "," + c + "]</td>" +
                            "<td colspan='7'>— Vacía —</td></tr>");
                    } else {
                        Carta carta = nodo.getDato();
                        String clase = carta.esLegendaria() ?
                            "legendaria" : "";
                        pw.println("<tr class='" + clase + "'>" +
                            "<td>[" + f + "," + c + "]</td>" +
                            "<td>" + carta.getCodigo() + "</td>" +
                            "<td>" + carta.getNombre() + "</td>" +
                            "<td>" + carta.getTipo() + "</td>" +
                            "<td>" + carta.getRareza() + "</td>" +
                            "<td>" + carta.getAtaque() + "</td>" +
                            "<td>" + carta.getDefensa() + "</td>" +
                            "<td>" + carta.getPs() + "</td>" +
                            "</tr>");
                    }
                    nodo = nodo.getDerecha();
                    c++;
                }
                fila = fila.getAbajo();
                f++;
            }

            pw.println("</table>");
            pw.println("<div class='footer'>" +
                "GameZone Pro</div>");
            pw.println("</body></html>");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        abrirEnNavegador(nombre);
    }

    public static void generarTorneos(ListaSimple torneos,
                                       ListaSimple ticketsVendidos) {
        String nombre = generarNombre("Torneos");
        try (PrintWriter pw = new PrintWriter(
                new FileWriter(nombre))) {
            pw.println("<!DOCTYPE html><html lang='es'><head>");
            pw.println("<meta charset='UTF-8'>");
            pw.println("<title>Reporte de Torneos</title>");
            pw.println(getCSS());
            pw.println("</head><body>");
            pw.println("<h1>GameZone Pro — Reporte de Torneos</h1>");
            pw.println("<p>Generado: " + LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern(
                    "dd/MM/yyyy HH:mm:ss")) + "</p>");

            pw.println("<h2>Torneos Disponibles</h2>");
            pw.println("<table>");
            pw.println("<tr><th>ID</th><th>Nombre</th>" +
                "<th>Juego</th><th>Fecha</th>" +
                "<th>Precio</th><th>Tickets</th></tr>");

            NodoSimple nodo = torneos.getFrente();
            while (nodo != null) {
                Torneo t = (Torneo) nodo.getDato();
                String badge = t.hayTickets() ?
                    "<span class='badge badge-verde'>Activo</span>" :
                    "<span class='badge badge-rojo'>Agotado</span>";
                pw.println("<tr>" +
                    "<td>" + t.getId() + "</td>" +
                    "<td>" + t.getNombre() + "</td>" +
                    "<td>" + t.getJuego() + "</td>" +
                    "<td>" + t.getFecha() + "</td>" +
                    "<td>Q" + String.format("%.2f",
                        t.getPrecioTicket()) + "</td>" +
                    "<td>" + badge + " " +
                        t.getTicketsDisponibles() + "</td>" +
                    "</tr>");
                nodo = nodo.getSiguiente();
            }
            pw.println("</table>");

            pw.println("<h2>Tickets Vendidos</h2>");
            if (ticketsVendidos.estaVacia()) {
                pw.println("<p>No hay tickets vendidos aún.</p>");
            } else {
                pw.println("<table>");
                pw.println("<tr><th>Participante</th>" +
                    "<th>Torneo</th><th>Fecha</th></tr>");
                nodo = ticketsVendidos.getFrente();
                while (nodo != null) {
                    Participante p = (Participante) nodo.getDato();
                    pw.println("<tr>" +
                        "<td>" + p.getNombre() + "</td>" +
                        "<td>" + p.getTorneo() + "</td>" +
                        "<td>" + p.getTimestamp() + "</td>" +
                        "</tr>");
                    nodo = nodo.getSiguiente();
                }
                pw.println("</table>");
            }

            pw.println("<div class='footer'>" +
                "GameZone Pro</div>");
            pw.println("</body></html>");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        abrirEnNavegador(nombre);
    }
}
