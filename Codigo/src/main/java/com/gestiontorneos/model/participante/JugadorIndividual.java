package com.gestiontorneos.model.participante;

/**
 * Representa un participante individual dentro de un torneo.
 * <p>
 * A diferencia de {@link Equipo}, este tipo de participante siempre cuenta como
 * una sola persona.
 * </p>
 *
 * @see Participante
 */
public class JugadorIndividual extends Participante {

    /**
     * Crea un nuevo jugador individual.
     *
     * @param nombre nombre del jugador.
     * @param contacto información de contacto del jugador.
     */
    public JugadorIndividual(String nombre, String contacto) {
        super(nombre, contacto);
    }

    /**
     * Obtiene la cantidad de integrantes del participante.
     *
     * @return siempre retorna {@code 1}.
     */
    @Override
    public int getCantidadIntegrantes() {
        return 1;
    }
}