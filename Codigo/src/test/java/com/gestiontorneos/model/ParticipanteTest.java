package com.gestiontorneos.model;

import com.gestiontorneos.model.excepciones.DatosInvalidosException;
import com.gestiontorneos.model.participante.Equipo;
import com.gestiontorneos.model.participante.JugadorIndividual;
import com.gestiontorneos.model.participante.Participante;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase de pruebas unitarias para verificar el comportamiento de la jerarquía
 * {@link Participante}.
 * <p>
 * Estas pruebas validan el comportamiento polimórfico de {@link JugadorIndividual}
 * y {@link Equipo}, incluyendo validaciones del constructor, el método
 * getCantidadIntegrantes y la igualdad basada en identificador único.
 * </p>
 *
 * @see Participante
 * @see JugadorIndividual
 * @see Equipo
 */
class ParticipanteTest {

    /**
     * Verifica que un jugador individual siempre reporta exactamente un integrante.
     * <p>
     * Por definición, un jugador individual representa a una sola persona,
     * por lo que getCantidadIntegrantes debe retornar siempre 1.
     * </p>
     */
    @Test
    void jugadorIndividualTieneUnIntegrante() {
        Participante jugador = new JugadorIndividual("Maxi", "maxi@gmail.com");
        assertEquals(1, jugador.getCantidadIntegrantes());
    }

    /**
     * Verifica que un equipo reporta correctamente la cantidad de integrantes recibidos.
     * <p>
     * El método getCantidadIntegrantes debe coincidir con el tamaño de la lista
     * de integrantes proporcionada al momento de la creación del equipo.
     * </p>
     */
    @Test
    void equipoTieneCantidadCorrectaDeIntegrantes() {
        Participante equipo = new Equipo("Udec", "udecdeportes@gmail.com",
                List.of("JugadorInformatico1", "JugadorInformatico2", "JugadorInformatico3"));
        assertEquals(3, equipo.getCantidadIntegrantes());
    }

    /**
     * Verifica que no sea posible crear un participante con nombre vacío.
     * <p>
     * El constructor de {@link Participante} debe lanzar una
     * {@link DatosInvalidosException} cuando el nombre es una cadena vacía.
     * </p>
     */
    @Test
    void nombreVacioLanzaExcepcion() {
        assertThrows(DatosInvalidosException.class, () ->
                new JugadorIndividual("", "cualquiercorreo@gmail.com")
        );
    }

    /**
     * Verifica que no sea posible crear un participante con nombre null.
     * <p>
     * El constructor de {@link Participante} debe lanzar una
     * {@link DatosInvalidosException} cuando el nombre es null.
     * </p>
     */
    @Test
    void nombreNullLanzaExcepcion() {
        assertThrows(DatosInvalidosException.class, () ->
                new JugadorIndividual(null, "cualquiercorreo2@gmail.com")
        );
    }

    /**
     * Verifica que no sea posible crear un equipo sin integrantes.
     * <p>
     * Un equipo sin integrantes no tiene sentido en el contexto del sistema,
     * por lo que el constructor debe lanzar una {@link DatosInvalidosException}
     * cuando la lista de integrantes está vacía.
     * </p>
     */
    @Test
    void equipoSinIntegrantesLanzaExcepcion() {
        assertThrows(DatosInvalidosException.class, () ->
                new Equipo("Nadie Team", "     @gmail.com", List.of())
        );
    }

    /**
     * Verifica que dos participantes distintos no son iguales aunque tengan el mismo nombre.
     * <p>
     * La igualdad entre participantes se basa en el identificador único generado
     * automáticamente, no en el nombre. Por lo tanto, dos instancias con el mismo
     * nombre pero creadas por separado deben ser consideradas distintas.
     * </p>
     */
    @Test
    void dosJugadoresDistintosNoSonIguales() {
        Participante jugador1 = new JugadorIndividual("Ana", "ana@gmail.com");
        Participante jugador2 = new JugadorIndividual("Ana", "ana@gmail.com");
        assertNotEquals(jugador1, jugador2);
    }

    /**
     * Verifica que un participante es siempre igual a sí mismo.
     * <p>
     * Al comparar un objeto consigo mismo, el método equals debe retornar
     * true independientemente de la implementación interna.
     * </p>
     */
    @Test
    void mismoJugadorEsIgualASiMismo() {
        Participante jugador = new JugadorIndividual("Ana", "ana@gmail.com");
        assertEquals(jugador, jugador);
    }

    /**
     * Verifica que agregar un integrante a un equipo aumenta correctamente su cantidad.
     * <p>
     * Luego de llamar a agregarIntegrante, el método getCantidadIntegrantes
     * debe reflejar el nuevo total de integrantes del equipo.
     * </p>
     */
    @Test
    void agregarIntegranteAumentaCantidad() {
        Equipo equipo = new Equipo("Udec FC", "udecFC@gmail.com",
                List.of("Pedro", "Juan"));
        equipo.agregarIntegrante("Diego");
        assertEquals(3, equipo.getCantidadIntegrantes());
    }
}