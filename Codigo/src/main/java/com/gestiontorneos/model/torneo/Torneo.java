package com.gestiontorneos.model.torneo;

import com.gestiontorneos.model.deporte.Deporte;
import com.gestiontorneos.model.deporte.TipoParticipacion;
import com.gestiontorneos.model.excepciones.DatosInvalidosException;
import com.gestiontorneos.model.participante.Participante;
import com.gestiontorneos.model.partido.Calendario;
import com.gestiontorneos.model.partido.Partido;
import com.gestiontorneos.model.partido.Resultado;
import com.gestiontorneos.model.torneo.formato.FormatoTorneo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.time.LocalDate;

/**
 * Representa un torneo dentro del sistema.
 * <p>
 * Un torneo contiene información general como nombre, deporte, formato,
 * fechas, participantes, calendario, clasificación y estado actual.
 * </p>
 * <p>
 * La generación de enfrentamientos y la actualización de puntos se delegan al
 * formato del torneo mediante el patrón Strategy.
 * </p>
 *
 * @see Deporte
 * @see FormatoTorneo
 * @see Participante
 * @see Calendario
 * @see Clasificacion
 */
public class Torneo {

    private String nombre;
    private Deporte deporte;
    private FormatoTorneo formato; //Puede ser LigaSimple, EliminacionDirecta, etc. — patrón Strategy
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private List<Participante> participantes;
    private Calendario calendario;
    private Clasificacion clasificacion;
    private String estado; // "INSCRIPCION", "EN_CURSO" o "FINALIZADO"

    /**
     * Crea un nuevo torneo con sus datos principales.
     *
     * @param nombre nombre del torneo.
     * @param deporte deporte asociado al torneo.
     * @param formato formato de competición utilizado.
     * @param fechaInicio fecha de inicio del torneo.
     * @param fechaFin fecha de finalización del torneo.
     * @throws DatosInvalidosException si algún dato obligatorio es inválido o si la fecha de fin es anterior a la de inicio.
     */
    public Torneo(String nombre, Deporte deporte, FormatoTorneo formato,
                  LocalDate fechaInicio, LocalDate fechaFin) { //Constructor para crear un torneo
        if (nombre == null || nombre.trim().isEmpty()) { //Validación de entradas
            throw new DatosInvalidosException("El nombre no puede estar vacío");
        }
        if (deporte == null) {
            throw new DatosInvalidosException("El deporte no puede ser null");
        }
        if (formato == null) {
            throw new DatosInvalidosException("El formato no puede ser null");
        }
        if (fechaInicio == null || fechaFin == null) {
            throw new DatosInvalidosException("Las fechas no pueden ser null");
        }
        if (fechaFin.isBefore(fechaInicio)) {
            throw new DatosInvalidosException("La fecha de fin no puede ser anterior a la de inicio");
        }

        this.nombre = nombre.trim();
        this.deporte = deporte;
        this.formato = formato;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.participantes = new ArrayList<>(); //Lista vacía, se llena con agregarParticipante()
        this.calendario = new Calendario(); //Sin partidos hasta que se genere el calendario
        this.clasificacion = new Clasificacion(); //Tabla vacía, se actualiza al registrar resultados
        this.estado = "INSCRIPCION"; //Todo torneo parte en fase de inscripción
    }

    /**
     * Inscribe un participante en el torneo.
     * <p>
     * Solo se permite agregar participantes mientras el torneo se encuentra en
     * fase de inscripción. Además, el participante debe ser compatible con el tipo
     * de participación del deporte asociado al torneo:
     * </p>
     * <ul>
     *     <li>En torneos individuales no se permiten participantes con más de un integrante.</li>
     *     <li>En torneos colectivos no se permiten participantes de un solo integrante.</li>
     * </ul>
     *
     * @param participante participante que se desea agregar.
     * @throws DatosInvalidosException si el participante es {@code null}, si ya está inscrito
     *                                 o si no coincide con el tipo de participación del deporte.
     * @throws IllegalStateException s
     * */
    public void agregarParticipante(Participante participante) { //Inscribe un participante al torneo
        if (participante == null) {
            throw new DatosInvalidosException("El participante no puede ser null");
        }
        if (!estado.equals("INSCRIPCION")) { //Solo se pueden inscribir antes de iniciar el torneo
            throw new IllegalStateException("Ya no se pueden inscribir participantes");
        }
        if (participantes.contains(participante)) { //Usa el equals() de Participante para detectar duplicados
            throw new DatosInvalidosException("El participante ya está inscrito");
        }
        if (deporte.getTipoParticipacion() == TipoParticipacion.INDIVIDUAL
                && participante.getCantidadIntegrantes() > 1) {
            throw new DatosInvalidosException("Este torneo es individual, no se pueden inscribir equipos");
        }
        if (deporte.getTipoParticipacion() == TipoParticipacion.COLECTIVO
                && participante.getCantidadIntegrantes() == 1) {
            throw new DatosInvalidosException("Este torneo es colectivo, no se pueden inscribir jugadores individuales");
        }
        participantes.add(participante);
        clasificacion.registrarParticipante(participante); //Lo agrega a la tabla con 0 puntos
    }

