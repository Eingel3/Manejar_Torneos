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


    public JButton elegirTipoParticipante() {
                JLabel lblTitulo = new JLabel("Crear Nuevo Participante");
                lblTitulo.setFont(new Font("Dialog", Font.BOLD, 24));
                lblTitulo.setForeground(Color.BLACK);
                lblTitulo.setAlignmentX(CENTER_ALIGNMENT);

                this.add(new JLabel("Indique si desea agregar un equipo o un jugador individual:"));
                tipoParticipante = new JComboBox<>(new String[]{"Equipo", "Jugador Individual"});
                this.add(tipoParticipante);

                btnSiguiente = creadorBotones.crear("Siguiente");
                this.add(btnSiguiente);
                return btnSiguiente;
    }
    public void elegirNombreEquipo() {

        this.add(new JLabel("Nombre del equipo:"));
        this.add(Box.createRigidArea(new Dimension(0, 30)));
        this.add(txtNombreEquipo = new JTextField());
        this.add(Box.createRigidArea(new Dimension(0, 60)));


        this.add(btnCrear = creadorBotones.crear("Crear"));
        this.add(Box.createRigidArea(new Dimension(0, 30)));

        this.add(btnCancelar = creadorBotones.crear("Cancelar"));
        this.add(Box.createRigidArea(new Dimension(0, 30)));

    }

    public void elegirTorneo() {
        this.add(new JLabel("Nombre del torneo en que participa:"));
        this.add(Box.createRigidArea(new Dimension(0, 30)));
        this.add(txtNombreTorneo = new JTextField());
        this.add(Box.createRigidArea(new Dimension(0, 60)));

        this.add(btnCrear = creadorBotones.crear("Crear"));
        this.add(Box.createRigidArea(new Dimension(0, 30)));

        this.add(btnCancelar = creadorBotones.crear("Cancelar"));
        this.add(Box.createRigidArea(new Dimension(0, 30)));
    }

    public void agregarParticipante() {
        this.add(new JLabel("Nombre del participante:"));
        this.add(txtNombreParticipante = new JTextField());
        this.add(Box.createRigidArea(new Dimension(0, 30)));

        this.add(new JLabel("Contacto del participante:"));
        this.add(txtContacto = new JTextField());
        this.add(Box.createRigidArea(new Dimension(0, 30)));

        this.add(btnCrear = creadorBotones.crear("Crear"));
        this.add(Box.createRigidArea(new Dimension(0, 30)));

        this.add(btnCancelar = creadorBotones.crear("Cancelar"));
        this.add(Box.createRigidArea(new Dimension(0, 30)));
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

    public void limpiarFormulario() {;
                if (tipoParticipante != null) {
                    tipoParticipante.setSelectedIndex(0);
                }
                if (txtNombreEquipo != null) {
                    txtNombreEquipo.setText("");
                }
                if (txtContacto != null) {
                    txtContacto.setText("");
                }
                if (tipoParticipante != null) {
                    tipoParticipante.setSelectedIndex(0);
                }
                if (txtNombreEquipo != null) {
                    txtNombreEquipo.setText("");
                }
                if (txtContacto != null) {
                    txtContacto.setText("");
                }
                if (txtNombreParticipante != null) {
                    txtNombreParticipante.setText("");
                }
                this.revalidate();
                this.repaint();
    }

    public void limpiarPanel() {
        this.removeAll();
        this.revalidate();
        this.repaint();
    }

    public void mostrarMensaje(String mensaje) {
                JOptionPane.showMessageDialog(this, mensaje);
    }

    public boolean confirmar(String pregunta){
                int respuesta = JOptionPane.showConfirmDialog(this,pregunta,
                        "Advertencia", JOptionPane.YES_NO_OPTION);
                return  respuesta == JOptionPane.YES_OPTION;
    }

    public JTextField getTxtNombreParticipante() {
        return txtNombreParticipante;
    }
    public JTextField getTxtContacto() {
        return txtContacto;
    }
}
