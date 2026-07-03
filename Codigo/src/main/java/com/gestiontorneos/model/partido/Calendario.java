package com.gestiontorneos.model.partido;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Calendario {

    private final List<Partido> partidos;

    public Calendario() {
        this.partidos = new ArrayList<>(); //ArrayList para almacenar los partidos
    }

    public void agregarPartido(Partido partido) {
        if (partido == null) throw new IllegalArgumentException("El partido no puede ser null"); //Validación
        partidos.add(partido);
    }

    public List<Partido> getPartidos() {
        return Collections.unmodifiableList(partidos); //Lista no modificable de partidos para evitar problemas
    }

    public List<Partido> getPendientes() { //Método para obtener los partidos pendientes
        List<Partido> pendientes = new ArrayList<>();
        for (Partido p : partidos) {
            if (p.getEstado() == EstadoPartido.PENDIENTE) {
                pendientes.add(p);
            }
        }
        return pendientes;
    }

    public List<Partido> getFinalizados() { //Método para obtener los partidos finalizados
        List<Partido> finalizados = new ArrayList<>();
        for (Partido p : partidos) {
            if (p.getEstado() == EstadoPartido.FINALIZADO) {
                finalizados.add(p);
            }
        }
        return finalizados;
    }

    public List<Partido> getPorRonda(int ronda) { //Método para obtener partidos según la ronda
        List<Partido> resultado = new ArrayList<>();
        for (Partido p : partidos) {
            if (p.getRonda() == ronda) {
                resultado.add(p);
            }
        }
        return resultado;
    }

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