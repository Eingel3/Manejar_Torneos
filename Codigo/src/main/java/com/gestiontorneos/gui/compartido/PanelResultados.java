package com.gestiontorneos.gui.compartido;
import com.gestiontorneos.gui.factory.PanelFactory;
import com.gestiontorneos.gui.factory.PanelLateral;
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
    private JPanel titulosPanel;
    private JPanel labelsPanel;
    private int anchoTitulosPanel;


    public PanelResultados(Torneo torneo) {
        //Recibimos un Torneo como argumento para poder mostrar sus detalles

        //Definimos las caracteriscticas del JPanel
        this.setBackground(Color.CYAN);
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        this.setPreferredSize(new Dimension(
                PanelInformacion.VENTANASINMENU.getAncho() -10,
                PanelInformacion.VENTANASINMENU.getAlto()));
        this.torneo = torneo;

        anchoTitulosPanel = 200;

        PanelFactory constructorPanel = new PanelLateral();
        titulosPanel = constructorPanel.crear(anchoTitulosPanel); //Panel Lateral con el layout tipo Box orientacion vertical
        labelsPanel = constructorPanel.crear(  //Otro panel que ocupe el resto de espacio libre
                PanelInformacion.VENTANASINMENU.getAncho() - anchoTitulosPanel - 30
        );

        iniciarAtributos();
        iniciarEstructura();
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

    public void iniciarEstructura() {

        titulosPanel.setBackground(Color.CYAN); //Le dejamos del mismo color que el resto del panel
        //Agregamos los titulos
        titulosPanel.add(Box.createRigidArea(new Dimension( //agregamos un espacio para el titulo
                PanelInformacion.MENULATERAL.getAncho(), 60)));
        titulosPanel.add(new JLabel("Descripcion del torneo:"));
        titulosPanel.add(Box.createRigidArea(new Dimension( //agregamos un espacio para la descripcion
                PanelInformacion.MENULATERAL.getAncho(), 120)));
        titulosPanel.add(new JLabel("Fechas del torneo:"));
        titulosPanel.add(Box.createRigidArea(new Dimension( //agregamos un espacio para las fechas
                PanelInformacion.MENULATERAL.getAncho(), 60)));
        titulosPanel.add(new JLabel("Deporte:"));
        titulosPanel.add(Box.createRigidArea(new Dimension( //agregamos un espacio para el deporte
                PanelInformacion.MENULATERAL.getAncho(), 90)));
        titulosPanel.add(new JLabel("Tipo Competicion:"));
        titulosPanel.add(Box.createRigidArea(new Dimension( //agregamos un espacio para el tipo de competicion
                PanelInformacion.MENULATERAL.getAncho(), 90)));
        titulosPanel.add(new JLabel("Ganador:"));
        titulosPanel.add(Box.createRigidArea(new Dimension( //agregamos un espacio para el ganador
                PanelInformacion.MENULATERAL.getAncho(), 30)));
        titulosPanel.add(new JLabel("Participantes:"));
        titulosPanel.add(Box.createRigidArea(new Dimension( //agregamos un espacio para los participantes
                PanelInformacion.MENULATERAL.getAncho(), 120)));
        this.add(titulosPanel); //Y lo agregamos

        this.add(labelsPanel);
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
        labelsPanel.add(nombreL);
        labelsPanel.add(descripcionL);
        labelsPanel.add(fechasL);
        labelsPanel.add(deporteL);
        labelsPanel.add(tipoCompeticionL);
        labelsPanel.add(ganadorL);
        labelsPanel.add(participantesL);
    }
}
