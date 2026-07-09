package com.gestiontorneos.gui.organizador;

import com.gestiontorneos.gui.compartido.PanelInformacion;
import com.gestiontorneos.gui.factory.BotonSimple;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Panel gráfico encargado de crear participantes para un torneo.
 * <p>
 * Esta vista permite registrar dos tipos de participantes: equipos y jugadores
 * individuales. Dependiendo de la opción seleccionada por el usuario, el panel
 * muestra campos distintos para solicitar la información necesaria.
 * </p>
 * <p>
 * También incorpora un modo de creación rápida, utilizado después de crear un
 * torneo, donde el sistema solicita registrar una cantidad determinada de
 * participantes antes de generar el calendario automáticamente.
 * </p>
 * <p>
 * La clase actúa principalmente como vista dentro del patrón MVC. Por ello,
 * obtiene datos ingresados por el usuario, muestra mensajes y expone botones para
 * que el controlador correspondiente pueda asociar sus eventos.
 * </p>
 *
 * @see JPanel
 * @see BotonSimple
 * @see PanelInformacion
 */
public class PanelCrearParticipante extends javax.swing.JPanel {

    /**
     * Campo de texto donde se ingresa el nombre de un participante individual.
     */
    private JTextField txtNombreParticipante;

    /**
     * Campo de texto donde se ingresa el contacto de un participante individual.
     */
    private JTextField txtContacto;

    /**
     * Lista desplegable que permite elegir entre equipo o jugador individual.
     */
    private JComboBox<String> tipoParticipante;

    /**
     * Campo de texto donde se ingresa el nombre del equipo.
     */
    private JTextField txtNombreEquipo;

    /**
     * Campo de texto donde se ingresan los integrantes de un equipo separados por coma.
     */
    private JTextField txtIntegrantes;

    /**
     * Campo de texto donde se ingresa el nombre del torneo al que se agregará el participante.
     */
    private JTextField txtNombreTorneo;

    /**
     * Botón utilizado para confirmar la creación del participante.
     */
    private JButton btnCrear;

    /**
     * Botón utilizado para cancelar la operación actual.
     */
    private JButton btnCancelar;

    /**
     * Botón utilizado para avanzar desde la selección del tipo de participante
     * hacia el formulario correspondiente.
     */
    private JButton btnSiguiente;

    /**
     * Fábrica utilizada para crear botones con el estilo común de la aplicación.
     */
    private BotonSimple creadorBotones;

    /**
     * Nombre del torneo asignado automáticamente cuando se utiliza el modo de
     * creación rápida.
     */
    private String nombreTorneoAsignado;

    /**
     * Cantidad total de participantes que se deben crear en modo de creación rápida.
     */
    private int totalParticipantes;

    /**
     * Cantidad de participantes creados hasta el momento en modo de creación rápida.
     */
    private int participantesCreados;

    /**
     * Etiqueta reservada para mostrar el progreso de participantes creados.
     */
    private JLabel lblContador;

