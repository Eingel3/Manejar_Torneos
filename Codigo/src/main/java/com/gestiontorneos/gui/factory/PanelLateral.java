package com.gestiontorneos.gui.factory;

import com.gestiontorneos.gui.compartido.PanelInformacion;

import javax.swing.*;
import java.awt.*;

public class PanelLateral implements PanelFactory{
    @Override
    public JPanel crear(){
        JPanel panel = new JPanel();
        panel.setBackground(Color.BLACK); //Color de fondo del panel
        panel.setPreferredSize(new Dimension(
                PanelInformacion.MENULATERAL.getAncho(),
                PanelInformacion.MENULATERAL.getAlto())); //Un poquit0 ancho + el largo de la VentanaPrincipal

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));//Usamos BoxLayout de forma vertical para colocar los botones del menu lateral

        return  panel;
    }
}
