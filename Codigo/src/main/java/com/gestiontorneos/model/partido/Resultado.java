package com.gestiontorneos.model.partido;

public class Resultado {

    private final int puntosLocal;
    private final int puntosVisitante;

    public Resultado(int puntosLocal, int puntosVisitante) {
        if (puntosLocal < 0 || puntosVisitante < 0) {
            throw new IllegalArgumentException("Los puntos no pueden ser negativos");
        }
        this.puntosLocal = puntosLocal;
        this.puntosVisitante = puntosVisitante;
    }

    public int getPuntosLocal() { return puntosLocal; }
    public int getPuntosVisitante() { return puntosVisitante; }

    public enum GanadorResultado { LOCAL, VISITANTE, EMPATE }

    public GanadorResultado determinarGanador() {
        if (puntosLocal > puntosVisitante) return GanadorResultado.LOCAL;
        if (puntosVisitante > puntosLocal) return GanadorResultado.VISITANTE;
        return GanadorResultado.EMPATE;
    }

    @Override
    public String toString() {
        return puntosLocal + " - " + puntosVisitante;
    }
}