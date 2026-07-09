package com.gestiontorneos.controller;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;

import com.gestiontorneos.model.partido.Resultado;
import com.gestiontorneos.model.torneo.Torneo;
import com.gestiontorneos.model.torneo.formato.FormatoTorneo;
import com.gestiontorneos.model.deporte.Deporte;
import com.gestiontorneos.model.deporte.TipoParticipacion;
import com.gestiontorneos.model.participante.Participante;
import com.gestiontorneos.model.partido.Partido;

import javax.swing.*;

/**
 * Controlador principal encargado de gestionar los torneos de la aplicación.
 * <p>
 * Esta clase centraliza las operaciones relacionadas con la creación, búsqueda,
 * eliminación y listado de torneos. También permite registrar participantes,
 * generar calendarios, crear partidos y registrar resultados.
 * </p>
 * <p>
 * Funciona como una capa intermedia entre la interfaz gráfica y el modelo,
 * evitando que las vistas manipulen directamente los objetos de dominio.
 * </p>
 */
public class TorneoController{

    /**
     * Lista de torneos existentes en memoria.
     */
    private List<Torneo> torneos;

    /**
     * Controlador encargado de administrar los deportes disponibles.
     */
    private DeporteController deporteController;

    //aca guarda los torneos con persistencia

    /**
     * Construye un controlador de torneos.
     * <p>
     * Inicializa la lista de torneos, el controlador de deportes y el controlador
     * de persistencia.
     * </p>
     */
    public TorneoController(){
        this.torneos = new ArrayList<>();
        this.deporteController = new DeporteController();
    }

    /**
     * Crea un nuevo torneo.
     * <p>
     * Si el deporte indicado no existe, se crea automáticamente con el tipo de
     * participación recibido. Las fechas se convierten desde texto a
     * {@link LocalDate}, por lo que deben tener un formato válido compatible con
     * {@code LocalDate.parse}, normalmente {@code yyyy-MM-dd}.
     * </p>
     *
     * @param nombreTorneo nombre del torneo.
     * @param nombreDeporte nombre del deporte asociado al torneo.
     * @param formato formato de competición del torneo.
     * @param fechaInicio fecha de inicio del torneo en formato {@code yyyy-MM-dd}.
     * @param fechaFin fecha de fin del torneo en formato {@code yyyy-MM-dd}.
     * @param tipoParticipacion tipo de participación del deporte.
     * @return el torneo creado, o {@code null} si ocurrió un error durante la creación.
     */
    public Torneo crearTorneo(String nombreTorneo,
                              String nombreDeporte,
                              FormatoTorneo formato,
                              String fechaInicio,
                              String fechaFin,
                              TipoParticipacion tipoParticipacion) {

        // Buscar si el deporte ya existe
        Deporte deporte = deporteController.buscarDeporte(nombreDeporte);

        // Si no existe, se crea con el tipo de participacion indicado
        if (deporte == null) {
            deporte = deporteController.crearDeporte(nombreDeporte, tipoParticipacion);
        }

        // Se crea el torneo
        try {
            LocalDate inicio = LocalDate.parse(fechaInicio);
            LocalDate fin = LocalDate.parse(fechaFin);

            Torneo torneo = new Torneo(nombreTorneo, deporte, formato, inicio, fin);
            torneos.add(torneo);

            return torneo;

        } catch (Exception e) {
            System.err.println("Error al crear torneo: " + e.getMessage());
            return null;
        }
    }

    /**
     * Busca un torneo por su nombre.
     *
     * @param nombre nombre del torneo que se desea buscar.
     * @return el torneo encontrado, o {@code null} si no existe un torneo con ese nombre.
     */
    public Torneo buscarTorneo(String nombre){
        for (Torneo torneo : torneos ){
            if (torneo.getNombre().equals(nombre)){
                return torneo;
            }
        }
        return null;
    }

    /**
     * Elimina un torneo usando su nombre.
     *
     * @param nombre nombre del torneo que se desea eliminar.
     * @return {@code true} si el torneo fue encontrado y eliminado;
     *         {@code false} si no existe un torneo con ese nombre.
     */
    public boolean eliminarTorneo(String nombre){
        Torneo torneo = buscarTorneo(nombre);

        if ( torneo != null ){
            torneos.remove(torneo);
            return true;
        }
        return false;
    }

    /**
     * Obtiene todos los torneos registrados.
     *
     * @return lista de torneos existentes.
     */
    public List<Torneo> listaTorneos() {
        return torneos;
    }

