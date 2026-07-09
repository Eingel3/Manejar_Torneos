package com.gestiontorneos.gui.compartido;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import com.gestiontorneos.gui.factory.BotonFactory;
import com.gestiontorneos.gui.factory.BotonSimple;

/**
 * Panel gráfico que representa el menú lateral de navegación de la aplicación.
 * <p>
 * Contiene botones para acceder a las distintas secciones del sistema, como
 * inicio, calendario, torneos, clasificaciones, partidos, futuros eventos y
 * creación de torneos.
 * </p>
 *
 * @see JPanel
 * @see JButton
 * @see ActionListener
 */
public class PanelMenu extends JPanel {

    private JButton torneos;
    private JButton clasificaciones;
    private JButton inicio;
    private JButton futurosEventos;
    private JButton crearTorneo;
    private JButton crearParticipante;
    private JButton registrarResultado;

    /**
     * Crea e inicializa el menú lateral.
     * <p>
     * Configura el fondo, dimensiones, layout vertical y borde del panel.
     * Posteriormente crea los botones de navegación.
     * </p>
     */
    public PanelMenu() {
        this.setBackground(Color.BLACK); //Color de fondo del panel
        this.setPreferredSize(new Dimension(
                PanelInformacion.MENULATERAL.getAncho(),
                PanelInformacion.MENULATERAL.getAlto())); //Un poquit0 ancho + el largo de la VentanaPrincipal

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); //Usamos BoxLayout de forma vertical para colocar los botones del menu lateral
        this.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY)); // Le ponemos un borde más claro para que se diferencie el mennú

        crearBotones();
    }

    /**
     * Crea los botones del menú lateral y los agrega al panel.
     * <p>
     * Utiliza una fábrica de botones para mantener una apariencia uniforme en
     * todos los elementos de navegación.
     * </p>
     */
    private void crearBotones() {
        BotonFactory crearBoton = new BotonSimple();

        inicio = crearBoton.crear("Inicio"); //creamos un boton para la pestaña de inicio
        this.add(inicio); //y lo agregamos al panel
        add(Box.createRigidArea(new Dimension(0, 30))); //Luego, agregamos un espacio entre este boton y el siguiente

        /*
         * Repetimos el proceso de arriba con todos los otros botones, creamos el
         * boton, lo agregamos al panel, agregamos un espaciado y creamos el
         * siguiente boton.
         */

        torneos = crearBoton.crear("Torneos");
        this.add(torneos);
        add(Box.createRigidArea(new Dimension(0, 30)));

        clasificaciones = crearBoton.crear("Clasificaciones");
        this.add(clasificaciones);
        add(Box.createRigidArea(new Dimension(0, 30)));

        futurosEventos = crearBoton.crear("Futuros eventos");
        this.add(futurosEventos);
        add(Box.createRigidArea(new Dimension(0, 30)));

        crearTorneo = crearBoton.crear("Crear torneo");
        this.add(crearTorneo);
        add(Box.createRigidArea(new Dimension(0, 30)));

        crearParticipante = crearBoton.crear("Crear participante");
        this.add(crearParticipante);
        add(Box.createRigidArea(new Dimension(0, 30)));

        registrarResultado = crearBoton.crear("Registrar resultado");
        this.add(registrarResultado);
        add(Box.createRigidArea(new Dimension(0, 30)));
    }

    /**
     * Registra un {@link ActionListener} en el botón identificado por el texto
     * recibido.
     *
     * @param id identificador del botón al que se desea asociar el evento.
     * @param evento listener que manejará la acción del botón.
     * @throws IllegalArgumentException si el identificador no corresponde a ningún botón conocido.
     */
    public void agregarListener(String id, ActionListener evento) {
        switch (id) {
            case "Inicio":
                inicio.addActionListener(evento);
                break;
            case "Torneos":
                torneos.addActionListener(evento);
                break;
            case "Clasificaciones":
                clasificaciones.addActionListener(evento);
                break;
            case "Futuros Eventos":
                futurosEventos.addActionListener(evento);
                break;
            case "Crear Torneo":
                crearTorneo.addActionListener(evento);
                break;
            case "Crear Participante":
                crearParticipante.addActionListener(evento);
                break;
            case "Registrar Resultado":
                registrarResultado.addActionListener(evento);
                break;
            default:
                throw new IllegalArgumentException("Botón desconocido: " + id);
        }
    }
}