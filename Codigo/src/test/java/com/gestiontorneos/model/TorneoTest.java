package com.gestiontorneos.model;

import com.gestiontorneos.model.deporte.Deporte;
import com.gestiontorneos.model.deporte.TipoParticipacion;
import com.gestiontorneos.model.excepciones.DatosInvalidosException;
import com.gestiontorneos.model.participante.JugadorIndividual;
import com.gestiontorneos.model.participante.Participante;
import com.gestiontorneos.model.torneo.Torneo;
import com.gestiontorneos.model.torneo.formato.LigaSimple;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase de pruebas unitarias para verificar el comportamiento de la clase
 * {@link Torneo}.
 * <p>
 * Estas pruebas validan la creación del torneo, las validaciones del constructor,
 * la inscripción de participantes y la generación del calendario de enfrentamientos.
 * </p>
 *
 * @see Torneo
 * @see LigaSimple
 * @see Participante
 */
class TorneoTest {

    private Torneo torneo;
    private Participante jugador1;
    private Participante jugador2;
    private Participante jugador3;

    /**
     * Inicializa los datos necesarios antes de cada prueba.
     * <p>
     * Crea un torneo en estado de inscripción con formato LigaSimple
     * y tres jugadores individuales disponibles para ser inscritos.
     * </p>
     */
    @BeforeEach
    void setUp() {
        torneo = new Torneo(
                "Copa test",
                new Deporte("Fútbol 1vs1", TipoParticipacion.INDIVIDUAL),
                new LigaSimple(),
                LocalDate.of(2024, 3, 1),
                LocalDate.of(2024, 3, 15)
        );
        jugador1 = new JugadorIndividual("Maxi", "maxi@gmail.com");
        jugador2 = new JugadorIndividual("Agustín", "agus@gmail.com");
        jugador3 = new JugadorIndividual("Felipazo", "felipazo@gmail.com");
    }

    /**
     * Verifica que no sea posible crear un torneo con nombre vacío.
     * <p>
     * El constructor debe lanzar una {@link DatosInvalidosException} cuando
     * el nombre proporcionado es una cadena vacía.
     * </p>
     */
    @Test
    void torneoNombreVacioLanzaExcepcion() {
        assertThrows(DatosInvalidosException.class, () ->
                new Torneo("", new Deporte("Taca-taca", TipoParticipacion.INDIVIDUAL),
                        new LigaSimple(),
                        LocalDate.of(2024, 3, 1),
                        LocalDate.of(2024, 3, 15))
        );
    }

    /**
     * Verifica que no sea posible crear un torneo con fecha de fin anterior a la de inicio.
     * <p>
     * El constructor debe lanzar una {@link DatosInvalidosException} cuando
     * la fecha de fin es cronológicamente anterior a la fecha de inicio.
     * </p>
     */
    @Test
    void fechaFinAntesQueInicioLanzaExcepcion() {
        assertThrows(DatosInvalidosException.class, () ->
                new Torneo("Test", new Deporte("Cachipun", TipoParticipacion.INDIVIDUAL),
                        new LigaSimple(),
                        LocalDate.of(2026, 7, 8),
                        LocalDate.of(2026, 7, 1))
        );
    }

    /**
     * Verifica que un participante válido puede inscribirse correctamente en el torneo.
     * <p>
     * Luego de agregar un participante, la lista de inscritos debe reflejar
     * exactamente un elemento.
     * </p>
     */
    @Test
    void agregarParticipanteExitoso() {
        torneo.agregarParticipante(jugador1);
        assertEquals(1, torneo.getParticipantes().size());
    }

    /**
     * Verifica que un mismo participante no pueda inscribirse dos veces en el torneo.
     * <p>
     * El sistema debe detectar duplicados mediante el equals de {@link Participante}
     * y lanzar una {@link DatosInvalidosException} al intentar inscribir
     * al mismo participante por segunda vez.
     * </p>
     */
    @Test
    void participanteDuplicadoLanzaExcepcion() {
        torneo.agregarParticipante(jugador1);
        assertThrows(DatosInvalidosException.class, () ->
                torneo.agregarParticipante(jugador1)
        );
    }

    /**
     * Verifica que no sea posible generar el calendario con menos de dos participantes.
     * <p>
     * Un torneo requiere al menos dos participantes para poder generar enfrentamientos.
     * Si se intenta generar el calendario con solo uno, debe lanzarse una
     * {@link IllegalStateException}.
     * </p>
     */
    @Test
    void generarCalendarioConMenosDeDosParticipantesLanzaExcepcion() {
        torneo.agregarParticipante(jugador1);
        assertThrows(IllegalStateException.class, () ->
                torneo.generarCalendario()
        );
    }

    /**
     * Verifica que generar el calendario cambia el estado del torneo a EN_CURSO.
     * <p>
     * Una vez generados los enfrentamientos, el torneo debe pasar del estado
     * INSCRIPCION al estado EN_CURSO, impidiendo nuevas inscripciones.
     * </p>
     */
    @Test
    void generarCalendarioCambiaTorneoAEnCurso() {
        torneo.agregarParticipante(jugador1);
        torneo.agregarParticipante(jugador2);
        torneo.generarCalendario();
        assertEquals("EN_CURSO", torneo.getEstado());
    }

    /**
     * Verifica que no sea posible inscribir participantes una vez iniciado el torneo.
     * <p>
     * Cuando el torneo está en estado EN_CURSO, intentar agregar un nuevo
     * participante debe lanzar una {@link IllegalStateException}.
     * </p>
     */
    @Test
    void noSePuedeInscribirDespuesDeIniciarTorneo() {
        torneo.agregarParticipante(jugador1);
        torneo.agregarParticipante(jugador2);
        torneo.generarCalendario();
        assertThrows(IllegalStateException.class, () ->
                torneo.agregarParticipante(jugador3)
        );
    }
}