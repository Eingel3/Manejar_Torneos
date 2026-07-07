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

/**
 * Clase de pruebas unitarias para verificar el comportamiento de la clase
 * {@link LigaSimple}.
 * <p>
 * Estas pruebas validan la generación correcta de enfrentamientos bajo el
 * formato todos contra todos, la asignación de puntos según los resultados
 * y el orden de la tabla de clasificación.
 * </p>
 *
 * @see LigaSimple
 * @see Torneo
 * @see Participante
 */
class LigaSimpleTest {

    private LigaSimple liga;
    private Torneo torneo;
    private Participante part1;
    private Participante part2;
    private Participante part3;
    private Participante part4;

    /**
     * Inicializa los datos necesarios antes de cada prueba.
     * <p>
     * Crea un torneo con formato LigaSimple y cuatro participantes inscritos,
     * listos para que se genere el calendario de enfrentamientos.
     * </p>
     */
    @BeforeEach
    void setUp() {
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

    /**
     * Verifica que con cuatro participantes se generen exactamente seis partidos.
     * <p>
     * En una liga simple con N participantes, la cantidad de partidos debe ser
     * N*(N-1)/2. Para N=4, el resultado esperado es 6.
     * </p>
     */
    @Test
    void con4ParticipantesGeneraSeisPartidos() {
        torneo.generarCalendario();
        assertEquals(6, torneo.getCalendario().getPartidos().size());
    }

    /**
     * Verifica que con dos participantes se genere exactamente un partido.
     * <p>
     * Este es el caso mínimo posible en una liga simple. Con N=2,
     * la fórmula N*(N-1)/2 da como resultado 1 partido.
     * </p>
     */
    @Test
    void con2ParticipantesGeneraUnPartido() {
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

    /**
     * Verifica que el ganador de un partido recibe tres puntos y el perdedor cero.
     * <p>
     * En el formato liga simple se aplica el sistema estándar de puntuación:
     * victoria otorga 3 puntos, derrota otorga 0 puntos.
     * </p>
     */
    @Test
    void ganadorObtiene3Puntos() {
        torneo.generarCalendario();
        Partido partido = torneo.getCalendario().getPartidos().get(0);
        torneo.registrarResultado(partido, new Resultado(2, 0));

        assertEquals(3, torneo.getClasificacion().getPuntos(partido.getLocal()));
        assertEquals(0, torneo.getClasificacion().getPuntos(partido.getVisitante()));
    }

    /**
     * Verifica que un empate otorga un punto a cada participante.
     * <p>
     * Cuando ambos equipos obtienen el mismo puntaje, ninguno gana ni pierde,
     * y cada uno debe recibir 1 punto en la tabla de clasificación.
     * </p>
     */
    @Test
    void empateOtorga1PuntoACadaUno() {
        torneo.generarCalendario();
        Partido partido = torneo.getCalendario().getPartidos().get(0);
        torneo.registrarResultado(partido, new Resultado(1, 1));

        assertEquals(1, torneo.getClasificacion().getPuntos(partido.getLocal()));
        assertEquals(1, torneo.getClasificacion().getPuntos(partido.getVisitante()));
    }

    /**
     * Verifica que el líder de la clasificación es quien acumula más puntos.
     * <p>
     * Luego de registrar resultados donde part1 gana dos partidos consecutivos,
     * el método getLider debe retornar a part1 como el participante con mayor puntaje.
     * </p>
     */
    @Test
    void liderQuienTieneMasPuntos() {
        torneo.generarCalendario();
        List<Partido> partidos = torneo.getCalendario().getPartidos();
        torneo.registrarResultado(partidos.get(0), new Resultado(3, 0));
        torneo.registrarResultado(partidos.get(1), new Resultado(3, 0));

        assertEquals(part1, torneo.getClasificacion().getLider());
    }

    /**
     * Verifica que el primer elemento de la tabla ordenada corresponde al líder.
     * <p>
     * La tabla debe estar ordenada de mayor a menor puntaje, por lo que
     * el participante con más puntos debe ocupar la primera posición.
     * </p>
     */
    @Test
    void tablaOrdenadaLider() {
        torneo.generarCalendario();
        List<Partido> partidos = torneo.getCalendario().getPartidos();
        torneo.registrarResultado(partidos.get(0), new Resultado(3, 0));

        List<Participante> tabla = torneo.getClasificacion().getTablaOrdenada();
        assertEquals(part1, tabla.get(0));
    }
}