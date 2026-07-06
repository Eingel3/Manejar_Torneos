package com.gestiontorneos.model.deporte;

/**
 * Define los tipos de participación posibles para un deporte.
 * <p>
 * Se utiliza para distinguir entre deportes practicados por participantes
 * individuales y deportes practicados por equipos.
 * </p>
 */
public enum TipoParticipacion { //Enum para el tipo de participacion

    /**
     * Representa deportes en los que participa una sola persona por inscripción.
     */
    INDIVIDUAL,  //Para deportes de 1v1

    /**
     * Representa deportes en los que participa un equipo o grupo de personas.
     */
    COLECTIVO //Para deportes de equipos
}