package com.gestiontorneos.gui.compartido;
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

        agregarTorneoGUI("Torneo 1", "01/07 - 10/07", "Futbol");
        agregarTorneoGUI("Torneo 2", "05/07 - 15/07", "Basketball");
        agregarTorneoGUI("Torneo 3", "10/07 - 20/07", "Tenis");
    }



    public void actualizarLista(List<Torneo> torneos) {
        this.removeAll();  // limpiar tarjetas viejas
        for (Torneo t : torneos) {
            agregarTorneoGUI(
                    t.getNombre(),
                    t.getFechaInicio() + " - " + t.getFechaFin(),
                    t.getDeporte().getNombre()
            );
        }
        this.revalidate();
        this.repaint();
    }

    /**
     * Agrega torneos de prueba al panel.
     * <p>
     * Este método está pensado como implementación temporal mientras se conecta
     * la vista con datos reales provenientes del modelo o controlador.
     * </p>
     */
    private void gestionarTorneosGUI() {
        //ciclo for para agregar todos los torneos
        for (int i = 0; i < cantidadTorneos; i++) {
            JButton detallesX = this.agregarTorneoGUI("Torneo " + (i + 1), "a", "b"); //agregamos los torneos
        }
    }

    /**
     * Crea una tarjeta visual para un torneo y la agrega al panel.
     *
     * @param torneo nombre del torneo.
     * @param fecha fecha o periodo asociado al torneo.
     * @param otroDato información adicional que se desea mostrar.
     * @return botón de detalles asociado al torneo creado.
     */
    public JButton agregarTorneoGUI(String torneo, String fecha, String otroDato){

        PanelTarjeta creadorTarjeta = new PanelTarjeta();
        JPanel torneoGUI = creadorTarjeta.crear(500, 150); //Aquí es donde dejaremos todos los datos
        JButton detalles = new JButton("Detalles");
        detalles.setPreferredSize(new Dimension(100, 50));
        torneoGUI.setLayout(new FlowLayout(FlowLayout.LEFT));//layout tipo Flow que agrega cada componente de izquierda a derecha

        //Creamos los distintos labels para cada informacion que mostremos
        JLabel lblNombre = new JLabel(torneo);
        JLabel lblFecha = new JLabel(fecha);
        JLabel lblOtroDato = new JLabel(otroDato);

        //Ahora le asignamos una fuente y el tamaño a los labels
        lblNombre.setFont(new Font("Dialog", Font.BOLD, 18)); //este es mas grando
        lblFecha.setFont(new Font("Dialog", Font.BOLD, 14));
        lblOtroDato.setFont(new Font("Dialog", Font.BOLD, 14));

        //Y agregamos los labels a torneoGUI
        torneoGUI.add(lblNombre);
        torneoGUI.add(lblFecha);
        torneoGUI.add(lblOtroDato);

        //Y agregamos el JButton detalles
        torneoGUI.add(detalles);

        this.add(torneoGUI); //añadimos al JPanel de PanelListaTorneos
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
}