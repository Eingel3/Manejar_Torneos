package com.gestiontorneos.gui.organizador;

import com.gestiontorneos.controller.TorneoController;
import com.gestiontorneos.gui.compartido.PanelInformacion;
import com.gestiontorneos.gui.factory.BotonSimple;

import javax.swing.*;
import java.awt.*;

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
     * Limpia el panel completo.
     */
    public void limpiarPanel() {
        this.removeAll();
        this.revalidate();
        this.repaint();
    }




}
