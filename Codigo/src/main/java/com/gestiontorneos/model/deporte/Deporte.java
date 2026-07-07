package com.gestiontorneos.model.deporte;

import com.gestiontorneos.model.excepciones.DatosInvalidosException;
import java.util.Objects;

/**
 * Representa un deporte disponible para la creación de torneos.
 * <p>
 * Cada deporte posee un nombre y un tipo de participación, el cual indica si
 * se juega de manera individual o colectiva.
 * </p>
 *
 * @see TipoParticipacion
 * @see DatosInvalidosException
 */
public class Deporte {

    private final String nombre;
    private final TipoParticipacion tipoParticipacion;

    /**
     * Crea un nuevo deporte con nombre y tipo de participación.
     * <p>
     * Valida que el nombre no esté vacío y que el tipo de participación no sea
     * {@code null}.
     * </p>
     *
     * @param nombre nombre del deporte.
     * @param tipoParticipacion tipo de participación del deporte.
     * @throws DatosInvalidosException si el nombre está vacío o si el tipo de participación es {@code null}.
     */
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

    /**
     * Obtiene el nombre del deporte.
     *
     * @return nombre del deporte.
     */
    public String getNombre() { //Getter para el nombre del deporte
        return nombre;
    }

    /**
     * Obtiene el tipo de participación del deporte.
     *
     * @return tipo de participación asociado al deporte.
     */
    public TipoParticipacion getTipoParticipacion() { //Getter para el tipo de participación
        return tipoParticipacion;
    }

    /**
     * Devuelve una representación textual del deporte.
     *
     * @return nombre del deporte.
     */
    @Override
    public String toString() { //toString para mostrar el nombre del deporte
        return nombre;
    }
// ... existing code ...
    /**
     * Compara este deporte con otro objeto para determinar si representan el mismo
     * deporte.
     * <p>
     * Dos deportes se consideran iguales cuando el objeto recibido también es una
     * instancia de {@code Deporte} y ambos tienen el mismo nombre.
     * </p>
     *
     * @param o objeto con el que se desea comparar este deporte.
     * @return {@code true} si el objeto recibido es un {@code Deporte} con el mismo
     *         nombre; {@code false} en caso contrario.
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Deporte)) {
            return false;
        }
        Deporte otro = (Deporte) o;
        return this.nombre.equals(otro.nombre);
    }

    /**
     * Calcula el código hash del deporte a partir de su nombre.
     * <p>
     * Este método es coherente con {@link #equals(Object)}, ya que ambos utilizan
     * el nombre como criterio de identidad del deporte.
     * </p>
     *
     * @return código hash generado a partir del nombre del deporte.
     */
    @Override
    public int hashCode() {
        return Objects.hash(nombre);
    }
}
