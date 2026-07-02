package com.gestiontorneos.gui.compartido;
import com.gestiontorneos.model.torneo.Torneo;

import javax.swing.*;
import java.awt.*;

/**
 * Representa la vista grafica de los resultados
 *
 * Este panel muestra los resultados de un torneo, estadísticas generales y etc
 */

public class PanelResultados extends JPanel {
    private String nombre;
    private String descripcion;
    private Torneo torneo;
    private String fechas;
    private String deporte;
    private String tipoCompeticion;
    private String ganador;
    private String participantes;


    public PanelResultados(Torneo torneo) {
        //Recibimos un Torneo como argumento para poder mostrar sus detalles

        //Definimos las caracteriscticas del JPanel
        this.setBackground(Color.CYAN);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        this.setPreferredSize(new Dimension(
                PanelInformacion.VENTANASINMENU.getAncho() -10,
                PanelInformacion.VENTANASINMENU.getAlto()));
        this.torneo = torneo;

        iniciarAtributos();
        iniciarLabels();
    }
    private void iniciarAtributos() {
        this.nombre = this.torneo.getNombre();
        this.descripcion = "descripcion";
        this.fechas = "fechas";
        this.deporte = "deporte";
        this.tipoCompeticion = "tipoCompeticion";
        this.ganador = "ganador";
        this.participantes = "participante";
    }

    public void iniciarLabels() {
        //Primero creamos los lables
        JLabel nombreL = new JLabel(this.nombre);
        JLabel descripcionL = new JLabel(this.descripcion);
        JLabel fechasL = new JLabel(this.fechas);
        JLabel deporteL = new JLabel(this.deporte);
        JLabel tipoCompeticionL= new JLabel(this.tipoCompeticion);
        JLabel ganadorL = new JLabel(this.ganador);
        JLabel participantesL = new JLabel(this.participantes);

        //Modificamos la alineación de los textos:
        nombreL.setHorizontalAlignment(JLabel.CENTER);
        descripcionL.setHorizontalAlignment(JLabel.LEFT);
        fechasL.setHorizontalAlignment(JLabel.LEFT);
        deporteL.setHorizontalAlignment(JLabel.LEFT);
        tipoCompeticionL.setHorizontalAlignment(JLabel.LEFT);
        ganadorL.setHorizontalAlignment(JLabel.LEFT);
        participantesL.setHorizontalAlignment(JLabel.LEFT);
        //Modificamos la alinceación de los JLabels:
        nombreL.setAlignmentX(Component.CENTER_ALIGNMENT);
        descripcionL.setAlignmentX(Component.LEFT_ALIGNMENT);
        fechasL.setAlignmentX(Component.LEFT_ALIGNMENT);
        deporteL.setAlignmentX(Component.LEFT_ALIGNMENT);
        tipoCompeticionL.setAlignmentX(Component.LEFT_ALIGNMENT);
        ganadorL.setAlignmentX(Component.LEFT_ALIGNMENT);
        participantesL.setAlignmentX(Component.LEFT_ALIGNMENT);

        //Y añadimos los JLAbels
        this.add(nombreL);
        this.add(descripcionL);
        this.add(fechasL);
        this.add(deporteL);
        this.add(tipoCompeticionL);
        this.add(ganadorL);
        this.add(participantesL);
    }
}
