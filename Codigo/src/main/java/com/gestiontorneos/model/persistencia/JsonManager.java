package com.gestiontorneos.model.persistencia;

import com.google.gson.Gson;//Convertir objetos Java a JSON y viceversa
//import java.io.FileReader;//Leer un archivo Json
//import java.io.FileWriter;//Escribir un archivo Json
//import java.io.IOException;//Manejar errores de lectura y escritura
import com.google.gson.GsonBuilder;//para configurar gson
import java.lang.reflect.Type;//sirve para  cargar listas
import com.google.gson.reflect.TypeToken;//trabaja con Type para indicar el tipo
import java.io.*;//implementa todos los import relacionados con los archivos


public class JsonManager{

    Gson gson = new Gson();

    public JsonManager(){
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public void guardar(){
        
    }
}