package com.gestiontorneos.model.partido;

import com.gestiontorneos.model.participante.Participante;
import com.gestiontorneos.model.excepciones.DatosInvalidosException;

public class Partido {

    private final Participante local;
    private final Participante visitante;
    private final int ronda;
    private Resultado resultado; //Sin final porque se asigna después, no en el constructor
    private EstadoPartido estado; //Sin final por la misma razón

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
    //Getters
    public Participante getLocal() { return local; }
    public Participante getVisitante() { return visitante; }
    public int getRonda() { return ronda; }
    public Resultado getResultado() { return resultado; }
    public EstadoPartido getEstado() { return estado; }

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

    public void cancelar() {
        if (estado == EstadoPartido.FINALIZADO) {
            throw new IllegalStateException("No se puede cancelar un partido ya finalizado");
        }
        this.estado = EstadoPartido.CANCELADO;
    }

    public Participante getGanador() {
        if (resultado == null) return null; //Si no hay resultado, no hay ganador todavía

        switch (resultado.determinarGanador()) { //Devuelve LOCAL, VISITANTE o EMPATE
            case LOCAL:     return local;
            case VISITANTE: return visitante;
            default:        return null; // En caso de empate no hay ganador
        }
    }

    @Override
    public String toString() {
        String texto = local.getNombre() + " vs " + visitante.getNombre() + " | Ronda " + ronda + " | " + estado;

        if (resultado != null) {
            texto = texto + " | " + resultado;
        }

        return texto;
    }
}