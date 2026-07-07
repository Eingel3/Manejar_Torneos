import com.gestiontorneos.gui.compartido.PanelInformacion;
import com.gestiontorneos.gui.factory.SubPanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SubPanelTest {
    private SubPanel factory;

    @BeforeEach
    void setUp() {
        factory = new SubPanel();
    }

    @Test
    @DisplayName("crear() sin parámetros debe usar VENTANASINMENU (ancho-10, alto)")
    void testCrearSinParametros() {
        JPanel panel = factory.crear();
        // Propiedades comunes
        assertEquals(Color.PINK, panel.getBackground());
        assertTrue(panel.getLayout() instanceof FlowLayout);
        assertTrue(panel.getBorder() instanceof LineBorder);
        assertEquals(Color.LIGHT_GRAY, ((LineBorder) panel.getBorder()).getLineColor());

        Dimension expectedPref = new Dimension(
                PanelInformacion.VENTANASINMENU.getAncho() - 10,
                PanelInformacion.VENTANASINMENU.getAlto());
        assertEquals(expectedPref, panel.getPreferredSize());
    }

    @Test
    @DisplayName("crear(int ancho) debe usar alto de VENTANASINMENU y el ancho indicado")
    void testCrearConAncho() {
        int ancho = 500;
        JPanel panel = factory.crear(ancho);
        Dimension expectedPref = new Dimension(ancho,
                PanelInformacion.VENTANASINMENU.getAlto());
        assertEquals(expectedPref, panel.getPreferredSize());
    }

    @Test
    @DisplayName("crear(int alto, int ancho) debe usar ancho-10 y alto indicado")
    void testCrearConAltoYAncho() {
        int alto = 400;
        int ancho = 700;
        JPanel panel = factory.crear(alto, ancho);
        Dimension expectedPref = new Dimension(ancho - 10, alto);
        assertEquals(expectedPref, panel.getPreferredSize());
    }
}
