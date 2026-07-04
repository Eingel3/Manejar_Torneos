package com.gestiontorneos.model.torneo.formato;

import com.gestiontorneos.model.participante.Participante;
import com.gestiontorneos.model.partido.Partido;
import com.gestiontorneos.model.partido.Calendario;
import com.gestiontorneos.model.torneo.Clasificacion;
import java.util.List;

public interface FormatoTorneo {


    List<Partido> generarEnfrentamientos(List<Participante> participantes); //Genera todos los partidos iniciales según los participantes inscritos

    void actualizarClasificacion(Clasificacion clasificacion, Partido partido);     //Recalcula la tabla cuando un partido termina

    boolean haTerminado(Calendario calendario); //Verifica si el torneo ha terminado

    Participante obtenerGanador(Calendario calendario, Clasificacion clasificacion); //Devuelve el ganador del torneo

    default List<Partido> generarSiguienteRonda(Calendario calendario){ //Necesario para generar nuevos partidos en torneos de eliminación directa
        return new ArrayList<>();
    }
}