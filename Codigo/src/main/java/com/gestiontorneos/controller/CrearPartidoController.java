package com.gestiontorneos.controller;
import com.gestiontorneos.gui.organizador.PanelCrearPartido;
import com.gestiontorneos.model.participante.Participante;
import com.gestiontorneos.model.torneo.Torneo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Controlador para la creación de partidos.
 * Conecta el panelCrearPartido {@link com.gestiontorneos.gui.organizador.PanelCrearPartido} con el modelo a través de {@link TorneoController}.
 */

public class CrearPartidoController {
    private PanelCrearPartido panelCrearPartido;
    private TorneoController torneoController;
    /**
     * Construye el controlador y asigna los listeners a los botones de la vista.
     *
     * @param panelCrearPartido Panel de creación de partido.
     * @param torneoController Controlador que gestiona los torneos y partidos.
     */
    public CrearPartidoController(PanelCrearPartido panelCrearPartido, TorneoController torneoController) {
        this.panelCrearPartido = panelCrearPartido;
        this.torneoController = torneoController;
        manejarEventos();
    }
    /**
     * Lógica principal para crear un partido a partir de los datos ingresados.
     */
    private void manejarEventos() {
        panelCrearPartido.getBotonCrear().addActionListener(e -> crearPartido());
        panelCrearPartido.getBotonCancelar().addActionListener(e -> cancelar());
        //panelCrearPartido.getBotonSiguiente().addActionListener(e -> siguiente());
    }

    private void crearPartido() {
        //Obtenemos cada una de las informaciones necesarias
        String nombreTorneo = panelCrearPartido.getNombreTorneo();
        String nombreLocal = panelCrearPartido.getNombreParticipanteLocal();
        String nombreVisitante = panelCrearPartido.getNombreParticipanteVisitante();
        String estadoSeleccionado = panelCrearPartido.getEstadoPartido();
        //Verificamos si los datos han sido ingresados correctamente
        if (nombreTorneo.isEmpty() || nombreLocal.isEmpty() || nombreVisitante.isEmpty()) {
            panelCrearPartido.mostrarMensaje("Todos los campos deben estar completos.");
            return;
        }

        if (nombreLocal.equalsIgnoreCase(nombreVisitante)) {
            panelCrearPartido.mostrarMensaje("El participante local y el visitante no pueden ser el mismo.");
            return;
        }

        //Verifcamos si el torneo existe
        Torneo torneo = torneoController.buscarTorneo(nombreTorneo);
        if (torneo == null) {
            panelCrearPartido.mostrarMensaje("El torneo '" + nombreTorneo + "' no existe.");
            return;
        }
        //Ahora hay que crear los participantes
        //Luego crear el partido

        List<Participante> participantes = torneoController.listarParticipantes(nombreTorneo);
        Participante local = null;
        Participante visitante = null;
        for (Participante p : participantes) {
            if (p.getNombre().equalsIgnoreCase(nombreLocal)) local = p;
            if (p.getNombre().equalsIgnoreCase(nombreVisitante)) visitante = p;
        }
        if (local == null) {
            panelCrearPartido.mostrarMensaje("Participante local '" + nombreLocal + "' no existe en el torneo.");
            return;
        }
        if (visitante == null) {
            panelCrearPartido.mostrarMensaje("Participante visitante '" + nombreVisitante + "' no existe en el torneo.");
            return;
        }
        // Crear el partido usando TorneoController
        boolean exito = torneoController.crearPartido(local, visitante, nombreTorneo, 1);
        if (exito) {
            panelCrearPartido.mostrarMensaje("Partido creado exitosamente!");
            panelCrearPartido.limpiarFormulario();
        } else {
            panelCrearPartido.mostrarMensaje("Error al crear el partido.");
        }

    }
    private void cancelar() {
        panelCrearPartido.limpiarPanel();
    }
    private void siguiente() {

    }
}
