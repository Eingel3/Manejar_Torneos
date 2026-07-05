package com.gestiontorneos.gui.compartido;
import javax.swing.*;
import java.awt.*;

/**
 * Representa la vista grafica de la clasificacion
 *
 * Este panel dibuja una tabla de calificaciones con todos los datos requeridos
 */

public class PanelClasificacion extends JPanel {

    public PanelClasificacion() {
        //Definimos las caracteriscticas del JPanel
        this.setBackground(Color.CYAN);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        this.setPreferredSize(new Dimension(
                PanelInformacion.VENTANASINMENU.getAncho(),
                PanelInformacion.VENTANASINMENU.getAlto()));



    }

}