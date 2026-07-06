package com.gestiontorneos.gui.compartido;
import com.gestiontorneos.model.torneo.Torneo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Representa la vista grafica de los torneos
 *
 * Este panel dibuja una lista con todos los torneos. Al ser clickeados se pueden ver los detalles
 */

public class PanelListaTorneos extends JPanel {
    private int cantidadTorneos;
    private ActionListener evento;
    public PanelListaTorneos(){
        //Ajustes del JPanel
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); //definimos el layout
        setBackground(Color.lightGray); //definimos el color de fondo
        setPreferredSize(new Dimension(
                PanelInformacion.VENTANASINMENU.getAncho(),
                PanelInformacion.VENTANASINMENU.getAlto()));//Y las dimensiones
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


    private void gestionarTorneosGUI(){ //esta funcion es de prueba por ahora
        //ciclo for para agregar todos los torneos
        for(int i = 0; i < cantidadTorneos; i++){
            JButton detallesX = this.agregarTorneoGUI("Torneo " + (i + 1), "a", "b"); //agregamos los torneos
        }
    }
    public JButton agregarTorneoGUI(String torneo, String fecha, String otroDato){


        JPanel torneoGUI = new JPanel(); //Aquí es donde dejaremos todos los datos
        JButton detalles = new JButton("Detalles");
        detalles.setPreferredSize(new Dimension(100, 50));
        torneoGUI.setLayout(new FlowLayout(FlowLayout.LEFT));//layout tipo Flow que agrega cada componente de izquierda a derecha
        torneoGUI.setBorder(BorderFactory.createLineBorder(Color.GRAY));//le damos un borde
        torneoGUI.setBackground(Color.PINK); //y un color de fondo
        torneoGUI.setPreferredSize(new Dimension(500, 150));//Y su tamaño
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
        this.add(torneoGUI);//añadimos al JPanel de PanelListaTorneos
        this.revalidate();
        this.repaint();
        return detalles; //Y finalmente retornamos el torneoGUI
    }


    /**
     * Permite al controlador registrar un listener para los clics en "Detalles".
     */
    public void agregarListener(ActionListener evento) {
        this.evento = evento;
    }

}