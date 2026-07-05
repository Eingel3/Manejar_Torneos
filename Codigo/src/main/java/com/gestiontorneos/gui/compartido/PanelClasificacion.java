package com.gestiontorneos.gui.compartido;
import com.gestiontorneos.gui.factory.PanelTarjeta;

import javax.swing.*;
import java.awt.*;

/**
 * Representa la vista grafica de la clasificacion
 *
 * Este panel dibuja una tabla de calificaciones con todos los datos requeridos
 */

public class PanelClasificacion extends JPanel {

    PanelTarjeta creadorTarjeta;
    JPanel puesto1;
    JPanel puesto2;
    JPanel puesto3;

    //Nombre del participante y otros datos
    JLabel nombrePuesto1;
    JLabel dato1Puesto1;
    JLabel dato2Puesto1;
    JLabel dato3Puesto1;
    JLabel dato4Puesto1;
    JLabel dato5Puesto1;
    JLabel nombrePuesto2;
    JLabel dato1Puesto2;
    JLabel dato2Puesto2;
    JLabel dato3Puesto2;
    JLabel dato4Puesto2;
    JLabel dato5Puesto2;
    JLabel nombrePuesto3;
    JLabel dato1Puesto3;
    JLabel dato2Puesto3;
    JLabel dato3Puesto3;
    JLabel dato4Puesto3;
    JLabel dato5Puesto3;

    public PanelClasificacion() {
        //Definimos las caracteriscticas del JPanel
        this.setBackground(Color.CYAN);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        this.setPreferredSize(new Dimension(
                PanelInformacion.VENTANASINMENU.getAncho(),
                PanelInformacion.VENTANASINMENU.getAlto()));
        //Creamos el creador de tarjetas
        creadorTarjeta = new PanelTarjeta();
        //Y creamos los puestos
        puesto1 = creadorTarjeta.crear();
        puesto2 = creadorTarjeta.crear();
        puesto3 = creadorTarjeta.crear();

        //Ahora, hay que crear los labels
        iniciarLabels();

    }

    public void iniciarLabels(){
        nombrePuesto1 = new JLabel("Nombre puesto 1");
        dato1Puesto1 = new JLabel("Dato 1");
        dato2Puesto1 = new JLabel("Dato 2");
        dato3Puesto1 = new JLabel("Dato 3");
        dato4Puesto1 = new JLabel("Dato 4");
        dato5Puesto1 = new JLabel("Dato 5");

        puesto1.add(nombrePuesto1);
        puesto1.add(dato1Puesto1);
        puesto1.add(dato2Puesto1);
        puesto1.add(dato3Puesto1);
        puesto1.add(dato4Puesto1);
        puesto1.add(dato5Puesto1);

        nombrePuesto2 = new JLabel("Nombre puesto 2");
        dato1Puesto2 = new JLabel("Dato 1");
        dato2Puesto2 = new JLabel("Dato 2");
        dato3Puesto2 = new JLabel("Dato 3");
        dato4Puesto2 = new JLabel("Dato 4");
        dato5Puesto2 = new JLabel("Dato 5");

        puesto2.add(nombrePuesto2);
        puesto2.add(dato1Puesto2);
        puesto2.add(dato2Puesto2);
        puesto2.add(dato3Puesto2);
        puesto2.add(dato4Puesto2);
        puesto2.add(dato5Puesto2);

        nombrePuesto3 = new JLabel("Nombre puesto 3");
        dato1Puesto3 = new JLabel("Dato 1");
        dato2Puesto3 = new JLabel("Dato 2");
        dato3Puesto3 = new JLabel("Dato 3");
        dato4Puesto3 = new JLabel("Dato 4");
        dato5Puesto3 = new JLabel("Dato 5");

        puesto3.add(nombrePuesto3);
        puesto3.add(dato1Puesto3);
        puesto3.add(dato2Puesto3);
        puesto3.add(dato3Puesto3);
        puesto3.add(dato4Puesto3);
        puesto3.add(dato5Puesto3);
    }



}