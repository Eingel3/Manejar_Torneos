package com.gestiontorneos.gui;

import com.gestiontorneos.gui.organizador.PanelCrearPartido;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PanelCrearPartidoTest {
    private PanelCrearPartido panelCrearPartido;

    @BeforeEach
    void setUp() {
        panelCrearPartido = new PanelCrearPartido();
    }

    @Test
    @DisplayName("crearPartido debe agregar los campos necesarios")
    void testCrearPartido() {
        panelCrearPartido.crearPartido();
        Component[] comps = panelCrearPartido.getComponents();
        // Verificar que existe algún JTextField
        boolean tieneTextField = false;
        for (Component c : comps) {
            if (c instanceof JTextField) {
                tieneTextField = true;
                break;
            }
        }
        assertTrue(tieneTextField);
    }

    @Test
    @DisplayName("getEstadoPartido debe devolver 'Pendiente' por defecto")
    void testGetEstadoPartido() {
        panelCrearPartido.crearPartido();
        assertEquals("Pendiente", panelCrearPartido.getEstadoPartido());
    }

    @Test
    @DisplayName("limpiarFormulario debe resetear los campos")
    void testLimpiarFormulario() {
        panelCrearPartido.crearPartido();
        panelCrearPartido.getTxtNombreParticipanteLocal().setText("Local");
        panelCrearPartido.getTxtNombreParticipanteVisitante().setText("Visitante");
        panelCrearPartido.getEstadoPartidoJCombo().setSelectedItem("Finalizado");
        panelCrearPartido.limpiarFormulario();
        assertEquals("", panelCrearPartido.getNombreParticipanteLocal());
        assertEquals("", panelCrearPartido.getNombreParticipanteVisitante());
        assertEquals("Pendiente", panelCrearPartido.getEstadoPartido()); // primer elemento
    }
}
