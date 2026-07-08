package com.gestiontorneos.controller;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;

import com.gestiontorneos.model.partido.Resultado;
import com.gestiontorneos.model.torneo.Torneo;
import com.gestiontorneos.model.torneo.formato.FormatoTorneo;
import com.gestiontorneos.model.deporte.Deporte;
import com.gestiontorneos.model.participante.Participante;
import com.gestiontorneos.model.partido.Partido;

import javax.swing.*;

//funcion: Gestionar todas las acciones relacionadas con los torneos
public class TorneoController{

    //guarda torneos existentes
    private List<Torneo> torneos;

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
                              String fechaInicio,
                              String fechaFin) {

        // Buscar si el deporte ya existe
        Deporte deporte = deporteController.buscarDeporte(nombreDeporte);

        // Si no existe, se crea
        if (deporte == null) {
            deporte = deporteController.crearDeporte(nombreDeporte);
        }

        // Se crea el torneo
        try {
            LocalDate inicio = LocalDate.parse(fechaInicio); //Convierte "2024-03-01" a LocalDate para que Torneo la entienda
            LocalDate fin = LocalDate.parse(fechaFin);

            Torneo torneo = new Torneo(nombreTorneo, deporte, formato, inicio, fin);
            torneos.add(torneo);



            return torneo;

        } catch (Exception e) {
            System.err.println("Error al crear torneo: " + e.getMessage());
            return null;
        }
    }

    //buscar torneo por su nombre
    public Torneo buscarTorneo(String nombre){
        for (Torneo torneo : torneos ){
            if (torneo.getNombre().equals(nombre)){
                return torneo;
            }
        }
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

    //retorna una lista de todos los torneos existentes
    public List<Torneo> listaTorneos() {
        return torneos;
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
        Torneo torneo = buscarTorneo(nombreTorneo);

        if (torneo != null){
            torneo.eliminarParticipante(participante);
            return true;
        }
        System.out.println("Fallo en EliminarParticipante");
        return false;
    }


    //lista de participantes
    public List<Participante> listarParticipantes(String nombreTorneo){
        Torneo torneo = buscarTorneo(nombreTorneo);

        if ( torneo != null ){
            return torneo.getParticipantes();
        }
        System.out.println("Fallo en lista de participantes");
        return new ArrayList<>();
    }

    public boolean generarCalendario(String nombreTorneo){
        Torneo torneo = buscarTorneo(nombreTorneo);
        if(torneo == null){
            return false;
        }else{
            try{
                torneo.generarCalendario();
                return true;
            }catch(Exception e){
                System.err.println("Error al generar calendario " + e.getMessage());
                return false;
            }
        }
    }

    public int cantidadPartidos(String nombreTorneo) {
        Torneo torneo = buscarTorneo(nombreTorneo);
        if (torneo == null) {
            return 0;
        }
        return torneo.getCalendario().getPartidos().size();
    }



    public boolean crearPartido(Participante local, Participante visitante, String nombreTorneo, int ronda){
        Torneo torneo = buscarTorneo(nombreTorneo);
        if(torneo != null){
            Partido partido = new Partido(local, visitante, ronda);
            torneo.agregarPartido(partido);
            return true;
        }
        System.out.println("Fallo en creacion de partido");
        return false;
    }

    public boolean registrarResultadoPartido(String nombreTorneo, int indicePartido, int puntosLocal, int puntosVisitante){
        Torneo torneo = buscarTorneo(nombreTorneo);

        if (torneo == null) {
            System.out.println("No se encontró torneo con el nombre de: " + nombreTorneo );
            return false;
        }
        List<Partido> pendientes = torneo.getCalendario().getPendientes();
        if (indicePartido < 0 || indicePartido >= pendientes.size()) {
            System.out.println("Índice de partido inválido");
            return false;
        }
        Partido partido = pendientes.get(indicePartido);
        Resultado resultado = new Resultado(puntosLocal, puntosVisitante);

        try {
            torneo.registrarResultado(partido, resultado);
            return true;
        } catch (Exception e) {
            System.out.println("Error al registrar resultado: " + e.getMessage());
            return false;
        }


    }
}
