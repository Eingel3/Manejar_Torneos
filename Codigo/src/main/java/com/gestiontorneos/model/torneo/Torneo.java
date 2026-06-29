package com.gestiontorneos.model.torneo;

import com.gestiontorneos.model.deporte.Deporte;
import com.gestiontorneos.model.participante.Participante;

/**
 * Clase editada para que no de problemas por ahora
 * Es decir, está incompleta
 */

public class Torneo {
    private String nombre;
    private Deporte deporte;

    public Torneo(String nombre, Deporte deporte) {
        this.nombre = nombre;
        this.deporte = deporte;
    }

    public String getNombre(){
        return nombre;
    }

    public void agregarParticipante(Participante participante){
        if ( participante != null ){
            //codigo
        }
    }
    public Deporte getDeporte(){
        return deporte;
    }
}