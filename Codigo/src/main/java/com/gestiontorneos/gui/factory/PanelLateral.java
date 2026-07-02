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
                PanelInformacion.MENULATERAL.getAlto())); //las dimensiones son determinadas por el enum PanelInformacion

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));//Usamos BoxLayout de forma vertical para colocar los botones del menu lateral

        return  panel;
    }
    @Override
    public JPanel crear(int ancho){
        JPanel panel = new JPanel();
        panel.setBackground(Color.BLACK); //Color de fondo del panel
        panel.setPreferredSize(new Dimension(
                ancho,
                PanelInformacion.MENULATERAL.getAlto()));

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));//Usamos BoxLayout de forma vertical para colocar los botones del menu lateral

        return  panel;
    }
    @Override
    public JPanel crear(int ancho, int alto){
        JPanel panel = new JPanel();
        panel.setBackground(Color.BLACK); //Color de fondo del panel
        panel.setPreferredSize(new Dimension(
                ancho,
                alto));

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));//Usamos BoxLayout de forma vertical para colocar los botones del menu lateral

        return  panel;
    }
}