    /**
     * Registra un participante en un torneo específico.
     * <p>
     * Si el torneo existe, se intenta agregar el participante a su lista de
     * participantes. Si ocurre un error durante el proceso, se informa por consola.
     * </p>
     *
     * @param nombreTorneo nombre del torneo donde se registrará el participante.
     * @param participante participante que se desea agregar.
     * @return {@code true} si el participante fue registrado correctamente;
     *         {@code false} si el torneo no existe o si ocurre un error.
     */
    public boolean registrarParticipante(String nombreTorneo, Participante participante){
        Torneo torneo = buscarTorneo(nombreTorneo);
        if (torneo != null){
            try {
                torneo.agregarParticipante(participante);
                return true;
            } catch (Exception e) {
                System.err.println("Error al registrar participante: " + e.getClass().getSimpleName() + " - " + e.getMessage());
                return false;
            }
        }
        System.err.println("No se encontro torneo con nombre: " + nombreTorneo);
        return false;
    }

    /**
     * Elimina un participante de un torneo.
     *
     * @param nombreTorneo nombre del torneo del cual se eliminará el participante.
     * @param participante participante que se desea eliminar.
     * @return {@code true} si el participante fue eliminado correctamente;
     *         {@code false} si el torneo no existe o si ocurre un error.
     */
    public boolean eliminarParticipante(String nombreTorneo, Participante participante){
        Torneo torneo = buscarTorneo(nombreTorneo);
        if (torneo != null){
            try {
                torneo.eliminarParticipante(participante);
                return true;
            } catch (Exception e) {
                System.err.println("Error al eliminar participante: " + e.getMessage());
                return false;
            }
        }
        System.out.println("Fallo en EliminarParticipante");
        return false;
    }

    /**
     * Lista los participantes registrados en un torneo.
     *
     * @param nombreTorneo nombre del torneo consultado.
     * @return lista de participantes del torneo, o una lista vacía si el torneo no existe.
     */
    public List<Participante> listarParticipantes(String nombreTorneo){
        Torneo torneo = buscarTorneo(nombreTorneo);

        if ( torneo != null ){
            return torneo.getParticipantes();
        }
        System.out.println("Fallo en lista de participantes");
        return new ArrayList<>();
    }

    /**
     * Genera el calendario de partidos de un torneo.
     * <p>
     * La generación depende del formato del torneo y de los participantes registrados.
     * Si ocurre algún error durante la generación, se informa por consola.
     * </p>
     *
     * @param nombreTorneo nombre del torneo para el cual se generará el calendario.
     * @return {@code true} si el calendario fue generado correctamente;
     *         {@code false} si el torneo no existe o si ocurre un error.
     */
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

    /**
     * Obtiene la cantidad de partidos registrados en el calendario de un torneo.
     *
     * @param nombreTorneo nombre del torneo consultado.
     * @return cantidad de partidos del calendario, o {@code 0} si el torneo no existe.
     */
    public int cantidadPartidos(String nombreTorneo) {
        Torneo torneo = buscarTorneo(nombreTorneo);
        if (torneo == null) {
            return 0;
        }
        return torneo.getCalendario().getPartidos().size();
    }

    /**
     * Crea un partido asociado a un torneo existente.
     * <p>
     * Este método permite construir un enfrentamiento entre dos participantes y
     * agregarlo al torneo indicado. Aunque originalmente podía servir como apoyo
     * para una creación manual de partidos, la versión final del sistema establece
     * que todo enfrentamiento debe pertenecer obligatoriamente a un torneo.
     * </p>
     * <p>
     * La idea de crear partidos amistosos o independientes fue descartada, ya que
     * el modelo actual organiza los partidos dentro del contexto competitivo de un
     * torneo y su calendario. Por ello, este método se conserva como una utilidad
     * interna o base futura para agregar partidos siempre vinculados a un torneo
     * válido.
     * </p>
     *
     * @param local participante local del partido.
     * @param visitante participante visitante del partido.
     * @param nombreTorneo nombre del torneo al que pertenece el partido.
     * @param ronda número de ronda del partido.
     * @return {@code true} si el partido fue creado y agregado correctamente al torneo;
     *         {@code false} si el torneo indicado no existe.
     */
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

    /**
     * Registra el resultado de un partido pendiente de un torneo.
     * <p>
     * El índice recibido corresponde a la posición del partido dentro de la lista
     * de partidos pendientes. Si el índice es inválido o el torneo no existe,
     * el resultado no se registra.
     * </p>
     *
     * @param nombreTorneo nombre del torneo donde se registrará el resultado.
     * @param indicePartido índice del partido dentro de la lista de pendientes.
     * @param puntosLocal puntos obtenidos por el participante local.
     * @param puntosVisitante puntos obtenidos por el participante visitante.
     * @return {@code true} si el resultado fue registrado correctamente;
     *         {@code false} si el torneo no existe, el índice es inválido o ocurre un error.
     */
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