package com.gestiontorneos.model;

import com.gestiontorneos.model.deporte.Deporte;
import com.gestiontorneos.model.deporte.TipoParticipacion;
import com.gestiontorneos.model.participante.JugadorIndividual;
import com.gestiontorneos.model.participante.Participante;
import com.gestiontorneos.model.partido.Partido;
import com.gestiontorneos.model.partido.Resultado;
import com.gestiontorneos.model.torneo.Torneo;
import com.gestiontorneos.model.torneo.formato.LigaSimple;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class LigaSimpleTest {

    private LigaSimple liga;
    private Torneo torneo;
    private Participante part1;
    private Participante part2;
    private Participante part3;
    private Participante part4;

    @BeforeEach
    void setUp() { //Crea una liga y un torneo con 4 participantes antes de cada test
        liga = new LigaSimple();
        torneo = new Torneo(
                "Copa Liga",
                new Deporte("Ajedrez", TipoParticipacion.INDIVIDUAL),
                liga,
                LocalDate.of(2024, 3, 1),
                LocalDate.of(2024, 3, 15)
        );
        part1 = new JugadorIndividual("part1", "part1@gmail.com");
        part2 = new JugadorIndividual("part2", "part2@gmail.com");
        part3 = new JugadorIndividual("part3", "part3@gmail.com");
        part4 = new JugadorIndividual("part4", "part4@gmail.com");

        torneo.agregarParticipante(part1);
        torneo.agregarParticipante(part2);
        torneo.agregarParticipante(part3);
        torneo.agregarParticipante(part4);
    }

    @Test
    void con4ParticipantesGeneraSeisPartidos() { //Con N participantes deben generarse N*(N-1)/2 partidos
        torneo.generarCalendario();
        assertEquals(6, torneo.getCalendario().getPartidos().size());
    }

    @Test
    void con2ParticipantesGeneraUnPartido() { //Caso mínimo, con 2 participantes se juega un partido
        Torneo torneoMin = new Torneo(
                "Copa caso Minimo",
                new Deporte("Ajedrez", TipoParticipacion.INDIVIDUAL),
                new LigaSimple(),
                LocalDate.of(2024, 3, 1),
                LocalDate.of(2024, 3, 15)
        );
        torneoMin.agregarParticipante(part1);
        torneoMin.agregarParticipante(part2);
        torneoMin.generarCalendario();
        assertEquals(1, torneoMin.getCalendario().getPartidos().size());
    }

    @Test
    void ganadorObtiene3Puntos() { //La victoria debe sumar 3 puntos
        torneo.generarCalendario();
        Partido partido = torneo.getCalendario().getPartidos().get(0);
        torneo.registrarResultado(partido, new Resultado(2, 0)); //Local gana

        assertEquals(3, torneo.getClasificacion().getPuntos(partido.getLocal()));
        assertEquals(0, torneo.getClasificacion().getPuntos(partido.getVisitante()));
    }

    @Test
    void empateOtorga1PuntoACadaUno() { //El empate debe sumar 1 punto a cada participante
        torneo.generarCalendario();
        Partido partido = torneo.getCalendario().getPartidos().get(0);
        torneo.registrarResultado(partido, new Resultado(1, 1));

        assertEquals(1, torneo.getClasificacion().getPuntos(partido.getLocal()));
        assertEquals(1, torneo.getClasificacion().getPuntos(partido.getVisitante()));
    }

    @Test
    void liderQuienTieneMasPuntos() { //getLider debe devolver al que acumula más puntos
        torneo.generarCalendario();
        List<Partido> partidos = torneo.getCalendario().getPartidos();
        torneo.registrarResultado(partidos.get(0), new Resultado(3, 0));
        torneo.registrarResultado(partidos.get(1), new Resultado(3, 0));

        assertEquals(part1, torneo.getClasificacion().getLider());
    }

    @Test
    void tablaOrdenadaLider() { //El primero de la tabla ordenada debe ser el líder
        torneo.generarCalendario();
        List<Partido> partidos = torneo.getCalendario().getPartidos();
        torneo.registrarResultado(partidos.get(0), new Resultado(3, 0));

        List<Participante> tabla = torneo.getClasificacion().getTablaOrdenada();
        assertEquals(part1, tabla.get(0));
    }
}