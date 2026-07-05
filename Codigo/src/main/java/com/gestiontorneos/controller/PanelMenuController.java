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
        menu.inicio.addActionListener(e -> ventanaPrincipal.mostrarPanel("Inicio"));
        menu.torneos.addActionListener(e -> ventanaPrincipal.mostrarPanel("Torneos"));
        menu.clasificaciones.addActionListener(e -> ventanaPrincipal.mostrarPanel("Clasificaciones"));
        menu.partidos.addActionListener(e -> ventanaPrincipal.mostrarPanel("Partidos"));
        menu.futurosEventos.addActionListener(e -> ventanaPrincipal.mostrarPanel("Futuros Eventos"));
    }
}
