package com.gestiontorneos.controller;

import com.gestiontorneos.model.deporte.Deporte;
import com.gestiontorneos.model.deporte.TipoParticipacion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class DeporteControllerTest {

    private DeporteController controller;
    @BeforeEach
    void setUp() {
        controller = new DeporteController();
    }

    @Test
    void testCrearDeporteColectivo() {
        Deporte deporte = controller.crearDeporte("Futbol", TipoParticipacion.COLECTIVO);
        assertNotNull(deporte);
        assertEquals("Futbol", deporte.getNombre());
        assertEquals(TipoParticipacion.COLECTIVO, deporte.getTipoParticipacion());
    }
}
