package com.gestiontorneos.gui.compartido;
import javax.swing.*;
import java.awt.*;

public class PanelMenu extends JPanel {
    public  PanelMenu() {
        this.setBackground(Color.BLACK); //Color de fondo del panel
        this.setPreferredSize(new Dimension(50, 800)); //Un poquit0 ancho + el largo de la VentanaPrincipal
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));//Usamos BoxLayout de forma vertical para colocar los botones del menu lateral
        this.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));// Le ponemos un borde más claro para que se diferencie el mennú
    }

    /**
     *
     * Metodo que se encarga de crear los botones que contiene el menu.
     * Se utiliza para crear los botones uno a uno
     *
     * @return boton retorna un JButton con ciertos tamaños
     */
    private JButton crearBotonMenu(String nombre){
    JButton boton = new JButton(nombre); //le damos su nombre
    boton.setBackground(Color.PINK); //Le colocamos color rosa
    boton.setAlignmentX(Component.CENTER_ALIGNMENT); //Le alineamos en el centro del menu
    boton.setPreferredSize(new Dimension(40, 50)); //Le dejamos como un rectangulo un poquito mas chico que el ancho del menu
    boton.setBorder(BorderFactory.createLineBorder(Color.MAGENTA));//Le añadimos un borde color magenta
    return boton; //Y ya que hemos configurado el boton, podemos devolverlo
    }
}
