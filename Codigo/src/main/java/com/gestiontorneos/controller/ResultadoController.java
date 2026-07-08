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

}
