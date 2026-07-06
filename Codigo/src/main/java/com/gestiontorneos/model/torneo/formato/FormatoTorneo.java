package com.gestiontorneos.model.torneo.formato;

import com.gestiontorneos.model.participante.Participante;
import com.gestiontorneos.model.partido.Partido;
import com.gestiontorneos.model.partido.Calendario;
import com.gestiontorneos.model.torneo.Clasificacion;

import java.util.ArrayList;
import java.util.List;

/**
 * Define el contrato común para los formatos de torneo.
 * <p>
 * Cada formato es responsable de generar enfrentamientos, actualizar la
 * clasificación, determinar si el torneo terminó y obtener el ganador.
 * </p>
 * <p>
 * Esta interfaz permite aplicar el patrón Strategy, dejando que cada torneo use
 * una estrategia distinta para organizar sus partidos.
 * </p>
 *
 * @see Partido
 * @see Participante
 * @see Calendario
 * @see Clasificacion
 */
public interface FormatoTorneo {

    /**
     * Genera los enfrentamientos iniciales del torneo.
     *
     * @param participantes lista de participantes inscritos.
     * @return lista de partidos generados.
     */
    List<Partido> generarEnfrentamientos(List<Participante> participantes); //Genera todos los partidos iniciales según los participantes inscritos

    /**
     * Actualiza la clasificación a partir de un partido finalizado.
     *
     * @param clasificacion clasificación que será actualizada.
     * @param partido partido usado para calcular puntos.
     */
    void actualizarClasificacion(Clasificacion clasificacion, Partido partido);     //Recalcula la tabla cuando un partido termina

    /**
     * Indica si el torneo ha terminado según el calendario.
     *
     * @param calendario calendario del torneo.
     * @return {@code true} si el torneo terminó; {@code false} en caso contrario.
     */
    boolean haTerminado(Calendario calendario); //Verifica si el torneo ha terminado

    /**
     * Obtiene el ganador del torneo.
     *
     * @param calendario calendario del torneo.
     * @param clasificacion clasificación actual.
     * @return participante ganador del torneo.
     */
    Participante obtenerGanador(Calendario calendario, Clasificacion clasificacion); //Devuelve el ganador del torneo

    /**
     * Genera la siguiente ronda del torneo.
     * <p>
     * Por defecto retorna una lista vacía, ya que no todos los formatos requieren
     * generar rondas posteriores.
     * </p>
     *
     * @param calendario calendario actual del torneo.
     * @return lista de partidos de la siguiente ronda.
     */
    default List<Partido> generarSiguienteRonda(Calendario calendario) { //Necesario para generar nuevos partidos en torneos de eliminación directa
        return new ArrayList<>();
    }
}