package com.gestiontorneos.gui;

import com.gestiontorneos.gui.organizador.PanelCrearParticipante;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class PanelCrearParticipanteTest {
    private PanelCrearParticipante panel;

    @BeforeEach
    void setUp() {
        panel = new PanelCrearParticipante();
    }

    @Test
    @DisplayName("elegirTipoParticipante debe agregar un combo y retornar botón Siguiente")
    void testElegirTipoParticipante() {
        JButton siguiente = panel.elegirTipoParticipante();
        assertNotNull(siguiente);
        assertEquals("Siguiente", siguiente.getText());
        // El combo debe existir
        Component[] comps = panel.getComponents();
        boolean comboEncontrado = false;
        for (Component c : comps) {
            if (c instanceof JComboBox) comboEncontrado = true;
        }
        assertTrue(comboEncontrado, "Debe existir el JComboBox de tipo de participante");
    }

    @Test
    @DisplayName("getTipoParticipante debe devolver la selección correcta")
    void testGetTipoParticipante() {
        panel.elegirTipoParticipante();
        // Por defecto está "Equipo"
        assertEquals("Equipo", panel.getTipoParticipante());
    }

    @Test
    @DisplayName("limpiarFormulario debe borrar los campos de texto")
    void testLimpiarFormulario() {
        // Primero agregamos campos
        panel.agregarParticipante();
        panel.getTxtNombreParticipante().setText("Juan");
        panel.getTxtContacto().setText("email");
        panel.limpiarFormulario();
        assertEquals("", panel.getNombreParticipante());
        assertEquals("", panel.getContacto());
    }
}
