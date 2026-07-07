package com.gestiontorneos.model;

import com.gestiontorneos.model.deporte.Deporte;
import com.gestiontorneos.model.deporte.TipoParticipacion;
import com.gestiontorneos.model.excepciones.DatosInvalidosException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase de pruebas unitarias para verificar el comportamiento de la clase
 * {@link Deporte}.
 * <p>
 * Estas pruebas validan la creación correcta de deportes, las validaciones
 * del constructor, el comportamiento del método toString y la igualdad
 * entre instancias basada en el nombre.
 * </p>
 *
 * @see Deporte
 * @see TipoParticipacion
 */
class DeporteTest {

    /**
     * Verifica que un deporte se crea correctamente con datos válidos.
     * <p>
     * Comprueba que el nombre y el tipo de participación se almacenan
     * correctamente al momento de la construcción.
     * </p>
     */
    @Test
    void deporteSeCreaCorrectamente() {
        Deporte deporte = new Deporte("Fútbol", TipoParticipacion.COLECTIVO);
        assertEquals("Fútbol", deporte.getNombre());
        assertEquals(TipoParticipacion.COLECTIVO, deporte.getTipoParticipacion());
    }

    /**
     * Verifica que no sea posible crear un deporte con nombre vacío.
     * <p>
     * El constructor debe lanzar una {@link DatosInvalidosException} cuando
     * el nombre es una cadena vacía.
     * </p>
     */
    @Test
    void nombreVacioLanzaExcepcion() {
        assertThrows(DatosInvalidosException.class, () ->
                new Deporte("", TipoParticipacion.INDIVIDUAL)
        );
    }

    /**
     * Verifica que no sea posible crear un deporte con nombre null.
     * <p>
     * El constructor debe lanzar una {@link DatosInvalidosException} cuando
     * el nombre es null.
     * </p>
     */
    @Test
    void nombreNullLanzaExcepcion() {
        assertThrows(DatosInvalidosException.class, () ->
                new Deporte(null, TipoParticipacion.INDIVIDUAL)
        );
    }

    /**
     * Verifica que no sea posible crear un deporte sin tipo de participación.
     * <p>
     * El tipo de participación es obligatorio. Si se pasa null, el constructor
     * debe lanzar una {@link DatosInvalidosException}.
     * </p>
     */
    @Test
    void tipoParticipacionNullLanzaExcepcion() {
        assertThrows(DatosInvalidosException.class, () ->
                new Deporte("Fútbol", null)
        );
    }

    /**
     * Verifica que el constructor elimina los espacios al inicio y al final del nombre.
     * <p>
     * El método trim() debe aplicarse al nombre antes de almacenarlo,
     * de forma que nombres con espacios innecesarios queden limpios.
     * </p>
     */
    @Test
    void nombreConEspaciosSeGuardaLimpio() {
        Deporte deporte = new Deporte("  Fútbol  ", TipoParticipacion.COLECTIVO);
        assertEquals("Fútbol", deporte.getNombre());
    }

    /**
     * Verifica que el método toString devuelve únicamente el nombre del deporte.
     * <p>
     * Esta representación es utilizada por la interfaz gráfica para mostrar
     * los deportes disponibles en listas y menús desplegables.
     * </p>
     */
    @Test
    void toStringDevuelveNombre() {
        Deporte deporte = new Deporte("Ajedrez", TipoParticipacion.INDIVIDUAL);
        assertEquals("Ajedrez", deporte.toString());
    }

    /**
     * Verifica que dos deportes con el mismo nombre son considerados iguales.
     * <p>
     * La igualdad entre deportes se basa exclusivamente en el nombre,
     * independientemente del tipo de participación asignado.
     * </p>
     */
    @Test
    void deportesConMismoNombreSonIguales() {
        Deporte d1 = new Deporte("Fútbol", TipoParticipacion.COLECTIVO);
        Deporte d2 = new Deporte("Fútbol", TipoParticipacion.INDIVIDUAL);
        assertEquals(d1, d2);
    }

    /**
     * Verifica que dos deportes con distinto nombre no son considerados iguales.
     * <p>
     * Si los nombres difieren, los deportes representan disciplinas distintas
     * y no deben ser tratados como el mismo objeto lógico.
     * </p>
     */
    @Test
    void deportesConDistintoNombreNoSonIguales() {
        Deporte d1 = new Deporte("Fútbol", TipoParticipacion.COLECTIVO);
        Deporte d2 = new Deporte("Ajedrez", TipoParticipacion.COLECTIVO);
        assertNotEquals(d1, d2);
    }
}