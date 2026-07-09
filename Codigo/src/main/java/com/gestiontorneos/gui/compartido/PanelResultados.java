package com.gestiontorneos.gui.compartido;
import java.util.List;
import com.gestiontorneos.gui.factory.PanelFactory;
import com.gestiontorneos.gui.factory.PanelLateral;
import com.gestiontorneos.model.partido.Partido;
import com.gestiontorneos.model.torneo.Torneo;

import javax.swing.*;
import java.awt.*;

/**
 * Panel gráfico encargado de mostrar la información y resultados de un torneo.
 * <p>
 * Presenta datos generales como nombre, descripción, fechas, deporte, tipo de
 * competición, ganador y participantes. La información se distribuye en dos
 * paneles: uno para los títulos y otro para los valores correspondientes.
 * </p>
 *
 * @see JPanel
 * @see JLabel
 * @see PanelFactory
 */
public class PanelResultados extends JPanel {

    private String nombre;
    private String descripcion;
    private String fechas;
    private String deporte;
    private String tipoCompeticion;
    private String ganador;
    private String participantes;
    private JPanel titulosPanel;
    private JPanel labelsPanel;
    private int anchoTitulosPanel;
    int anchoLabelsPanel;

    private JTextArea partidosArea;

    JLabel nombreL;
    JLabel descripcionL;
    JLabel fechasL;
    JLabel deporteL;
    JLabel tipoCompeticionL;
    JLabel ganadorL;
    JLabel participantesL;

    /**
     * Crea e inicializa el panel de resultados.
     * <p>
     * Configura el color de fondo, layout, borde y tamaño del panel. También
     * inicializa valores por defecto, crea la estructura visual e inserta los
     * {@link JLabel} donde se mostrarán los datos del torneo.
     * </p>
     */
    public PanelResultados() {

        //Definimos las caracteriscticas del JPanel
        this.setBackground(Color.CYAN);
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        this.setPreferredSize(new Dimension(
                PanelInformacion.VENTANASINMENU.getAncho(),
                PanelInformacion.VENTANASINMENU.getAlto()));

        anchoTitulosPanel = 200;
        anchoLabelsPanel = PanelInformacion.VENTANASINMENU.getAncho() - anchoTitulosPanel - 30; //Le quitamos 30 para su correcta visualizacion

        PanelFactory constructorPanel = new PanelLateral();
        titulosPanel = constructorPanel.crear(anchoTitulosPanel); //Panel Lateral con el layout tipo Box orientacion vertical
        labelsPanel = constructorPanel.crear(anchoLabelsPanel); //Otro panel que ocupe el resto de espacio libre

        iniciarAtributos();
        iniciarEstructura();
        iniciarLabels();
        agregarSeccionPartidos();
    }

    /**
     * Inicializa los valores por defecto que se mostrarán en el panel.
     * <p>
     * Estos valores funcionan como contenido temporal hasta que sean reemplazados
     * por información real proveniente del controlador o del modelo.
     * </p>
     */
    private void iniciarAtributos() {
        this.nombre = "Nombre Torneo";
        this.descripcion = "descripcion";
        this.fechas = "fechas";
        this.deporte = "deporte";
        this.tipoCompeticion = "tipoCompeticion";
        this.ganador = "ganador";
        this.participantes = "participante";
    }

    /**
     * Construye la estructura visual del panel de resultados.
     * <p>
     * Crea una columna lateral para los títulos de cada dato y un segundo panel
     * para los valores correspondientes. Ambos paneles son agregados al panel
     * principal.
     * </p>
     */
    public void iniciarEstructura() {
        titulosPanel.setBackground(Color.CYAN); //Le dejamos del mismo color que el resto del panel

        //Agregamos los titulos
        titulosPanel.add(Box.createRigidArea(new Dimension( //agregamos un espacio para el titulo
                PanelInformacion.MENULATERAL.getAncho(), 60)));
        titulosPanel.add(new JLabel("Descripcion del torneo:"));

        titulosPanel.add(Box.createRigidArea(new Dimension( //agregamos un espacio para la descripcion
                PanelInformacion.MENULATERAL.getAncho(), 120)));
        titulosPanel.add(new JLabel("Fechas del torneo:"));

        titulosPanel.add(Box.createRigidArea(new Dimension( //agregamos un espacio para las fechas
                PanelInformacion.MENULATERAL.getAncho(), 60)));
        titulosPanel.add(new JLabel("Deporte:"));

        titulosPanel.add(Box.createRigidArea(new Dimension( //agregamos un espacio para el deporte
                PanelInformacion.MENULATERAL.getAncho(), 90)));
        titulosPanel.add(new JLabel("Tipo Competicion:"));

        titulosPanel.add(Box.createRigidArea(new Dimension( //agregamos un espacio para el tipo de competicion
                PanelInformacion.MENULATERAL.getAncho(), 90)));
        titulosPanel.add(new JLabel("Ganador:"));

        titulosPanel.add(Box.createRigidArea(new Dimension( //agregamos un espacio para el ganador
                PanelInformacion.MENULATERAL.getAncho(), 30)));
        titulosPanel.add(new JLabel("Participantes:"));

        titulosPanel.add(Box.createRigidArea(new Dimension( //agregamos un espacio para los participantes
                PanelInformacion.MENULATERAL.getAncho(), 120)));

        this.add(titulosPanel); //Y lo agregamos

        //Ahora preparamos y añadimos el labelsPanel
        labelsPanel.setBackground(Color.CYAN);
        this.add(labelsPanel);
    }

