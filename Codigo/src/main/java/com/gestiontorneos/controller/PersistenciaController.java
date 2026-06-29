package com.gestiontorneos.controller;

import java.util.List;
import java.util.ArrayList;
import com.gestiontorneos.model.torneo.Torneo;
import com.gestiontorneos.model.deporte.Deporte;
import com.gestiontorneos.model.persistencia.JsonManager;


public class PersistenciaController{

    private JsonManager jsonManager;

    public  PersistenciaController(){
        this.jsonManager = new JsonManager();
    }

    
}