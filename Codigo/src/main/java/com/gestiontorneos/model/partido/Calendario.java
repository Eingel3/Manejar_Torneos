package com.gestiontorneos.model.partido;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Representa el calendario de partidos de un torneo.
 * <p>
 * Esta clase almacena los partidos generados para un torneo y permite consultar
 * partidos según su estado o ronda. También ofrece una verificación general para
 * saber si todos los partidos han sido finalizados o cancelados.
 * </p>
 *
 * @see Partido
 * @see EstadoPartido
 */
public class Calendario {

    private final List<Partido> partidos;

    /**
     * Crea un calendario vacío.
     * <p>
     * Inicializa internamente una lista donde se almacenarán los partidos del
     * torneo.
     * </p>
     */
    public Calendario() {
        this.partidos = new ArrayList<>(); //ArrayList para almacenar los partidos
    }

    /**
     * Agrega un partido al calendario.
     *
     * @param partido partido que se desea agregar.
     * @throws IllegalArgumentException si el partido recibido es {@code null}.
     */
    public void agregarPartido(Partido partido) {
        if (partido == null) throw new IllegalArgumentException("El partido no puede ser null"); //Validación
        partidos.add(partido);
    }

    /**
     * Obtiene todos los partidos registrados en el calendario.
     * <p>
     * La lista retornada es de solo lectura para evitar modificaciones externas
     * directas sobre la colección interna.
     * </p>
     *
     * @return lista no modificable de partidos.
     */
    public List<Partido> getPartidos() {
        return Collections.unmodifiableList(partidos); //Lista no modificable de partidos para evitar problemas
    }

    /**
     * Obtiene los partidos que se encuentran pendientes.
     *
     * @return lista de partidos con estado {@link EstadoPartido#PENDIENTE}.
     */
    public List<Partido> getPendientes() { //Método para obtener los partidos pendientes
        List<Partido> pendientes = new ArrayList<>();
        for (Partido p : partidos) {
            if (p.getEstado() == EstadoPartido.PENDIENTE) {
                pendientes.add(p);
            }
        }
        return pendientes;
    }

    /**
     * Obtiene los partidos que ya han finalizado.
     *
     * @return lista de partidos con estado {@link EstadoPartido#FINALIZADO}.
     */
    public List<Partido> getFinalizados() { //Método para obtener los partidos finalizados
        List<Partido> finalizados = new ArrayList<>();
        for (Partido p : partidos) {
            if (p.getEstado() == EstadoPartido.FINALIZADO) {
                finalizados.add(p);
            }
        }
        return finalizados;
    }

    /**
     * Obtiene los partidos correspondientes a una ronda específica.
     *
     * @param ronda número de ronda que se desea consultar.
     * @return lista de partidos pertenecientes a la ronda indicada.
     */
    public List<Partido> getPorRonda(int ronda) { //Método para obtener partidos según la ronda
        List<Partido> resultado = new ArrayList<>();
        for (Partido p : partidos) {
            if (p.getRonda() == ronda) {
                resultado.add(p);
            }
        }
        return resultado;
    }

    /**
     * Indica si todos los partidos del calendario se encuentran finalizados o
     * cancelados.
     *
     * @return {@code true} si todos los partidos están finalizados o cancelados; {@code false} en caso contrario.
     */
    public boolean todosFinalizados() { //Método para obtener partidos o finalizados o cancelados
        for (Partido p : partidos) {
            if (p.getEstado() != EstadoPartido.FINALIZADO
                    && p.getEstado() != EstadoPartido.CANCELADO) {
                return false;
            }
        }
        return true;
    }
}