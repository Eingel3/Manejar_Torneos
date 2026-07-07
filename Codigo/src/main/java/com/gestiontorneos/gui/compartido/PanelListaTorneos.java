package com.gestiontorneos.gui.compartido;
import com.gestiontorneos.gui.factory.BotonSimple;
import com.gestiontorneos.gui.factory.PanelTarjeta;
import com.gestiontorneos.model.torneo.Torneo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Panel gráfico encargado de mostrar una lista de torneos disponibles.
 * <p>
 * Cada torneo se representa mediante una tarjeta visual con información básica
 * y un botón de detalles. El panel está preparado para que un controlador pueda
 * registrar eventos sobre dichos botones.
 * </p>
 *
 * @see JPanel
 * @see JButton
 * @see ActionListener
 */
public class PanelListaTorneos extends JPanel {

    private int cantidadTorneos;
    private ActionListener evento;
    private JPanel panelReciente;

    /**
     * Crea e inicializa el panel de lista de torneos.
     * <p>
     * Configura el layout, color de fondo, dimensiones y agrega datos de prueba
     * para representar torneos en pantalla.
     * </p>
     */
    public PanelListaTorneos() {
        //Ajustes del JPanel
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); //definimos el layout
        setBackground(Color.lightGray); //definimos el color de fondo
        setPreferredSize(new Dimension(
                PanelInformacion.VENTANASINMENU.getAncho(),
                PanelInformacion.VENTANASINMENU.getAlto()));//Y las dimensiones
        this.add(Box.createRigidArea(new Dimension(0, 10))); //Agregamos un espacio arriba

        this.add(new JLabel("¡Hola!, por ahora no hay torneos."));
        this.add(Box.createRigidArea(new Dimension(0, 20)));
        this.add(new JLabel("Sin embargo, aquí hay un ejemplo de un torneo."));
        this.add(Box.createRigidArea(new Dimension(0, 20)));
        agregarTorneoGUI("Nombre del torneo", "2001-02-02", "2002-06-06", "Dato/Descripcion del torneo");
    }



    public void actualizarLista(List<Torneo> torneos) {
        this.removeAll();  // limpiar tarjetas viejas
        //Verificamos si la lista de torneos es null o está vacía, en cuyo caso, ya que no hay torneos creamos el mensaje de bienvenida, si no, creamos la lista de torneos de forma normal
        if (torneos == null || torneos.isEmpty()) {
            // Volver a poner el contenido de bienvenida como en el constructor
            this.add(new JLabel("¡Hola!, por ahora no hay torneos."));
            this.add(Box.createRigidArea(new Dimension(0, 20)));
            this.add(new JLabel("Sin embargo, aquí hay un ejemplo de un torneo."));
            this.add(Box.createRigidArea(new Dimension(0, 20)));
            agregarTorneoGUI("Nombre del torneo", "2001-02-02", "2002-06-06", "Dato/Descripcion del torneo");
        } else {
            for (Torneo t : torneos) {
                agregarTorneoGUI(
                        t.getNombre(),
                        " " + t.getFechaInicio(), " " + t.getFechaFin(),
                        t.getDeporte().getNombre()
                );
            }
        }
        this.revalidate();
        this.repaint();
    }

    /**
     * Crea una tarjeta visual para un torneo y la agrega al panel.
     *
     * @param torneo nombre del torneo.
     * @param fechaInicio fecha de inicio del torneo.
     * @param fechaFin fecha de termino del torneo.
     * @param otroDato información adicional que se desea mostrar.
     * @return botón de detalles asociado al torneo creado.
     */
    public JButton agregarTorneoGUI(String torneo, String fechaInicio, String fechaFin, String otroDato){

        PanelTarjeta creadorTarjeta = new PanelTarjeta();
        JPanel torneoGUI = creadorTarjeta.crear(500, 150); //Aquí es donde dejaremos todos los datos

        //Creamos el boton de detalles
        BotonSimple creadorBoton = new BotonSimple();
        JButton detalles = creadorBoton.crear("Detalles");

        //Creamos los distintos labels para cada informacion que mostremos
        JLabel lblNombre = new JLabel(torneo);
        JLabel lblFechaInicio = new JLabel("                            " + fechaInicio);
        JLabel lblFechaIniciomsj = new JLabel("Fecha de inicio del torneo: ");
        JLabel lblFechaFin = new JLabel("                            " + fechaFin);
        JLabel lblFechaFinmsj = new JLabel("Fecha de fin del torneo: ");
        JLabel lblOtroDato = new JLabel(otroDato);

        //Creamos un JPanel para las fechas
        JPanel fechas = creadorTarjeta.crear(200, 140);
        fechas.setLayout(new BoxLayout(fechas, BoxLayout.Y_AXIS));
        fechas.setBorder(null);

        //Ahora le asignamos una fuente y el tamaño a los labels
        lblNombre.setFont(new Font("Dialog", Font.BOLD, 24)); //este es mas grando
        lblFechaInicio.setFont(new Font("Dialog", Font.BOLD, 14));
        lblFechaFin.setFont(new Font("Dialog", Font.BOLD, 14));
        lblOtroDato.setFont(new Font("Dialog", Font.PLAIN, 14));
        lblFechaIniciomsj.setFont(new Font("Dialog", Font.PLAIN, 16));
        lblFechaFinmsj.setFont(new Font("Dialog", Font.PLAIN, 16));

        //Agregamos las fechas
        fechas.add(Box.createRigidArea(new Dimension(0, 30)));
        fechas.add(lblFechaIniciomsj);
        fechas.add(Box.createRigidArea(new Dimension(0, 10))); //Luego, agregamos un espacio entre este label y el siguiente
        fechas.add(lblFechaInicio);
        fechas.add(Box.createRigidArea(new Dimension(0, 10)));
        fechas.add(lblFechaFinmsj);
        fechas.add(Box.createRigidArea(new Dimension(0, 10)));
        fechas.add(lblFechaFin);

        //Y agregamos los labels a torneoGUI
        torneoGUI.add(lblNombre);
        torneoGUI.add(Box.createRigidArea(new Dimension(80, 0))); //Luego, agregamos un espacio entre este label y el siguiente
        torneoGUI.add(fechas);
        torneoGUI.add(Box.createRigidArea(new Dimension(80, 0))); //Luego, agregamos un espacio entre las fechas y el siguiente label
        torneoGUI.add(lblOtroDato);
        torneoGUI.add(Box.createRigidArea(new Dimension(100, 0))); //Luego, agregamos un espacio entre este label y el boton de detalles

        // Guardamos la referencia al panel más reciente antes de añadir el boton de detalles
        this.panelReciente = torneoGUI;
        //Identificar el boton con el nombre del torneo
        detalles.setActionCommand(torneo);
        // Agregar el listener externo si existe
        if (evento != null) {
            detalles.addActionListener(evento);
        }
        //Y agregamos el JButton detalles
        torneoGUI.add(detalles);

        this.add(torneoGUI); //añadimos al JPanel de PanelListaTorneos
        this.add(Box.createRigidArea(new Dimension(0, 40))); //Luego, agregamos un espacio entre este torneoGUI y el siguiente

        this.revalidate();
        this.repaint();

        return detalles; //Y finalmente retornamos el torneoGUI
    }

    /**
     * Permite registrar un {@link ActionListener} para manejar los eventos de
     * interacción con los botones de detalles.
     *
     * @param evento listener que será utilizado por el controlador.
     */
    public void agregarListener(ActionListener evento) {
        this.evento = evento;
    }

    public JPanel getTorneoReciente(){
        return this.panelReciente;
    }
}