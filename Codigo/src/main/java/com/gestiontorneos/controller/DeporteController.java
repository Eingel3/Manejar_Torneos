package com.gestiontorneos.controller;

import java.util.List;
import java.util.ArrayList;
import com.gestiontorneos.model.deporte.Deporte;
import com.gestiontorneos.model.deporte.TipoParticipacion;

public class DeporteController {

    private List<Deporte> deportes;

    public DeporteController(){
        this.deportes = new ArrayList<>();
    }

    public Deporte buscarDeporte(String nombre){
        for (Deporte deporte : deportes){
            if ( deporte.getNombre().equals(nombre)){
                return deporte;

            }
        }
        return null;
    }

    //por mientras coloco tipo individual para no complicar, despues sera a eleccion del organizador
    public Deporte crearDeporte(String nombre){
            Deporte deporte = new Deporte(nombre, TipoParticipacion.INDIVIDUAL);
        deportes.add(deporte);
        return deporte;
    }

    public boolean eliminarDeporte(String nombre){
        Deporte deporte = buscarDeporte(nombre);
        if (deporte != null){
            deportes.remove(deporte);
            return true;
        }
        return false;
    }

    public List<Deporte> listaDeportes(){
        return deportes;
    }

}
