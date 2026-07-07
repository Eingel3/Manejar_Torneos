import com.gestiontorneos.gui.compartido.PanelInformacion;
import com.gestiontorneos.gui.factory.BotonSimple;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class BotonSimpleTest {
    private BotonSimple factory;

    @BeforeEach
    void setUp() {
        factory = new BotonSimple();
    }

    @Test
    @DisplayName("Debe crear un JButton con el texto correcto")
    void testCrearTexto() {
        JButton boton = factory.crear("Aceptar");
        assertNotNull(boton);
        assertEquals("Aceptar", boton.getText());
    }

    @Test
    @DisplayName("Debe tener color de fondo rosa y alineación centrada")
    void testEstiloVisual() {
        JButton boton = factory.crear("Test");
        assertEquals(Color.PINK, boton.getBackground());
        assertEquals(Component.CENTER_ALIGNMENT, boton.getAlignmentX(), 0.0f);
    }

    @Test
    @DisplayName("Debe tener dimensiones preferidas basadas en MENULATERAL")
    void testDimensiones() {
        JButton boton = factory.crear("Test");
        Dimension expected = new Dimension(
                PanelInformacion.MENULATERAL.getAncho() - 10,
                30);
        assertEquals(expected, boton.getPreferredSize());
    }

    @Test
    @DisplayName("Debe tener un borde de línea magenta")
    void testBorde() {
        JButton boton = factory.crear("Test");
        assertTrue(boton.getBorder() instanceof LineBorder);
        LineBorder border = (LineBorder) boton.getBorder();
        assertEquals(Color.MAGENTA, border.getLineColor());
    }
}
