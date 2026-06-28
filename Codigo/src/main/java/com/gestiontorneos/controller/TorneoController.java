package com.gestiontorneos.controller;

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

    }

    public Torneo crearTorneo(String nombreTorneo,
                              String nombreDeporte,
                              formatoTorneo formato,
                              localDate fechaInicio,
                              localDate fechaFin){


        //buscar deporte
        Deporte deporte = deporteController.buscarDeporte(nombreDeporte);


        //si no existe el deporte se crea
        if ( deporte == null ){
            deporte = deporteController.crearDeporte(nombreDeporte)
        }

        //se crea el torneo para el deporte
        Torneo torneo = new Torneo(nombreTorneo, deporte);

        //mientras no se use json no es necesario esto
        //persistenciaController.guardarTorneo(torneos);

        torneos.add(torneo);

        return torneo;
    }

}


