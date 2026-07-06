package com.gestiontorneos.gui.compartido;

/**
 * Enumeración que centraliza las dimensiones principales utilizadas por los
 * paneles de la interfaz gráfica.
 * <p>
 * Cada constante representa una sección visual de la aplicación y almacena su
 * ancho y alto correspondiente. Esto permite reutilizar medidas en diferentes
 * componentes Swing sin repetir valores numéricos.
 * </p>
 */
public enum PanelInformacion {

    /**
     * Dimensiones generales de la ventana principal.
     */
    VENTANAPRINCIPAL(1200, 800),

    /**
     * Dimensiones del menú lateral.
     */
    MENULATERAL(100, 800),

    /**
     * Dimensiones del área principal sin incluir el menú lateral.
     */
    VENTANASINMENU(1090, 800),

    /**
     * Dimensiones estándar de una tarjeta visual.
     */
    TARJETA(1000, 200);

    private final int ancho;
    private final int alto;

    /**
     * Crea una constante de información de panel con ancho y alto definidos.
     *
     * @param ancho ancho del panel.
     * @param alto alto del panel.
     */
    PanelInformacion(int ancho, int alto) {
        this.ancho = ancho;
        this.alto = alto;
    }

    /**
     * Obtiene el ancho asociado a la constante.
     *
     * @return ancho del panel.
     */
    public int getAncho() {
        return ancho;
    }

    /**
     * Obtiene el alto asociado a la constante.
     *
     * @return alto del panel.
     */
    public int getAlto() {
        return alto;
    }
}