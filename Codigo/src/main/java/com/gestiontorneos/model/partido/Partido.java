package com.gestiontorneos.model.partido;

import com.gestiontorneos.model.torneo.Torneo;

public class Partido{

    private String nombre;
    private Torneo torneo;
    private EstadoPartido estado;

    public Partido(String nombre, Torneo torneo){
        this.nombre = nombre;
        this.torneo = torneo;
    }

    //GETTERS

    public String getNombre(){
        return nombre;
    }

    //por mientras getTorneo que es mas generico pero util
    public Torneo getTorneo(){
        return torneo;
    }

    public EstadoPartido getEstadoPartido(){
        return estado;
    }


    //SETTERS


    public void setEstadoPartido(EstadoPartido estado){
        this.estado = estado;
    }

}