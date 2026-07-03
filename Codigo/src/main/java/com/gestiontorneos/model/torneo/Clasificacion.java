package com.gestiontorneos.model.torneo;

import com.gestiontorneos.model.participante.Participante;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Clasificacion {

    private final Map<Participante, Integer> puntos; //Diccionario que representa la tabla de puntos

    public Clasificacion() {
        this.puntos = new LinkedHashMap<>(); //Tabla de clasificación
    }

    public void registrarParticipante(Participante participante) { //Método para registrar un participante comprobando que no haya estado anteriormente
        if (!puntos.containsKey(participante)) {
            puntos.put(participante, 0);
        }
    }

    public void sumarPuntos(Participante participante, int cantidad) { //Método para sumar puntos a un participante
        int puntosActuales = 0;
        if (puntos.containsKey(participante)) {
            puntosActuales = puntos.get(participante);
        }
        puntos.put(participante, puntosActuales + cantidad);
    }

    public int getPuntos(Participante participante) { //Getter para obtener los puntos de un participante
        if (puntos.containsKey(participante)) {
            return puntos.get(participante);
        }
        return 0;
    }

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