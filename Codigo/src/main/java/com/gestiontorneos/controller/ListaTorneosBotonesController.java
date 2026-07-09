package com.gestiontorneos.controller;

import com.gestiontorneos.gui.VentanaPrincipal;
import com.gestiontorneos.gui.compartido.PanelBracket;
import com.gestiontorneos.gui.compartido.PanelResultados;
import com.gestiontorneos.model.participante.Participante;
import com.gestiontorneos.model.partido.Partido;
import com.gestiontorneos.model.torneo.Torneo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Controlador encargado de gestionar las acciones de los botones asociados
 * a la lista de torneos.
 * <p>
 * Dependiendo del comando recibido, permite mostrar el bracket de un torneo
 * o cargar su información completa en el panel de resultados.
 * </p>
 */
public class ListaTorneosBotonesController implements ActionListener {

    /**
     * Ventana principal de la aplicación, utilizada para cambiar entre paneles.
     */
    private VentanaPrincipal ventanaPrincipal;

    /**
     * Controlador que administra la información de los torneos.
     */
    private TorneoController torneoController;

    /**
     * Construye el controlador de botones para la lista de torneos.
     *
     * @param ventanaPrincipal ventana principal de la aplicación.
     * @param torneoController controlador que gestiona los torneos.
     */
    public ListaTorneosBotonesController(VentanaPrincipal ventanaPrincipal, TorneoController torneoController) {
        this.ventanaPrincipal = ventanaPrincipal;
        this.torneoController = torneoController;
    }

    /**
     * Ejecuta la acción correspondiente al botón presionado.
     * <p>
     * Si el comando inicia con {@code bracket}, se muestra el panel del bracket
     * del torneo. En caso contrario, se interpreta el comando como el nombre de
     * un torneo y se cargan sus datos en el panel de resultados.
     * </p>
     *
     * @param e evento generado por el botón presionado.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        if (actionCommand.startsWith("bracket")){
            String nombreTorneo = actionCommand.split("bracket")[1];
            Torneo torneo = torneoController.buscarTorneo(nombreTorneo);
            if (torneo == null) return;
            else{
                PanelBracket panelBracket = ventanaPrincipal.getPanelBracket();
                panelBracket.actualizarBracket(torneo);
                ventanaPrincipal.mostrarPanel("Bracket");
            }
            return;
        }
        String nombreTorneo = e.getActionCommand();
        Torneo torneo = torneoController.buscarTorneo(nombreTorneo);
        if (torneo == null) return;

        PanelResultados resultados = ventanaPrincipal.getResultados();

        resultados.actualizarNombre(torneo.getNombre());

        String descripcion = "Formato: " + torneo.getFormato().toString()
                + " | Estado: " + torneo.getEstado();
        resultados.actualizarDescripcion(descripcion);

        resultados.actualizarFechas("Fecha de inicio:   " + torneo.getFechaInicio() + "    Fecha de fin:     " + torneo.getFechaFin());

        resultados.actualizarDeporte(torneo.getDeporte().getNombre());

        resultados.actualizarTipoCompeticion(torneo.getFormato().toString());

        String ganador = "Por definir";
        if ("FINALIZADO".equals(torneo.getEstado())) {
            Participante lider = torneo.getClasificacion().getLider();
            if (lider != null) {
                ganador = lider.getNombre();
            }
        }
        resultados.actualizarGanador(ganador);

        List<Participante> participantes = torneo.getParticipantes();
        String[] nombres = new String[participantes.size()];
        for (int i = 0; i < participantes.size(); i++) {
            nombres[i] = participantes.get(i).getNombre();
        }
        String listaParticipantes = String.join(", ", nombres);
        resultados.actualizarParticipantes(listaParticipantes);

        List<Partido> partidos = torneo.getCalendario().getPartidos();
        resultados.actualizarPartidos(partidos);

        ventanaPrincipal.mostrarPanel("Resultados");
    }
}