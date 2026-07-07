package com.gestiontorneos.gui;
import com.gestiontorneos.gui.compartido.PanelInformacion;
import com.gestiontorneos.gui.compartido.PanelResultados;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class PanelResultadosTest {
    private PanelResultados panelResultados;

    @BeforeEach
    void setUp() {
        panelResultados = new PanelResultados();
    }

    @Test
    @DisplayName("El constructor debe configurar correctamente las propiedades del panelResultados")
    void testConstructorProperties() {
        assertNotNull(panelResultados);
        assertEquals(Color.CYAN, panelResultados.getBackground());
        assertTrue(panelResultados.getLayout() instanceof FlowLayout);
        assertEquals(FlowLayout.LEFT, ((FlowLayout) panelResultados.getLayout()).getAlignment());
        assertTrue(panelResultados.getBorder() instanceof LineBorder);
        assertEquals(Color.LIGHT_GRAY, ((LineBorder) panelResultados.getBorder()).getLineColor());

        Dimension expectedSize = new Dimension(
                PanelInformacion.VENTANASINMENU.getAncho(),
                PanelInformacion.VENTANASINMENU.getAlto());
        assertEquals(expectedSize, panelResultados.getPreferredSize());
    }



    @Test
    @DisplayName("Los atributos iniciales deben tener los valores por defecto")
    void testDefaultAttributes() {
        assertEquals("Nombre Torneo", panelResultados.getNombre());
        assertEquals("descripcion", panelResultados.getDescripcion());
        assertEquals("fechas", panelResultados.getFechas());
        assertEquals("deporte", panelResultados.getDeporte());
        assertEquals("tipoCompeticion", panelResultados.getTipoCompeticion());
        assertEquals("ganador", panelResultados.getGanador());
        assertEquals("participante", panelResultados.getParticipantes());
    }

    @Test
    @DisplayName("Las etiquetas deben mostrar los textos iniciales correctos")
    void testLabelsInitialText() {
        assertEquals("Nombre Torneo", panelResultados.getNombreL().getText());
        assertEquals("descripcion", panelResultados.getDescripcionL().getText());
        assertEquals("fechas", panelResultados.getFechasL().getText());
        assertEquals("deporte", panelResultados.getDeporteL().getText());
        assertEquals("tipoCompeticion", panelResultados.getTipoCompeticionL().getText());
        assertEquals("ganador", panelResultados.getGanadorL().getText());
        assertEquals("participante", panelResultados.getParticipantesL().getText());
    }

    @Test
    @DisplayName("actualizarNombre debe cambiar el texto de la etiqueta correspondiente")
    void testActualizarNombre() {
        panelResultados.actualizarNombre("Torneo de prueba");
        assertEquals("Torneo de prueba", panelResultados.getNombreL().getText());
    }

    @Test
    @DisplayName("actualizarDescripcion debe cambiar el texto de la etiqueta")
    void testActualizarDescripcion() {
        panelResultados.actualizarDescripcion("Torneo internacional");
        assertEquals("Torneo internacional", panelResultados.getDescripcionL().getText());
    }

    @Test
    @DisplayName("actualizarFechas debe cambiar el texto")
    void testActualizarFechas() {
        panelResultados.actualizarFechas("2025-10-8 hasta 2027-07-07");
        assertEquals("2025-10-8 hasta 2027-07-07", panelResultados.getFechasL().getText());
    }

    @Test
    @DisplayName("actualizarDeporte debe cambiar el texto")
    void testActualizarDeporte() {
        panelResultados.actualizarDeporte("Volleyball");
        assertEquals("Volleyball", panelResultados.getDeporteL().getText());
    }

    @Test
    @DisplayName("actualizarTipoCompeticion debe cambiar el texto")
    void testActualizarTipoCompeticion() {
        panelResultados.actualizarTipoCompeticion("Liga Simple");
        assertEquals("Liga Simple", panelResultados.getTipoCompeticionL().getText());
    }

    @Test
    @DisplayName("actualizarGanador debe cambiar el texto")
    void testActualizarGanador() {
        panelResultados.actualizarGanador("Equipo Tigresas");
        assertEquals("Equipo Tigresas", panelResultados.getGanadorL().getText());
    }

    @Test
    @DisplayName("actualizarParticipantes debe cambiar el texto")
    void testActualizarParticipantes() {
        panelResultados.actualizarParticipantes("10 equipos");
        assertEquals("10 equipos", panelResultados.getParticipantesL().getText());
    }



}
