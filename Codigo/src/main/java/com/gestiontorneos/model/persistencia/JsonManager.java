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

    private Gson gson;

    public JsonManager(){
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public void guardar(String archivo, Object datos){
        //se ocupa filewriter para abrir el archivo o crrearlo
        try(FileWriter writer = new FileWriter(archivo)){

            gson.toJson(datos, writer);
        //el tipo de error que ocurre mas comun
        }catch (IOException e){
            System.err.println("Error al guardar el archivo.");
            //print enfocado en errores detallados
            e.printStackTrace();
        }

        //el <T> T significa que es una lista generica pero una lista dentro de todo
        public <T> T deserializar(String archivo){

            try(FileReader reader = new FileReader(archivo)){

                return gson.fromJson(reader);

            }catch(IOexception e){
                System.err.println("Error al cargar el archivo.");
                //print enfocado en errores detallados
                e.printStackTrace();
                return null;
            }
        }
    }
}