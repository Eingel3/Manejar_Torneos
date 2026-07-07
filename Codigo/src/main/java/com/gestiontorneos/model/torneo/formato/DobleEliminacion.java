package com.gestiontorneos.model.torneo.formato;

import com.gestiontorneos.model.participante.Participante;
import com.gestiontorneos.model.partido.Calendario;
import com.gestiontorneos.model.partido.EstadoPartido;
import com.gestiontorneos.model.partido.Partido;
import com.gestiontorneos.model.torneo.Clasificacion;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación del formato de doble eliminación.
 * <p>
 * En este formato, los participantes comienzan en el bracket de ganadores. Al
 * perder una vez pasan al bracket de perdedores y, si pierden nuevamente, quedan
 * eliminados del torneo.
 * </p>
 *
 * @see FormatoTorneo
 */
public class DobleEliminacion implements FormatoTorneo {

    private List<Participante> bracketGanadores; //Participantes que no han perdido ningún partido
    private List<Participante> bracketPerdedores; //Participantes que han perdido una vez
    private int rondaActual;
    private boolean turnoGanadores; //Alterna entre procesar bracket ganadores y perdedores

    /**
     * Crea un formato de doble eliminación.
     * <p>
     * Inicializa los brackets, define la primera ronda y establece que el primer
     * turno corresponde al bracket de ganadores.
     * </p>
     */
    public DobleEliminacion() {
        this.bracketGanadores = new ArrayList<>();
        this.bracketPerdedores = new ArrayList<>();
        this.rondaActual = 1;
        this.turnoGanadores = true; //Siempre empieza el bracket de ganadores
    }

    /**
     * Genera los enfrentamientos iniciales del bracket de ganadores.
     *
     * @param participantes lista de participantes inscritos.
     * @return lista de partidos iniciales.
     */
    @Override
    public List<Partido> generarEnfrentamientos(List<Participante> participantes) { //Genera la primera ronda con todos los participantes
        bracketGanadores.addAll(participantes);
        return emparejar(bracketGanadores, rondaActual);
    }

    /**
     * Genera la siguiente ronda según el bracket que corresponda procesar.
     *
     * @param calendario calendario actual del torneo.
     * @return lista de partidos de la siguiente ronda, o una lista vacía si no se puede avanzar.
     */
    @Override
    public List<Partido> generarSiguienteRonda(Calendario calendario) {
        List<Partido> rondaAnterior = calendario.getPorRonda(rondaActual);

        if (rondaAnterior.isEmpty()) return new ArrayList<>();

        for (Partido p : rondaAnterior) { //Si algún partido no terminó, no se puede avanzar
            if (p.getEstado() != EstadoPartido.FINALIZADO) {
                return new ArrayList<>();
            }
        }

        //Separamos ganadores y perdedores de la ronda que acaba de terminar
        List<Participante> ganadoresRonda = new ArrayList<>();
        List<Participante> perdedoresRonda = new ArrayList<>();

        for (Partido p : rondaAnterior) {
            Participante ganador = p.getGanador();
            if (ganador != null) {
                ganadoresRonda.add(ganador);
                //El perdedor es el que no ganó
                Participante perdedor;
                if (p.getLocal().equals(ganador)) {
                    perdedor = p.getVisitante();
                } else {
                    perdedor = p.getLocal();
                }
                perdedoresRonda.add(perdedor);
            }
        }

        rondaActual++;
        List<Partido> siguientes = new ArrayList<>();

        if (turnoGanadores) {
            //Acaba de terminar una ronda del bracket ganadores
            bracketGanadores.clear();
            bracketGanadores.addAll(ganadoresRonda); //Ganadores siguen en su bracket
            bracketPerdedores.addAll(perdedoresRonda); //Perdedores bajan al bracket perdedores
            turnoGanadores = false; //Ahora le toca al bracket perdedores

            if (bracketGanadores.size() == 1 && bracketPerdedores.size() == 1) {
                //En la final Los últimos que quedan de cada bracket se enfrentan
                siguientes.add(new Partido(bracketGanadores.get(0), bracketPerdedores.get(0), rondaActual));
            } else if (bracketPerdedores.size() >= 2) {
                siguientes.addAll(emparejar(bracketPerdedores, rondaActual));
            }

        } else {
            //Acaba de terminar una ronda del bracket perdedores
            bracketPerdedores.clear();
            bracketPerdedores.addAll(ganadoresRonda); //Ganadores siguen en bracket perdedores
            //PerdedoresRonda queda eliminado — segunda derrota, fuera del torneo
            turnoGanadores = true; //Ahora le toca al bracket ganadores

            if (bracketGanadores.size() == 1 && bracketPerdedores.size() == 1) {
                //La final
                siguientes.add(new Partido(bracketGanadores.get(0), bracketPerdedores.get(0), rondaActual));
            } else if (bracketGanadores.size() >= 2) {
                siguientes.addAll(emparejar(bracketGanadores, rondaActual));
            }
        }

        return siguientes;
    }

    /**
     * Actualiza la clasificación sumando un punto al ganador del partido.
     *
     * @param clasificacion clasificación del torneo.
     * @param partido partido finalizado.
     */
    @Override
    public void actualizarClasificacion(Clasificacion clasificacion, Partido partido) { //1 punto por cada victoria
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
    public boolean haTerminado(Calendario calendario) { //Termina cuando todos los partidos están finalizados
        return calendario.todosFinalizados();
    }

    /**
     * Obtiene el campeón del torneo según el líder de la clasificación.
     *
     * @param calendario calendario del torneo.
     * @param clasificacion clasificación actual.
     * @return participante ganador del torneo.
     */
    @Override
    public Participante obtenerGanador(Calendario calendario, Clasificacion clasificacion) { //El campeón es quien ganó la final
        return clasificacion.getLider();
    }

    /**
     * Empareja participantes de dos en dos para generar partidos.
     *
     * @param participantes participantes que serán emparejados.
     * @param ronda ronda asignada a los partidos.
     * @return lista de partidos generados.
     */
    private List<Partido> emparejar(List<Participante> participantes, int ronda) { //Empareja de a dos
        List<Partido> partidos = new ArrayList<>();
        for (int i = 0; i < participantes.size() - 1; i += 2) {
            partidos.add(new Partido(participantes.get(i), participantes.get(i + 1), ronda));
        }
        return partidos;
    }
    @Override
    public String toString() {
        return "Doble Eliminacion:  En este formato, los participantes comienzan en el bracket de ganadores.\n" +
                " * Al perder una vez pasan al bracket de perdedores y, si pierden nuevamente, quedan eliminados del torneo.";
    }
}