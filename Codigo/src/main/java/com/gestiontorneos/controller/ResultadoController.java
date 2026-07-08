package com.gestiontorneos.controller;

import com.gestiontorneos.gui.organizador.PanelRegistrarResultado;

/**
 * Controlador para el registro de resultados de partidos.
 * <p>
 * Conecta el {@link PanelRegistrarResultado} con el {@link TorneoController}
 * para permitir al usuario registrar los puntajes de un partido.
 * </p>
 */
public class ResultadoController {
    private PanelRegistrarResultado panelRegistrarResultado;
    private TorneoController torneoController;

    /**
     * Construye el controlador y asigna los listeners a los botones de la vista.
     *
     * @param panelRegistrarResultado Panel de registro de resultados.
     * @param torneoController Controlador que gestiona los torneos y partidos.
     */
    public ResultadoController(PanelRegistrarResultado panelRegistrarResultado, TorneoController torneoController) {
        this.panelRegistrarResultado = panelRegistrarResultado;
        this.torneoController = torneoController;
        manejarEventos();
    }

    private void manejarEventos() {
        panelRegistrarResultado.getBotonRegistrar().addActionListener(e -> registrarResultado());
        panelRegistrarResultado.getBotonCancelar().addActionListener(e -> cancelar());
    }


    /**
     * Registra el resultado del partido seleccionado.
     * <p>
     * Valida que todos los campos estén completos, que los puntajes sean números
     * válidos, y que se haya seleccionado un partido. Luego llama al
     * {@link TorneoController} para procesar el registro.
     * </p>
     */
    private void registrarResultado() {
        String nombreTorneo = panelRegistrarResultado.getNombreTorneo();
        int indicePartido = panelRegistrarResultado.getIndicePartido();
        String puntosLocalStr = panelRegistrarResultado.getPuntosLocal();
        String puntosVisitanteStr = panelRegistrarResultado.getPuntosVisitante();

        if (nombreTorneo == null || nombreTorneo.isEmpty()) {
            panelRegistrarResultado.mostrarMensaje("Seleccione un torneo.");
            return;
        }

        if (indicePartido < 0) {
            panelRegistrarResultado.mostrarMensaje("Seleccione un partido pendiente.");
            return;
        }

        if (puntosLocalStr.isEmpty() || puntosVisitanteStr.isEmpty()) {
            panelRegistrarResultado.mostrarMensaje("Ingrese los puntajes del local y del visitante.");
            return;
        }

        int puntosLocal;
        int puntosVisitante;
        try {
            puntosLocal = Integer.parseInt(puntosLocalStr);
            puntosVisitante = Integer.parseInt(puntosVisitanteStr);
        } catch (NumberFormatException ex) {
            panelRegistrarResultado.mostrarMensaje("Los puntajes deben ser números enteros.");
            return;
        }

        if (puntosLocal < 0 || puntosVisitante < 0) {
            panelRegistrarResultado.mostrarMensaje("Los puntajes no pueden ser negativos.");
            return;
        }

        boolean exito = torneoController.registrarResultadoPartido(nombreTorneo, indicePartido, puntosLocal, puntosVisitante);
        if (exito) {
            panelRegistrarResultado.mostrarMensaje("Resultado registrado exitosamente!");
            panelRegistrarResultado.limpiarFormulario();
            panelRegistrarResultado.cargarPartidosPendientes();
        } else {
            panelRegistrarResultado.mostrarMensaje("Error al registrar resultado");
        }
    }

    /**
     * Limpia el formulario y recarga los torneos disponibles.
     */
    private void cancelar() {
        panelRegistrarResultado.limpiarFormulario();
        panelRegistrarResultado.cargarTorneos();
    }

}