    /**
     * Inicializa los {@link JLabel} que muestran los valores del torneo.
     * <p>
     * Configura su alineación horizontal, alineación dentro del contenedor y los
     * agrega al panel correspondiente junto con separadores rígidos.
     * </p>
     */
    public void iniciarLabels() {
        //Primero creamos los lables
        nombreL = new JLabel(this.nombre);
        descripcionL = new JLabel(this.descripcion);
        fechasL = new JLabel(this.fechas);
        deporteL = new JLabel(this.deporte);
        tipoCompeticionL = new JLabel(this.tipoCompeticion);
        ganadorL = new JLabel(this.ganador);
        participantesL = new JLabel(this.participantes);

        //Modificamos la alineación de los textos:
        nombreL.setHorizontalAlignment(JLabel.CENTER);
        descripcionL.setHorizontalAlignment(JLabel.LEFT);
        fechasL.setHorizontalAlignment(JLabel.LEFT);
        deporteL.setHorizontalAlignment(JLabel.LEFT);
        tipoCompeticionL.setHorizontalAlignment(JLabel.LEFT);
        ganadorL.setHorizontalAlignment(JLabel.LEFT);
        participantesL.setHorizontalAlignment(JLabel.LEFT);

        //Modificamos la alinceación de los JLabels:
        nombreL.setAlignmentX(Component.CENTER_ALIGNMENT);
        descripcionL.setAlignmentX(Component.LEFT_ALIGNMENT);
        fechasL.setAlignmentX(Component.LEFT_ALIGNMENT);
        deporteL.setAlignmentX(Component.LEFT_ALIGNMENT);
        tipoCompeticionL.setAlignmentX(Component.LEFT_ALIGNMENT);
        ganadorL.setAlignmentX(Component.LEFT_ALIGNMENT);
        participantesL.setAlignmentX(Component.LEFT_ALIGNMENT);

        //Y añadimos los JLAbels
        labelsPanel.add(nombreL);
        labelsPanel.add(Box.createRigidArea(new Dimension( //agregamos un espacio entre labels
                anchoTitulosPanel, 30)));

        labelsPanel.add(descripcionL);
        labelsPanel.add(Box.createRigidArea(new Dimension( //agregamos un espacio entre labels
                anchoTitulosPanel, 30)));

        labelsPanel.add(fechasL);
        labelsPanel.add(Box.createRigidArea(new Dimension( //agregamos un espacio entre labels
                anchoTitulosPanel, 30)));

        labelsPanel.add(deporteL);
        labelsPanel.add(Box.createRigidArea(new Dimension( //agregamos un espacio entre labels
                anchoTitulosPanel, 30)));

        labelsPanel.add(tipoCompeticionL);
        labelsPanel.add(Box.createRigidArea(new Dimension( //agregamos un espacio entre labels
                anchoTitulosPanel, 30)));

        labelsPanel.add(ganadorL);
        labelsPanel.add(Box.createRigidArea(new Dimension( //agregamos un espacio entre labels
                anchoTitulosPanel, 30)));

        labelsPanel.add(participantesL);

        actualizarCambios();
    }

    /**
     * Metodo para agregar el area donde los partidos serán mostrados
     * utiliza un scroll
     */
    private void agregarSeccionPartidos() {
        JLabel tituloPartidos = new JLabel("Partidos:");
        tituloPartidos.setFont(new Font("Dialog", Font.BOLD, 16));
        titulosPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        titulosPanel.add(tituloPartidos);
        //Creamos el area de texto donde se podran ver los partidos
        partidosArea = new JTextArea(10, 30);
        partidosArea.setEditable(false);
        partidosArea.setLineWrap(true);
        partidosArea.setWrapStyleWord(true);
        //Y agregamos un scroll
        JScrollPane scrollPartidos = new JScrollPane(partidosArea);
        scrollPartidos.setPreferredSize(new Dimension(anchoLabelsPanel, 200));
        labelsPanel.add(scrollPartidos);
    }

    /**
     * Rellena el área de partidos con la información de cada partido.
     * @param partidos lista de partidos del torneo
     */
    public void actualizarPartidos(List<Partido> partidos) {

        if (partidosArea == null || partidos == null) return;
        //En el String texto guardaremos el texto que se mostrará en el TextArea de los partidos
        String texto = "";
        for (int i = 0; i < partidos.size(); i++) {
            Partido p = partidos.get(i);
            //Añadimos información respecto a la ronda y quienes se enfrentan
            texto += "Ronda " + p.getRonda() + ": "
                    + p.getLocal().getNombre() + " vs " + p.getVisitante().getNombre() + "\n";

            //Y agregamos información respecto a quién ganó
            if (p.getResultado() != null) {
                texto += "El individuo o equipo que ganó es: " + p.getGanador().getNombre();
            } else {
                texto += "Sin resultado";
            }
            // añadimos dos saltos de línea
                texto += "\n" + "\n";
        }
        partidosArea.setText(texto);
        actualizarCambios();
    }

