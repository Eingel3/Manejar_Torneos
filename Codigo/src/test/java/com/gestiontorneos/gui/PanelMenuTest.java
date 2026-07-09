import com.gestiontorneos.gui.compartido.PanelMenu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static org.junit.jupiter.api.Assertions.*;

public class PanelMenuTest {
    private PanelMenu menu;

    @BeforeEach
    void setUp() {
        menu = new PanelMenu();
    }

    @Test
    @DisplayName("Debe crear los botones y contenerlos")
    void testBotonesExisten() {
        Component[] comps = menu.getComponents();
        int botones = 0;
        for (Component c : comps) {
            if (c instanceof JButton) botones++;
        }
        assertEquals(7, botones); // Inicio, Calendario, Torneos, Clasificaciones, Partidos, Futuros Eventos, Crear Torneo
    }

    @Test
    @DisplayName("agregarListener debe lanzar excepción para identificador inválido")
    void testAgregarListenerInvalido() {
        assertThrows(IllegalArgumentException.class, () -> menu.agregarListener("Inexistente", e -> {}));
    }

    @Test
    @DisplayName("agregarListener debe asignar correctamente un ActionListener")
    void testAgregarListenerValido() {
        final boolean[] ejecutado = {false};
        ActionListener listener = e -> ejecutado[0] = true;
        menu.agregarListener("Inicio", listener);
        // Simular clic buscando el botón Inicio
        for (Component c : menu.getComponents()) {
            if (c instanceof JButton && ((JButton) c).getText().equals("Inicio")) {
                ((JButton) c).doClick();
                break;
            }
        }
        assertTrue(ejecutado[0], "El listener no fue invocado");
    }
}
