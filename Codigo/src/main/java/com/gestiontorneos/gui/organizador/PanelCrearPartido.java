package com.gestiontorneos.gui.organizador;

import com.gestiontorneos.gui.compartido.PanelInformacion;
import com.gestiontorneos.gui.factory.BotonSimple;

import javax.swing.*;
import java.awt.*;

/**
 * Panel gráfico encargado de mostrar y gestionar partidos.
 * <p>
 * Esta vista permite crear los partidos de un torneo y está pensada para
 * definir información relevante como fecha, participantes enfrentados y estado
 * del partido.
 * </p>
 *
 * @see JPanel
 */
public class PanelCrearPartido extends JPanel {
    private JTextField txtNombreParticipante;
    private JComboBox<String> tipoParticipante; //Si es local o visitante
    private JTextField txtNombreTorneo;
    private JButton btnCrear;
    private JButton btnCancelar;
    private JButton btnSiguiente;
    private BotonSimple creadorBotones;
    /**
     * Crea un nuevo panel de partidos.

     */
    public PanelCrearPartido() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.LIGHT_GRAY);
        setPreferredSize(new Dimension(
                PanelInformacion.VENTANASINMENU.getAncho(),
                PanelInformacion.VENTANASINMENU.getAlto()));
        creadorBotones = new BotonSimple();
    }
}