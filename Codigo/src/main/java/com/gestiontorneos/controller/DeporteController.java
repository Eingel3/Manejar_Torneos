package com.gestiontorneos.controller;

import com.gestiontorneos.model.deporte.Deporte;

import java.util.List;
import java.util.ArrayList;
// import com.gestiontorneos.model.deporte.Deporte;

public class DeporteController {

    private ArrayList<Deporte> deportes;

    public DeporteController(){
        this.deportes = new ArrayList<Deporte>();
    }

    public Deporte buscarDeporte(String nombre){
        for (Deporte deporte : deportes){
            if ( deporte.getNombre().equals(nombre)){
                return deporte;

            }
        }
        return null;
    }


    public Deporte crearDeporte(String nombreDeporte){
        return null;
    }

    public boolean eliminarDeporte(){
        return false;
    }

    public ArrayList<Deporte> listaDeportes(){
        return deportes;
    }

}