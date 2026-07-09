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
 * Panel gráfico encargado de crear torneos desde la interfaz de organizador.
 * <p>
 * Esta vista permite ingresar los datos principales de un torneo, incluyendo
 * nombre, fechas, deporte, tipo de participación, formato de competición y una
 * descripción general.
 * </p>
 * <p>
 * Además de recopilar datos, este panel contiene parte del flujo posterior a la
 * creación del torneo: una vez creado correctamente, pregunta cuántos participantes
 * tendrá el torneo y, si corresponde, redirige al usuario al panel de creación de
 * participantes en modo de creación rápida.
 * </p>
 * <p>
 * La clase trabaja en conjunto con {@link TorneoController}, encargado de crear el
 * torneo en el modelo, y con {@link VentanaPrincipal}, que permite actualizar las
 * vistas y cambiar de panel.
 * </p>
 *
 * @see JPanel
 * @see TorneoController
 * @see VentanaPrincipal
 * @see FormatoTorneo
 */
public class PanelCrearTorneo extends JPanel {

    /**
     * Campo de texto para ingresar el nombre del torneo.
     */
    private JTextField txtNombre;

    /**
     * Campo de texto para ingresar la fecha de inicio del torneo.
     */
    private JTextField txtFechaInicio;

    /**
     * Campo de texto para ingresar la fecha de fin del torneo.
     */
    private JTextField txtFechaFin;

    /**
     * Campo de texto para ingresar el deporte asociado al torneo.
     */
    private JTextField txtDeporte;

    /**
     * Lista desplegable con los formatos de torneo disponibles.
     */
    private JComboBox<String> formato;

    /**
     * Lista desplegable para seleccionar si la participación será individual o colectiva.
     */
    private JComboBox<String> tipoParticipacion;

    /**
     * Campo de texto para ingresar una descripción del torneo.
     */
    private JTextField txtDescripcion;

    /**
     * Botón utilizado para confirmar la creación del torneo.
     */
    private JButton btnCrear;

    /**
     * Botón utilizado para cancelar la operación.
     */
    private JButton btnCancelar;

    /**
     * Controlador encargado de gestionar la creación y almacenamiento en memoria
     * de los torneos.
     */
    private TorneoController torneoController;

    /**
     * Ventana principal de la aplicación, utilizada para actualizar paneles y
     * navegar entre vistas.
     */
    private VentanaPrincipal ventanaPrincipal;

    /**
     * Asigna el controlador de torneos que será utilizado por este panel.
     *
     * @param tc controlador de torneos.
     */
    public void setTorneoController(TorneoController tc) {
        this.torneoController = tc;
    }

    /**
     * Asigna la ventana principal asociada a este panel.
     * <p>
     * Esta referencia permite actualizar la lista de torneos y cambiar hacia otros
     * paneles después de crear un torneo.
     * </p>
     *
     * @param vp ventana principal de la aplicación.
     */
    public void setVentanaPrincipal(VentanaPrincipal vp) {
        this.ventanaPrincipal = vp;
    }

    /**
     * Crea e inicializa el panel de creación de torneos.
     * <p>
     * Configura el layout, color de fondo, dimensiones y componentes visuales.
     * También registra la acción principal del botón "Crear", incluyendo validación
     * de campos, validación de fechas, selección de formato y creación del torneo.
     * </p>
     */
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

            Torneo torneo = torneoController.crearTorneo(
                    getNombre(),
                    getDeporte(),
                    formatoTorneo,
                    getFechaInicio(),
                    getFechaFin(),
                    getTipoParticipacionEnum()
            );

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

    /**
     * Agrega los componentes gráficos del formulario al panel.
     * <p>
     * Crea las etiquetas, campos de texto, listas desplegables y botones necesarios
     * para ingresar la información del torneo.
     * </p>
     */
    private void agregarComponentes() {
        JLabel lblTitulo = new JLabel("Crear Nuevo Torneo");
        lblTitulo.setFont(new Font("Dialog", Font.BOLD, 24));
        lblTitulo.setForeground(Color.BLACK);
        lblTitulo.setAlignmentX(CENTER_ALIGNMENT);

        this.add(lblTitulo);
        this.add(Box.createRigidArea(new Dimension(0, 20)));

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

    /**
     * Obtiene el botón utilizado para crear el torneo.
     *
     * @return botón de creación.
     */
    public JButton getBotonCrear() {
        return btnCrear;
    }

    /**
     * Obtiene el botón utilizado para cancelar la creación del torneo.
     *
     * @return botón de cancelación.
     */
    public JButton getBotonCancelar() {
        return btnCancelar;
    }

    /**
     * Obtiene el nombre ingresado para el torneo.
     *
     * @return nombre del torneo sin espacios al inicio o al final.
     */
    public String getNombre() {
        return txtNombre.getText().trim();
    }

    /**
     * Obtiene la fecha de inicio ingresada.
     *
     * @return fecha de inicio en formato de texto.
     */
    public String getFechaInicio() {
        return txtFechaInicio.getText().trim();
    }

    /**
     * Obtiene la fecha de fin ingresada.
     *
     * @return fecha de fin en formato de texto.
     */
    public String getFechaFin() {
        return txtFechaFin.getText().trim();
    }

    /**
     * Obtiene el deporte ingresado para el torneo.
     *
     * @return nombre del deporte sin espacios al inicio o al final.
     */
    public String getDeporte() {
        return txtDeporte.getText().trim();
    }

    /**
     * Obtiene el formato seleccionado en el formulario.
     *
     * @return nombre del formato seleccionado.
     */
    public String getFormato() {
        return (String) formato.getSelectedItem();
    }

    /**
     * Obtiene la descripción ingresada para el torneo.
     *
     * @return descripción del torneo sin espacios al inicio o al final.
     */
    public String getDescripcion() {
        return txtDescripcion.getText().trim();
    }

    /**
     * Convierte la selección del combo de tipo de participación al enum correspondiente.
     *
     * @return {@link TipoParticipacion#COLECTIVO} si se seleccionó "Colectivo";
     *         {@link TipoParticipacion#INDIVIDUAL} en caso contrario.
     */
    public TipoParticipacion getTipoParticipacionEnum() {
        String seleccion = (String) tipoParticipacion.getSelectedItem();
        if ("Colectivo".equals(seleccion)) {
            return TipoParticipacion.COLECTIVO;
        }
        return TipoParticipacion.INDIVIDUAL;
    }

    /**
     * Limpia todos los campos del formulario y devuelve los combos a su opción inicial.
     */
    public void limpiarFormulario() {
        txtNombre.setText("");
        txtFechaInicio.setText("");
        txtFechaFin.setText("");
        txtDeporte.setText("");
        tipoParticipacion.setSelectedIndex(0);
        formato.setSelectedIndex(0);
        txtDescripcion.setText("");
    }

    /**
     * Muestra un mensaje informativo al usuario.
     *
     * @param mensaje texto que será mostrado en el cuadro de diálogo.
     */
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    /**
     * Muestra una pregunta de confirmación al usuario.
     *
     * @param pregunta texto de la pregunta.
     * @return {@code true} si el usuario selecciona "Sí";
     *         {@code false} en caso contrario.
     */
    public boolean confirmar(String pregunta){
        int respuesta = JOptionPane.showConfirmDialog(this, pregunta,
                "Advertencia", JOptionPane.YES_NO_OPTION);
        return respuesta == JOptionPane.YES_OPTION;
    }
}