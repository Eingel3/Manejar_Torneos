package com.gestiontorneos.controller;
import com.gestiontorneos.gui.organizador.PanelCrearPartido;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controlador para la creación de partidos.
 * Conecta la vista {@link com.gestiontorneos.gui.organizador.PanelCrearPartido} con el modelo a través de {@link TorneoController}.
 */

public class CrearPartidoController {
    private PanelCrearPartido panelCrearPartido;
    private TorneoController torneoController;
    /**
     * Construye el controlador y asigna los listeners a los botones de la vista.
     *
     * @param vista Panel de creación de partido.
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
    private void manejarEventos() {}

}
