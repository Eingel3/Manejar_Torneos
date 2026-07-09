package com.gestiontorneos.controller;

import com.gestiontorneos.model.torneo.Torneo;
import com.gestiontorneos.model.torneo.formato.FormatoTorneo;
import com.gestiontorneos.model.torneo.formato.LigaSimple;
import com.gestiontorneos.model.torneo.formato.EliminacionDirecta;
import com.gestiontorneos.model.deporte.TipoParticipacion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TorneoControllerTest {

    private TorneoController controller;

    @BeforeEach
    void setUp() {
        controller = new TorneoController();
    }

    @Test
    void testCrearTorneo() {
        Torneo torneo = controller.crearTorneo(
                "Torneo Futbol",
                "Futbol",
                 new LigaSimple(),
                "2026-08-01",
                "2026-08-30",
                TipoParticipacion.COLECTIVO
        );

        assertNotNull(torneo);
        assertEquals("Torneo Futbol", torneo.getNombre());
    }

    @Test
    void testCrearMultiplesTorneos() {
        controller.crearTorneo("T1", "Futbol", new LigaSimple(),
                "2026-08-01", "2026-08-30", TipoParticipacion.COLECTIVO);
        controller.crearTorneo("T2", "Tenis", new EliminacionDirecta(),
                "2026-09-01", "2026-09-15", TipoParticipacion.INDIVIDUAL);
        assertEquals(2, controller.listaTorneos().size());
    }


    // ===== buscarTorneo =====

    @Test
    void testBuscarTorneoExistente() {
        controller.crearTorneo(
                "Copa Verano",
                "Tenis",
                new EliminacionDirecta(),
                "2026-09-01",
                "2026-09-15",
                TipoParticipacion.INDIVIDUAL
        );

        Torneo encontrado = controller.buscarTorneo("Copa Verano");
        assertNotNull(encontrado);
        assertEquals("Copa Verano", encontrado.getNombre());
    }

    @Test
    void testBuscarTorneoInexistente() {
        Torneo encontrado = controller.buscarTorneo("No Existe");
        assertNull(encontrado);
    }

    // ===== eliminarTorneo =====

    @Test
    void testEliminarTorneoExistente() {
        controller.crearTorneo("ParaEliminar", "Futbol", new LigaSimple(),
                "2026-08-01", "2026-08-30", TipoParticipacion.COLECTIVO);
        boolean eliminado = controller.eliminarTorneo("ParaEliminar");
        assertTrue(eliminado);
        assertNull(controller.buscarTorneo("ParaEliminar"));
        assertEquals(0, controller.listaTorneos().size());
    }

    @Test
    void testEliminarTorneoInexistente() {
        boolean eliminado = controller.eliminarTorneo("Fantasma");
        assertFalse(eliminado);
    }
    
}
