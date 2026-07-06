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
        private JTextField txtNombre;
        private JTextField txtFechaInicio;
        private JTextField txtFechaFin;
        private JTextField txtDeporte;
        private JTextField txtFormato;
        private JTextField txtDescripcion;
        private JButton btnCrear;
        private JButton btnCancelar;

    public PanelCrearTorneo() {
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            setBackground(Color.LIGHT_GRAY);
            setPreferredSize(new Dimension(
                    PanelInformacion.VENTANASINMENU.getAncho(),
                    PanelInformacion.VENTANASINMENU.getAlto()));
            agregarComponentes();
        }



    private void agregarComponentes() {
            JLabel lblTitulo = new JLabel("Crear Nuevo Torneo");
            lblTitulo.setFont(new Font("Dialog", Font.BOLD, 24));
            lblTitulo.setForeground(Color.BLACK);
            lblTitulo.setAlignmentX(CENTER_ALIGNMENT);

            this.add(new JLabel("Nombre del torneo:"));
            this.add(txtNombre = new JTextField());
            this.add(Box.createRigidArea(new Dimension(0, 30)));

            this.add(new JLabel("Fecha inicio:"));
            this.add(txtFechaInicio = new JTextField());
            this.add(Box.createRigidArea(new Dimension(0, 30)));

            this.add(new JLabel("Fecha fin:"));
            this.add(txtFechaFin = new JTextField());
            this.add(Box.createRigidArea(new Dimension(0, 30)));

            this.add(new JLabel("Deporte:"));
            this.add(txtDeporte = new JTextField());
            this.add(Box.createRigidArea(new Dimension(0, 30)));

            this.add(new JLabel("Formato:"));
            this.add(txtFormato = new JTextField());
            this.add(Box.createRigidArea(new Dimension(0, 30)));

            this.add(new JLabel("Descripcion:"));
            this.add(txtDescripcion = new JTextField());
            this.add(Box.createRigidArea(new Dimension(0, 30)));

            this.add(btnCrear = new JButton("Crear"));
            this.add(Box.createRigidArea(new Dimension(0, 30)));

            this.add(btnCancelar = new JButton("Cancelar"));
            this.add(Box.createRigidArea(new Dimension(0, 30)));

    }



    public JButton getBotonCrear() {
        return btnCrear;
    }

    public JButton getBotonCancelar() {
        return btnCancelar;
    }

    public String getNombre() {
        return txtNombre.getText().trim();
    }

    public String getFechaInicio() {
        return txtFechaInicio.getText().trim();
    }

    public String getFechaFin() {
        return txtFechaFin.getText().trim();
    }

    public String getDeporte() {
        return (String) txtDeporte.getText().trim();
    }

    public String getFormato() {
        return (String) txtFormato.getText().trim();
    }

    public String getDescripcion() {
        return txtDescripcion.getText().trim();
    }

}