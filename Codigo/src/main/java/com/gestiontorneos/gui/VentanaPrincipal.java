package com.gestiontorneos.gui;
import com.gestiontorneos.gui.compartido.*;
import com.gestiontorneos.gui.organizador.PanelCrearTorneo;
import com.gestiontorneos.gui.organizador.PanelMisTorneos;
import com.gestiontorneos.gui.organizador.PanelParticipantes;
import com.gestiontorneos.gui.organizador.PanelPartidos;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * Panel principal de la interfaz gráfica de la app que maneja torneos.
 * <p>
 * Esta clase extiende {@link JPanel} e implementa {@link MouseListener} para
 * capturar los clicks realizados por el usuario sobre la interfaz.
 * </p>
 * <p>
 * El panel principal contiene y coordina dos subpaneles:
 * </p>
 */
public class VentanaPrincipal extends JPanel implements MouseListener {

    //Paneles compartidos entre todos los usuarios
    private PanelCalendario calendario; //Instancia de segmento del panel correspondiente al calendario
    private PanelClasificacion clasificacion; //Instancia de segmento del panel correspondiente a la clasificacion
    private PanelListaTorneos torneos; //Instancia de segmento del panel correspondiente a la lista de los torneos
    private PanelResultados resultados; //Instancia de segmento del panel correspondiente a los resultados
    //Paneles unicamente para usuarios tipo organizador
    private PanelCrearTorneo crearTorneo; //Instancia de segmento del panel correspondiente a la opcion de crear torneos
    private PanelMisTorneos torneosOrganizador; ////Instancia de segmento del panel correspondiente a los torneos del organizador
    private PanelParticipantes participantesOrganizador; //Instancia de segmento del panel correspondiente a los participantes para que el organizador los pueda editar
    private PanelPartidos partidosOrganizador; //Instancia de segmento del panel correspondiente a los partidos para que el organizador pueda editarlos
    private PanelMenu menuLateral; //Instancia de segmento del panel correspondiente al menu lateral que contiene los botones que dirigen a cada panel

    /**
     * Crea e inicializa la ventana principal de la aplicación.
     * <p>
     * Configura las dimensiones de la ventana, el color de fondo y registra el
     * listener de mouse. Además, crea una instancia compartida del modelo
     * </p>
     */
    public VentanaPrincipal() {
        this.setLayout(new FlowLayout(FlowLayout.LEFT)); //Utilizamos FlowLayout que deja los componentes desde la izquierda a la derecha
        this.setPreferredSize(new Dimension(
                PanelInformacion.VENTANAPRINCIPAL.getAncho(),
                PanelInformacion.VENTANAPRINCIPAL.getAlto())); //Dimensiones del panel
        this.setBackground(Color.BLACK); //Color de fondo del panel
        this.addMouseListener(this); //Listener del panel
        newPanels(); //Inicializamos los paneles
        this.add(menuLateral);//Agregamos el menu lateral
        menuLateral.setVisible(true);
        menuLateral.setBounds(0, 0,
                PanelInformacion.MENULATERAL.getAncho(),
                PanelInformacion.MENULATERAL.getAlto());
        this.add(torneos);
        torneos.setVisible(true);
    }

    /**
     * Metodo que crea todos los paneles que van a ser usados
     *
     */
    private void newPanels(){
        calendario = new PanelCalendario();
        clasificacion = new PanelClasificacion();
        torneos = new PanelListaTorneos();
        // resultados = new PanelResultados(); //por ahora no lo iniciamos
        crearTorneo = new PanelCrearTorneo();
        torneosOrganizador = new PanelMisTorneos();
        participantesOrganizador = new PanelParticipantes();
        partidosOrganizador = new PanelPartidos();
        menuLateral = new PanelMenu();
    }

    /**
     * Metodo usado exteriormente para ver la ventana.
     * Crea un nuevo Jframe y agrega la VentanaPrincipal en el
     */
    public void mostrar() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this); //Agregamos la VentanaPrincipal
        frame.pack(); //Esto es para que se ajuste de forma automática
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setTitle("Torneos"); //el título
    }

    /**
     * Dibuja los componentes gráficos del panel principal.
     * <p>
     * limpia el panel mediante la implementación de {@link JPanel}
     * </p>
     *
     * @param g contexto gráfico utilizado para dibujar sobre el panel.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g); //Limpia la pantalla y pinta el fondo blanco
    }

    /**
     * Maneja el evento de click del mouse sobre el panel principal.
     * <p>
     * Obtiene las coordenadas del click y las delega a los subpaneles para que
     * cada uno determine si debe reaccionar al evento. Finalmente, repinta el
     * panel para reflejar posibles cambios visuales.
     * </p>
     *
     * @param e evento de mouse que contiene la posición del click.
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        int mouseX = e.getX(); //Obtenemos coordenadas del mouse
        int mouseY = e.getY();

        //aqui hay que llamar a los controladores para manejar los eventos
        
        repaint();
    }

    /**
     * Método requerido por {@link MouseListener}.
     * <p>
     * No realiza ninguna acción cuando se presiona el botón del mouse.
     * </p>
     *
     * @param e evento de mouse recibido.
     */
    @Override
    public void mousePressed(MouseEvent e) {}

    /**
     * Método requerido por {@link MouseListener}.
     * <p>
     * No realiza ninguna acción cuando se suelta el botón del mouse.
     * </p>
     *
     * @param e evento de mouse recibido.
     */
    @Override
    public void mouseReleased(MouseEvent e) {}

    /**
     * Método requerido por {@link MouseListener}.
     * <p>
     * No realiza ninguna acción cuando el cursor entra al panel.
     * </p>
     *
     * @param e evento de mouse recibido.
     */
    @Override
    public void mouseEntered(MouseEvent e) {}

    /**
     * Método requerido por {@link MouseListener}.
     * <p>
     * No realiza ninguna acción cuando el cursor sale del panel.
     * </p>
     *
     * @param e evento de mouse recibido.
     */
    @Override
    public void mouseExited(MouseEvent e) {}
}
