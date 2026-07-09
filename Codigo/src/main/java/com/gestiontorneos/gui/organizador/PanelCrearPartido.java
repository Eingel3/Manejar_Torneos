package com.gestiontorneos.gui.organizador;

import com.gestiontorneos.gui.compartido.PanelInformacion;
import com.gestiontorneos.gui.factory.BotonSimple;

import javax.swing.*;
import java.awt.*;

/**
 * Panel gráfico asociado a la creación o gestión manual de partidos.
 * <p>
 * Esta clase fue diseñada inicialmente para permitir que el usuario agregara
 * partidos manualmente desde la interfaz. Más adelante, la idea evolucionó hacia
 * la posibilidad de crear partidos amistosos, independientes del calendario normal
 * de un torneo.
 * </p>
 * <p>
 * Sin embargo, esta funcionalidad fue descartada en la versión actual del sistema,
 * ya que el modelo final establece que cada enfrentamiento debe formar parte de un
 * torneo. Por esa razón, los partidos utilizados por la aplicación se generan y
 * administran dentro del contexto de un torneo, principalmente mediante su
 * calendario.
 * </p>
 * <p>
 * A pesar de que la creación de partidos amistosos no se incluyó en la versión
 * final, este panel se conserva como base visual para posibles futuras extensiones
 * relacionadas con la gestión de partidos. También sirve como estructura de apoyo
 * para campos, botones y métodos que podrían reutilizarse si en una versión futura
 * se decide incorporar algún tipo de administración manual controlada.
 * </p>
 *
 * @see JPanel
 * @see BotonSimple
 * @see PanelInformacion
 */
public class PanelCrearPartido extends JPanel {

    /**
     * Campo de texto utilizado para ingresar el nombre del participante o equipo local.
     */
    private JTextField txtNombreParticipanteLocal;

    /**
     * Campo de texto utilizado para ingresar el nombre del participante o equipo visitante.
     */
    private JTextField txtNombreParticipanteVisitante;

    /**
     * Combo originalmente pensado para indicar el tipo o rol del participante.
     * <p>
     * En la versión actual no cumple una función principal, ya que la creación
     * manual o amistosa de partidos fue descartada.
     * </p>
     */
    private JComboBox<String> tipoParticipante;

    /**
     * Campo de texto utilizado para indicar el torneo al que pertenece el partido.
     * <p>
     * Se conserva porque, en el diseño final, todo partido debe estar asociado a un
     * torneo existente.
     * </p>
     */
    private JTextField txtNombreTorneo;

    /**
     * Botón originalmente utilizado para confirmar la creación manual de un partido.
     */
    private JButton btnCrear;

    /**
     * Botón utilizado para cancelar o limpiar la operación actual.
     */
    private JButton btnCancelar;

    /**
     * Botón reservado para una posible navegación por pasos dentro del formulario.
     */
    private JButton btnSiguiente;

    /**
     * Fábrica utilizada para crear botones con el estilo visual común de la aplicación.
     */
    private BotonSimple creadorBotones;

    /**
     * Combo utilizado para seleccionar el estado inicial del partido.
     * <p>
     * Este campo formaba parte de la idea original de crear partidos manuales o
     * amistosos. Aunque esa funcionalidad fue descartada, se conserva como parte de
     * la estructura base del panel.
     * </p>
     */
    private JComboBox<String> estadoPartido;

