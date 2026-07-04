package com.gestiontorneos.model.torneo.formato;

import com.gestiontorneos.model.participante.Participante;
import com.gestiontorneos.model.partido.Calendario;
import com.gestiontorneos.model.partido.EstadoPartido;
import com.gestiontorneos.model.partido.Partido;
import com.gestiontorneos.model.torneo.Clasificacion;
import java.util.ArrayList;
import java.util.List;

public class EliminacionDirecta implements FormatoTorneo {

    private int rondaActual; //Lleva la cuenta de en qué ronda estamos

    public EliminacionDirecta() {
        this.rondaActual = 1;
    }

    @Override
    public List<Partido> generarEnfrentamientos(List<Participante> participantes) { //Genera los partidos de la primera ronda
        return emparejar(participantes, rondaActual);
    }

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

    @Override
    public void actualizarClasificacion(Clasificacion clasificacion, Partido partido) { //1 punto por victoria, para ver quién ganó más partidos
        Participante ganador = partido.getGanador();
        if (ganador != null) {
            clasificacion.sumarPuntos(ganador, 1);
        }
    }

    @Override
    public boolean haTerminado(Calendario calendario) { //Termina cuando todos los partidos de todas las rondas están finalizados
        return calendario.todosFinalizados();
    }

    @Override
    public Participante obtenerGanador(Calendario calendario, Clasificacion clasificacion) { //El campeón es quien tiene más victorias
        return clasificacion.getLider();
    }

    private List<Partido> emparejar(List<Participante> participantes, int ronda) { //Método interno que empareja de a dos
        List<Partido> partidos = new ArrayList<>();
        for (int i = 0; i < participantes.size() - 1; i += 2) { //i += 2 porque cada vuelta consume dos participantes
            partidos.add(new Partido(participantes.get(i), participantes.get(i + 1), ronda));
        }
        return partidos;
    }
}