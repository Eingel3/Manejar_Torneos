package com.gestiontorneos.gui.factory;

import com.gestiontorneos.gui.compartido.PanelInformacion;

import javax.swing.*;
import java.awt.*;

/**
 * Fábrica de paneles tipo tarjeta.
 * <p>
 * Esta clase implementa {@link PanelFactory} para crear paneles visuales con
 * apariencia de tarjeta. Los paneles creados utilizan {@link BoxLayout} vertical,
 * borde decorativo y tamaños controlados para evitar problemas visuales.
 * </p>
 *
 * @see PanelFactory
 * @see JPanel
 * @see BoxLayout
 */
public class PanelTarjeta implements PanelFactory {

    /**
     * Crea una tarjeta con dimensiones predeterminadas.
     *
     * @return panel tipo tarjeta con tamaño estándar.
     */
    @Override
    public JPanel crear() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.PINK); //Color de fondo del panel
        panel.setPreferredSize(new Dimension(
                PanelInformacion.TARJETA.getAncho(),
                PanelInformacion.TARJETA.getAlto())); //las dimensiones son determinadas por el enum PanelInformacion
        panel.setMinimumSize(new Dimension( //Declaramos un tamaño minimo para evitar bugs visuales
                PanelInformacion.TARJETA.getAncho() - 400,
                PanelInformacion.TARJETA.getAlto() - 100));
        panel.setMaximumSize(new Dimension( //Y un tamaño maximo para evitar comportamientos no deseados
                PanelInformacion.TARJETA.getAncho(),
                PanelInformacion.TARJETA.getAlto()));
        panel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));//layout tipo Flow que agrega cada componente de izquierda a derecha
        return panel;
    }

    /**
     * Crea una tarjeta con alto personalizado y ancho predeterminado.
     *
     * @param alto alto deseado para la tarjeta.
     * @return panel tipo tarjeta con el alto indicado.
     */
    @Override
    public JPanel crear(int alto) {
        JPanel panel = new JPanel();
        panel.setBackground(Color.PINK); //Color de fondo del panel
        panel.setPreferredSize(new Dimension(
                PanelInformacion.TARJETA.getAncho(),
                alto));
        panel.setMinimumSize(new Dimension( //Declaramos un tamaño minimo para evitar bugs visuales
                PanelInformacion.TARJETA.getAncho() - 400,
                PanelInformacion.TARJETA.getAlto() - 100));
        panel.setMaximumSize(new Dimension( //Y un tamaño maximo para evitar comportamientos no deseados
                PanelInformacion.TARJETA.getAncho(),
                PanelInformacion.TARJETA.getAlto()));
        panel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));//layout tipo Flow que agrega cada componente de izquierda a derecha
        return panel;
    }

    /**
     * Crea una tarjeta con ancho y alto personalizados.
     *
     * @param ancho ancho deseado para la tarjeta.
     * @param alto alto deseado para la tarjeta.
     * @return panel tipo tarjeta con dimensiones personalizadas.
     */
    @Override
    public JPanel crear(int ancho, int alto) {
        JPanel panel = new JPanel();
        panel.setBackground(Color.PINK); //Color de fondo del panel
        panel.setPreferredSize(new Dimension(
                ancho,
                alto));
        panel.setMinimumSize(new Dimension( //Declaramos un tamaño minimo para evitar bugs visuales
                PanelInformacion.TARJETA.getAncho() - 400,
                PanelInformacion.TARJETA.getAlto() - 100));
        panel.setMaximumSize(new Dimension( //Y un tamaño maximo para evitar comportamientos no deseados
                PanelInformacion.TARJETA.getAncho(),
                PanelInformacion.TARJETA.getAlto()));
        panel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));//layout tipo Flow que agrega cada componente de izquierda a derecha
        return panel;
    }
}