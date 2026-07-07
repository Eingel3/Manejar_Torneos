package com.gestiontorneos.model.torneo.formato;

import com.gestiontorneos.model.participante.Participante;
import com.gestiontorneos.model.partido.Calendario;
import com.gestiontorneos.model.partido.Partido;
import com.gestiontorneos.model.torneo.Clasificacion;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación del formato de liga simple.
 * <p>
 * En este formato, cada participante se enfrenta una vez contra todos los demás.
 * La clasificación se actualiza con tres puntos por victoria y un punto para
 * cada participante en caso de empate.
 * </p>
 *
 * @see FormatoTorneo
 */
public class LigaSimple implements FormatoTorneo {

    /**
     * Genera todos los enfrentamientos posibles entre los participantes.
     *
     * @param participantes lista de participantes inscritos.
     * @return lista de partidos donde cada participante enfrenta una vez a los demás.
     */
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

    /**
     * Actualiza la clasificación según el resultado del partido.
     * <p>
     * Suma tres puntos al ganador. Si no hay ganador, considera el partido como
     * empate y suma un punto a cada participante.
     * </p>
     *
     * @param clasificacion clasificación del torneo.
     * @param partido partido finalizado.
     */
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

    /**
     * Indica si todos los partidos del calendario han finalizado o fueron
     * cancelados.
     *
     * @param calendario calendario del torneo.
     * @return {@code true} si el torneo terminó; {@code false} en caso contrario.
     */
    @Override
    public boolean haTerminado(Calendario calendario) { //El torneo termina cuando todos los partidos están finalizados
        return calendario.todosFinalizados();
    }

    /**
     * Obtiene el ganador del torneo según el líder de la clasificación.
     *
     * @param calendario calendario del torneo.
     * @param clasificacion clasificación actual.
     * @return participante con mayor puntaje.
     */
    @Override
    public Participante obtenerGanador(Calendario calendario, Clasificacion clasificacion) { //El ganador es quien tiene más puntos en la tabla
        return clasificacion.getLider();
    }
    @Override
    public String toString() {
        return "Liga Simple: En este formato, cada participante se enfrenta una vez contra todos los demás.\n" +
                " La clasificación se actualiza con tres puntos por victoria y un punto para cada participante en caso de empate.";
    }
}