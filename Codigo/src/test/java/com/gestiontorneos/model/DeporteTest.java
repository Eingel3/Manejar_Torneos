package com.gestiontorneos.model;

import com.gestiontorneos.model.deporte.Deporte;
import com.gestiontorneos.model.deporte.TipoParticipacion;
import com.gestiontorneos.model.excepciones.DatosInvalidosException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DeporteTest {

    @Test
    void deporteSeCreaCorrectamente() { //Creación básica sin errores
        Deporte deporte = new Deporte("Fútbol", TipoParticipacion.COLECTIVO);
        assertEquals("Fútbol", deporte.getNombre());
        assertEquals(TipoParticipacion.COLECTIVO, deporte.getTipoParticipacion());
    }

    @Test
    void nombreVacioLanzaExcepcion() { //No debe poder crearse sin nombre
        assertThrows(DatosInvalidosException.class, () ->
                new Deporte("", TipoParticipacion.INDIVIDUAL)
        );
    }

    @Test
    void nombreNullLanzaExcepcion() { //Null tampoco es válido
        assertThrows(DatosInvalidosException.class, () ->
                new Deporte(null, TipoParticipacion.INDIVIDUAL)
        );
    }

    @Test
    void tipoParticipacionNullLanzaExcepcion() { //Debe tener un tipo de participación
        assertThrows(DatosInvalidosException.class, () ->
                new Deporte("Fútbol", null)
        );
    }

    @Test
    void nombreConEspaciosSeGuardaLimpio() { //trim() debe limpiar espacios
        Deporte deporte = new Deporte("  Fútbol  ", TipoParticipacion.COLECTIVO);
        assertEquals("Fútbol", deporte.getNombre());
    }

    @Test
    void toStringDevuelveNombre() { //toString debe devolver solo el nombre
        Deporte deporte = new Deporte("Ajedrez", TipoParticipacion.INDIVIDUAL);
        assertEquals("Ajedrez", deporte.toString());
    }

    @Test
    void deportesConMismoNombreSonIguales() { //equals basado en nombre
        Deporte d1 = new Deporte("Fútbol", TipoParticipacion.COLECTIVO);
        Deporte d2 = new Deporte("Fútbol", TipoParticipacion.INDIVIDUAL);
        assertEquals(d1, d2);
    }

    @Test
    void deportesConDistintoNombreNoSonIguales() { //Nombres distintos implica que sean objetos distintos
        Deporte d1 = new Deporte("Fútbol", TipoParticipacion.COLECTIVO);
        Deporte d2 = new Deporte("Ajedrez", TipoParticipacion.COLECTIVO);
        assertNotEquals(d1, d2);
    }
}