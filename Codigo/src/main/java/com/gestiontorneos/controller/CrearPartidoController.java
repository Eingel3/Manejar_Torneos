package com.gestiontorneos.controller;

import com.gestiontorneos.gui.organizador.PanelCrearPartido;
import com.gestiontorneos.model.participante.Participante;
import com.gestiontorneos.model.torneo.Torneo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Controlador encargado de gestionar la creación manual de partidos.
 * <p>
 * Esta clase conecta la vista {@link PanelCrearPartido} con la lógica del
 * {@link TorneoController}. Su responsabilidad principal es obtener los datos
 * introducidos por el usuario, validarlos y solicitar la creación del partido
 * dentro del torneo correspondiente.
 * </p>
 */
public class CrearPartidoController {

    /**
     * Panel gráfico utilizado para introducir los datos del partido.
     */
    private PanelCrearPartido panelCrearPartido;

    /**
     * Controlador que administra los torneos, participantes y partidos.
     */
    private TorneoController torneoController;

    /**
     * Construye un controlador para la creación de partidos.
     * <p>
     * Al crearse, configura los eventos de los botones del panel.
     * </p>
     *
     * @param panelCrearPartido panel de creación de partido.
     * @param torneoController controlador que gestiona los torneos.
     */
    public CrearPartidoController(PanelCrearPartido panelCrearPartido, TorneoController torneoController) {
        this.panelCrearPartido = panelCrearPartido;
        this.torneoController = torneoController;
        manejarEventos();
    }

    /**
     * Configura los eventos de la interfaz gráfica.
     * <p>
     * Asocia el botón de creación con el método {@link #crearPartido()} y el botón
     * de cancelación con el método {@link #cancelar()}.
     * </p>
     */
    private void manejarEventos() {
        panelCrearPartido.getBotonCrear().addActionListener(e -> crearPartido());
        panelCrearPartido.getBotonCancelar().addActionListener(e -> cancelar());
        //panelCrearPartido.getBotonSiguiente().addActionListener(e -> siguiente());
    }

    /**
     * Crea un partido a partir de los datos ingresados en el formulario.
     * <p>
     * El método valida que el torneo exista, que los participantes estén registrados
     * en dicho torneo y que el participante local no sea el mismo que el visitante.
     * Si todas las validaciones son correctas, solicita al {@link TorneoController}
     * la creación del partido.
     * </p>
     */
    private void crearPartido() {
        String nombreTorneo = panelCrearPartido.getNombreTorneo();
        String nombreLocal = panelCrearPartido.getNombreParticipanteLocal();
        String nombreVisitante = panelCrearPartido.getNombreParticipanteVisitante();
        String estadoSeleccionado = panelCrearPartido.getEstadoPartido();

        if (nombreTorneo.isEmpty() || nombreLocal.isEmpty() || nombreVisitante.isEmpty()) {
            panelCrearPartido.mostrarMensaje("Todos los campos deben estar completos.");
            return;
        }

        if (nombreLocal.equalsIgnoreCase(nombreVisitante)) {
            panelCrearPartido.mostrarMensaje("El participante local y el visitante no pueden ser el mismo.");
            return;
        }

        Torneo torneo = torneoController.buscarTorneo(nombreTorneo);
        if (torneo == null) {
            panelCrearPartido.mostrarMensaje("El torneo '" + nombreTorneo + "' no existe.");
            return;
        }

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

        boolean exito = torneoController.crearPartido(local, visitante, nombreTorneo, 1);
        if (exito) {
            panelCrearPartido.mostrarMensaje("Partido creado exitosamente!");
            panelCrearPartido.limpiarFormulario();
        } else {
            panelCrearPartido.mostrarMensaje("Error al crear el partido.");
        }
    }

    /**
     * Cancela la creación del partido y limpia el panel.
     */
    private void cancelar() {
        panelCrearPartido.limpiarPanel();
    }

    /**
     * Método reservado para implementar una navegación futura dentro del formulario.
     */
    private void siguiente() {

    }
}