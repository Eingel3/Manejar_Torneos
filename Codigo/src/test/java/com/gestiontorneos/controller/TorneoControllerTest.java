package com.gestiontorneos.controller;

import com.gestiontorneos.model.torneo.Torneo;
import com.gestiontorneos.model.torneo.formato.FormatoTorneo;
import com.gestiontorneos.model.torneo.formato.LigaSimple;
import com.gestiontorneos.model.torneo.formato.EliminacionDirecta;
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
                "2026-08-30"
        );

        assertNotNull(torneo);
        assertEquals("Torneo Futbol", torneo.getNombre());
    }

    @Test
    void testBuscarTorneoExistente() {
        controller.crearTorneo(
                "Copa Verano",
                "Tenis",
                new EliminacionDirecta(),
                "2026-09-01",
                "2026-09-15"
        );

        Torneo encontrado = controller.buscarTorneo("Copa Verano");
        assertNotNull(encontrado);
        assertEquals("Copa Verano", encontrado.getNombre());
    }

    
}
