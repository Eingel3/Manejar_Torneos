package com.gestiontorneos.gui.factory;

import javax.swing.*;
import java.awt.*;

/**
 * Implementación simple de {@link BotonFactory}.
 * <p>
 * Crea botones con una apariencia estándar para ser utilizados principalmente
 * dentro del menú lateral de la aplicación.
 * </p>
 *
 * @see BotonFactory
 * @see JButton
 */
public class BotonSimple implements BotonFactory {

    /**
     * Crea un botón básico con estilo predefinido.
     * <p>
     * El botón recibe un color de fondo, alineación centrada, tamaño preferido y
     * borde visual.
     * </p>
     *
     * @param nombre texto que se mostrará en el botón.
     * @return botón configurado con el estilo simple de la aplicación.
     */
    @Override
    public JButton crear(String nombre) {
        JButton boton = new JButton(nombre); //le damos su nombre
        boton.setBackground(Color.PINK); //Le colocamos color rosa
        boton.setAlignmentX(Component.CENTER_ALIGNMENT); //Le alineamos en el centro del menu
        boton.setPreferredSize(new Dimension(com.gestiontorneos.gui.compartido.PanelInformacion.MENULATERAL.getAncho() - 10, 80)); //Le dejamos como un rectangulo un poquito mas chico que el ancho del menu
        boton.setBorder(BorderFactory.createLineBorder(Color.MAGENTA)); //Le añadimos un borde color magenta
        return boton; //Y ya que hemos configurado el boton, podemos devolverlo
    }
}