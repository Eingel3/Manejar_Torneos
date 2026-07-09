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

    @Test
    void testCrearDeporteIndividual() {
        Deporte deporte = controller.crearDeporte("Tenis", TipoParticipacion.INDIVIDUAL);
        assertNotNull(deporte);
        assertEquals("Tenis", deporte.getNombre());
        assertEquals(TipoParticipacion.INDIVIDUAL, deporte.getTipoParticipacion());
    }

    @Test
    void testBuscarDeporteExistente() {
        controller.crearDeporte("Futbol", TipoParticipacion.COLECTIVO);
        Deporte encontrado = controller.buscarDeporte("Futbol");
        assertNotNull(encontrado);
        assertEquals("Futbol", encontrado.getNombre());
    }

    @Test
    void testBuscarDeporteInexistente() {
        Deporte encontrado = controller.buscarDeporte("NoExiste");
        assertNull(encontrado);
    }
    @Test
    void testBuscarDeporteListaVacia() {
        Deporte encontrado = controller.buscarDeporte("Futbol");
        assertNull(encontrado);
    }

    @Test
    void testEliminarDeporteExistente() {
        controller.crearDeporte("Futbol", TipoParticipacion.COLECTIVO);
        boolean eliminado = controller.eliminarDeporte("Futbol");
        assertTrue(eliminado);
        assertNull(controller.buscarDeporte("Futbol"));
        assertEquals(0, controller.listaDeportes().size());
    }
    @Test
    void testEliminarDeporteInexistente() {
        boolean eliminado = controller.eliminarDeporte("Fantasma");
        assertFalse(eliminado);
    }
    @Test
    void testEliminarDeporteNoAfectaOtros() {
        controller.crearDeporte("Futbol", TipoParticipacion.COLECTIVO);
        controller.crearDeporte("Tenis", TipoParticipacion.INDIVIDUAL);
        controller.eliminarDeporte("Futbol");
        assertEquals(1, controller.listaDeportes().size());
        assertNotNull(controller.buscarDeporte("Tenis"));
    }

}
