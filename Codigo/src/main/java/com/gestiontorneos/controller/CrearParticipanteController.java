package com.gestiontorneos.controller;

import com.gestiontorneos.gui.organizador.PanelCrearParticipante;
import com.gestiontorneos.model.participante.Equipo;
import com.gestiontorneos.model.participante.JugadorIndividual;

import javax.swing.*;
import java.util.ArrayList;

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
        String nombreTorneo;
        if (panel.isModoCreacionRapida()) {
            nombreTorneo = panel.getNombreTorneoAsignado();
        } else {
            nombreTorneo = panel.getNombreTorneo();
        }
        String tipo = panel.getTipoParticipante();
        boolean exito;
        if (tipo.equals("Equipo")) {
            String nombreEquipo = panel.getNombreEquipo();
            if (nombreEquipo.isEmpty()) {
                panel.mostrarMensaje("Ingrese el nombre del equipo.");
                return;
            }
            Equipo equipo = new Equipo(nombreEquipo, "", new ArrayList<>());
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
        if (exito) {
            panel.mostrarMensaje("Participante registrado exitosamente!");
            if (panel.isModoCreacionRapida()) {
                panel.incrementarContador();
                if (panel.isCompleto()) {
                    boolean calendarioOk = torneoController.generarCalendario(nombreTorneo);
                    if (calendarioOk) {
                        int total = torneoController.cantidadPartidos(nombreTorneo);
                        panel.mostrarMensaje("Se crearon todos los participantes!\n" +
                                "Calendario generado: " + total + " partidos.");
                    } else {
                        panel.mostrarMensaje("Participantes creados, pero hubo un error al generar el calendario.");
                    }
                    panel.cancelarModoCreacionRapida();
                    panel.limpiarPanel();
                    panel.elegirTipoParticipante();
                    configurarBotonSiguiente();
                } else {
                    panel.limpiarPanel();
                    panel.elegirTipoParticipante();
                    configurarBotonSiguiente();
                }
            } else {
                panel.limpiarPanel();
                panel.elegirTipoParticipante();
                configurarBotonSiguiente();
            }
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
