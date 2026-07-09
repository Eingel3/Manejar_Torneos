package com.gestiontorneos.gui.organizador;

import com.gestiontorneos.controller.TorneoController;
import com.gestiontorneos.gui.VentanaPrincipal;
import com.gestiontorneos.gui.compartido.PanelInformacion;
import com.gestiontorneos.model.deporte.TipoParticipacion;
import com.gestiontorneos.model.torneo.formato.EliminacionDirecta;
import com.gestiontorneos.model.torneo.formato.FormatoTorneo;
import com.gestiontorneos.model.torneo.formato.LigaSimple;
import com.gestiontorneos.model.torneo.formato.DobleEliminacion;
import com.gestiontorneos.model.torneo.Torneo;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

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
    private JComboBox<String> formato;
    private JComboBox<String> tipoParticipacion;
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
            if (getNombre().isEmpty() || getDeporte().isEmpty()) {
                mostrarMensaje("Llenar campos de Nombre y Deporte");
                return;
            }
            LocalDate inicio, fin;
            try {
                inicio = LocalDate.parse(getFechaInicio());
                fin = LocalDate.parse(getFechaFin());
            } catch (DateTimeParseException ex) {
                mostrarMensaje("Fecha no valida, Use yyyy-MM-dd");
                return;
            }
            if (fin.isBefore(inicio)) {
                mostrarMensaje("La fecha de fin no puede ser anterior a la de inicio");
                return;
            }
            String seleccion = (String) formato.getSelectedItem();
            FormatoTorneo formatoTorneo;
            switch (seleccion) {
                case "Liga Simple":
                    formatoTorneo = new LigaSimple();
                    break;
                case "Doble Eliminacion":
                    formatoTorneo = new DobleEliminacion();
                    break;
                default:
                    formatoTorneo = new EliminacionDirecta();
                    break;
            }
            Torneo torneo = torneoController.crearTorneo(getNombre(), getDeporte(), formatoTorneo, getFechaInicio(), getFechaFin(), getTipoParticipacionEnum());
            if (torneo == null) {
                mostrarMensaje("Error al crear el torneo.");
                return;
            }
            System.out.println("Torneo creado: " + getNombre() + " | Deporte: " + getDeporte() + " | formato: " + getFormato() + " | Inicio: " + getFechaInicio() + " | Fin: " + getFechaFin());
            String input = JOptionPane.showInputDialog(this, "Torneo creado exitosamente!\n¿Cuántos participantes tendrá el torneo?");
            if (input == null || input.trim().isEmpty()) {
                mostrarMensaje("Se creó el torneo sin asignar participantes.");
                limpiarFormulario();
                ventanaPrincipal.actualizarTorneos();
                ventanaPrincipal.mostrarPanel("Torneos");
                return;
            }
            try {
                int total = Integer.parseInt(input.trim());
                if (total <= 0) {
                    mostrarMensaje("Ingrese un número mayor a 0.");
                    limpiarFormulario();
                    ventanaPrincipal.actualizarTorneos();
                    ventanaPrincipal.mostrarPanel("Torneos");
                    return;
                }
                String nombreTorneo = getNombre();
                limpiarFormulario();
                ventanaPrincipal.actualizarTorneos();
                ventanaPrincipal.getCrearParticipante().configurarModoCreacionRapida(nombreTorneo, total);
                ventanaPrincipal.refrescarBotonSiguienteParticipante();
                ventanaPrincipal.mostrarPanel("Crear Participante");
            } catch (NumberFormatException e2) {
                mostrarMensaje("Ingrese un número válido.");
                limpiarFormulario();
                ventanaPrincipal.actualizarTorneos();
                ventanaPrincipal.mostrarPanel("Torneos");
            }
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

        this.add(new JLabel("Tipo de participacion:"));
        tipoParticipacion = new JComboBox<>(new String[]{"Individual", "Colectivo"});
        this.add(tipoParticipacion);
        this.add(Box.createRigidArea(new Dimension(0, 30)));

        this.add(new JLabel("formato:"));
        formato = new JComboBox<>(new String[]{"Eliminacion Directa", "Liga Simple", "Doble Eliminacion"});
        this.add(formato);

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
        return (String) formato.getSelectedItem();
    }

    public String getDescripcion() {
        return txtDescripcion.getText().trim();
    }

    public TipoParticipacion getTipoParticipacionEnum() {
        String seleccion = (String) tipoParticipacion.getSelectedItem();
        if ("Colectivo".equals(seleccion)) {
            return TipoParticipacion.COLECTIVO;
        }
        return TipoParticipacion.INDIVIDUAL;
    }

    public void limpiarFormulario() {
        txtNombre.setText("");
        txtFechaInicio.setText("");
        txtFechaFin.setText("");
        txtDeporte.setText("");
        tipoParticipacion.setSelectedIndex(0);
        formato.setSelectedIndex(0);
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