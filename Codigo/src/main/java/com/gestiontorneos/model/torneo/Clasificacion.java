package com.gestiontorneos.model.torneo;

import com.gestiontorneos.model.participante.Participante;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Representa la tabla de clasificación de un torneo.
 * <p>
 * Almacena los puntos obtenidos por cada participante y permite consultar la
 * tabla ordenada o el participante líder.
 * </p>
 *
 * @see Participante
 */
public class Clasificacion {

    private final Map<Participante, Integer> puntos; //Diccionario que representa la tabla de puntos

    /**
     * Crea una clasificación vacía.
     * <p>
     * Utiliza un {@link LinkedHashMap} para mantener el orden de inserción de
     * los participantes.
     * </p>
     */
    public Clasificacion() {
        this.puntos = new LinkedHashMap<>(); //Tabla de clasificación
    }

    /**
     * Registra un participante en la clasificación con cero puntos.
     * <p>
     * Si el participante ya existe en la tabla, no se vuelve a registrar.
     * </p>
     *
     * @param participante participante que se desea registrar.
     */
    public void registrarParticipante(Participante participante) { //Método para registrar un participante comprobando que no haya estado anteriormente
        if (!puntos.containsKey(participante)) {
            puntos.put(participante, 0);
        }
    }

    /**
     * Suma puntos a un participante.
     * <p>
     * Si el participante no estaba registrado previamente, se agrega a la tabla
     * con la cantidad indicada.
     * </p>
     *
     * @param participante participante al que se le sumarán puntos.
     * @param cantidad cantidad de puntos a sumar.
     */
    public void sumarPuntos(Participante participante, int cantidad) { //Método para sumar puntos a un participante
        int puntosActuales = 0;
        if (puntos.containsKey(participante)) {
            puntosActuales = puntos.get(participante);
        }
        puntos.put(participante, puntosActuales + cantidad);
    }

    /**
     * Obtiene los puntos actuales de un participante.
     *
     * @param participante participante consultado.
     * @return puntos del participante, o {@code 0} si no está registrado.
     */
    public int getPuntos(Participante participante) { //Getter para obtener los puntos de un participante
        if (puntos.containsKey(participante)) {
            return puntos.get(participante);
        }
        return 0;
    }

    /**
     * Obtiene la tabla de participantes ordenada de mayor a menor puntaje.
     *
     * @return lista de participantes ordenada por puntos descendentes.
     */
    public List<Participante> getTablaOrdenada() {
        List<Participante> lista = new ArrayList<>(puntos.keySet());  //Sacamos todos los participantes del mapa en una lista

        for (int i = 0; i < lista.size() - 1; i++) { //Ordenamiento simple de menor a mayor puntaje
            for (int j = 0; j < lista.size() - 1 - i; j++) {
                int puntosJ = puntos.get(lista.get(j));
                int puntosJSig = puntos.get(lista.get(j + 1));
                if (puntosJ < puntosJSig) {
                    //Intercambio
                    Participante temp = lista.get(j);
                    lista.set(j, lista.get(j + 1));
                    lista.set(j + 1, temp);
                }
            }
        }
        return lista;
    }

    /**
     * Obtiene el participante con mayor puntaje.
     *
     * @return participante líder, o {@code null} si la clasificación está vacía.
     */
    public Participante getLider() { //Obtener al primero en la tabla
        if (puntos.isEmpty()) {
            return null;
        }

        Participante lider = null;
        int maxPuntos = -1; //Parte en -1 para garantizar que el primer participante sea el líder inicial

        for (Participante p : puntos.keySet()) { //For simple para comparar entre los puntos de todos los participantes
            if (puntos.get(p) > maxPuntos) {
                maxPuntos = puntos.get(p);
                lider = p;
            }
        }
        return lider;
    }
}