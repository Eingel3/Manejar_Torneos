package com.gestiontorneos;
import com.gestiontorneos.controller.DeporteController;
import com.gestiontorneos.controller.TorneoController;
import com.gestiontorneos.gui.VentanaPrincipal;

public class Main{
    public static void main(String[] args){


        VentanaPrincipal ventana = new VentanaPrincipal();
        TorneoController tc = new TorneoController();
        ventana.setTorneoController(tc);
        ventana.setDeporteController(new DeporteController());
        ventana.getCrearTorneo().setTorneoController(tc);
        ventana.getCrearTorneo().setVentanaPrincipal(ventana);
        ventana.mostrar();
    }
}