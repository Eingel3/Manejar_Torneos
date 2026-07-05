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
        menu.agregarListener("Inicio", e -> ventanaPrincipal.mostrarPanel("Inicio"));
        menu.agregarListener("Torneos", e -> ventanaPrincipal.mostrarPanel("Torneos"));
        menu.agregarListener("Clasificaciones", e -> ventanaPrincipal.mostrarPanel("Clasificaciones"));
        menu.agregarListener("Partidos", e -> ventanaPrincipal.mostrarPanel("Partidos"));
        menu.agregarListener("Futuros Eventos", e -> ventanaPrincipal.mostrarPanel("Futuros Eventos"));
        menu.agregarListener("Crear Torneo", e -> ventanaPrincipal.mostrarPanel("Crear Torneo"));
    }
}
