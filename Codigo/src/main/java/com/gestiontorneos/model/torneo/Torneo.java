package com.gestiontorneos.model.torneo;

import com.gestiontorneos.model.deporte.Deporte;
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
        participantes.add(participante);
        clasificacion.registrarParticipante(participante); //Lo agrega a la tabla con 0 puntos
    }

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
    //Getters
    public String getNombre() { return nombre; }
    public Deporte getDeporte() { return deporte; }
    public FormatoTorneo getFormato() { return formato; }
    public LocalDate getFechaInicio() { return fechaInicio; }
    public LocalDate getFechaFin() { return fechaFin; }
    public String getEstado() { return estado; }
    public Calendario getCalendario() { return calendario; }
    public Clasificacion getClasificacion() { return clasificacion; }

    public List<Participante> getParticipantes() { //Devuelve la lista de participantes como solo lectura
        return Collections.unmodifiableList(participantes);
    }

    @Override
    public String toString() { //toString para mostrar información básica del torneo
        return nombre + " | " + deporte.getNombre() + " | " + estado;
    }

    public void agregarPartido(Partido partido) {
        calendario.agregarPartido(partido);
    }
    public void eliminarParticipante(Participante participante) {
        if (participante == null) return;
        if (!estado.equals("INSCRIPCION")) { //No se puede eliminar si el torneo ya empezó
            throw new IllegalStateException("No se pueden eliminar participantes una vez iniciado el torneo");
        }
        participantes.remove(participante);
    }
}