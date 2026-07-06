package com.gestiontorneos.gui.factory;

import com.gestiontorneos.gui.compartido.PanelInformacion;

import javax.swing.*;
import java.awt.*;

/**
 * Fábrica de paneles laterales.
 * <p>
 * Esta clase implementa {@link PanelFactory} para crear paneles angostos con
 * layout vertical basado en {@link BoxLayout}. Se utiliza principalmente para
 * construir secciones laterales o columnas de información.
 * </p>
 *
 * @see PanelFactory
 * @see JPanel
 * @see BoxLayout
 */
public class PanelLateral implements PanelFactory {

    /**
     * Crea un panel lateral con dimensiones predeterminadas.
     *
     * @return panel lateral con ancho y alto definidos en {@link PanelInformacion}.
     */
    @Override
    public JPanel crear() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.BLACK); //Color de fondo del panel
        panel.setPreferredSize(new Dimension(
                PanelInformacion.MENULATERAL.getAncho(),
                PanelInformacion.MENULATERAL.getAlto())); //las dimensiones son determinadas por el enum PanelInformacion
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); //Usamos BoxLayout de forma vertical para colocar las adiciones
        return panel;
    }

    /**
     * Crea un panel lateral con ancho personalizado y alto predeterminado.
     *
     * @param ancho ancho deseado para el panel.
     * @return panel lateral con el ancho indicado.
     */
    @Override
    public JPanel crear(int ancho) {
        JPanel panel = new JPanel();
        panel.setBackground(Color.BLACK); //Color de fondo del panel
        panel.setPreferredSize(new Dimension(
                ancho,
                PanelInformacion.MENULATERAL.getAlto()));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); //Usamos BoxLayout de forma vertical para colocar las adiciones
        return panel;
    }

    /**
     * Crea un panel lateral con ancho y alto personalizados.
     *
     * @param ancho ancho deseado para el panel.
     * @param alto alto deseado para el panel.
     * @return panel lateral con dimensiones personalizadas.
     */
    @Override
    public JPanel crear(int ancho, int alto) {
        JPanel panel = new JPanel();
        panel.setBackground(Color.BLACK); //Color de fondo del panel
        panel.setPreferredSize(new Dimension(
                ancho,
                alto));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); //Usamos BoxLayout de forma vertical para colocar las adiciones
        return panel;
    }
}