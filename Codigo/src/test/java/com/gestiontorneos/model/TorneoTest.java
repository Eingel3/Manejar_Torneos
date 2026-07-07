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

class TorneoTest {

    private Torneo torneo;
    private Participante jugador1;
    private Participante jugador2;
    private Participante jugador3;

    @BeforeEach
    void setUp() { //Se ejecuta antes de cada test
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

    @Test
    void torneoNombreVacioLanzaExcepcion() { //No debería poder crearse un torneo sin nombre
        assertThrows(DatosInvalidosException.class, () ->
                new Torneo("", new Deporte("Taca-taca", TipoParticipacion.INDIVIDUAL),
                        new LigaSimple(),
                        LocalDate.of(2024, 3, 1),
                        LocalDate.of(2024, 3, 15))
        );
    }

    @Test
    void fechaFinAntesQueInicioLanzaExcepcion() { //Fecha fin no puede ser anterior a fecha inicio
        assertThrows(DatosInvalidosException.class, () ->
                new Torneo("Test", new Deporte("Cachipun", TipoParticipacion.INDIVIDUAL),
                        new LigaSimple(),
                        LocalDate.of(2026, 7, 8), //Inicio
                        LocalDate.of(2026, 7, 1))  //Fin anterior al inicio
        );
    }

    @Test
    void agregarParticipanteExitoso() { //Debería poder inscribirse sin problemas
        torneo.agregarParticipante(jugador1);
        assertEquals(1, torneo.getParticipantes().size());
    }

    @Test
    void participanteDuplicadoLanzaExcepcion() { //No debería poder inscribirse dos veces
        torneo.agregarParticipante(jugador1);
        assertThrows(DatosInvalidosException.class, () ->
                torneo.agregarParticipante(jugador1)
        );
    }

    @Test
    void generarCalendarioConMenosDeDosParticipantesLanzaExcepcion() { //Un torneo necesita al menos 2 participantes para jugarse
        torneo.agregarParticipante(jugador1);
        assertThrows(IllegalStateException.class, () ->
                torneo.generarCalendario()
        );
    }

    @Test
    void generarCalendarioCambiaCambiaTorneoAEnCurso() { //Después de generar, el estado debe ser EN_CURSO
        torneo.agregarParticipante(jugador1);
        torneo.agregarParticipante(jugador2);
        torneo.generarCalendario();
        assertEquals("EN_CURSO", torneo.getEstado());
    }

    @Test
    void noSePuedeInscribirDespuesDeIniciarTorneo() { //Una vez en EN_CURSO, no se aceptan más inscripciones
        torneo.agregarParticipante(jugador1);
        torneo.agregarParticipante(jugador2);
        torneo.generarCalendario();
        assertThrows(IllegalStateException.class, () ->
                torneo.agregarParticipante(jugador3)
        );
    }
}