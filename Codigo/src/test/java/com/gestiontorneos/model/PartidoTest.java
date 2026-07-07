package com.gestiontorneos.model;

import com.gestiontorneos.model.excepciones.DatosInvalidosException;
import com.gestiontorneos.model.participante.JugadorIndividual;
import com.gestiontorneos.model.participante.Participante;
import com.gestiontorneos.model.partido.EstadoPartido;
import com.gestiontorneos.model.partido.Partido;
import com.gestiontorneos.model.partido.Resultado;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PartidoTest {

    private Partido partido;
    private Participante local;
    private Participante visitante;

    @BeforeEach
    void setUp() { //Crea un partido antes de cada test
        local = new JugadorIndividual("Maxi", "Maxi@gmail.com");
        visitante = new JugadorIndividual("Maxi2", "maxi2@gmail.com");
        partido = new Partido(local, visitante, 1);
    }

    @Test
    void partidoNuevoEstaEnEstadoPendiente() { //Todos los partidos parten en estado pendiente
        assertEquals(EstadoPartido.PENDIENTE, partido.getEstado());
    }

    @Test
    void partidoNuevoNoTieneResultado() { //No hay resultado hasta que se registre
        assertNull(partido.getResultado());
    }

    @Test
    void participanteContraSimismoLanzaExcepcion() { //No se puede jugar contra sí mismo
        assertThrows(DatosInvalidosException.class, () ->
                new Partido(local, local, 1)
        );
    }

    @Test
    void registrarResultadoCambiaEstadoAFinalizado() { //Al registrar resultado, queda en estado finalizado
        partido.registrarResultado(new Resultado(2, 1));
        assertEquals(EstadoPartido.FINALIZADO, partido.getEstado());
    }

    @Test
    void getGanadorDevuelveLocalSiGanoLocal() { //Si el local tiene más puntos, devuelve local
        partido.registrarResultado(new Resultado(2, 1));
        assertEquals(local, partido.getGanador());
    }

    @Test
    void getGanadorDevuelveVisitanteSiGanoVisitante() { //Si el visitante tiene más puntos, devuelve visitante
        partido.registrarResultado(new Resultado(0, 3));
        assertEquals(visitante, partido.getGanador());
    }

    @Test
    void getGanadorDevuelveNullEnEmpate() { //En empate no hay ganador
        partido.registrarResultado(new Resultado(1, 1));
        assertNull(partido.getGanador());
    }

    @Test
    void noSePuedeRegistrarResultadoDosVeces() { //Un partido ya finalizado no acepta más resultados
        partido.registrarResultado(new Resultado(2, 1));
        assertThrows(IllegalStateException.class, () ->
                partido.registrarResultado(new Resultado(0, 3))
        );
    }

    @Test
    void cancelarPartidoCambiaEstado() { //Cancelar un partido debe cambiar su estado a cancelado
        partido.cancelar();
        assertEquals(EstadoPartido.CANCELADO, partido.getEstado());
    }

    @Test
    void noPuedeCancelarPartidoFinalizado() { //No tiene sentido cancelar algo ya terminado
        partido.registrarResultado(new Resultado(2, 1));
        assertThrows(IllegalStateException.class, () ->
                partido.cancelar()
        );
    }
}