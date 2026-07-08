package com.gestiontorneos.controller;

import com.gestiontorneos.gui.organizador.PanelCrearParticipante;
import com.gestiontorneos.model.participante.Equipo;
import com.gestiontorneos.model.participante.JugadorIndividual;
import com.gestiontorneos.model.excepciones.DatosInvalidosException;

import java.util.List;

public class CrearParticipanteController {

    private PanelCrearParticipante panel;
    private TorneoController torneoController;

    public CrearParticipanteController(PanelCrearParticipante panel, TorneoController torneoController) {
        this.panel = panel;
        this.torneoController = torneoController;
        manejarEventos();
    }

    private void manejarEventos() {
        panel.getBotonCrear().addActionListener(e -> crearParticipante());
        panel.getBotonCancelar().addActionListener(e -> cancelar());
    }

    private void crearParticipante() {
        String nombreTorneo = panel.getNombreTorneo();
        if (nombreTorneo.isEmpty()) {
            panel.mostrarMensaje("Ingrese el nombre del torneo.");
            return;
        }

        boolean exito;
        try {
            if (panel.esEquipo()) {
                String nombreEquipo = panel.getNombreEquipo();
                if (nombreEquipo.isEmpty()) {
                    panel.mostrarMensaje("Ingrese el nombre del equipo.");
                    return;
                }
                List<String> integrantes = panel.getIntegrantes();
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
            panel.limpiarFormulario();
        } else {
            panel.mostrarMensaje("Error al registrar participante.");
        }
    }

    public void cancelar() {
        panel.limpiarFormulario();
    }
}
