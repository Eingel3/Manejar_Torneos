package com.gestiontorneos.gui.compartido;

import com.gestiontorneos.gui.factory.PanelTarjeta;

import javax.swing.*;
import java.awt.*;

/**
 * Panel gráfico encargado de mostrar la clasificación principal de un torneo.
 * <p>
 * Esta vista contiene tres tarjetas que representan los primeros puestos de la
 * clasificación. Cada tarjeta muestra el nombre del participante y varios datos
 * asociados, como puntos, estadísticas u otra información definida por el
 * controlador.
 * </p>
 *
 * @see JPanel
 * @see PanelTarjeta
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

    /**
     * Crea e inicializa el panel de clasificación.
     * <p>
     * Configura el color de fondo, layout, borde y tamaño del panel. También
     * crea las tarjetas correspondientes a los tres primeros puestos e inicializa
     * sus etiquetas.
     * </p>
     */
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
        puesto1.setLayout(new BoxLayout(puesto1, BoxLayout.Y_AXIS)); //Usamos BoxLayout de forma vertical para colocar las adiciones

        puesto2 = creadorTarjeta.crear();
        puesto2.setLayout(new BoxLayout(puesto2, BoxLayout.Y_AXIS)); //Usamos BoxLayout de forma vertical para colocar las adiciones

        puesto3 = creadorTarjeta.crear();
        puesto3.setLayout(new BoxLayout(puesto3, BoxLayout.Y_AXIS)); //Usamos BoxLayout de forma vertical para colocar las adiciones

        //Ahora, hay que crear los labels
        iniciarLabels();

        this.add(puesto1);
        this.add(puesto2);
        this.add(puesto3);
    }

    /**
     * Inicializa las etiquetas de texto correspondientes a los tres primeros
     * puestos de la clasificación.
     * <p>
     * Cada grupo de etiquetas se agrega a su respectiva tarjeta visual.
     * </p>
     */
    public void iniciarLabels() {
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

    /**
     * Actualiza la información mostrada en la tarjeta del primer puesto.
     *
     * @param nombrePuesto nombre del equipo o participante ubicado en el primer puesto.
     * @param dato1 primer dato asociado al participante.
     * @param dato2 segundo dato asociado al participante.
     * @param dato3 tercer dato asociado al participante.
     * @param dato4 cuarto dato asociado al participante.
     * @param dato5 quinto dato asociado al participante.
     */
    public void setPuesto1(String nombrePuesto, String dato1, String dato2, String dato3, String dato4, String dato5) {
        nombrePuesto1.setText(nombrePuesto);
        dato1Puesto1.setText(dato1);
        dato2Puesto1.setText(dato2);
        dato3Puesto1.setText(dato3);
        dato4Puesto1.setText(dato4);
        dato5Puesto1.setText(dato5);
    }

    /**
     * Actualiza la información mostrada en la tarjeta del segundo puesto.
     *
     * @param nombrePuesto nombre del equipo o participante ubicado en el segundo puesto.
     * @param dato1 primer dato asociado al participante.
     * @param dato2 segundo dato asociado al participante.
     * @param dato3 tercer dato asociado al participante.
     * @param dato4 cuarto dato asociado al participante.
     * @param dato5 quinto dato asociado al participante.
     */
    public void setPuesto2(String nombrePuesto, String dato1, String dato2, String dato3, String dato4, String dato5) {
        nombrePuesto2.setText(nombrePuesto);
        dato1Puesto2.setText(dato1);
        dato2Puesto2.setText(dato2);
        dato3Puesto2.setText(dato3);
        dato4Puesto2.setText(dato4);
        dato5Puesto2.setText(dato5);
    }

    /**
     * Actualiza la información mostrada en la tarjeta del tercer puesto.
     *
     * @param nombrePuesto nombre del equipo o participante ubicado en el tercer puesto.
     * @param dato1 primer dato asociado al participante.
     * @param dato2 segundo dato asociado al participante.
     * @param dato3 tercer dato asociado al participante.
     * @param dato4 cuarto dato asociado al participante.
     * @param dato5 quinto dato asociado al participante.
     */
    public void setPuesto3(String nombrePuesto, String dato1, String dato2, String dato3, String dato4, String dato5) {
        nombrePuesto2.setText(nombrePuesto);
        dato1Puesto2.setText(dato1);
        dato2Puesto2.setText(dato2);
        dato3Puesto2.setText(dato3);
        dato4Puesto2.setText(dato4);
        dato5Puesto2.setText(dato5);
    }
}