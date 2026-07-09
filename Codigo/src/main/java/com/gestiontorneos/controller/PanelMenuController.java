package com.gestiontorneos.controller;

import com.gestiontorneos.gui.VentanaPrincipal;
import com.gestiontorneos.gui.compartido.PanelMenu;
import com.gestiontorneos.model.torneo.Torneo;

import java.util.List;

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
        menu.agregarListener("Clasificaciones", e -> {
            List<Torneo> lista = ventanaPrincipal.getTorneoController().listaTorneos();
            if (!lista.isEmpty()) {
                Torneo ultimo = lista.get(lista.size() - 1);
                ventanaPrincipal.actualizarClasificacion(ultimo);
            }
            ventanaPrincipal.mostrarPanel("Clasificacion");
        });        menu.agregarListener("Futuros Eventos", e -> ventanaPrincipal.mostrarPanel("Futuros Eventos"));
        menu.agregarListener("Crear Torneo", e -> ventanaPrincipal.mostrarPanel("Crear Torneo"));
        menu.agregarListener("Crear Participante", e -> ventanaPrincipal.mostrarPanel("Crear Participante"));
        menu.agregarListener("Registrar Resultado", e -> ventanaPrincipal.mostrarPanel("Registrar Resultado"));
    }
}