    /**
     * Actualiza el texto del label que muestra el nombre del torneo.
     *
     * @param nombre nuevo nombre del torneo.
     */
    public void actualizarNombre(String nombre) {
        nombreL.setText(nombre);
    }

    /**
     * Actualiza el texto del label que muestra la descripción del torneo.
     *
     * @param descripcion nueva descripción del torneo.
     */
    public void actualizarDescripcion(String descripcion) {
        descripcionL.setText(descripcion);
    }

    /**
     * Actualiza el texto del label que muestra las fechas del torneo.
     *
     * @param fechas nuevas fechas o periodo del torneo.
     */
    public void actualizarFechas(String fechas) {
        fechasL.setText(fechas);
    }

    /**
     * Actualiza el texto del label que muestra el deporte del torneo.
     *
     * @param deporte nuevo deporte asociado al torneo.
     */
    public void actualizarDeporte(String deporte) {
        deporteL.setText(deporte);
    }

    /**
     * Actualiza el texto del label que muestra el tipo de competición.
     *
     * @param tipoCompeticion nuevo tipo de competición del torneo.
     */
    public void actualizarTipoCompeticion(String tipoCompeticion) {
        tipoCompeticionL.setText(tipoCompeticion);
    }

    /**
     * Actualiza el texto del label que muestra el ganador del torneo.
     *
     * @param ganador nuevo ganador del torneo.
     */
    public void actualizarGanador(String ganador) {
        ganadorL.setText(ganador);
    }

    /**
     * Actualiza el texto del label que muestra los participantes del torneo.
     *
     * @param participantes nuevo texto descriptivo de los participantes.
     */
    public void actualizarParticipantes(String participantes) {
        participantesL.setText(participantes);
    }

    /**
     * Fuerza la actualización visual del panel invocando {@code revalidate()} y {@code repaint()}.
     * <p>
     * Debe llamarse cada vez que se modifique la composición o los textos mostrados
     * para reflejar los cambios en la interfaz.
     * </p>
     */
    public void actualizarCambios() {
        revalidate();
        repaint();
    }

    /**
     * Devuelve el {@link JLabel} que muestra el nombre del torneo.
     *
     * @return el label del nombre.
     */
    public JLabel getNombreL() {
        return nombreL;
    }

    /**
     * Devuelve el {@link JLabel} que muestra la descripción del torneo.
     *
     * @return el label de la descripción.
     */
    public JLabel getDescripcionL() {
        return descripcionL;
    }

    /**
     * Devuelve el {@link JLabel} que muestra las fechas del torneo.
     *
     * @return el label de las fechas.
     */
    public JLabel getFechasL() {
        return fechasL;
    }

    /**
     * Devuelve el {@link JLabel} que muestra el deporte del torneo.
     *
     * @return el label del deporte.
     */
    public JLabel getDeporteL() {
        return deporteL;
    }

    /**
     * Devuelve el {@link JLabel} que muestra el tipo de competición.
     *
     * @return el label del tipo de competición.
     */
    public JLabel getTipoCompeticionL() {
        return tipoCompeticionL;
    }

    /**
     * Devuelve el {@link JLabel} que muestra el ganador del torneo.
     *
     * @return el label del ganador.
     */
    public JLabel getGanadorL() {
        return ganadorL;
    }

    /**
     * Devuelve el {@link JLabel} que muestra los participantes del torneo.
     *
     * @return el label de participantes.
     */
    public JLabel getParticipantesL() {
        return participantesL;
    }

    /**
     * Obtiene el valor por defecto almacenado para el nombre del torneo.
     *
     * @return el nombre del torneo.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene el valor por defecto almacenado para la descripción del torneo.
     *
     * @return la descripción del torneo.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Obtiene el valor por defecto almacenado para las fechas del torneo.
     *
     * @return las fechas del torneo.
     */
    public String getFechas() {
        return fechas;
    }

    /**
     * Obtiene el valor por defecto almacenado para el deporte del torneo.
     *
     * @return el deporte del torneo.
     */
    public String getDeporte() {
        return deporte;
    }

    /**
     * Obtiene el valor por defecto almacenado para el tipo de competición.
     *
     * @return el tipo de competición.
     */
    public String getTipoCompeticion() {
        return tipoCompeticion;
    }

    /**
     * Obtiene el valor por defecto almacenado para el ganador del torneo.
     *
     * @return el ganador del torneo.
     */
    public String getGanador() {
        return ganador;
    }

    /**
     * Obtiene el valor por defecto almacenado para los participantes del torneo.
     *
     * @return los participantes del torneo.
     */
    public String getParticipantes() {
        return participantes;
    }
}