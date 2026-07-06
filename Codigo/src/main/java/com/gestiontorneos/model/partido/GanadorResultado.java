package com.gestiontorneos.model.partido;

/**
 * Define los posibles resultados ganadores de un partido.
 */
public enum GanadorResultado {

    /**
     * Indica que ganó el participante local.
     */
    LOCAL,

    /**
     * Indica que ganó el participante visitante.
     */
    VISITANTE,

    /**
     * Indica que ambos participantes terminaron empatados.
     */
    EMPATE
}