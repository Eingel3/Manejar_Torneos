package com.gestiontorneos.gui.factory;

import javax.swing.*;
import java.awt.*;

 public class BotonSimple implements BotonFactory {
     /**
      * Metodo que se encarga de crear los botones genericos
      *  Se utiliza para crear los botones uno a uno
      * @param nombre es un String con el texto que se mostrara en el boton
      * @return boton retorna un JButton con ciertos tamaños
      */
    @Override
    public JButton crear(String nombre) {
        JButton boton = new JButton(nombre); //le damos su nombre
        boton.setBackground(Color.PINK); //Le colocamos color rosa
        boton.setAlignmentX(Component.CENTER_ALIGNMENT); //Le alineamos en el centro del menu
        boton.setPreferredSize(new Dimension(com.gestiontorneos.gui.compartido.PanelInformacion.MENULATERAL.getAncho() - 10, 80)); //Le dejamos como un rectangulo un poquito mas chico que el ancho del menu
        boton.setBorder(BorderFactory.createLineBorder(Color.MAGENTA));//Le añadimos un borde color magenta
        return boton; //Y ya que hemos configurado el boton, podemos devolverlo

    }
}
