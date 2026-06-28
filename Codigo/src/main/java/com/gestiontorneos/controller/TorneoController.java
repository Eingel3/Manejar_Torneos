package com.gestiontorneos.controller;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;


//funcion: Gestionar todas las acciones relacionadas con los torneos
public class TorneoController{

    //guarda torneos existentes
    private List<Torneo> torneos;

    //es la lista de deportes
    private List<Deporte> deportes;

    //sirve para utilizar deportes existentes, eliminando la necesidad de crearlo de cero
    private DeporteController deporteController;

    //aca guarda los torneos con persistencia
    private PersistenciaController persistenciaController;


    public TorneoController(){
        this.torneos = new ArrayList<>();
        this.deporteController = new DeporteController();
        this.persistenciaController = new PersistenciaController();
    }

    // Crear un torneo nuevo
    public Torneo crearTorneo(String nombreTorneo,
                              String nombreDeporte,
                              FormatoTorneo formato,
                              LocalDate fechaInicio,
                              LocalDate fechaFin) {

        // Buscar si el deporte ya existe
        Deporte deporte = deporteController.buscarDeporte(nombreDeporte);

        // Si no existe, se crea
        if (deporte == null) {
            deporte = deporteController.crearDeporte(nombreDeporte);
        }

        // Se crea el torneo
        Torneo torneo = new Torneo(nombreTorneo, deporte);

        torneos.add(torneo);
        
        return torneo;
    }

}


