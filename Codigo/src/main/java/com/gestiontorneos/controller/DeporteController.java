package com.gestiontorneos.controller;

import java.util.List;
import java.util.ArrayList;

import com.gestiontorneos.model.deporte.Deporte;
import com.gestiontorneos.model.deporte.TipoParticipacion;

/**
 * Controlador encargado de gestionar los deportes disponibles en la aplicación.
 * <p>
 * Permite crear, buscar, eliminar y listar deportes. Cada deporte está asociado
 * a un tipo de participación, definido por {@link TipoParticipacion}.
 * </p>
 */
public class DeporteController {

    /**
     * Lista de deportes registrados en memoria.
     */
    private List<Deporte> deportes;

    /**
     * Construye un controlador de deportes con una lista vacía.
     */
    public DeporteController(){
        this.deportes = new ArrayList<>();
    }

    /**
     * Busca un deporte por su nombre.
     *
     * @param nombre nombre del deporte que se desea buscar.
     * @return el deporte encontrado, o {@code null} si no existe un deporte con ese nombre.
     */
    public Deporte buscarDeporte(String nombre){
        for (Deporte deporte : deportes){
            if ( deporte.getNombre().equals(nombre)){
                return deporte;
            }
        }
        return null;
    }

    /**
     * Crea un nuevo deporte y lo agrega a la lista de deportes registrados.
     *
     * @param nombre nombre del deporte.
     * @param tipoParticipacion tipo de participación del deporte.
     * @return el deporte creado.
     */
    public Deporte crearDeporte(String nombre, TipoParticipacion tipoParticipacion){
        Deporte deporte = new Deporte(nombre, tipoParticipacion);
        deportes.add(deporte);
        return deporte;
    }

    /**
     * Elimina un deporte de la lista usando su nombre.
     *
     * @param nombre nombre del deporte que se desea eliminar.
     * @return {@code true} si el deporte fue encontrado y eliminado;
     *         {@code false} si no existe un deporte con ese nombre.
     */
    public boolean eliminarDeporte(String nombre){
        Deporte deporte = buscarDeporte(nombre);
        if (deporte != null){
            deportes.remove(deporte);
            return true;
        }
        return false;
    }

    /**
     * Obtiene la lista de deportes registrados.
     *
     * @return lista de deportes disponibles.
     */
    public List<Deporte> listaDeportes(){
        return deportes;
    }
}