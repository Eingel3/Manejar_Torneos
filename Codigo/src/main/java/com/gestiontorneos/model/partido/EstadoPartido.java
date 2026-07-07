package com.gestiontorneos.model.partido;

/**
 * Define los posibles estados de un partido.
 * <p>
 * Estos estados permiten controlar el ciclo de vida de un partido dentro del
 * calendario de un torneo.
 * </p>
 */
public enum EstadoPartido { //Enum para el estado del partido

    /**
     * Indica que el partido todavía no ha comenzado.
     */
    PENDIENTE, //Para los partidos que no han empezado

    /**
     * Indica que el partido se encuentra actualmente en desarrollo.
     */
    EN_CURSO, //Para los partidos que estan en curso

    /**
     * Indica que el partido terminó y ya posee un resultado registrado.
     */
    FINALIZADO, //Para los partidos que han finalizado

    /**
     * Indica que el partido fue cancelado y no se disputará.
     */
    CANCELADO //Para los partidos que han sido cancelados
}