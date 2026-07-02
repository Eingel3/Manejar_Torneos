package com.gestiontorneos.model.partido;

import com.gestiontorneos.model.torneo.Torneo;

public class Partido{

    private final int numero;
    private String nombre;
    private Torneo torneo;
    private EstadoPartido estado;

    public Partido(String nombre, Torneo torneo, int numero){
        this.nombre = nombre;//si es semifinal, final o algo asi, si es torneo por puntos es igual al atributo numerod e abajo
        this.torneo = torneo;
        this.numero = numero;//si es un torneo por puntos este int identifica a cada partido
        this.estado = EstadoPartido.PENDIENTE;
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