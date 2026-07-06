package com.gestiontorneos.gui.factory;

import javax.swing.*;

/**
 * Interfaz de fábrica para la creación de paneles gráficos.
 * <p>
 * Define métodos sobrecargados que permiten crear {@link JPanel} con tamaños
 * predeterminados o personalizados según la implementación concreta.
 * </p>
 *
 * @see JPanel
 */
public interface PanelFactory {

    /**
     * Crea un panel con configuración predeterminada.
     *
     * @return panel configurado por la implementación concreta.
     */
    JPanel crear();

    /**
     * Crea un panel con dimensiones personalizadas.
     *
     * @param alto alto deseado para el panel.
     * @param ancho ancho deseado para el panel.
     * @return panel configurado con las dimensiones indicadas.
     */
    JPanel crear(int alto, int ancho);

    /**
     * Crea un panel usando un único valor personalizado.
     * <p>
     * El significado del parámetro depende de la implementación concreta; puede
     * representar ancho, alto u otra medida relevante.
     * </p>
     *
     * @param personalizado valor personalizado usado para construir el panel.
     * @return panel configurado según el valor recibido.
     */
    JPanel crear(int personalizado);
}