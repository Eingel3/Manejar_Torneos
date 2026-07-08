package com.gestiontorneos.model.participante;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.gestiontorneos.model.excepciones.DatosInvalidosException;

/**
 * Representa un participante compuesto por varios integrantes.
 * <p>
 * Un equipo hereda los datos comunes de {@link Participante} y agrega una lista
 * de integrantes. Esta lista se protege mediante copias y vistas de solo lectura
 * para evitar modificaciones externas no controladas.
 * </p>
 *
 * @see Participante
 * @see DatosInvalidosException
 */
public class Equipo extends Participante {

    private final List<String> integrantes;

    /**
     * Crea un nuevo equipo con nombre, contacto e integrantes.
     *
     * @param nombre nombre del equipo.
     * @param contacto información de contacto del equipo.
     * @param integrantes lista inicial de integrantes del equipo.
     * @throws DatosInvalidosException si la lista de integrantes es {@code null} o está vacía.
     */
    public Equipo(String nombre, String contacto, List<String> integrantes) {
        super(nombre, contacto);
        if (integrantes == null || integrantes.isEmpty()) { //Un equipo sin integrantes no tiene sentido
            throw new DatosInvalidosException("El equipo debe tener al menos un integrante");
        }

        this.integrantes = new ArrayList<>(integrantes); //Copia la lista para no depender de la original
    }

    /**
     * Obtiene la cantidad de integrantes del equipo.
     *
     * @return número de integrantes registrados en el equipo.
     */
    @Override
    public int getCantidadIntegrantes() {
        return integrantes.size();
    }
    /**
     * Obtiene el tipo de participante como "Equipo".
     *
     * @return string con el tipo de participante.
     */
    @Override
    public String getTipo() {
        return "Equipo";
    }

    /**
     * Obtiene la lista de integrantes del equipo como una lista de solo lectura.
     *
     * @return lista no modificable de integrantes.
     */
    public List<String> getIntegrantes() { //Devuelve la lista como solo lectura para proteger los datos internos
        return Collections.unmodifiableList(integrantes);
    }

    /**
     * Agrega un nuevo integrante al equipo.
     *
     * @param nombreIntegrante nombre del integrante que se desea agregar.
     * @throws DatosInvalidosException si el nombre del integrante es {@code null} o está vacío.
     */
    public void agregarIntegrante(String nombreIntegrante) {
        if (nombreIntegrante == null || nombreIntegrante.trim().isEmpty()) {
            throw new DatosInvalidosException("El nombre no puede estar vacío");
        }
        integrantes.add(nombreIntegrante.trim());
    }
}