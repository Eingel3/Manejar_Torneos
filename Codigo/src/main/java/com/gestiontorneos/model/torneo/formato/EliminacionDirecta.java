package com.gestiontorneos.model.torneo.formato;

import com.gestiontorneos.model.participante.Participante;
import com.gestiontorneos.model.partido.Calendario;
import com.gestiontorneos.model.partido.EstadoPartido;
import com.gestiontorneos.model.partido.Partido;
import com.gestiontorneos.model.torneo.Clasificacion;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación del formato de eliminación directa.
 * <p>
 * En este formato, los participantes se enfrentan por rondas y solo los
 * ganadores avanzan. Cuando queda un único ganador, el torneo se considera
 * definido.
 * </p>
 *
 * @see FormatoTorneo
 */
public class EliminacionDirecta implements FormatoTorneo {

    private int rondaActual; //Lleva la cuenta de en qué ronda estamos

    /**
     * Crea un formato de eliminación directa comenzando en la primera ronda.
     */
    public EliminacionDirecta() {
        this.rondaActual = 1;
    }

    /**
     * Genera los partidos iniciales de la primera ronda.
     *
     * @param participantes lista de participantes inscritos.
     * @return lista de partidos generados para la primera ronda.
     */
    @Override
    public List<Partido> generarEnfrentamientos(List<Participante> participantes) { //Genera los partidos de la primera ronda
        return emparejar(participantes, rondaActual);
    }

    /**
     * Genera la siguiente ronda a partir de los ganadores de la ronda actual.
     *
     * @param calendario calendario actual del torneo.
     * @return lista de partidos de la siguiente ronda, o una lista vacía si no se puede avanzar.
     */
    @Override
    public List<Partido> generarSiguienteRonda(Calendario calendario) { //Revisa si la ronda actual terminó y genera la siguiente con los ganadores
        List<Partido> rondaAnterior = calendario.getPorRonda(rondaActual);

        for (Partido p : rondaAnterior) { //Si algún partido de la ronda actual no terminó, todavía no se puede avanzar
            if (p.getEstado() != EstadoPartido.FINALIZADO) {
                return new ArrayList<>();
            }
        }

        List<Participante> ganadores = new ArrayList<>();
        for (Partido p : rondaAnterior) { //Recolecta los ganadores de cada partido
            Participante ganador = p.getGanador();
            if (ganador != null) {
                ganadores.add(ganador);
            }
        }

        if (ganadores.size() <= 1) { //Si queda uno o ningún ganador, el torneo terminó
            return new ArrayList<>();
        }

        rondaActual++; //Avanza a la siguiente ronda
        return emparejar(ganadores, rondaActual); //Genera los partidos de la nueva ronda
    }

    /**
     * Actualiza la clasificación sumando un punto al ganador del partido.
     *
     * @param clasificacion clasificación del torneo.
     * @param partido partido finalizado.
     */
    @Override
    public void actualizarClasificacion(Clasificacion clasificacion, Partido partido) { //1 punto por victoria, para ver quién ganó más partidos
        Participante ganador = partido.getGanador();
        if (ganador != null) {
            clasificacion.sumarPuntos(ganador, 1);
        }
    }

    /**
     * Indica si todos los partidos del calendario se encuentran cerrados.
     *
     * @param calendario calendario del torneo.
     * @return {@code true} si todos los partidos están finalizados o cancelados.
     */
    @Override
    public boolean haTerminado(Calendario calendario) { //Termina cuando todos los partidos de todas las rondas están finalizados
        return calendario.todosFinalizados();
    }

    /**
     * Obtiene el campeón del torneo según la clasificación.
     *
     * @param calendario calendario del torneo.
     * @param clasificacion clasificación actual.
     * @return participante con más victorias registradas.
     */
    @Override
    public Participante obtenerGanador(Calendario calendario, Clasificacion clasificacion) { //El campeón es quien tiene más victorias
        return clasificacion.getLider();
    }

    /**
     * Empareja participantes de dos en dos para crear partidos.
     *
     * @param participantes participantes que serán emparejados.
     * @param ronda ronda asignada a los partidos generados.
     * @return lista de partidos creados.
     */
    private List<Partido> emparejar(List<Participante> participantes, int ronda) { //Método interno que empareja de a dos
        List<Partido> partidos = new ArrayList<>();
        for (int i = 0; i < participantes.size() - 1; i += 2) { //i += 2 porque cada vuelta consume dos participantes
            partidos.add(new Partido(participantes.get(i), participantes.get(i + 1), ronda));
        }
        return partidos;
    }
}