package com.gestiontorneos.gui.compartido;
import javax.swing.*;
import java.awt.*;

/**
 * Representa la vista grafica de los torneos
 *
 * Este panel dibuja una lista con todos los torneos. Al ser clickeados se pueden ver los detalles
 */

public class PanelListaTorneos extends JPanel {
    public PanelListaTorneos(){

    }
    private JPanel agregarTorneoGUI(String torneo){ //Eventualmente en vez de String torneo debería de ser tipo Torneo
        //Por ahora hay muchas cosas que no se incluirán debido al hecho de que model todavía no está listo
        //Por ejemplo, aquí deberíamos de utilizar los metodos de Torneo para obtener el nombre y otros detalles
        //Por ahora solo utilizaremos String para todas las variables
        String nombre = "Torneo x";
        String fecha = "Desde xx/xx/xxxx hasta xx/xx/xxxx";
        String otroDato = "Otro dato";
        JPanel torneoGUI = new JPanel(); //Aquí es donde dejaremos todos los datos
        JButton detalles = new JButton("Detalles");
        detalles.setPreferredSize(new Dimension(100, 50));
        torneoGUI.setLayout(new BoxLayout(torneoGUI, BoxLayout.Y_AXIS)); //layout tipo cajas como el usado en PanelMenu
        torneoGUI.setBorder(BorderFactory.createLineBorder(Color.GRAY));//le damos un borde
        torneoGUI.setBackground(Color.PINK); //y un color de fondo
        torneoGUI.setPreferredSize(new Dimension(500, 150));//Y su tamaño
        

    }

}