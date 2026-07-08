package com.gestiontorneos.gui.organizador;

import com.gestiontorneos.controller.TorneoController;
import com.gestiontorneos.gui.compartido.PanelInformacion;
import com.gestiontorneos.gui.factory.BotonSimple;
import com.gestiontorneos.model.partido.Partido;
import com.gestiontorneos.model.torneo.Torneo;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PanelRegistrarResultado extends JPanel {

    private JComboBox<String> comboTorneos;
    private JComboBox<String> comboPartidos;
    private JTextField txtPuntosLocal;
    private JTextField txtPuntosVisitante;
    private JButton btnRegistrar;
    private JButton btnCancelar;
    private BotonSimple creadorBotones;
    private TorneoController torneoController;

    /**
     * Crea el panel de registro de resultados.
     */
    public PanelRegistrarResultado() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.LIGHT_GRAY);
        setPreferredSize(new Dimension(
                PanelInformacion.VENTANASINMENU.getAncho(),
                PanelInformacion.VENTANASINMENU.getAlto()));
        creadorBotones = new BotonSimple();
        crearFormulario();
    }



    private void crearFormulario() {
        JLabel lblTitulo = new JLabel("Registrar Resultado de Partido");
        lblTitulo.setFont(new Font("Dialog", Font.BOLD, 24));
        lblTitulo.setForeground(Color.BLACK);
        lblTitulo.setAlignmentX(CENTER_ALIGNMENT);
        this.add(lblTitulo);
        this.add(Box.createRigidArea(new Dimension(0, 60)));

        this.add(new JLabel("Seleccione el torneo:"));
        this.add(Box.createRigidArea(new Dimension(0, 30)));
        comboTorneos = new JComboBox<>();
        comboTorneos.addActionListener(e -> cargarPartidosPendientes());
        this.add(comboTorneos);
        this.add(Box.createRigidArea(new Dimension(0, 60)));

        this.add(new JLabel("Seleccione el partido pendiente:"));
        this.add(Box.createRigidArea(new Dimension(0, 30)));
        comboPartidos = new JComboBox<>();
        this.add(comboPartidos);
        this.add(Box.createRigidArea(new Dimension(0, 60)));

        this.add(new JLabel("Puntos del participante local:"));
        this.add(Box.createRigidArea(new Dimension(0, 10)));
        txtPuntosLocal = new JTextField();
        this.add(txtPuntosLocal);
        this.add(Box.createRigidArea(new Dimension(0, 30)));

        this.add(new JLabel("Puntos del participante visitante:"));
        this.add(Box.createRigidArea(new Dimension(0, 10)));
        txtPuntosVisitante = new JTextField();
        this.add(txtPuntosVisitante);
        this.add(Box.createRigidArea(new Dimension(0, 60)));

        this.add(btnRegistrar = creadorBotones.crear("Registrar Resultado"));
        this.add(Box.createRigidArea(new Dimension(0, 30)));

        this.add(btnCancelar = creadorBotones.crear("Cancelar"));
        this.add(Box.createRigidArea(new Dimension(0, 30)));
    }

    public void cargarPartidosPendientes() {
        comboPartidos.removeAllItems();
        if (torneoController == null) return;

        String nombreTorneo = (String) comboTorneos.getSelectedItem();
        if (nombreTorneo == null) return;

        Torneo torneo = torneoController.buscarTorneo(nombreTorneo);
        if (torneo == null) return;

        List<Partido> pendientes = torneo.getCalendario().getPendientes();
        for (Partido p : pendientes) {
            comboPartidos.addItem(p.getLocal().getNombre() + " vs " + p.getVisitante().getNombre());
        }
    }




    /**
     * Obtiene el nombre del torneo seleccionado.
     *
     * @return nombre del torneo.
     */
    public String getNombreTorneo() {
        return (String) comboTorneos.getSelectedItem();
    }

    /**
     * Obtiene el índice del partido seleccionado en la lista de pendientes.
     *
     * @return índice del partido.
     */
    public int getIndicePartido() {
        return comboPartidos.getSelectedIndex();
    }

    /**
     * Obtiene los puntos ingresados para el participante local.
     *
     * @return texto del campo de puntos local.
     */
    public String getPuntosLocal() {
        return txtPuntosLocal.getText().trim();
    }

    /**
     * Obtiene los puntos ingresados para el participante visitante.
     *
     * @return texto del campo de puntos visitante.
     */
    public String getPuntosVisitante() {
        return txtPuntosVisitante.getText().trim();
    }

    /**
     * Obtiene el botón de registrar.
     *
     * @return botón registrar.
     */
    public JButton getBotonRegistrar() {
        return btnRegistrar;
    }

    /**
     * Obtiene el botón de cancelar.
     *
     * @return botón cancelar.
     */
    public JButton getBotonCancelar() {
        return btnCancelar;
    }

    /**
     * Limpia todos los campos del formulario.
     */
    public void limpiarFormulario() {
        txtPuntosLocal.setText("");
        txtPuntosVisitante.setText("");
        if (comboPartidos.getItemCount() > 0) {
            comboPartidos.setSelectedIndex(0);
        }
        this.revalidate();
        this.repaint();
    }


    /**
     * Limpia el panel completo.
     */
    public void limpiarPanel() {
        this.removeAll();
        this.revalidate();
        this.repaint();
    }




}
