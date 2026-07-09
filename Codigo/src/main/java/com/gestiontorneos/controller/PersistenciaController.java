package com.gestiontorneos.controller;

import java.util.List;
import java.util.ArrayList;

import com.gestiontorneos.model.torneo.Torneo;
import com.gestiontorneos.model.deporte.Deporte;
import com.gestiontorneos.model.persistencia.JsonManager;

/**
 * Controlador encargado de gestionar la persistencia de datos de la aplicación.
 * <p>
 * Esta clase forma parte de un sistema de persistencia que fue considerado durante
 * el desarrollo del proyecto, pero que finalmente no se integró completamente en
 * la versión actual de la aplicación.
 * </p>
 * <p>
 * Aunque su uso quedó descartado para esta versión, se conserva como una base
 * inicial para una posible implementación futura de guardado y carga de datos,
 * especialmente mediante archivos JSON.
 * </p>
 * <p>
 * Utiliza {@link JsonManager} como punto de partida para guardar información en
 * archivos JSON. Actualmente solo contempla el guardado de la lista de torneos
 * registrados.
 * </p>
 */
public class PersistenciaController{

    /**
     * Gestor encargado de realizar las operaciones de lectura y escritura JSON.
     * <p>
     * Forma parte de la estructura inicial pensada para la persistencia de datos,
     * aunque dicha funcionalidad no se encuentra completamente integrada en la
     * aplicación actual.
     * </p>
     */
    private JsonManager jsonManager;

    /**
     * Construye un controlador de persistencia e inicializa el gestor JSON.
     * <p>
     * Este constructor se mantiene como parte de la base preparada para una futura
     * implementación completa del sistema de persistencia.
     * </p>
     */
    public PersistenciaController(){
        this.jsonManager = new JsonManager();
    }

    /**
     * Guarda la lista de torneos en un archivo JSON.
     * <p>
     * Los torneos se almacenan en el archivo {@code torneos.json}. Este método
     * representa una funcionalidad base del sistema de persistencia que finalmente
     * no fue utilizado de forma completa en la versión actual, pero que puede servir
     * como punto de partida para futuras versiones.
     * </p>
     *
     * @param torneos lista de torneos que se desea guardar.
     */
    public void guardarTorneos(List<Torneo> torneos){
        jsonManager.guardar("torneos.json", torneos);
    }
}