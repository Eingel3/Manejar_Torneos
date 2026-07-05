package com.gestiontorneos.gui.compartido;
import javax.swing.*;
import java.awt.*;

/**
 * Representa la vista grafica de los torneos
 *
 * Este panel dibuja una lista con todos los torneos. Al ser clickeados se pueden ver los detalles
 */

public class PanelListaTorneos extends JPanel {
    private int cantidadTorneos;
    public PanelListaTorneos(){
        //Ajustes del JPanel
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); //definimos el layout
        setBackground(Color.lightGray); //definimos el color de fondo
        setPreferredSize(new Dimension(
                PanelInformacion.VENTANASINMENU.getAncho(),
                PanelInformacion.VENTANASINMENU.getAlto()));//Y las dimensiones
        cantidadTorneos = 3; //A futuro hay que reemplazar con una llamada a metodo obtenerCantidadTorneos();

        JPanel torneo1 = this.agregarTorneoGUI("Torneo 1"); //agregamos una tarjeta de torneo
        this.gestionarTorneosGUI();//llamamos a la funcion que gestiona la agregacion de torneos
    }
    private void gestionarTorneosGUI(){ //esta funcion es de prueba por ahora
        //ciclo for para agregar todos los torneos
        for(int i = 0; i < cantidadTorneos; i++){
            JPanel torneoX = this.agregarTorneoGUI("Torneo " + (i + 1)); //agregamos los torneos
        }
    }
    private JPanel agregarTorneoGUI(String torneo){ //Eventualmente en vez de String torneo debería de ser tipo Torneo
        //Por ahora hay muchas cosas que no se incluirán debido al hecho de que model todavía no está listo
        //Por ejemplo, aquí deberíamos de utilizar los metodos de Torneo para obtener el nombre y otros detalles
        //Por ahora solo utilizaremos String para todas las variables
        String nombre = torneo;
        String fecha = "Desde xx/xx/xxxx hasta xx/xx/xxxx";
        String otroDato = "Otro dato";
        JPanel torneoGUI = new JPanel(); //Aquí es donde dejaremos todos los datos
        JButton detalles = new JButton("Detalles");
        detalles.setPreferredSize(new Dimension(100, 50));
        torneoGUI.setLayout(new BoxLayout(torneoGUI, BoxLayout.Y_AXIS)); //layout tipo cajas como el usado en PanelMenu
        torneoGUI.setBorder(BorderFactory.createLineBorder(Color.GRAY));//le damos un borde
        torneoGUI.setBackground(Color.PINK); //y un color de fondo
        torneoGUI.setPreferredSize(new Dimension(500, 150));//Y su tamaño
        //Creamos los distintos labels para cada informacion que mostremos
        JLabel lblNombre = new JLabel(nombre);
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
        return torneoGUI; //Y finalmente retornamos el torneoGUI
    }

}