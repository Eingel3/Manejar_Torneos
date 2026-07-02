package com.gestiontorneos.model.torneo;

import java.util.List;
import java.util.ArrayList;
import com.gestiontorneos.model.deporte.Deporte;
import com.gestiontorneos.model.participante.Participante;
import com.gestiontorneos.model.partido.Partido;

/**
 * Clase editada para que no de problemas por ahora
 * Es decir, está incompleta
 */

public class Torneo {
    private String nombre;
    private Deporte deporte;
    private List<Participante> participantes;
    private List<Partido> partidos;

    public Torneo(String nombre, Deporte deporte) {
        this.nombre = nombre;
        this.deporte = deporte;
        this.participantes = new ArrayList<>();
        this.partidos= new ArrayList<>();
    }

    public String getNombre(){
        return nombre;
    }

    public List<Participante> getParticipantes() {
        return participantes;
    }

    public void agregarParticipante(Participante participante) {
        if (participante != null) {
            participantes.add(participante);
        }
    }

    public void eliminarParticipante(Participante participante) {
        participantes.remove(participante);
    }

    public Deporte getDeporte(){
        return deporte;
    }


    public void agregarPartido(Partido partido){
        if (partido != null){
            partidos.add(partido);
        }
        System.out.println("Fallo en agregar partido");
    }

    //metodo para buscar un partido por su nombre
    public Partido buscarPartido(String nombre) {
        for (Partido partido : partidos) {
            if (partido.getNombre().equals(nombre)) {
                return partido;
            }
        }
        return null;
    }



}