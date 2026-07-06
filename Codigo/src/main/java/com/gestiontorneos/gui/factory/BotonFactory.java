package com.gestiontorneos.gui.factory;

import javax.swing.*;

/**
 * Interfaz de fábrica para la creación de botones de la interfaz gráfica.
 * <p>
 * Permite definir distintas implementaciones de creación de {@link JButton}
 * manteniendo una estructura común para todos los botones utilizados en la
 * aplicación.
 * </p>
 *
 * @see JButton
 */
public interface BotonFactory {

    /**
     * Crea un botón con el texto indicado.
     *
     * @param nombre texto que se mostrará dentro del botón.
     * @return botón configurado según la implementación concreta.
     */
    JButton crear(String nombre);
}