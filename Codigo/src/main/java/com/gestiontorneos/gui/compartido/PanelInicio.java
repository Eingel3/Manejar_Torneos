package com.gestiontorneos.gui.compartido;

import com.gestiontorneos.gui.factory.SubPanel;

import javax.swing.*;
import java.awt.*;

public class PanelInicio extends JPanel {
    public PanelInicio() {
        this.setBackground(Color.CYAN);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        this.setPreferredSize(new Dimension(
                PanelInformacion.VENTANASINMENU.getAncho(),
                PanelInformacion.VENTANASINMENU.getAlto()));

    }
}
