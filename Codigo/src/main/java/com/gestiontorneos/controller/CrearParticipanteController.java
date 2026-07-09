package com.gestiontorneos.controller;

import com.gestiontorneos.gui.organizador.PanelCrearParticipante;
import com.gestiontorneos.model.participante.Equipo;
import com.gestiontorneos.model.participante.JugadorIndividual;
import com.gestiontorneos.model.excepciones.DatosInvalidosException;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Controlador encargado de gestionar la creación de participantes dentro de un torneo.
 * <p>
 * Esta clase conecta la vista {@link PanelCrearParticipante} con la lógica de torneos
 * proporcionada por {@link TorneoController}. Permite registrar participantes individuales
 * o equipos, validar los datos ingresados por el usuario y, si corresponde, generar
 * automáticamente el calendario del torneo.
 * </p>
 */
public class CrearParticipanteController {

    /**
     * Panel gráfico desde el cual el usuario introduce la información del participante.
     */
    private PanelCrearParticipante panel;

    /**
     * Controlador principal encargado de gestionar los torneos y sus participantes.
     */
    private TorneoController torneoController;

    /**
     * Construye un controlador para crear participantes.
     * <p>
     * Al inicializarse, registra los eventos necesarios en los botones del panel.
     * </p>
     *
     * @param panel panel de creación de participantes.
     * @param torneoController controlador que administra los torneos.
     */
    public CrearParticipanteController(PanelCrearParticipante panel, TorneoController torneoController){
        this.panel = panel;
        this.torneoController = torneoController;
        manejarEventos();
    }

    /**
     * Configura los eventos iniciales del panel.
     * <p>
     * Actualmente se encarga de configurar el botón "Siguiente", que permite avanzar
     * en el formulario según el tipo de participante seleccionado.
     * </p>
     */
    private void manejarEventos() {
        configurarBotonSiguiente();
    }

    /**
     * Configura la acción del botón "Siguiente".
     * <p>
     * Dependiendo de si el usuario seleccionó un equipo o un participante individual,
     * prepara el formulario correspondiente. También registra los eventos del botón
     * para crear el participante y del botón para cancelar la operación.
     * </p>
     */
    public void configurarBotonSiguiente() {
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

    /**
     * Crea y registra un participante en el torneo seleccionado.
     * <p>
     * El método valida los datos ingresados por el usuario, distingue entre la creación
     * de un equipo y la creación de un jugador individual, y posteriormente delega el
     * registro al {@link TorneoController}.
     * </p>
     * <p>
     * Si el panel se encuentra en modo de creación rápida, el método también controla
     * el contador de participantes creados y genera el calendario automáticamente cuando
     * se completa la cantidad requerida.
     * </p>
     */
    private void crearParticipante() {
        String nombreTorneo;
        if (panel.isModoCreacionRapida()) {
            nombreTorneo = panel.getNombreTorneoAsignado();
        } else {
            nombreTorneo = panel.getNombreTorneo();
        }
        String tipo = panel.getTipoParticipante();
        if (nombreTorneo == null || nombreTorneo.isEmpty()) {
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
                if (panel.confirmar("¿Desea generar el calendario del torneo ahora?")) {
                    boolean calendarioOk = torneoController.generarCalendario(nombreTorneo);
                    if (calendarioOk) {
                        int total = torneoController.cantidadPartidos(nombreTorneo);
                        panel.mostrarMensaje("Calendario generado: " + total + " partidos.");
                    } else {
                        panel.mostrarMensaje("No se pudo generar el calendario (se requieren al menos 2 participantes, o ya fue generado).");
                    }
                }
                panel.limpiarPanel();
                panel.elegirTipoParticipante();
                configurarBotonSiguiente();
            }
        } else {
            panel.mostrarMensaje("Error al registrar participante.");
        }
    }

    /**
     * Cancela el proceso actual de creación de participante.
     * <p>
     * Limpia el panel, vuelve a mostrar la selección del tipo de participante y
     * configura nuevamente el botón "Siguiente".
     * </p>
     */
    public void cancelar(){
        panel.limpiarPanel();
        panel.elegirTipoParticipante();
        configurarBotonSiguiente();
    }
}