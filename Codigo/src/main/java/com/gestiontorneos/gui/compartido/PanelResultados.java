package com.gestiontorneos.gui.compartido;
import com.gestiontorneos.model.torneo.Torneo;

import javax.swing.*;
import java.awt.*;

/**
 * Representa la vista grafica de los resultados
 *
 * Este panel muestra los resultados de un torneo, estadísticas generales y etc
 */

public class PanelResultados extends JPanel {
    private String nombre;
    private String descripcion;
    private Torneo torneo;
    private String fechas;


    public PanelResultados(Torneo torneo) {
        //Recibimos un Torneo como argumento para poder mostrar sus detalles
        this.setBackground(Color.CYAN);
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        this.setPreferredSize(new Dimension(
                PanelInformacion.VENTANASINMENU.getAncho(),
                PanelInformacion.VENTANASINMENU.getAlto()));

        this.nombre = torneo.getNombre();
    }
}