package com.gestiontorneos.model.partido;

import com.gestiontorneos.model.participante.Participante;
import com.gestiontorneos.model.torneo.Torneo;
import com.gestiontorneos.model.excepciones.DatosInvalidosException;

public class Partido {

    private final Participante local;
    private final Participante visitante;
    private final int ronda;
    private Torneo torneo;  // ← AGREGADO para compatibilidad con TorneoController
    private Resultado resultado;
    private EstadoPartido estado;


    public Partido(Participante local,
                   Participante visitante,
                   int ronda,
                   Torneo torneo) {
        if (local == null || visitante == null) {
            throw new DatosInvalidosException("Los participantes no pueden ser null");
        }
        if (local.equals(visitante)) {
            throw new DatosInvalidosException("Un participante no puede jugar contra sí mismo");
        }
        if (ronda < 1) {
            throw new DatosInvalidosException("La ronda debe ser mayor a 0");
        }
        this.local = local;
        this.visitante = visitante;
        this.ronda = ronda;
        this.torneo = torneo;  // ← AGREGADO
        this.estado = EstadoPartido.PENDIENTE;
        this.resultado = null;
    }
    // GETTERS
    public Participante getLocal() { return local; }
    public Participante getVisitante() { return visitante; }
    public int getRonda() { return ronda; }
    public Torneo getTorneo() { return torneo; }  // ← AGREGADO
    public Resultado getResultado() { return resultado; }
    public EstadoPartido getEstado() { return estado; }
    // MÉTODOS EXISTENTES DEL COMPAÑERO
    public void registrarResultado(Resultado resultado) {
        if (resultado == null) {
            throw new DatosInvalidosException("El resultado no puede ser null");
        }
        if (estado == EstadoPartido.FINALIZADO || estado == EstadoPartido.CANCELADO) {
            throw new IllegalStateException("El partido ya no puede recibir resultados");
        }
        this.resultado = resultado;
        this.estado = EstadoPartido.FINALIZADO;
    }
    public void cancelar() {
        if (estado == EstadoPartido.FINALIZADO) {
            throw new IllegalStateException("No se puede cancelar un partido ya finalizado");
        }
        this.estado = EstadoPartido.CANCELADO;
    }
    public Participante getGanador() {
        if (resultado == null) return null;
        switch (resultado.determinarGanador()) {
            case LOCAL:     return local;
            case VISITANTE: return visitante;
            default:        return null;
        }
    }
    @Override
    public String toString() {
        String texto = local.getNombre() + " vs " + visitante.getNombre()
                + " | Ronda " + ronda + " | " + estado;
        if (resultado != null) {
            texto = texto + " | " + resultado;
        }
        return texto;
    }
}