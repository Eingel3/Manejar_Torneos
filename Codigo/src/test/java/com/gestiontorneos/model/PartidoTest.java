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

/**
 * Clase de pruebas unitarias para verificar el comportamiento de la clase
 * {@link Partido}.
 * <p>
 * Estas pruebas validan la creación de partidos, su estado inicial, el registro
 * de resultados, la obtención del ganador y las restricciones relacionadas con
 * partidos finalizados o cancelados.
 * </p>
 *
 * @see Partido
 * @see Resultado
 * @see EstadoPartido
 * @see Participante
 */
class PartidoTest {

    private Partido partido;
    private Participante local;
    private Participante visitante;

    /**
     * Inicializa los datos necesarios antes de cada prueba.
     * <p>
     * Crea dos participantes individuales y un partido pendiente entre ambos.
     * Esto permite que cada test se ejecute con un estado limpio e independiente.
     * </p>
     */
    @BeforeEach
    void setUp() {
        local = new JugadorIndividual("Maxi", "Maxi@gmail.com");
        visitante = new JugadorIndividual("Maxi2", "maxi2@gmail.com");
        partido = new Partido(local, visitante, 1);
    }

    /**
     * Verifica que un partido recién creado tenga estado pendiente.
     * <p>
     * Todo partido nuevo debe comenzar en {@link EstadoPartido#PENDIENTE}, ya que
     * aún no posee resultado registrado ni ha sido cancelado.
     * </p>
     */
    @Test
    void partidoNuevoEstaEnEstadoPendiente() {
        assertEquals(EstadoPartido.PENDIENTE, partido.getEstado());
    }

    /**
     * Verifica que un partido recién creado no tenga resultado asociado.
     * <p>
     * El resultado debe permanecer como {@code null} hasta que se registre
     * explícitamente mediante el método correspondiente.
     * </p>
     */
    @Test
    void partidoNuevoNoTieneResultado() {
        assertNull(partido.getResultado());
    }

    /**
     * Verifica que no sea posible crear un partido donde el participante local y
     * el visitante sean el mismo objeto.
     * <p>
     * Un participante no puede competir contra sí mismo, por lo que el constructor
     * debe lanzar una {@link DatosInvalidosException}.
     * </p>
     */
    @Test
    void participanteContraSimismoLanzaExcepcion() {
        assertThrows(DatosInvalidosException.class, () ->
                new Partido(local, local, 1)
        );
    }

    /**
     * Verifica que registrar un resultado cambie el estado del partido a
     * finalizado.
     * <p>
     * Una vez registrado el marcador, el partido debe quedar en
     * {@link EstadoPartido#FINALIZADO}.
     * </p>
     */
    @Test
    void registrarResultadoCambiaEstadoAFinalizado() {
        partido.registrarResultado(new Resultado(2, 1));
        assertEquals(EstadoPartido.FINALIZADO, partido.getEstado());
    }

    /**
     * Verifica que el ganador del partido sea el participante local cuando su
     * puntaje es mayor que el del visitante.
     */
    @Test
    void getGanadorDevuelveLocalSiGanoLocal() {
        partido.registrarResultado(new Resultado(2, 1));
        assertEquals(local, partido.getGanador());
    }

    /**
     * Verifica que el ganador del partido sea el participante visitante cuando su
     * puntaje es mayor que el del local.
     */
    @Test
    void getGanadorDevuelveVisitanteSiGanoVisitante() {
        partido.registrarResultado(new Resultado(0, 3));
        assertEquals(visitante, partido.getGanador());
    }

    /**
     * Verifica que un partido empatado no tenga ganador.
     * <p>
     * Si ambos participantes obtienen el mismo puntaje, el método encargado de
     * obtener el ganador debe retornar {@code null}.
     * </p>
     */
    @Test
    void getGanadorDevuelveNullEnEmpate() {
        partido.registrarResultado(new Resultado(1, 1));
        assertNull(partido.getGanador());
    }

    /**
     * Verifica que no se pueda registrar un resultado más de una vez.
     * <p>
     * Cuando un partido ya está finalizado, intentar registrar otro resultado debe
     * lanzar una {@link IllegalStateException}.
     * </p>
     */
    @Test
    void noSePuedeRegistrarResultadoDosVeces() {
        partido.registrarResultado(new Resultado(2, 1));
        assertThrows(IllegalStateException.class, () ->
                partido.registrarResultado(new Resultado(0, 3))
        );
    }

    /**
     * Verifica que cancelar un partido cambie su estado a cancelado.
     * <p>
     * Un partido pendiente puede ser cancelado y, luego de hacerlo, debe quedar en
     * {@link EstadoPartido#CANCELADO}.
     * </p>
     */
    @Test
    void cancelarPartidoCambiaEstado() {
        partido.cancelar();
        assertEquals(EstadoPartido.CANCELADO, partido.getEstado());
    }

    /**
     * Verifica que no sea posible cancelar un partido que ya finalizó.
     * <p>
     * Una vez registrado el resultado, el partido queda finalizado y no debería
     * permitir cambiar su estado a cancelado. En este caso se espera una
     * {@link IllegalStateException}.
     * </p>
     */
    @Test
    void noPuedeCancelarPartidoFinalizado() {
        partido.registrarResultado(new Resultado(2, 1));
        assertThrows(IllegalStateException.class, () ->
                partido.cancelar()
        );
    }
}