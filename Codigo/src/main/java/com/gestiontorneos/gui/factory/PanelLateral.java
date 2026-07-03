package com.gestiontorneos.gui.factory;

import com.gestiontorneos.gui.compartido.PanelInformacion;

import javax.swing.*;
import java.awt.*;

/**
 * clase que implementa al PanelFactory.
 *  Se utliza para crear un panel angosto, de layout tipo Box, disposicion vertical.
 *  Gracias a la sobrecarga del metodo crear se puede adaptar a distintos usos.
 */
public class PanelLateral implements PanelFactory{
    /**
     * Override del metodo crear, esta funcion crea un JPanel lateral predeterminado
     * @return retorna un JPanel angosto, de layout tipo Box, disposicion vertical.
     */
    @Override
    public JPanel crear(){
        JPanel panel = new JPanel();
        panel.setBackground(Color.BLACK); //Color de fondo del panel
        panel.setPreferredSize(new Dimension(
                PanelInformacion.MENULATERAL.getAncho(),
                PanelInformacion.MENULATERAL.getAlto())); //las dimensiones son determinadas por el enum PanelInformacion
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));//Usamos BoxLayout de forma vertical para colocar las adiciones
        return  panel;
    }

    /**
     *   Override del metodo crear, esta funcion crea un JPanel lateral predeterminado
     *
     * @param ancho es el ancho que se desea que tenga el JPanel lateral
     * @return Retorna un JPanel de ancho personalizado, dado por la variable ancho, de layout tipo Box, disposicion vertical.
     */
    @Override
    public JPanel crear(int ancho){
        JPanel panel = new JPanel();
        panel.setBackground(Color.BLACK); //Color de fondo del panel
        panel.setPreferredSize(new Dimension(
                ancho,
                PanelInformacion.MENULATERAL.getAlto()));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));//Usamos BoxLayout de forma vertical para colocar las adiciones
        return  panel;
    }

    /**
     *   Override del metodo crear, esta funcion crea un JPanel lateral predeterminado
     *
     * @param ancho es el ancho que se desea que tenga el JPanel lateral
     * @param alto es el alto que se desea que tenga el JPanel lateral
     * @return Retorna un JPanel de ancho  y alto personalizados, dados por las variables ancho y alto, de layout tipo Box, disposicion vertical.
     */
    @Override
    public JPanel crear(int ancho, int alto){
        JPanel panel = new JPanel();
        panel.setBackground(Color.BLACK); //Color de fondo del panel
        panel.setPreferredSize(new Dimension(
                ancho,
                alto));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));//Usamos BoxLayout de forma vertical para colocar las adiciones
        return  panel;
    }
}
