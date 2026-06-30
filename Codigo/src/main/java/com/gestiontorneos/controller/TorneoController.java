package com.gestiontorneos.controller;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;
import  com.gestiontorneos.model.torneo.Torneo;
import com.gestiontorneos.model.torneo.formato.FormatoTorneo;
import com.gestiontorneos.model.deporte.Deporte;
import com.gestiontorneos.model.participante.Participante;
import com.gestiontorneos.controller.DeporteController;

//esto debe importance cuando exista
// import com.gestiontorneos.model.torneo.Torneo;
// import com.gestiontorneos.model.deporte.Deporte;
// import com.gestiontorneos.model.torneo.formato.FormatoTorneo;

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
    //buscar torneo por su nombre
    public Torneo buscarTorneo(String nombre){//importar eso de Torneo
        for (Torneo torneo : torneos ){
            if (torneo.getNombre().equals(nombre)){//revisa que el noombre del torneo
                return torneo;
            }
        }
        // lanzar excepcion
        return null;
    }

    // Eliminar el torneo por su nombre
    public boolean eliminarTorneo(String nombre){
        Torneo torneo = buscarTorneo(nombre);

        if ( torneo != null ){
            torneos.remove(torneo);
            return true;
        }
        return false;
    }


    //registrar a los participantes de un torneo
    public boolean registrarParticipante(String nombreTorneo, Participante participante){

        Torneo torneo = buscarTorneo(nombreTorneo);

        if ( torneo != null ){
            torneo.agregarParticipante(participante);
            return true;
        }
        return false;

    }

    //eliminar participantes
    public boolean eliminarParticipante(String nombreTorneo, Participante participante){

    }


    //lista de participantes
    public List<Participantes> listarParticipantes(String nombreTorneo){
        Torneo torneo = buscarTorneo(nombreTorneo);

        if ( torneo != null ){
            return torneo.getParticipantes(nombreTorneo);
        }
        System.out.println("Lista vacia")
        return new ArrayList<>();
    }


    //retorna una lista de todos los torneos existentes
    public List<Torneo> listaTorneos() {
        return torneos;
    }
}


