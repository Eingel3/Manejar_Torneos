package com.gestiontorneos.model.participante;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.gestiontorneos.model.excepciones.DatosInvalidosException;

public class Equipo extends Participante {

    private final List<String> integrantes;

    public Equipo(String nombre, String contacto, List<String> integrantes) {
        super(nombre, contacto);
        // toDo: Validaciones

        this.integrantes = new ArrayList<>(integrantes);

    }

    @Override
    public int getCantidadIntegrantes() {
        return integrantes.size();
    }

    public List<String> getIntegrantes() { //toDo
        return null;
    }

    public void agregarIntegrante(String nombreIntegrante) {
        if(nombreIntegrante == null || nombreIntegrante.trim().isEmpty()) {
            throw new DatosInvalidosException("El nombre no puede estar vacío");
        }
        integrantes.add(nombreIntegrante.trim());
    }
}