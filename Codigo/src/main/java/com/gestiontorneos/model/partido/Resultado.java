package com.gestiontorneos.model.partido;

/**
 * Representa el resultado numérico de un partido.
 * <p>
 * Almacena los puntos obtenidos por el participante local y el visitante.
 * También permite determinar si ganó el local, el visitante o si hubo empate.
 * </p>
 */
public class Resultado {

    private final int puntosLocal;
    private final int puntosVisitante;

    /**
     * Crea un nuevo resultado con los puntos indicados.
     *
     * @param puntosLocal puntos obtenidos por el participante local.
     * @param puntosVisitante puntos obtenidos por el participante visitante.
     * @throws IllegalArgumentException si alguno de los puntajes es negativo.
     */
    public Resultado(int puntosLocal, int puntosVisitante) {
        if (puntosLocal < 0 || puntosVisitante < 0) {
            throw new IllegalArgumentException("Los puntos no pueden ser negativos");
        }
        this.puntosLocal = puntosLocal;
        this.puntosVisitante = puntosVisitante;
    }

    /**
     * Obtiene los puntos del participante local.
     *
     * @return puntos del local.
     */
    public int getPuntosLocal() {
        return puntosLocal;
    }

    /**
     * Obtiene los puntos del participante visitante.
     *
     * @return puntos del visitante.
     */
    public int getPuntosVisitante() {
        return puntosVisitante;
    }



    /**
     * Determina el ganador según los puntos registrados.
     *
     * @return {@link GanadorResultado#LOCAL} si ganó el local,
     * {@link GanadorResultado#VISITANTE} si ganó el visitante o
     * {@link GanadorResultado#EMPATE} si ambos tienen el mismo puntaje.
     */
    public GanadorResultado determinarGanador() {
        if (puntosLocal > puntosVisitante) return GanadorResultado.LOCAL;
        if (puntosVisitante > puntosLocal) return GanadorResultado.VISITANTE;
        return GanadorResultado.EMPATE;
    }

    /**
     * Devuelve una representación textual del resultado.
     *
     * @return resultado en formato {@code puntosLocal - puntosVisitante}.
     */
    @Override
    public String toString() {
        return puntosLocal + " - " + puntosVisitante;
    }
}