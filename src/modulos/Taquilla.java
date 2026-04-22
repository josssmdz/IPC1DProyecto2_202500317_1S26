package modulos;

import modelos.*;
import estructuras.*;
import java.util.Random;

// Thread que simula una taquilla procesando la cola de participantes. Módulo 3 — Concurrencia con Java Threads
public class Taquilla extends Thread {

    private int numero;
    private Cola cola;
    private Torneo torneo;
    private ListaSimple ticketsVendidos;
    private PanelTorneos panel;
    private Random random;

    public Taquilla(int numero, Cola cola, Torneo torneo,
                    ListaSimple ticketsVendidos, PanelTorneos panel) {
        this.numero = numero;
        this.cola = cola;
        this.torneo = torneo;
        this.ticketsVendidos = ticketsVendidos;
        this.panel = panel;
        this.random = new Random();
    }

    @Override
    public void run() {
        panel.actualizarEstadoTaquilla(numero, "Iniciando...");

        while (!cola.estaVacia() && torneo.hayTickets()) {
            Participante participante = (Participante) cola.desencolar();

            if (participante == null) break;

            torneo.reducirTicket();

            panel.actualizarEstadoTaquilla(numero,
                "Procesando a: " + participante.getNombre());

            try {
                int tiempoEspera = 800 + random.nextInt(1200);
                Thread.sleep(tiempoEspera);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }

            synchronized (ticketsVendidos) {
                ticketsVendidos.insertar(participante);
            }
            panel.agregarLog("Taquilla " + numero +
                " — Ticket vendido a: " + participante.getNombre() +
                " [" + torneo.getNombre() + "]");

            panel.actualizarEstadoTaquilla(numero, "Libre");
        }

        panel.actualizarEstadoTaquilla(numero, "Finalizada");
        panel.agregarLog("— Taquilla " + numero + " finalizó —");
    }
}
