package com.gestiontorneos.gui.compartido;
import javax.swing.*;
import java.awt.*;

public class PanelMenu extends JPanel {
    public  PanelMenu() {
        this.setBackground(Color.BLACK); //Color de fondo del panel
        this.setPreferredSize(new Dimension(50, 800)); //Un poquit0 ancho + el largo de la VentanaPrincipal
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));//Usamos BoxLayout de forma vertical para colocar los botones del menu lateral
        this.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));// Le ponemos un borde más claro para que se diferencie el mennú
    }
}
