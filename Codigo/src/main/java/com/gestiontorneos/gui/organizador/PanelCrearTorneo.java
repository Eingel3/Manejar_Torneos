package com.gestiontorneos.gui.organizador;

import com.gestiontorneos.controller.TorneoController;
import com.gestiontorneos.gui.VentanaPrincipal;
import com.gestiontorneos.gui.compartido.PanelInformacion;
import com.gestiontorneos.model.torneo.formato.EliminacionDirecta;
import com.gestiontorneos.model.torneo.formato.FormatoTorneo;
import com.gestiontorneos.model.torneo.formato.LigaSimple;
import com.gestiontorneos.model.torneo.formato.DobleEliminacion;

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
    private JComboBox<String> Formato;
    private JTextField txtDescripcion;
    private JButton btnCrear;
    private JButton btnCancelar;

    //se añade para interactuar con torneocontroller
    private TorneoController torneoController;

    //se añade para recargar la pagina principal tras enviar los datos
    private VentanaPrincipal ventanaPrincipal;

    //para recargar torneocontrolelr
    public void setTorneoController(TorneoController tc) {
        this.torneoController = tc;
    }

    //para recargar la ventana principal
    public void setVentanaPrincipal(VentanaPrincipal vp) {
        this.ventanaPrincipal = vp;
    }



    public PanelCrearTorneo() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.LIGHT_GRAY);
        setPreferredSize(new Dimension(
                PanelInformacion.VENTANASINMENU.getAncho(),
                PanelInformacion.VENTANASINMENU.getAlto()));
        agregarComponentes();

        btnCrear.addActionListener(e -> {

            String seleccion = (String) Formato.getSelectedItem();
            FormatoTorneo formato;
            switch (seleccion) {
                case "Liga Simple":         
                    formato = new LigaSimple();
                    break;
                case "Doble Eliminacion":
                    formato = new DobleEliminacion();
                    break;
                default:
                    formato = new EliminacionDirecta();
                    break;
            }
                torneoController.crearTorneo(getNombre(),getDeporte(), formato,getFechaInicio(),getFechaFin());
                System.out.println("Torneo creado: " + getNombre() + " | Deporte: " + getDeporte() + " | Formato: " + getFormato() + " | Inicio: " + getFechaInicio() + " | Fin: " + getFechaFin());
                mostrarMensaje("Torneo creado exitosamente!");
                limpiarFormulario();

                ventanaPrincipal.actualizarTorneos();
                ventanaPrincipal.mostrarPanel("Torneos");



        });
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
        Formato = new JComboBox<>(new String[]{"Eliminacion Directa", "Liga Simple", "Doble Eliminacion"});
        this.add(Formato);

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
        return (String) Formato.getSelectedItem();
    }

    public String getDescripcion() {
        return txtDescripcion.getText().trim();
    }

    public void limpiarFormulario() {
        txtNombre.setText("");
        txtFechaInicio.setText("");
        txtFechaFin.setText("");
        txtDeporte.setText("");
        Formato.setSelectedIndex(0);
        txtDescripcion.setText("");
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