package com.gestiontorneos.controller;

import com.gestiontorneos.gui.VentanaPrincipal;
import com.gestiontorneos.gui.compartido.PanelMenu;
import com.gestiontorneos.model.torneo.Torneo;

import java.util.List;

/**
 * Controlador encargado de gestionar las opciones del menú principal.
 * <p>
 * Esta clase enlaza los botones u opciones del {@link PanelMenu} con los paneles
 * correspondientes de la {@link VentanaPrincipal}. También realiza acciones previas
 * cuando ciertos paneles requieren información actualizada antes de mostrarse.
 * </p>
 */
public class PanelMenuController {

    /**
     * Panel de menú que contiene las opciones principales de navegación.
     */
    private final PanelMenu menu;

    /**
     * Ventana principal donde se muestran los distintos paneles de la aplicación.
     */
    private final VentanaPrincipal ventanaPrincipal;

    /**
     * Construye el controlador del menú principal.
     * <p>
     * Al inicializarse, configura todos los listeners de navegación del menú.
     * </p>
     *
     * @param menu panel de menú de la aplicación.
     * @param ventanaPrincipal ventana principal que contiene los paneles.
     */
    public PanelMenuController(PanelMenu menu, VentanaPrincipal ventanaPrincipal) {
        this.menu = menu;
        this.ventanaPrincipal = ventanaPrincipal;
        manejarEventos();
    }

    /**
     * Configura los eventos de navegación del menú principal.
     * <p>
     * Cada opción del menú muestra un panel específico dentro de la ventana principal.
     * En algunos casos, como clasificaciones o registro de resultados, se actualiza
     * la información antes de cambiar de panel.
     * </p>
     */
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
        });
        menu.agregarListener("Futuros Eventos", e -> ventanaPrincipal.mostrarPanel("Futuros Eventos"));
        menu.agregarListener("Crear Torneo", e -> ventanaPrincipal.mostrarPanel("Crear Torneo"));
        menu.agregarListener("Crear Participante", e -> ventanaPrincipal.mostrarPanel("Crear Participante"));
        menu.agregarListener("Registrar Resultado", e -> {
            ventanaPrincipal.getRegistrarResultado().cargarTorneos();
            ventanaPrincipal.mostrarPanel("Registrar Resultado");
        });
    }
}