package com.gestiontorneos.gui.organizador;

import com.gestiontorneos.controller.TorneoController;
import com.gestiontorneos.gui.compartido.PanelInformacion;
import com.gestiontorneos.gui.factory.BotonSimple;
import com.gestiontorneos.model.partido.Partido;
import com.gestiontorneos.model.torneo.Torneo;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Panel gráfico encargado de registrar resultados de partidos pendientes.
 * <p>
 * Esta vista permite seleccionar un torneo, escoger uno de sus partidos pendientes
 * e ingresar los puntos obtenidos por el participante local y el participante
 * visitante.
 * </p>
 * <p>
 * La clase funciona como vista dentro del flujo de registro de resultados. No
 * procesa directamente la lógica del torneo, sino que obtiene datos desde la
 * interfaz y los deja disponibles para que el controlador correspondiente los
 * valide y registre.
 * </p>
 *
 * @see JPanel
 * @see TorneoController
 * @see Partido
 * @see Torneo
 */
public class PanelRegistrarResultado extends JPanel {

    /**
     * Lista desplegable con los torneos disponibles.
     */
    private JComboBox<String> comboTorneos;

    /**
     * Lista desplegable con los partidos pendientes del torneo seleccionado.
     */
    private JComboBox<String> comboPartidos;

    /**
     * Campo de texto para ingresar los puntos del participante local.
     */
    private JTextField txtPuntosLocal;

    /**
     * Campo de texto para ingresar los puntos del participante visitante.
     */
    private JTextField txtPuntosVisitante;

    /**
     * Botón utilizado para confirmar el registro del resultado.
     */
    private JButton btnRegistrar;

    /**
     * Botón utilizado para cancelar o limpiar la operación actual.
     */
    private JButton btnCancelar;

    /**
     * Fábrica utilizada para crear botones con el estilo visual de la aplicación.
     */
    private BotonSimple creadorBotones;

    /**
     * Controlador de torneos desde el cual se obtienen los torneos y partidos.
     */
    private TorneoController torneoController;

    /**
     * Crea e inicializa el panel de registro de resultados.
     * <p>
     * Configura el layout vertical, color de fondo, tamaño preferido y fábrica de
     * botones. Luego construye el formulario mediante {@link #crearFormulario()}.
     * </p>
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

    /**
     * Crea los componentes visuales del formulario.
     * <p>
     * Incluye las listas desplegables para torneos y partidos, los campos de puntos
     * y los botones de registrar y cancelar. Además, configura el combo de torneos
     * para recargar los partidos pendientes cuando cambia la selección.
     * </p>
     */
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

    /**
     * Carga en el combo de partidos los partidos pendientes del torneo seleccionado.
     * <p>
     * Si no existe un controlador asignado, no hay torneo seleccionado o el torneo
     * no se encuentra, el método termina sin modificar el flujo de la aplicación.
     * </p>
     */
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
     * Asigna el controlador de torneos utilizado por el panel.
     * <p>
     * Después de asignarlo, carga automáticamente la lista de torneos disponibles.
     * </p>
     *
     * @param torneoController controlador de torneos.
     */
    public void setTorneoController(TorneoController torneoController) {
        this.torneoController = torneoController;
        cargarTorneos();
    }

    /**
     * Carga en el combo de torneos todos los torneos registrados en el controlador.
     * <p>
     * Si no hay controlador asignado, el método no realiza ninguna acción.
     * </p>
     */
    public void cargarTorneos() {
        comboTorneos.removeAllItems();
        if (torneoController == null) return;

        for (Torneo t : torneoController.listaTorneos()) {
            comboTorneos.addItem(t.getNombre());
        }
    }

    /**
     * Obtiene el nombre del torneo seleccionado.
     *
     * @return nombre del torneo seleccionado, o {@code null} si no hay selección.
     */
    public String getNombreTorneo() {
        return (String) comboTorneos.getSelectedItem();
    }

    /**
     * Obtiene el índice del partido seleccionado dentro de la lista de pendientes.
     *
     * @return índice del partido seleccionado; puede ser {@code -1} si no hay selección.
     */
    public int getIndicePartido() {
        return comboPartidos.getSelectedIndex();
    }

    /**
     * Obtiene los puntos ingresados para el participante local.
     *
     * @return texto del campo de puntos local sin espacios al inicio o al final.
     */
    public String getPuntosLocal() {
        return txtPuntosLocal.getText().trim();
    }

    /**
     * Obtiene los puntos ingresados para el participante visitante.
     *
     * @return texto del campo de puntos visitante sin espacios al inicio o al final.
     */
    public String getPuntosVisitante() {
        return txtPuntosVisitante.getText().trim();
    }

    /**
     * Obtiene el botón usado para registrar el resultado.
     *
     * @return botón de registro.
     */
    public JButton getBotonRegistrar() {
        return btnRegistrar;
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
     * Limpia los campos de puntos y reinicia la selección de partidos si existe
     * al menos un partido disponible.
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
     * Limpia completamente el panel eliminando todos sus componentes.
     * <p>
     * Este método deja el panel vacío y actualiza la vista. Si se desea volver a
     * mostrar el formulario, será necesario reconstruir los componentes.
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
}