package com.gestiontorneos.controller;

import com.gestiontorneos.gui.VentanaPrincipal;
import com.gestiontorneos.gui.compartido.PanelMenu;

public class PanelMenuController {
    private final PanelMenu menu;
    private final VentanaPrincipal ventanaPrincipal;
    public PanelMenuController(PanelMenu menu, VentanaPrincipal ventanaPrincipal) {
        this.menu = menu;
        this.ventanaPrincipal = ventanaPrincipal;
        manejarEventos();
    }
    private void manejarEventos() {

    }
}
