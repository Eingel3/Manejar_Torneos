package com.gestiontorneos.model.participante;

public class JugadorIndividual extends Participante {

    public JugadorIndividual(String nombre, String contacto) {
        super(nombre, contacto);
    }

    @Override
    public int getCantidadIntegrantes() {
        return 1;
    }
}