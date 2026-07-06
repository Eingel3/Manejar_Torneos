package com.gestiontorneos.gui.compartido;

import com.gestiontorneos.gui.factory.PanelTarjeta;
import com.gestiontorneos.gui.factory.SubPanel;

import javax.swing.*;
import java.awt.*;

public class PanelInicio extends JPanel {
    JPanel bienvenida;
    JPanel torneoReciente;
    JPanel futuroEvento;
    JPanel puesto1;
    JPanel informacion;
    PanelTarjeta creadorTarjeta;
    public PanelInicio() {
        this.setBackground(Color.CYAN);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        this.setPreferredSize(new Dimension(
                PanelInformacion.VENTANASINMENU.getAncho(),
                PanelInformacion.VENTANASINMENU.getAlto()));
        iniciarEstructura();
    }

    private void iniciarEstructura(){
        this.creadorTarjeta = new PanelTarjeta();

        this.bienvenida = creadorTarjeta.crear();
        this.futuroEvento = creadorTarjeta.crear();
        this.torneoReciente = creadorTarjeta.crear();
        this.informacion = creadorTarjeta.crear();
        this.puesto1 = creadorTarjeta.crear();

        this.add(Box.createRigidArea(new Dimension(0, 10))); //Agregamos un espacio al inicio
        this.add(bienvenida);
        this.add(Box.createRigidArea(new Dimension(0, 40))); //Agregamos un espacio entre cada componente
        this.add(torneoReciente);
        this.add(Box.createRigidArea(new Dimension(0, 40))); //Agregamos un espacio entre cada componente
        this.add(futuroEvento);
        this.add(Box.createRigidArea(new Dimension(0, 40))); //Agregamos un espacio entre cada componente
        this.add(puesto1);
        this.add(Box.createRigidArea(new Dimension(0, 40))); //Agregamos un espacio entre cada componente
        this.add(informacion);
    }
}
