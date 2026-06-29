package com.gestiontorneos.controller;

import java.util.List;
import java.util.ArrayList;
// import com.gestiontorneos.model.deporte.Deporte;

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


    public Deporte crearDeportes(){

    }

    public boolean eliminarDeporte(){

    }

    public List<Deporte> listaDeportes(){
        return deportes;
    }

}