package com.gestiontorneos.model;

import com.gestiontorneos.model.excepciones.DatosInvalidosException;
import com.gestiontorneos.model.participante.Equipo;
import com.gestiontorneos.model.participante.JugadorIndividual;
import com.gestiontorneos.model.participante.Participante;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class ParticipanteTest {

    @Test
    void jugadorIndividualTieneUnIntegrante() { //Siempre debe devolver 1
        Participante jugador = new JugadorIndividual("Maxi", "maxi@gmail.com");
        assertEquals(1, jugador.getCantidadIntegrantes());
    }

    @Test
    void equipoTieneCantidadCorrectaDeIntegrantes() { //Debe coincidir con la lista pasada
        Participante equipo = new Equipo("Udec", "udecdeportes@gmail.com",
                List.of("JugadorInformatico1", "JugadorInformatico2", "JugadorInformatico3"));
        assertEquals(3, equipo.getCantidadIntegrantes());
    }

    @Test
    void nombreVacioLanzaExcepcion() { //No debe poder crearse sin nombre
        assertThrows(DatosInvalidosException.class, () ->
                new JugadorIndividual("", "cualquiercorreo@gmail.com")
        );
    }

    @Test
    void nombreNullLanzaExcepcion() { //Null tampoco es válido
        assertThrows(DatosInvalidosException.class, () ->
                new JugadorIndividual(null, "cualquiercorreo2@gmail.com")
        );
    }

    @Test
    void equipoSinIntegrantesLanzaExcepcion() { //Un equipo vacío no tiene sentido
        assertThrows(DatosInvalidosException.class, () ->
                new Equipo("Nadie Team", "     @gmail.com", List.of())
        );
    }

    @Test
    void dosJugadoresDistintosNoSonIguales() { //Test de equals por id — dos objetos distintos no son iguales
        Participante jugador1 = new JugadorIndividual("Ana", "ana@gmail.com");
        Participante jugador2 = new JugadorIndividual("Ana", "ana@gmail.com");
        assertNotEquals(jugador1, jugador2); //Mismo nombre pero distinto id
    }

    @Test
    void mismoJugadorEsIgualASiMismo() { //Un participante siempre es igual a sí mismo
        Participante jugador = new JugadorIndividual("Ana", "ana@gmail.com");
        assertEquals(jugador, jugador);
    }

    @Test
    void agregarIntegranteAumentaCantidad() { //Agregar un integrante debe aumentar el conteo
        Equipo equipo = new Equipo("Udec FC", "udecFC@gmail.com",
                List.of("Pedro", "Juan"));
        equipo.agregarIntegrante("Diego");
        assertEquals(3, equipo.getCantidadIntegrantes());
    }
}