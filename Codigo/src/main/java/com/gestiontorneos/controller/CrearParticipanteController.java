package com.gestiontorneos.controller;

import com.gestiontorneos.gui.organizador.PanelCrearParticipante;
import javax.swing.*;

public class CrearParticipanteController {

    private PanelCrearParticipante panel;
    private TorneoController torneoController;


    public CrearParticipanteController(PanelCrearParticipante panel, TorneoController torneoController){
        this.panel = panel;
        this.torneoController = torneoController;
        manejarEventos();
    }


    private void manejarEventos() {
        panel.getBotonSiguiente().addActionListener(e -> {
            String tipo = panel.getTipoParticipante();
            panel.limpiarPanel();
            if (tipo.equals("Equipo")) {
                panel.elegirNombreEquipo();
            } else {
                panel.agregarParticipante();
            }
            panel.elegirTorneo();
        });
    }



}
