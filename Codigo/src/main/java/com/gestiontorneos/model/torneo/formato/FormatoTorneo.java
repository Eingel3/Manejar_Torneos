package com.gestiontorneos.model.torneo.formato;

import com.gestiontorneos.model.participante.Participante;
import com.gestiontorneos.model.partido.Partido;
import com.gestiontorneos.model.partido.Calendario;
import com.gestiontorneos.model.torneo.Clasificacion;
import java.util.List;

public interface FormatoTorneo {

    //Genera todos los partidos iniciales según los participantes inscritos
    List<Partido> generarEnfrentamientos(List<Participante> participantes);

    //Recalcula la tabla cuando un partido termina
    void actualizarClasificacion(Clasificacion clasificacion, Partido partido);

    // Variable para determinar si terminó el torneo
    boolean haTerminado(Calendario calendario);

    //Se obtiene el ganador
    Participante obtenerGanador(Calendario calendario, Clasificacion clasificacion);
}