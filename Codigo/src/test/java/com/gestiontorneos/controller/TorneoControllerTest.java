package com.gestiontorneos.controller;

import com.gestiontorneos.model.participante.Equipo;
import com.gestiontorneos.model.torneo.Torneo;
import com.gestiontorneos.model.torneo.formato.FormatoTorneo;
import com.gestiontorneos.model.torneo.formato.LigaSimple;
import com.gestiontorneos.model.torneo.formato.EliminacionDirecta;
import com.gestiontorneos.model.deporte.TipoParticipacion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

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

    @Test
    void testEliminarParticipante() {
        controller.crearTorneo("Futbol", "Futbol", new LigaSimple(),
                "2026-08-01", "2026-08-30", TipoParticipacion.COLECTIVO);
        Equipo equipo = new Equipo("Los Leones", "contacto@leo.com", Arrays.asList("Juan", "Pedro"));
        controller.registrarParticipante("Futbol", equipo);
        boolean eliminado = controller.eliminarParticipante("Futbol", equipo);
        assertTrue(eliminado);
        assertEquals(0, controller.listarParticipantes("Futbol").size());
    }

    @Test
    void testListaTorneosVacia() {
        List<Torneo> lista = controller.listaTorneos();
        assertNotNull(lista);
        assertTrue(lista.isEmpty());
    }

    @Test
    void testListaTorneosConTorneos() {
        controller.crearTorneo("T1", "Futbol", new LigaSimple(),
                "2026-08-01", "2026-08-30", TipoParticipacion.COLECTIVO);
        controller.crearTorneo("T2", "Basquet", new LigaSimple(),
                "2026-08-01", "2026-08-30", TipoParticipacion.COLECTIVO);
        assertEquals(2, controller.listaTorneos().size());
    }

    @Test
    void testRegistrarParticipante() {
        controller.crearTorneo("Futbol", "Futbol", new LigaSimple(),
                "2026-08-01", "2026-08-30", TipoParticipacion.COLECTIVO);
        Equipo equipo = new Equipo("Los Leones", "contacto@leo.com", Arrays.asList("Juan", "Pedro"));
        boolean registrado = controller.registrarParticipante("Futbol", equipo);
        assertTrue(registrado);
        assertEquals(1, controller.listarParticipantes("Futbol").size());
    }

    @Test
    void testGenerarCalendario() {
        controller.crearTorneo("Futbol", "Futbol", new LigaSimple(),
                "2026-08-01", "2026-08-30", TipoParticipacion.COLECTIVO);
        Equipo e1 = new Equipo("Equipo A", "a@a.com", Arrays.asList("J1", "J2"));
        Equipo e2 = new Equipo("Equipo B", "b@b.com", Arrays.asList("J3", "J4"));
        controller.registrarParticipante("Futbol", e1);
        controller.registrarParticipante("Futbol", e2);
        boolean generado = controller.generarCalendario("Futbol");
        assertTrue(generado);
        assertEquals("EN_CURSO", controller.buscarTorneo("Futbol").getEstado());
    }

    @Test
    void testCantidadPartidos() {
        controller.crearTorneo("Futbol", "Futbol", new LigaSimple(),
                "2026-08-01", "2026-08-30", TipoParticipacion.COLECTIVO);
        Equipo e1 = new Equipo("A", "a@a.com", Arrays.asList("J1", "J2"));
        Equipo e2 = new Equipo("B", "b@b.com", Arrays.asList("J3", "J4"));
        controller.registrarParticipante("Futbol", e1);
        controller.registrarParticipante("Futbol", e2);
        controller.generarCalendario("Futbol");
        int cantidad = controller.cantidadPartidos("Futbol");
        assertTrue(cantidad > 0);
    }

    @Test
    void testCrearPartido() {
        controller.crearTorneo("Futbol", "Futbol", new LigaSimple(),
                "2026-08-01", "2026-08-30", TipoParticipacion.COLECTIVO);
        Equipo e1 = new Equipo("A", "a@a.com", Arrays.asList("J1"));
        Equipo e2 = new Equipo("B", "b@b.com", Arrays.asList("J2"));
        controller.registrarParticipante("Futbol", e1);
        controller.registrarParticipante("Futbol", e2);
        boolean creado = controller.crearPartido(e1, e2, "Futbol", 1);
        assertTrue(creado);
    }




}
