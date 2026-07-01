package com.gestiontorneos.gui.compartido;
import javax.swing.*;
import java.awt.*;
import com.gestiontorneos.gui.compartido.PanelInformacion;
import com.gestiontorneos.gui.factory.Boton;
import com.gestiontorneos.gui.factory.BotonSimple;

public class PanelMenu extends JPanel {
    JButton calendario;
    JButton torneos;
    JButton clasificaciones;
    JButton partidos;
    JButton inicio;

    public  PanelMenu() {
        this.setBackground(Color.BLACK); //Color de fondo del panel
        this.setPreferredSize(new Dimension(
                PanelInformacion.MENULATERAL.getAncho(),
                PanelInformacion.MENULATERAL.getAlto())); //Un poquit0 ancho + el largo de la VentanaPrincipal

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));//Usamos BoxLayout de forma vertical para colocar los botones del menu lateral
        this.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));// Le ponemos un borde más claro para que se diferencie el mennú

        Boton crearBoton = new BotonSimple();

        inicio = crearBoton.crear("Inicio"); //creamos un boton para la pestaña de inicio
        this.add(inicio); //y lo agregamos al panel
        add(Box.createRigidArea(new Dimension(0, 30))); //Luego, agregamos un espacio entre este boton y el siguiente

        /**
         * Repetimos el proceso de arriba con todos los otros botones, creamos el boton, lo agrgamos al panel, agregamos un espaciado y creamos el siguiente boton
         */
        calendario = crearBoton.crear("Calendario");
        this.add(calendario);
        add(Box.createRigidArea(new Dimension(0, 30)));
        torneos = crearBoton.crear("Torneos");
        this.add(torneos);
        add(Box.createRigidArea(new Dimension(0, 30)));
        clasificaciones = crearBoton.crear("Clasificaciones");
        this.add(clasificaciones);
        add(Box.createRigidArea(new Dimension(0, 30)));
        partidos = crearBoton.crear("Partidos");
        this.add(partidos);
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
    boton.setPreferredSize(new Dimension(PanelInformacion.MENULATERAL.getAncho(), 80)); //Le dejamos como un rectangulo un poquito mas chico que el ancho del menu
    boton.setBorder(BorderFactory.createLineBorder(Color.MAGENTA));//Le añadimos un borde color magenta
    return boton; //Y ya que hemos configurado el boton, podemos devolverlo
    }
}
