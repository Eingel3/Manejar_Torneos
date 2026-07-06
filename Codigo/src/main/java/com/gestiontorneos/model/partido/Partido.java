package com.gestiontorneos.model.partido;

import com.gestiontorneos.model.participante.Participante;
import com.gestiontorneos.model.excepciones.DatosInvalidosException;

/**
 * Representa un partido entre dos participantes dentro de una ronda específica.
 * <p>
 * Un partido posee un participante local, un participante visitante, una ronda,
 * un estado y, opcionalmente, un resultado cuando finaliza.
 * </p>
 *
 * @see Participante
 * @see Resultado
 * @see EstadoPartido
 */
public class Partido {

    private final Participante local;
    private final Participante visitante;
    private final int ronda;
    private Resultado resultado; //Sin final porque se asigna después, no en el constructor
    private EstadoPartido estado; //Sin final por la misma razón

    /**
     * Crea un nuevo partido entre dos participantes.
     *
     * @param local participante local.
     * @param visitante participante visitante.
     * @param ronda número de ronda a la que pertenece el partido.
     * @throws DatosInvalidosException si algún participante es {@code null}, si ambos participantes son iguales o si la ronda es menor que 1.
     */
    public Partido(Participante local, Participante visitante, int ronda) {
        if (local == null || visitante == null) {
            throw new DatosInvalidosException("Los participantes no pueden ser null");
        }
        if (local.equals(visitante)) { //Un participante no puede jugar contra sí mismo
            throw new DatosInvalidosException("Un participante no puede jugar contra sí mismo");
        }
        if (ronda < 1) {
            throw new DatosInvalidosException("La ronda debe ser mayor a 0");
        }

        this.local = local;
        this.visitante = visitante;
        this.ronda = ronda;
        this.estado = EstadoPartido.PENDIENTE; //Todo partido parte sin jugarse
        this.resultado = null; //No hay resultado hasta que el partido termine
    }

    /**
     * Obtiene el participante local.
     *
     * @return participante local.
     */
    public Participante getLocal() {
        return local;
    }

    /**
     * Obtiene el participante visitante.
     *
     * @return participante visitante.
     */
    public Participante getVisitante() {
        return visitante;
    }

    /**
     * Obtiene la ronda a la que pertenece el partido.
     *
     * @return número de ronda.
     */
    public int getRonda() {
        return ronda;
    }

    /**
     * Obtiene el resultado del partido.
     *
     * @return resultado del partido, o {@code null} si todavía no ha sido registrado.
     */
    public Resultado getResultado() {
        return resultado;
    }

    /**
     * Obtiene el estado actual del partido.
     *
     * @return estado actual del partido.
     */
    public EstadoPartido getEstado() {
        return estado;
    }

    /**
     * Registra el resultado del partido y lo marca como finalizado.
     *
     * @param resultado resultado que se desea registrar.
     * @throws DatosInvalidosException si el resultado es {@code null}.
     * @throws IllegalStateException si el partido ya se encuentra finalizado o cancelado.
     */
    public void registrarResultado(Resultado resultado) {
        if (resultado == null) {
            throw new DatosInvalidosException("El resultado no puede ser null");
        }
        if (estado == EstadoPartido.FINALIZADO || estado == EstadoPartido.CANCELADO) { //No se puede modificar un partido ya cerrado
            throw new IllegalStateException("El partido ya no puede recibir resultados");
        }
        this.resultado = resultado;
        this.estado = EstadoPartido.FINALIZADO; //Al registrar el resultado, el partido queda cerrado
    }

    /**
     * Cancela el partido.
     *
     * @throws IllegalStateException si el partido ya se encuentra finalizado.
     */
    public void cancelar() {
        if (estado == EstadoPartido.FINALIZADO) {
            throw new IllegalStateException("No se puede cancelar un partido ya finalizado");
        }
        this.estado = EstadoPartido.CANCELADO;
    }

    /**
     * Obtiene el ganador del partido.
     *
     * @return participante ganador, o {@code null} si no hay resultado o si el partido terminó empatado.
     */
    public Participante getGanador() {
        if (resultado == null) return null; //Si no hay resultado, no hay ganador todavía

        switch (resultado.determinarGanador()) { //Devuelve LOCAL, VISITANTE o EMPATE
            case LOCAL:
                return local;
            case VISITANTE:
                return visitante;
            default:
                return null; //En caso de empate no hay ganador
        }
    }

    /**
     * Devuelve una representación textual del partido.
     *
     * @return texto con participantes, ronda, estado y resultado si existe.
     */
    @Override
    public String toString() {
        String texto = local.getNombre() + " vs " + visitante.getNombre() + " | Ronda " + ronda + " | " + estado;

        if (resultado != null) {
            texto = texto + " | " + resultado;
        }

        return texto;
    }
}