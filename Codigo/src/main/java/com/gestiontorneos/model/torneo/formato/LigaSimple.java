package com.gestiontorneos.model.torneo.formato;

import com.gestiontorneos.model.participante.Participante;
import com.gestiontorneos.model.partido.Calendario;
import com.gestiontorneos.model.partido.Partido;
import com.gestiontorneos.model.partido.Resultado;
import com.gestiontorneos.model.torneo.Clasificacion;
import java.util.ArrayList;
import java.util.List;

public class LigaSimple implements FormatoTorneo {

    @Override
    public List<Partido> generarEnfrentamientos(List<Participante> participantes) { //Genera todos los partidos: cada uno contra todos los demás una vez
        List<Partido> partidos = new ArrayList<>();
        int ronda = 1;

        for (int i = 0; i < participantes.size() - 1; i++) {
            for (int j = i + 1; j < participantes.size(); j++) { //j arranca en i+1 para no repetir parejas
                Participante local = participantes.get(i);
                Participante visitante = participantes.get(j);
                partidos.add(new Partido(local, visitante, ronda));
                ronda++;
            }
        }
        return partidos;
    }

    @Override
    public void actualizarClasificacion(Clasificacion clasificacion, Partido partido) { //Suma puntos según el resultado: 3 victoria, 1 empate, 0 derrota
        Participante ganador = partido.getGanador();

        if (ganador != null) { //Hubo un ganador
            clasificacion.sumarPuntos(ganador, 3); //3 puntos al ganador
        } else { //Empate
            clasificacion.sumarPuntos(partido.getLocal(), 1); //1 punto a cada uno
            clasificacion.sumarPuntos(partido.getVisitante(), 1);
        }
    }

    @Override
    public boolean haTerminado(Calendario calendario) { //El torneo termina cuando todos los partidos están finalizados
        return calendario.todosFinalizados();
    }

    @Override
    public Participante obtenerGanador(Calendario calendario, Clasificacion clasificacion) { //El ganador es quien tiene más puntos en la tabla
        return clasificacion.getLider();
    }
}