package com.gestiontorneos.gui.organizador;

import com.gestiontorneos.gui.compartido.PanelInformacion;

import javax.swing.*;
import java.awt.*;

/**
 * Representa la vista grafica del torneo
 *
 * Este panel indica los detalles de un torneo, y ofrece la opcion de cambiar los detalles
 */

public class PanelCrearTorneo extends JPanel {
    public PanelCrearTorneo() {
        private JTextField txtNombre;
        private JTextField txtFechaInicio;
        private JTextField txtFechaFin;
        private JComboBox<String> cmbDeporte;
        private JComboBox<String> cmbFormato;
        private JTextArea txtDescripcion;
        private JButton btnCrear;
        private JButton btnCancelar;

    public PanelCrearTorneo() {
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            setBackground(Color.LIGHT_GRAY);
            setPreferredSize(new Dimension(
                    PanelInformacion.VENTANASINMENU.getAncho(),
                    PanelInformacion.VENTANASINMENU.getAlto()));
        }

    }
}