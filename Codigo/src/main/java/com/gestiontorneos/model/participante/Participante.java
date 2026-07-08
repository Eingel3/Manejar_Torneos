package com.gestiontorneos.model.participante;

import java.util.Objects;
import java.util.UUID;
import com.gestiontorneos.model.excepciones.DatosInvalidosException;

/**
 * Clase base abstracta para representar a cualquier participante de un torneo.
 * <p>
 * Un participante puede ser un jugador individual o un equipo. Cada participante
 * posee un identificador único, un nombre y un contacto opcional.
 * </p>
 * <p>
 * La igualdad entre participantes se determina mediante su identificador único,
 * no por su nombre.
 * </p>
 *
 * @see Equipo
 * @see JugadorIndividual
 * @see DatosInvalidosException
 */
public abstract class Participante {

    private final String id;
    private final String nombre;
    private final String contacto;

    /**
     * Crea un participante con nombre y contacto.
     * <p>
     * Genera automáticamente un identificador único para distinguir participantes,
     * incluso si tienen el mismo nombre.
     * </p>
     *
     * @param nombre nombre del participante.
     * @param contacto información de contacto del participante.
     * @throws DatosInvalidosException si el nombre es {@code null} o está vacío.
     */
    protected Participante(String nombre, String contacto) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new DatosInvalidosException("El nombre del participante no puede estar vacío");
        }
        this.id = UUID.randomUUID().toString(); //Id para diferenciar entre participantes de mismo nombre
        this.nombre = nombre.trim();
        this.contacto = contacto;
    }

    /**
     * Obtiene el identificador único del participante.
     *
     * @return identificador único del participante.
     */
    public String getId() {
        return id;
    }

    /**
     * Obtiene el nombre del participante.
     *
     * @return nombre del participante.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene la información de contacto del participante.
     *
     * @return contacto del participante.
     */
    public String getContacto() {
        return contacto;
    }

    /**
     * Obtiene la cantidad de integrantes asociados al participante.
     *
     * @return cantidad de integrantes del participante.
     */
    public abstract int getCantidadIntegrantes();


    /**
     * Obtiene un string con el tipo de participante.
     *
     * @return string con el tipo de participante.
     */
    public abstract String getTipo();

    /**
     * Devuelve una representación textual del participante.
     *
     * @return nombre del participante.
     */
    @Override
    public String toString() {
        return nombre;
    }

    /**
     * Compara este participante con otro objeto.
     * <p>
     * Dos participantes se consideran iguales si tienen el mismo identificador
     * único.
     * </p>
     *
     * @param o objeto a comparar.
     * @return {@code true} si ambos participantes tienen el mismo identificador; {@code false} en caso contrario.
     */
    @Override
    public boolean equals(Object o) { //Validar igualdad entre participantes
        if (!(o instanceof Participante)) { //Validamos que el objeto a comparar sea de la clase Participante
            return false;
        }
        Participante otro = (Participante) o; //Convertimos el objeto a comparar a la clase Participante
        return this.id.equals(otro.id); //Comparamos id
    }

    /**
     * Genera el código hash del participante usando su identificador único.
     *
     * @return código hash del participante.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}