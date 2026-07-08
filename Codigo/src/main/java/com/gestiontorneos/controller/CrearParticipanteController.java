package com.gestiontorneos.controller;

import com.gestiontorneos.gui.organizador.PanelCrearParticipante;
import com.gestiontorneos.model.participante.Equipo;
import com.gestiontorneos.model.participante.JugadorIndividual;
import com.gestiontorneos.model.excepciones.DatosInvalidosException;

import javax.swing.*;
import java.util.List;

public class CrearParticipanteController {

    private PanelCrearParticipante panel;
    private TorneoController torneoController;


    public CrearParticipanteController(PanelCrearParticipante panel, TorneoController torneoController){
        this.panel = panel;
        this.torneoController = torneoController;
        manejarEventos();
    }


    private void manejarEventos() {
        configurarBotonSiguiente();
    }
    private void configurarBotonSiguiente() {
        panel.getBotonSiguiente().addActionListener(e -> {
            String tipo = panel.getTipoParticipante();
            panel.limpiarPanel();
            if (tipo.equals("Equipo")) {
                panel.elegirNombreEquipo();
            } else {
                panel.agregarParticipante();
            }
            panel.elegirTorneo();
            panel.getBotonCrear().addActionListener(ev -> crearParticipante());
            panel.getBotonCancelar().addActionListener(ev -> cancelar());
        });
    }


    private void crearParticipante() {
        String nombreTorneo = panel.getNombreTorneo();
        String tipo = panel.getTipoParticipante();
        if (nombreTorneo.isEmpty()) {
            panel.mostrarMensaje("Ingrese el nombre del torneo.");
            return;
        }
        boolean exito;
        try {
            if (tipo.equals("Equipo")) {
                String nombreEquipo = panel.getNombreEquipo();
                if (nombreEquipo.isEmpty()) {
                    panel.mostrarMensaje("Ingrese el nombre del equipo.");
                    return;
                }
                List<String> integrantes = panel.getIntegrantes();
                if (integrantes.isEmpty()) {
                    panel.mostrarMensaje("El equipo debe tener al menos un integrante.");
                    return;
                }
                Equipo equipo = new Equipo(nombreEquipo, "", integrantes);
                exito = torneoController.registrarParticipante(nombreTorneo, equipo);
            } else {
                String nombre = panel.getNombreParticipante();
                String contacto = panel.getContacto();
                if (nombre.isEmpty()) {
                    panel.mostrarMensaje("Ingrese el nombre del participante.");
                    return;
                }
                JugadorIndividual jugador = new JugadorIndividual(nombre, contacto);
                exito = torneoController.registrarParticipante(nombreTorneo, jugador);
            }
        } catch (DatosInvalidosException ex) {
            panel.mostrarMensaje(ex.getMessage());
            return;
        }
        if (exito) {
            panel.mostrarMensaje("Participante registrado exitosamente!");
            panel.limpiarPanel();
            panel.elegirTipoParticipante();
            configurarBotonSiguiente();
        } else {
            panel.mostrarMensaje("Error al registrar participante.");
        }
    }


    public void cancelar(){
        panel.limpiarPanel();
        panel.elegirTipoParticipante();
        configurarBotonSiguiente();
    }


}
