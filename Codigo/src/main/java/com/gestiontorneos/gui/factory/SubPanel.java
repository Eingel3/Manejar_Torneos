package com.gestiontorneos.gui.factory;

import com.gestiontorneos.gui.compartido.PanelInformacion;

import javax.swing.*;
import java.awt.*;

/**
 * Panel contenedor utilizado para mostrar las distintas secciones principales
 * de la aplicación.
 * <p>
 * También implementa {@link PanelFactory}, por lo que puede crear paneles con
 * configuraciones predeterminadas o dimensiones personalizadas. Se utiliza como
 * base para vistas como lista de torneos, calendario u otras pestañas.
 * </p>
 *
 * @see JPanel
 * @see PanelFactory
 */
public class SubPanel extends JPanel implements PanelFactory {

    /**
     * Crea un subpanel con dimensiones predeterminadas para el área principal
     * sin menú lateral.
     *
     * @return panel configurado con color, layout, borde y tamaño estándar.
     */
    @Override
    public JPanel crear() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.PINK); //definimos el color de fondo
        panel.setLayout(new FlowLayout()); //definimos el layout
        panel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY)); //Definimos un borde
        panel.setPreferredSize(new Dimension( //Y definimos las dimensiones
                PanelInformacion.VENTANASINMENU.getAncho() - 10, //Le quitamos 10 para una correcta implementación
                PanelInformacion.VENTANASINMENU.getAlto()));
        return panel; //Finalmente retornamos el panel
    }

    /**
     * Crea un subpanel con ancho personalizado y alto predeterminado.
     *
     * @param ancho ancho deseado para el panel.
     * @return panel configurado con el ancho indicado.
     */
    @Override
    public JPanel crear(int ancho) {
        JPanel panel = new JPanel();
        panel.setBackground(Color.PINK); //definimos el color de fondo
        panel.setLayout(new FlowLayout()); //definimos el layout
        panel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY)); //Definimos un borde
        panel.setPreferredSize(new Dimension( //Y definimos las dimensiones
                ancho,
                PanelInformacion.VENTANASINMENU.getAlto()));
        return panel; //Finalmente retornamos el panel
    }

    /**
     * Crea un subpanel con alto y ancho personalizados.
     *
     * @param alto alto deseado para el panel.
     * @param ancho ancho deseado para el panel.
     * @return panel configurado con las dimensiones indicadas.
     */
    @Override
    public JPanel crear(int alto, int ancho) {
        JPanel panel = new JPanel();
        panel.setBackground(Color.PINK); //definimos el color de fondo
        panel.setLayout(new FlowLayout()); //definimos el layout
        panel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY)); //Definimos un borde
        panel.setPreferredSize(new Dimension( //Y definimos las dimensiones
                ancho - 10,
                alto));
        return panel; //Finalmente retornamos el panel
    }
}