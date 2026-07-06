package com.gestiontorneos.gui.organizador;

import com.gestiontorneos.gui.compartido.PanelInformacion;

import javax.swing.*;
import java.awt.*;

/**
 * Panel gráfico que permite al organizador crear un nuevo torneo.
 * <p>
 * Contiene campos de texto para ingresar el nombre del torneo, fechas, deporte,
 * formato y descripción. Además, incluye botones para confirmar o cancelar la
 * creación.
 * </p>
 *
 * @see JPanel
 * @see JTextField
 * @see JButton
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

    /**
     * Crea e inicializa el formulario de creación de torneos.
     * <p>
     * Configura el layout vertical, color de fondo, dimensiones del panel y
     * agrega todos los componentes del formulario.
     * </p>
     */
    public PanelCrearTorneo() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.LIGHT_GRAY);
        setPreferredSize(new Dimension(
                PanelInformacion.VENTANASINMENU.getAncho(),
                PanelInformacion.VENTANASINMENU.getAlto()));
        agregarComponentes();
    }

    /**
     * Agrega al panel todos los componentes visuales del formulario.
     * <p>
     * Incluye etiquetas descriptivas, campos de texto para los datos del torneo
     * y botones de acción.
     * </p>
     */
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
}