package com.gestiontorneos.gui.organizador;

import com.gestiontorneos.gui.compartido.PanelInformacion;
import com.gestiontorneos.gui.factory.BotonSimple;

import javax.swing.*;
import java.awt.*;

public class PanelCrearParticipante extends javax.swing.JPanel {
    private JTextField txtNombreParticipante;
    private JTextField txtContacto;
    private JComboBox<String> tipoParticipante;
    private JTextField txtNombreEquipo;
    private JTextField txtNombreTorneo;
    private JButton btnCrear;
    private JButton btnCancelar;
    private JButton btnSiguiente;
    private BotonSimple creadorBotones;

    public PanelCrearParticipante() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.LIGHT_GRAY);
        setPreferredSize(new Dimension(
                PanelInformacion.VENTANASINMENU.getAncho(),
                PanelInformacion.VENTANASINMENU.getAlto()));
        creadorBotones = new BotonSimple();
    }




    public JButton getBotonCrear() {
                return btnCrear;
    }

    public JButton getBotonCancelar() {
                return btnCancelar;
    }

    public String getNombreTorneo() {
                return txtNombreTorneo.getText().trim();
    }

    public String getContacto() {
                return txtContacto.getText().trim();
    }

    public String getNombreParticipante() {
                return (String) txtNombreParticipante.getText().trim();
    }

    public String getTipoParticipante() {
                return (String) tipoParticipante.getSelectedItem();
    }

    public String getNombreEquipo() {
                return txtNombreEquipo.getText().trim();
    }

    public void limpiarFormulario() {
                txtNombreParticipante.setText("");
                txtContacto.setText("");
                tipoParticipante.setSelectedIndex(0);
                txtNombreEquipo.setText("");
    }

    public void mostrarMensaje(String mensaje) {
                JOptionPane.showMessageDialog(this, mensaje);
    }

    public boolean confirmar(String pregunta){
                int respuesta = JOptionPane.showConfirmDialog(this,pregunta,
                        "Advertencia", JOptionPane.YES_NO_OPTION);
                return  respuesta == JOptionPane.YES_OPTION;
    }
}
