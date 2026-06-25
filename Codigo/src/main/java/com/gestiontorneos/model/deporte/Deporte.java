package com.gestiontorneos.model.deporte;

import com.gestiontorneos.model.excepciones.DatosInvalidosException;

public class Deporte {

    private final String nombre;
    private final TipoParticipacion tipoParticipacion;

    public Deporte(String nombre, TipoParticipacion tipoParticipacion) { //Constructor para crear un deporte
        if (nombre == null || nombre.trim().isEmpty()) { //Validación de entradas
            throw new DatosInvalidosException("El nombre del deporte no puede estar vacío");
        }
        if (tipoParticipacion == null) {
            throw new DatosInvalidosException("El deporte debe tener un tipo de participación");
        }

        this.nombre = nombre.trim();
        this.tipoParticipacion = tipoParticipacion;
    }

    public String getNombre() { //Getter para el nombre del deporte
        return nombre;
    }

    public TipoParticipacion getTipoParticipacion() { //Getter para el tipo de participación
        return tipoParticipacion;
    }

    @Override
    public String toString() { //toString para mostrar el nombre del deporte
        return nombre;
    }


}