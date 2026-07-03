package com.gestiontorneos.model.torneo.formato;

import com.gestiontorneos.model.participante.Participante;
import com.gestiontorneos.model.partido.Partido;
import com.gestiontorneos.model.partido.Calendario;
import com.gestiontorneos.model.torneo.Clasificacion;
import java.util.List;

public interface FormatoTorneo {

    List<Partido> generarEnfrentamientos(List<Participante> participantes);
    void actualizarClasificacion(Clasificacion clasificacion, Partido partido);
    boolean haTerminado(Calendario calendario);
    Participante obtenerGanador(Calendario calendario, Clasificacion clasificacion);
}