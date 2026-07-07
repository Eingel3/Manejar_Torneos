package com.gestiontorneos;
import com.gestiontorneos.controller.DeporteController;
import com.gestiontorneos.controller.TorneoController;
import com.gestiontorneos.gui.VentanaPrincipal;

public class Main{
    public static void main(String[] args){


        VentanaPrincipal ventanaPrincipal = new VentanaPrincipal();
        TorneoController torneoController = new TorneoController();
        ventanaPrincipal.setTorneoController(torneoController);
        DeporteController deporteController = new DeporteController();
        ventanaPrincipal.setDeporteController(deporteController);
        ventanaPrincipal.inicializarControladores();
        ventanaPrincipal.getCrearTorneo().setTorneoController(torneoController);
        ventanaPrincipal.getCrearTorneo().setVentanaPrincipal(ventanaPrincipal);
        ventanaPrincipal.configurarListenersTorneos();
        ventanaPrincipal.actualizarTorneos();
        ventanaPrincipal.mostrar();
    }
}