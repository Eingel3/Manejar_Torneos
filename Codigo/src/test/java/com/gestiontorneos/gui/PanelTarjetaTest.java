import com.gestiontorneos.gui.compartido.PanelInformacion;
import com.gestiontorneos.gui.factory.PanelTarjeta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class PanelTarjetaTest {
    private PanelTarjeta factory;

    @BeforeEach
    void setUp() {
        factory = new PanelTarjeta();
    }

    @Test
    @DisplayName("crear() sin parámetros debe usar dimensiones de TARJETA")
    void testCrearSinParametros() {
        JPanel panel = factory.crear();
        assertNotNull(panel);
        // Propiedades comunes
        assertEquals(Color.PINK, panel.getBackground());
        assertTrue(panel.getBorder() instanceof LineBorder);
        assertEquals(Color.WHITE, ((LineBorder) panel.getBorder()).getLineColor());
        assertTrue(panel.getLayout() instanceof FlowLayout);
        assertEquals(FlowLayout.LEFT, ((FlowLayout) panel.getLayout()).getAlignment());

        // Dimensiones
        Dimension expectedPref = new Dimension(
                PanelInformacion.TARJETA.getAncho(),
                PanelInformacion.TARJETA.getAlto());
        assertEquals(expectedPref, panel.getPreferredSize());

        Dimension expectedMin = new Dimension(
                PanelInformacion.TARJETA.getAncho() - 400,
                PanelInformacion.TARJETA.getAlto() - 100);
        assertEquals(expectedMin, panel.getMinimumSize());

        Dimension expectedMax = new Dimension(
                PanelInformacion.TARJETA.getAncho(),
                PanelInformacion.TARJETA.getAlto());
        assertEquals(expectedMax, panel.getMaximumSize());
    }

    @Test
    @DisplayName("crear(int alto) debe usar ancho de TARJETA y el alto indicado")
    void testCrearConAlto() {
        int altoPersonalizado = 300;
        JPanel panel = factory.crear(altoPersonalizado);
        Dimension expectedPref = new Dimension(
                PanelInformacion.TARJETA.getAncho(),
                altoPersonalizado);
        assertEquals(expectedPref, panel.getPreferredSize());
        // Los tamaños minimo y maximo se mantienen según TARJETA
        assertEquals(new Dimension(PanelInformacion.TARJETA.getAncho() - 400,
                PanelInformacion.TARJETA.getAlto() - 100), panel.getMinimumSize());
        assertEquals(new Dimension(PanelInformacion.TARJETA.getAncho(),
                PanelInformacion.TARJETA.getAlto()), panel.getMaximumSize());
    }

    @Test
    @DisplayName("crear(int ancho, int alto) debe usar las dimensiones exactas")
    void testCrearConAnchoYAlto() {
        int ancho = 600;
        int alto = 250;
        JPanel panel = factory.crear(ancho, alto);
        Dimension expectedPref = new Dimension(ancho, alto);
        assertEquals(expectedPref, panel.getPreferredSize());
        // minimo y maximo depeden del enum de TARJETA
        assertEquals(new Dimension(PanelInformacion.TARJETA.getAncho() - 400,
                PanelInformacion.TARJETA.getAlto() - 100), panel.getMinimumSize());
        assertEquals(new Dimension(PanelInformacion.TARJETA.getAncho(),
                PanelInformacion.TARJETA.getAlto()), panel.getMaximumSize());
    }
}
