package com.gestiontorneos.controller;

import com.gestiontorneos.gui.organizador.PanelRegistrarResultado;

/**
 * Controlador para el registro de resultados de partidos.
 * <p>
 * Conecta el {@link PanelRegistrarResultado} con el {@link TorneoController}
 * para permitir al usuario registrar los puntajes de un partido pendiente.
 * </p>
 */
public class ResultadoController {

    /**
     * Panel gráfico utilizado para seleccionar el torneo, el partido y los puntajes.
     */
    private PanelRegistrarResultado panelRegistrarResultado;

    /**
     * Controlador que administra los torneos y procesa el registro de resultados.
     */
    private TorneoController torneoController;

    /**
     * Construye el controlador y asigna los listeners a los botones de la vista.
     * <p>
     * También entrega al panel una referencia del {@link TorneoController}, para que
     * pueda cargar los torneos y partidos disponibles.
     * </p>
     *
     * @param panelRegistrarResultado panel de registro de resultados.
     * @param torneoController controlador que gestiona los torneos y partidos.
     */
    public ResultadoController(PanelRegistrarResultado panelRegistrarResultado, TorneoController torneoController) {
        this.panelRegistrarResultado = panelRegistrarResultado;
        this.torneoController = torneoController;
        panelRegistrarResultado.setTorneoController(torneoController);
        manejarEventos();
    }

    /**
     * Configura los eventos de los botones del panel.
     * <p>
     * El botón de registro ejecuta el proceso de guardar el resultado, mientras que
     * el botón de cancelar limpia el formulario y recarga la información disponible.
     * </p>
     */
    private void manejarEventos() {
        panelRegistrarResultado.getBotonRegistrar().addActionListener(e -> registrarResultado());
        panelRegistrarResultado.getBotonCancelar().addActionListener(e -> cancelar());
    }

    /**
     * Registra el resultado del partido seleccionado.
     * <p>
     * Valida que exista un torneo seleccionado, que se haya elegido un partido
     * pendiente, que los puntajes estén completos y que sean números enteros
     * no negativos. Si todo es válido, delega el registro del resultado al
     * {@link TorneoController}.
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
     * Cancela el registro actual.
     * <p>
     * Limpia el formulario y vuelve a cargar los torneos disponibles.
     * </p>
     */
    private void cancelar() {
        panelRegistrarResultado.limpiarFormulario();
        panelRegistrarResultado.cargarTorneos();
    }
}