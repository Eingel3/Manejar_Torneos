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
    @DisplayName("mostrarFormularioCompleto debe agregar todos los campos")
    void testMostrarFormularioCompleto() {
        Component[] comps = panel.getComponents();
        boolean tieneTextField = false;
        boolean tieneBotonCrear = false;
        boolean tieneBotonCancelar = false;
        for (Component c : comps) {
            if (c instanceof JTextField) tieneTextField = true;
            if (c instanceof JButton) {
                String text = ((JButton) c).getText();
                if ("Crear".equals(text)) tieneBotonCrear = true;
                if ("Cancelar".equals(text)) tieneBotonCancelar = true;
            }
        }
        assertTrue(tieneTextField, "Debe tener campos de texto");
        assertTrue(tieneBotonCrear, "Debe tener boton Crear");
        assertTrue(tieneBotonCancelar, "Debe tener boton Cancelar");
    }

    @Test
    @DisplayName("limpiarFormulario debe borrar los campos de texto")
    void testLimpiarFormulario() {
        panel.getTxtNombreParticipante().setText("Juan");
        panel.getTxtContacto().setText("email");
        panel.limpiarFormulario();
        assertEquals("", panel.getNombreParticipante());
        assertEquals("", panel.getContacto());
    }
}
