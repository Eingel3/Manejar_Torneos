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
    private JTextField txtNombreParticipanteLocal;
    private JTextField txtNombreParticipanteVisitante;
    private JComboBox<String> tipoParticipante; //Si es local o visitante
    private JTextField txtNombreTorneo;
    private JButton btnCrear;
    private JButton btnCancelar;
    private JButton btnSiguiente;
    private BotonSimple creadorBotones;
    private JComboBox<String> estadoPartido;
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
    public void crearPartido() {
        JLabel lblTitulo = new JLabel("Crear Nuevo Partido");
        lblTitulo.setFont(new Font("Dialog", Font.BOLD, 24));
        lblTitulo.setForeground(Color.BLACK);
        lblTitulo.setAlignmentX(CENTER_ALIGNMENT);

        this.add(new JLabel("Indique el torneo al que se quiere agregar el partido:"));
        this.add(Box.createRigidArea(new Dimension(0, 30)));
        this.add(txtNombreTorneo = new JTextField());
        this.add(Box.createRigidArea(new Dimension(0, 60)));

        this.add(new JLabel("Nombre del participante o equipo marcado como 'Local':"));
        this.add(txtNombreParticipanteLocal = new JTextField());
        this.add(Box.createRigidArea(new Dimension(0, 30)));

        this.add(new JLabel("Nombre del participante o equipo marcado como 'Visitante':"));
        this.add(txtNombreParticipanteVisitante = new JTextField());
        this.add(Box.createRigidArea(new Dimension(0, 60)));



        this.add(new JLabel("Indique el estado del partido:"));
        this.add(Box.createRigidArea(new Dimension(0, 30)));
        estadoPartido = new JComboBox<>(new String[]{"Pendiente", "En curso", "Finalizado", "Cancelado"});
        this.add(estadoPartido);
        this.add(Box.createRigidArea(new Dimension(0, 60)));





        this.add(btnCrear = creadorBotones.crear("Crear"));
        this.add(Box.createRigidArea(new Dimension(0, 30)));

        this.add(btnCancelar = creadorBotones.crear("Cancelar"));
        this.add(Box.createRigidArea(new Dimension(0, 30)));
    }


    public void elegirTorneo() {
        this.add(new JLabel("Nombre del torneo en que participa:"));


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

    public String getNombreParticipanteLocal() {
        return (String) txtNombreParticipanteLocal.getText().trim();
    }

        public String getNombreParticipanteVisitante() {
        return (String) txtNombreParticipanteVisitante.getText().trim();
    }

    public String getTipoParticipante() {
        return (String) tipoParticipante.getSelectedItem();
    }

    public String getEstadoPartido() {
        return (String) estadoPartido.getSelectedItem();
    }


    public void limpiarFormulario() {
        txtNombreParticipanteLocal.setText("");
        txtNombreParticipanteVisitante.setText("");
        tipoParticipante.setSelectedIndex(0);
        txtNombreTorneo.setText("");
        estadoPartido.setSelectedIndex(0);
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
}