package com.gestiontorneos.gui.compartido;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import com.gestiontorneos.gui.factory.BotonFactory;
import com.gestiontorneos.gui.factory.BotonSimple;

public class PanelMenu extends JPanel {
    private JButton calendario;
    private JButton torneos;
    private JButton clasificaciones;
    private JButton partidos;
    private JButton inicio;
    private JButton futurosEventos;

        public PanelMenu() {
            this.setBackground(Color.BLACK); //Color de fondo del panel
            this.setPreferredSize(new Dimension(
                    PanelInformacion.MENULATERAL.getAncho(),
                    PanelInformacion.MENULATERAL.getAlto())); //Un poquit0 ancho + el largo de la VentanaPrincipal

            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));//Usamos BoxLayout de forma vertical para colocar los botones del menu lateral
            this.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));// Le ponemos un borde más claro para que se diferencie el mennú

            crearBotones();

        }

        private void crearBotones() {
            BotonFactory crearBoton = new BotonSimple();

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
            add(Box.createRigidArea(new Dimension(0, 30)));
            futurosEventos = crearBoton.crear("Futuros Eventos");
            this.add(futurosEventos);
        }

        public void agregarListener(String id, ActionListener evento) {
            switch  (id) {
                case "Inicio":
                    inicio.addActionListener(evento);
                    break;
               case "Calendario":
                    calendario.addActionListener(evento);
                    break;
               case "Torneos":
                     torneos.addActionListener(evento);
                     break;
               case "Clasificaciones":
                      clasificaciones.addActionListener(evento);
                      break;
               case "Partidos":
                       partidos.addActionListener(evento);
                       break;
               case "Futuros Eventos":
                   futurosEventos.addActionListener(evento);
                   break;
                default:
                    throw new IllegalArgumentException("Botón desconocido: " + id);
            }

        }
    }
