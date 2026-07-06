package com.gestiontorneos.gui.compartido;

import com.gestiontorneos.gui.factory.PanelTarjeta;
import com.gestiontorneos.gui.factory.SubPanel;
import com.gestiontorneos.model.torneo.Torneo;

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

        revalidate();
        repaint();
    }
    public void setBienvenida(JPanel bienvenida){
        this.bienvenida.removeAll(); //Removemos lo que ya existe
        this.bienvenida.add(bienvenida); //Y agregamos el JPanel que se desea agregar
        this.bienvenida.revalidate();
        this.bienvenida.repaint();
    }
    public void setTorneoReciente(JPanel torneoReciente){
        this.torneoReciente.removeAll();
        this.torneoReciente.add(torneoReciente);
        this.torneoReciente.revalidate();
        this.torneoReciente.repaint();
    }
    public void setFuturoEvento(JPanel futuroEvento){
        this.futuroEvento.removeAll();
        this.futuroEvento.add(futuroEvento);
        this.futuroEvento.revalidate();
        this.futuroEvento.repaint();
    }
    public void setPuesto1(JPanel puesto1){
        this.puesto1.removeAll();
        this.puesto1.add(puesto1);
        this.puesto1.revalidate();
        this.puesto1.repaint();
    }
    public void setInformacion(JPanel informacion){
        this.informacion.removeAll();
        this.informacion.add(informacion);
        this.informacion.revalidate();
        this.informacion.repaint();
    }
}
