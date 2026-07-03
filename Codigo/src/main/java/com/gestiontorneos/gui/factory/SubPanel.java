package com.gestiontorneos.gui.factory;

import com.gestiontorneos.gui.compartido.PanelInformacion;

import javax.swing.*;
import java.awt.*;

//Este metodo crea el panel donde se muestran la lista de torneos, o el calendario, y en general todas las pestañas
public class SubPanel extends JPanel implements PanelFactory {
    @Override public JPanel crear(){
        JPanel panel = new JPanel();
        panel.setBackground(Color.PINK); //definimos el color de fondo
        panel.setLayout(new FlowLayout());//definimos el layout
        panel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY)); //Definimos un borde
        panel.setPreferredSize(new Dimension(//Y definimos las dimensiones
                PanelInformacion.VENTANASINMENU.getAncho() - 10, //Le quitamos 10 para una correcta implementación
                PanelInformacion.VENTANASINMENU.getAlto()));
        return panel; //Finalmente retornamos el panel
    }

    @Override public JPanel crear(int ancho){
        JPanel panel = new JPanel();
        panel.setBackground(Color.PINK); //definimos el color de fondo
        panel.setLayout(new FlowLayout());//definimos el layout
        panel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY)); //Definimos un borde
        panel.setPreferredSize(new Dimension(//Y definimos las dimensiones
                ancho,
                PanelInformacion.VENTANASINMENU.getAlto()));
        return panel; //Finalmente retornamos el panel
    }

    @Override public JPanel crear(int alto, int ancho){
        JPanel panel = new JPanel();
        panel.setBackground(Color.PINK); //definimos el color de fondo
        panel.setLayout(new FlowLayout());//definimos el layout
        panel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY)); //Definimos un borde
        panel.setPreferredSize(new Dimension(//Y definimos las dimensiones
                ancho - 10,
                alto));
        return panel; //Finalmente retornamos el panel
    }
}
