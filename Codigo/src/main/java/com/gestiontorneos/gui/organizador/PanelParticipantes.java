package com.gestiontorneos.gui.organizador;
import com.gestiontorneos.gui.compartido.PanelInformacion;
import com.gestiontorneos.gui.factory.PanelTarjeta;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Panel gráfico encargado de mostrar y gestionar participantes.
 * <p>
 * Esta vista está orientada al organizador y permitirá visualizar, agregar,
 * editar o eliminar participantes asociados a un torneo.
 * </p>
 *
 * @see JPanel
 */
public class PanelParticipantes extends JPanel {

    public PanelParticipantes() {
        //Ajustes del JPanel
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); //definimos el layout
        setBackground(Color.lightGray); //definimos el color de fondo
        setPreferredSize(new Dimension(
                PanelInformacion.VENTANASINMENU.getAncho(),
                PanelInformacion.VENTANASINMENU.getAlto()));//Y las dimensiones
    }

        public void agregarParticipanteGUI(String nombre, String enQueParticipa, String esUnEquipoOUnIndividuo){

            PanelTarjeta creadorTarjeta = new PanelTarjeta();
            JPanel participanteGUI = creadorTarjeta.crear(500, 150); //Aquí es donde dejaremos todos los datos
            //Creamos los distintos labels para cada informacion que mostremos
            JLabel lblNombre = new JLabel(nombre);
            JLabel lblParticipa = new JLabel(enQueParticipa);
            JLabel lblQueEs = new JLabel(esUnEquipoOUnIndividuo);
            //Ahora le asignamos una fuente y el tamaño a los labels
            lblNombre.setFont(new Font("Dialog", Font.BOLD, 18)); //este es mas grando
            lblParticipa.setFont(new Font("Dialog", Font.BOLD, 14));
            lblQueEs.setFont(new Font("Dialog", Font.BOLD, 14));
            //Y agregamos los labels a torneoGUI
            participanteGUI.add(lblNombre);
            participanteGUI.add(lblParticipa);
            participanteGUI.add(lblQueEs);

            this.add(participanteGUI);//añadimos al JPanel de PanelListaTorneos
            this.revalidate();
            this.repaint();
        }

}