    /**
     * Crea e inicializa el panel de creación o gestión de partidos.
     * <p>
     * Configura el layout vertical, color de fondo, tamaño preferido y la fábrica
     * de botones. Luego construye el formulario mediante {@link #crearPartido()}.
     * </p>
     * <p>
     * Aunque el formulario fue pensado originalmente para partidos manuales o
     * amistosos, actualmente se mantiene como una base visual reutilizable, ya que
     * la versión final exige que cada partido pertenezca a un torneo.
     * </p>
     */
    public PanelCrearPartido() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.LIGHT_GRAY);
        setPreferredSize(new Dimension(
                PanelInformacion.VENTANASINMENU.getAncho(),
                PanelInformacion.VENTANASINMENU.getAlto()));
        creadorBotones = new BotonSimple();
        crearPartido();
    }

    /**
     * Construye el formulario visual para ingresar los datos de un partido.
     * <p>
     * Incluye campos para el nombre del torneo, participante local, participante
     * visitante y estado del partido. También agrega los botones de creación y
     * cancelación.
     * </p>
     * <p>
     * Este formulario corresponde a una funcionalidad que no quedó activa como flujo
     * principal en la versión final. La idea de crear partidos amistosos fue
     * descartada porque el sistema actual requiere que todo enfrentamiento esté
     * vinculado a un torneo.
     * </p>
     */
    public void crearPartido() {
        JLabel lblTitulo = new JLabel("Crear Nuevo Partido");
        lblTitulo.setFont(new Font("Dialog", Font.BOLD, 24));
        lblTitulo.setForeground(Color.BLACK);
        lblTitulo.setAlignmentX(CENTER_ALIGNMENT);

        this.add(lblTitulo);
        this.add(Box.createRigidArea(new Dimension(0, 20)));

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

    /**
     * Agrega una sección mínima para seleccionar o indicar el torneo del partido.
     * <p>
     * Este método forma parte de la estructura original del panel. Se conserva como
     * apoyo para posibles reutilizaciones, especialmente porque el diseño final del
     * sistema exige que cualquier enfrentamiento esté asociado a un torneo.
     * </p>
     */
    public void elegirTorneo() {
        this.add(new JLabel("Nombre del torneo en que participa:"));

        this.add(btnCrear = creadorBotones.crear("Crear"));
        this.add(Box.createRigidArea(new Dimension(0, 30)));

        this.add(btnCancelar = creadorBotones.crear("Cancelar"));
        this.add(Box.createRigidArea(new Dimension(0, 30)));
    }

    /**
     * Obtiene el botón utilizado originalmente para crear el partido.
     *
     * @return botón de creación.
     */
    public JButton getBotonCrear() {
        return btnCrear;
    }

    /**
     * Obtiene el botón utilizado para cancelar la operación.
     *
     * @return botón de cancelación.
     */
    public JButton getBotonCancelar() {
        return btnCancelar;
    }

    /**
     * Obtiene el nombre del torneo ingresado en el formulario.
     *
     * @return nombre del torneo sin espacios al inicio o al final.
     */
    public String getNombreTorneo() {
        return txtNombreTorneo.getText().trim();
    }

    /**
     * Obtiene el botón reservado para avanzar en un posible formulario por pasos.
     *
     * @return botón siguiente.
     */
    public JButton getBotonSiguiente() {
        return btnSiguiente;
    }

    /**
     * Obtiene el nombre del participante o equipo local ingresado.
     *
     * @return nombre del participante local sin espacios al inicio o al final.
     */
    public String getNombreParticipanteLocal() {
        return txtNombreParticipanteLocal.getText().trim();
    }

    /**
     * Obtiene el nombre del participante o equipo visitante ingresado.
     *
     * @return nombre del participante visitante sin espacios al inicio o al final.
     */
    public String getNombreParticipanteVisitante() {
        return txtNombreParticipanteVisitante.getText().trim();
    }

    /**
     * Obtiene el tipo de participante seleccionado.
     * <p>
     * Este valor pertenece a una parte del diseño original y puede no estar
     * disponible si el combo no fue inicializado.
     * </p>
     *
     * @return tipo de participante seleccionado.
     */
    public String getTipoParticipante() {
        return (String) tipoParticipante.getSelectedItem();
    }

    /**
     * Obtiene el estado seleccionado para el partido.
     *
     * @return estado seleccionado en el combo de estado.
     */
    public String getEstadoPartido() {
        return (String) estadoPartido.getSelectedItem();
    }

    /**
     * Limpia los campos del formulario y reinicia los combos disponibles.
     * <p>
     * Este método permite dejar el panel listo para una nueva operación, aunque la
     * creación manual o amistosa de partidos no forme parte del flujo final de la
     * aplicación.
     * </p>
     */
    public void limpiarFormulario() {
        if (tipoParticipante != null) {
            tipoParticipante.setSelectedIndex(0);
        }
        if (estadoPartido != null) {
            estadoPartido.setSelectedIndex(0);
        }
        if (txtNombreTorneo != null) {
            txtNombreTorneo.setText("");
        }
        if (txtNombreParticipanteLocal != null) {
            txtNombreParticipanteLocal.setText("");
        }
        if (txtNombreParticipanteVisitante != null) {
            txtNombreParticipanteVisitante.setText("");
        }
        this.revalidate();
        this.repaint();
    }

    /**
     * Limpia completamente el panel eliminando todos sus componentes.
     * <p>
     * Después de llamar a este método, el formulario visual queda vacío.
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
     * @param pregunta texto de la pregunta.
     * @return {@code true} si el usuario selecciona "Sí";
     *         {@code false} en caso contrario.
     */
    public boolean confirmar(String pregunta){
        int respuesta = JOptionPane.showConfirmDialog(this, pregunta,
                "Advertencia", JOptionPane.YES_NO_OPTION);
        return respuesta == JOptionPane.YES_OPTION;
    }

    /**
     * Obtiene el campo de texto del participante local.
     *
     * @return campo de texto del participante local.
     */
    public JTextField getTxtNombreParticipanteLocal() {
        return txtNombreParticipanteLocal;
    }

    /**
     * Obtiene el campo de texto del participante visitante.
     *
     * @return campo de texto del participante visitante.
     */
    public JTextField getTxtNombreParticipanteVisitante() {
        return txtNombreParticipanteVisitante;
    }

    /**
     * Obtiene el combo utilizado para seleccionar el estado del partido.
     *
     * @return combo de estado del partido.
     */
    public JComboBox<String> getEstadoPartidoJCombo(){
        return estadoPartido;
    }
}