    /**
     * Crea e inicializa el panel de creación de participantes.
     * <p>
     * Configura el layout vertical, color de fondo, tamaño preferido y crea la
     * fábrica de botones. Finalmente, muestra la primera sección del formulario,
     * donde el usuario elige si desea crear un equipo o un jugador individual.
     * </p>
     */
    public PanelCrearParticipante() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.LIGHT_GRAY);
        setPreferredSize(new Dimension(
                PanelInformacion.VENTANASINMENU.getAncho(),
                PanelInformacion.VENTANASINMENU.getAlto()));
        creadorBotones = new BotonSimple();
        elegirTipoParticipante();
    }

    /**
     * Configura el modo de creación rápida de participantes.
     * <p>
     * Este modo se utiliza cuando, después de crear un torneo, se solicita ingresar
     * inmediatamente una cantidad definida de participantes. En este caso, el nombre
     * del torneo ya queda asignado y no es necesario seleccionarlo manualmente.
     * </p>
     *
     * @param nombreTorneo nombre del torneo al que se asociarán los participantes.
     * @param total cantidad total de participantes que se deben crear.
     */
    public void configurarModoCreacionRapida(String nombreTorneo, int total) {
        this.nombreTorneoAsignado = nombreTorneo;
        this.totalParticipantes = total;
        this.participantesCreados = 0;
        limpiarPanel();
        elegirTipoParticipante();
    }

    /**
     * Indica si el panel se encuentra en modo de creación rápida.
     *
     * @return {@code true} si existe un torneo asignado automáticamente;
     *         {@code false} en caso contrario.
     */
    public boolean isModoCreacionRapida() {
        return nombreTorneoAsignado != null;
    }

    /**
     * Obtiene el nombre del torneo asignado en modo de creación rápida.
     *
     * @return nombre del torneo asignado.
     */
    public String getNombreTorneoAsignado() {
        return nombreTorneoAsignado;
    }

    /**
     * Incrementa en uno el contador de participantes creados.
     * <p>
     * Se utiliza durante el modo de creación rápida para verificar el progreso
     * respecto al total solicitado.
     * </p>
     */
    public void incrementarContador() {
        participantesCreados++;
    }

    /**
     * Indica si ya se creó la cantidad total de participantes requerida.
     *
     * @return {@code true} si la cantidad de participantes creados es mayor o igual
     *         al total solicitado; {@code false} en caso contrario.
     */
    public boolean isCompleto() {
        return participantesCreados >= totalParticipantes;
    }

    /**
     * Obtiene la cantidad de participantes creados hasta el momento.
     *
     * @return número de participantes creados.
     */
    public int getParticipantesCreados() {
        return participantesCreados;
    }

    /**
     * Obtiene la cantidad total de participantes que se deben crear.
     *
     * @return total de participantes requeridos.
     */
    public int getTotalParticipantes() {
        return totalParticipantes;
    }

    /**
     * Cancela el modo de creación rápida.
     * <p>
     * Limpia el torneo asignado y reinicia los contadores asociados al proceso.
     * </p>
     */
    public void cancelarModoCreacionRapida() {
        nombreTorneoAsignado = null;
        totalParticipantes = 0;
        participantesCreados = 0;
    }

    /**
     * Muestra la sección inicial donde el usuario elige el tipo de participante.
     * <p>
     * Permite seleccionar entre equipo y jugador individual. Al final de la sección
     * se crea el botón "Siguiente", que será utilizado por el controlador para
     * avanzar al formulario correspondiente.
     * </p>
     *
     * @return botón "Siguiente" creado en el panel.
     */
    public JButton elegirTipoParticipante() {
        JLabel lblTitulo = new JLabel("Crear Nuevo Participante");
        lblTitulo.setFont(new Font("Dialog", Font.BOLD, 24));
        lblTitulo.setForeground(Color.BLACK);
        lblTitulo.setAlignmentX(CENTER_ALIGNMENT);

        this.add(lblTitulo);
        this.add(Box.createRigidArea(new Dimension(0, 20)));
        this.add(new JLabel("Indique si desea agregar un equipo o un jugador individual:"));
        this.add(Box.createRigidArea(new Dimension(0, 10)));

        tipoParticipante = new JComboBox<>(new String[]{"Equipo", "Jugador Individual"});
        tipoParticipante.setMaximumSize(new Dimension(300, 30));
        this.add(tipoParticipante);
        this.add(Box.createRigidArea(new Dimension(0, 30)));

        btnSiguiente = creadorBotones.crear("Siguiente");
        btnSiguiente.setMaximumSize(new Dimension(200, 40));
        this.add(btnSiguiente);

        this.revalidate();
        this.repaint();

        return btnSiguiente;
    }

    /**
     * Agrega al panel los campos necesarios para registrar un equipo.
     * <p>
     * Solicita el nombre del equipo y una lista de integrantes separados por coma.
     * </p>
     */
    public void elegirNombreEquipo() {
        this.add(new JLabel("Nombre del equipo:"));
        this.add(Box.createRigidArea(new Dimension(0, 10)));

        txtNombreEquipo = new JTextField();
        txtNombreEquipo.setMaximumSize(new Dimension(300, 30));
        this.add(txtNombreEquipo);
        this.add(Box.createRigidArea(new Dimension(0, 15)));

        this.add(new JLabel("Integrantes (separados por coma):"));
        this.add(Box.createRigidArea(new Dimension(0, 10)));

        txtIntegrantes = new JTextField();
        txtIntegrantes.setMaximumSize(new Dimension(300, 30));
        this.add(txtIntegrantes);
        this.add(Box.createRigidArea(new Dimension(0, 30)));

        this.revalidate();
        this.repaint();
    }

    /**
     * Agrega al panel los campos necesarios para indicar el torneo de destino.
     * <p>
     * Además crea los botones de confirmación y cancelación utilizados por el
     * controlador.
     * </p>
     */
    public void elegirTorneo() {
        this.add(new JLabel("Nombre del torneo en que participa:"));
        this.add(Box.createRigidArea(new Dimension(0, 10)));

        txtNombreTorneo = new JTextField();
        txtNombreTorneo.setMaximumSize(new Dimension(300, 30));
        this.add(txtNombreTorneo);
        this.add(Box.createRigidArea(new Dimension(0, 30)));

        btnCrear = creadorBotones.crear("Crear");
        btnCrear.setMaximumSize(new Dimension(200, 40));
        this.add(btnCrear);
        this.add(Box.createRigidArea(new Dimension(0, 15)));

        btnCancelar = creadorBotones.crear("Cancelar");
        btnCancelar.setMaximumSize(new Dimension(200, 40));
        this.add(btnCancelar);
        this.add(Box.createRigidArea(new Dimension(0, 30)));

        this.revalidate();
        this.repaint();
    }

    /**
     * Agrega al panel los campos necesarios para registrar un jugador individual.
     * <p>
     * Solicita el nombre del participante y un dato de contacto.
     * </p>
     */
    public void agregarParticipante() {
        this.add(new JLabel("Nombre del participante:"));
        this.add(Box.createRigidArea(new Dimension(0, 10)));

        txtNombreParticipante = new JTextField();
        txtNombreParticipante.setMaximumSize(new Dimension(300, 30));
        this.add(txtNombreParticipante);
        this.add(Box.createRigidArea(new Dimension(0, 20)));

        this.add(new JLabel("Contacto del participante:"));
        this.add(Box.createRigidArea(new Dimension(0, 10)));

        txtContacto = new JTextField();
        txtContacto.setMaximumSize(new Dimension(300, 30));
        this.add(txtContacto);
        this.add(Box.createRigidArea(new Dimension(0, 30)));

        this.revalidate();
        this.repaint();
    }

    /**
     * Obtiene el botón usado para crear el participante.
     *
     * @return botón de creación.
     */
    public JButton getBotonCrear() {
        return btnCrear;
    }

    /**
     * Obtiene el botón usado para cancelar la operación.
     *
     * @return botón de cancelación.
     */
    public JButton getBotonCancelar() {
        return btnCancelar;
    }

    /**
     * Obtiene el nombre del torneo ingresado manualmente.
     *
     * @return nombre del torneo sin espacios al inicio o al final.
     */
    public String getNombreTorneo() {
        return txtNombreTorneo.getText().trim();
    }

    /**
     * Obtiene el contacto ingresado para un jugador individual.
     *
     * @return contacto del participante sin espacios al inicio o al final.
     */
    public String getContacto() {
        return txtContacto.getText().trim();
    }

    /**
     * Obtiene el nombre ingresado para un jugador individual.
     *
     * @return nombre del participante sin espacios al inicio o al final.
     */
    public String getNombreParticipante() {
        return txtNombreParticipante.getText().trim();
    }

    /**
     * Obtiene el tipo de participante seleccionado.
     *
     * @return texto seleccionado en el combo de tipo de participante.
     */
    public String getTipoParticipante() {
        return (String) tipoParticipante.getSelectedItem();
    }

    /**
     * Obtiene el nombre ingresado para el equipo.
     *
     * @return nombre del equipo sin espacios al inicio o al final.
     */
    public String getNombreEquipo() {
        return txtNombreEquipo.getText().trim();
    }

    /**
     * Obtiene la lista de integrantes ingresados para un equipo.
     * <p>
     * El texto del campo se divide usando comas como separador. Los espacios en
     * blanco al inicio y al final de cada nombre son eliminados, y los valores
     * vacíos son ignorados.
     * </p>
     *
     * @return lista de integrantes del equipo, o una lista vacía si no hay datos.
     */
    public List<String> getIntegrantes() {
        if (txtIntegrantes == null || txtIntegrantes.getText().trim().isEmpty()) {
            return new ArrayList<>();
        }

        String[] partes = txtIntegrantes.getText().split(",");
        List<String> lista = new ArrayList<>();

        for (String parte : partes) {
            String trimmed = parte.trim();
            if (!trimmed.isEmpty()) {
                lista.add(trimmed);
            }
        }

        return lista;
    }

    /**
     * Limpia los campos del formulario sin eliminar la estructura visual completa.
     * <p>
     * Reinicia los campos de texto y devuelve los combos a su primera opción
     * cuando existen en pantalla.
     * </p>
     */
    public void limpiarFormulario() {
        if (tipoParticipante != null) {
            tipoParticipante.setSelectedIndex(0);
        }
        if (txtNombreEquipo != null) {
            txtNombreEquipo.setText("");
        }
        if (txtContacto != null) {
            txtContacto.setText("");
        }
        if (txtIntegrantes != null) {
            txtIntegrantes.setText("");
        }
        if (txtNombreParticipante != null) {
            txtNombreParticipante.setText("");
        }

        this.revalidate();
        this.repaint();
    }

    /**
     * Limpia completamente el panel.
     * <p>
     * Elimina todos los componentes gráficos actuales y actualiza la vista.
     * </p>
     */
    public void limpiarPanel() {
        this.removeAll();
        this.revalidate();
        this.repaint();
    }

    /**
     * Muestra un mensaje informativo al usuario mediante un cuadro de diálogo.
     *
     * @param mensaje texto que será mostrado.
     */
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    /**
     * Muestra una pregunta de confirmación al usuario.
     *
     * @param pregunta texto de la pregunta que será mostrada.
     * @return {@code true} si el usuario selecciona "Sí";
     *         {@code false} en caso contrario.
     */
    public boolean confirmar(String pregunta){
        int respuesta = JOptionPane.showConfirmDialog(this, pregunta,
                "Advertencia", JOptionPane.YES_NO_OPTION);
        return respuesta == JOptionPane.YES_OPTION;
    }

    /**
     * Obtiene el campo de texto del nombre del participante individual.
     *
     * @return campo de texto del nombre del participante.
     */
    public JTextField getTxtNombreParticipante() {
        return txtNombreParticipante;
    }

    /**
     * Obtiene el campo de texto del contacto del participante individual.
     *
     * @return campo de texto del contacto.
     */
    public JTextField getTxtContacto() {
        return txtContacto;
    }

    /**
     * Obtiene el botón "Siguiente".
     *
     * @return botón usado para avanzar en el formulario.
     */
    public JButton getBotonSiguiente(){
        return btnSiguiente;
    }
}