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

public class ListaTorneosBotonesController implements ActionListener {
    private VentanaPrincipal ventanaPrincipal;
    private TorneoController torneoController;

    public ListaTorneosBotonesController(VentanaPrincipal ventanaPrincipal, TorneoController torneoController) {
        this.ventanaPrincipal = ventanaPrincipal;
        this.torneoController = torneoController;
    }

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

        //Nombre
        resultados.actualizarNombre(torneo.getNombre());

        //Descripcion
        String descripcion = "Formato: " + torneo.getFormato().toString()
                + " | Estado: " + torneo.getEstado();
        resultados.actualizarDescripcion(descripcion);

        //Fechas
        resultados.actualizarFechas("Fecha de inicio:   " + torneo.getFechaInicio() + "    Fecha de fin:     " + torneo.getFechaFin());

        //Deporte
        resultados.actualizarDeporte(torneo.getDeporte().getNombre());

        //Tipo de competicion
        resultados.actualizarTipoCompeticion(torneo.getFormato().toString());

        //Ganador
        String ganador = "Por definir";
        if ("FINALIZADO".equals(torneo.getEstado())) {
            Participante lider = torneo.getClasificacion().getLider();
            if (lider != null) {
                ganador = lider.getNombre();
            }
        }
        resultados.actualizarGanador(ganador);


        List<Participante> participantes = torneo.getParticipantes();
        //crear una lista de solo los nombres
        String[] nombres = new String[participantes.size()];
        for (int i = 0; i < participantes.size(); i++) {
            nombres[i] = participantes.get(i).getNombre();
        }
        String listaParticipantes = String.join(", ", nombres);
        resultados.actualizarParticipantes(listaParticipantes);

        //partidos
        List<Partido> partidos = torneo.getCalendario().getPartidos();
        resultados.actualizarPartidos(partidos);

        //direccionar al panel de resultados
        ventanaPrincipal.mostrarPanel("Resultados");
    }
}
