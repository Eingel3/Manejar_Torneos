package com.gestiontorneos.model.participante;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.gestiontorneos.model.excepciones.DatosInvalidosException;

public class Equipo extends Participante {

    private final List<String> integrantes;

    public Equipo(String nombre, String contacto, List<String> integrantes) {
        super(nombre, contacto);
        if (integrantes == null || integrantes.isEmpty()) { //Un equipo sin integrantes no tiene sentido
            throw new DatosInvalidosException("El equipo debe tener al menos un integrante");
        }

        this.integrantes = new ArrayList<>(integrantes); //Copia la lista para no depender de la original
    }

    @Override
    public int getCantidadIntegrantes() {
        return integrantes.size();
    }

    public List<String> getIntegrantes() { //Devuelve la lista como solo lectura para proteger los datos internos
        return Collections.unmodifiableList(integrantes);
    }

    public void agregarIntegrante(String nombreIntegrante) {
        if(nombreIntegrante == null || nombreIntegrante.trim().isEmpty()) {
            throw new DatosInvalidosException("El nombre no puede estar vacío");
        }
        integrantes.add(nombreIntegrante.trim());
    }
}