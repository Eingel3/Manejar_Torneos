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
        this.setBackground(Color.CYAN);
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        this.setPreferredSize(new Dimension(
                PanelInformacion.VENTANASINMENU.getAncho(),
                PanelInformacion.VENTANASINMENU.getAlto()));
        this.torneo = torneo;
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
}
