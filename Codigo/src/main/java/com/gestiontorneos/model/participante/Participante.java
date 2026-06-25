package com.gestiontorneos.model.participante;

import com.gestiontorneos.model.excepciones.DatosInvalidosException;

public abstract class Participante {

    private final String rut; //TODO: Definir si utilizar rut o id
    private final String nombre;
    private final String contacto;

    protected Participante(String nombre, String rut, String contacto) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new DatosInvalidosException("El nombre del participante no puede estar vacío");
        }
        this.rut = rut;
        this.nombre = nombre.trim();
        this.contacto = contacto;
    }

    public String getRut() {
        return rut;
    }

    public String getNombre() {
        return nombre;
    }

    public String getContacto() {
        return contacto;
    }

    public abstract int getCantidadIntegrantes();

    @Override
    public String toString() {
        return nombre;
    }
}

