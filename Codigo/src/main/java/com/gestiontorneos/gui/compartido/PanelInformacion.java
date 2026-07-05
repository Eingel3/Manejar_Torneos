package com.gestiontorneos.gui.compartido;

public enum PanelInformacion {
    VENTANAPRINCIPAL(1200, 800),
    MENULATERAL(100, 800),
    VENTANASINMENU(1100, 800),
    TARJETA(1090, 200);

    private final int ancho;
    private final int alto;
    PanelInformacion(int ancho, int alto) {
        this.ancho = ancho;
        this.alto = alto;
    }
    public int getAncho() {
        return ancho;
    }
    public int getAlto() {
        return alto;
    }
}