    /**
     * Genera el calendario inicial del torneo según su formato.
     *
     * @throws IllegalStateException si hay menos de dos participantes o si el calendario ya fue generado.
     */
    public void generarCalendario() { //Crea todos los partidos según el formato elegido
        if (participantes.size() < 2) {
            throw new IllegalStateException("Se necesitan al menos 2 participantes");
        }
        if (!estado.equals("INSCRIPCION")) {
            throw new IllegalStateException("El calendario ya fue generado");
        }

        List<Partido> partidos = formato.generarEnfrentamientos(participantes); //Torneo no sabe cómo se generan, lo decide el formato
        for (Partido p : partidos) {
            calendario.agregarPartido(p);
        }
        estado = "EN_CURSO"; //Una vez generado el calendario, no se pueden inscribir más participantes
    }

    /**
     * Registra el resultado de un partido y actualiza la clasificación.
     * <p>
     * Después de registrar el resultado, solicita al formato del torneo la
     * generación de una posible siguiente ronda.
     * </p>
     *
     * @param partido partido al que se le registrará el resultado.
     * @param resultado resultado del partido.
     * @throws IllegalStateException si el torneo no está en curso.
     */
    public void registrarResultado(Partido partido, Resultado resultado) { //Anota el resultado de un partido y actualiza la tabla
        if (!estado.equals("EN_CURSO")) {
            throw new IllegalStateException("El torneo no está en curso");
        }
        partido.registrarResultado(resultado); //Guarda el resultado en el partido
        formato.actualizarClasificacion(clasificacion, partido); //El formato decide cuántos puntos suma cada uno

        List<Partido> siguienteRonda = formato.generarSiguienteRonda(calendario); //En LigaSimple devuelve lista vacía
        for (Partido p : siguienteRonda) {
            calendario.agregarPartido(p); //Agrega los nuevos partidos al calendario
        }
    }

    /**
     * Obtiene el nombre del torneo.
     *
     * @return nombre del torneo.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene el deporte asociado al torneo.
     *
     * @return deporte del torneo.
     */
    public Deporte getDeporte() {
        return deporte;
    }

    /**
     * Obtiene el formato de competición del torneo.
     *
     * @return formato del torneo.
     */
    public FormatoTorneo getFormato() {
        return formato;
    }

    /**
     * Obtiene la fecha de inicio del torneo.
     *
     * @return fecha de inicio.
     */
    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    /**
     * Obtiene la fecha de finalización del torneo.
     *
     * @return fecha de finalización.
     */
    public LocalDate getFechaFin() {
        return fechaFin;
    }

    /**
     * Obtiene el estado actual del torneo.
     *
     * @return estado del torneo.
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Obtiene el calendario del torneo.
     *
     * @return calendario del torneo.
     */
    public Calendario getCalendario() {
        return calendario;
    }

    /**
     * Obtiene la clasificación del torneo.
     *
     * @return clasificación del torneo.
     */
    public Clasificacion getClasificacion() {
        return clasificacion;
    }

    /**
     * Obtiene la lista de participantes inscritos.
     * <p>
     * La lista retornada es de solo lectura.
     * </p>
     *
     * @return lista no modificable de participantes.
     */
    public List<Participante> getParticipantes() { //Devuelve la lista de participantes como solo lectura
        return Collections.unmodifiableList(participantes);
    }

    /**
     * Devuelve una representación textual básica del torneo.
     *
     * @return texto con nombre, deporte y estado del torneo.
     */
    @Override
    public String toString() { //toString para mostrar información básica del torneo
        return nombre + " | " + deporte.getNombre() + " | " + estado;
    }

    /**
     * Agrega directamente un partido al calendario del torneo.
     *
     * @param partido partido que se desea agregar.
     */
    public void agregarPartido(Partido partido) {
        calendario.agregarPartido(partido);
    }

    /**
     * Elimina un participante del torneo durante la fase de inscripción.
     *
     * @param participante participante que se desea eliminar.
     * @throws IllegalStateException si el torneo ya fue iniciado.
     */
    public void eliminarParticipante(Participante participante) {
        if (participante == null) return;
        if (!estado.equals("INSCRIPCION")) { //No se puede eliminar si el torneo ya empezó
            throw new IllegalStateException("No se pueden eliminar participantes una vez iniciado el torneo");
        }
        participantes.remove(participante);
    }
}