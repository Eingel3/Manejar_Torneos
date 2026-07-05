package com.gestiontorneos.gui;
import com.gestiontorneos.controller.DeporteController;
import com.gestiontorneos.controller.TorneoController;
import com.gestiontorneos.gui.compartido.*;
import com.gestiontorneos.gui.factory.SubPanel;
import com.gestiontorneos.gui.organizador.PanelCrearTorneo;
import com.gestiontorneos.gui.organizador.PanelMisTorneos;
import com.gestiontorneos.gui.organizador.PanelParticipantes;
import com.gestiontorneos.gui.organizador.PanelPartidos;
import com.gestiontorneos.model.deporte.Deporte;
import com.gestiontorneos.model.deporte.TipoParticipacion;
import com.gestiontorneos.model.torneo.Torneo;
import com.gestiontorneos.model.torneo.formato.EliminacionDirecta;

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
    private PanelFuturosEventos eventos; //Instancia de segmento del panel correspondiente a un panel que contiene la visualizacion de futuros eventos
    private JPanel subPanel;
    private CardLayout cardLayout;

    private TorneoController torneoController;
    private DeporteController deporteController;

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
        //Configuramos el menuLateral
        menuLateral.setBounds(0, 0,
                PanelInformacion.MENULATERAL.getAncho(),
                PanelInformacion.MENULATERAL.getAlto());
        //Ahora respecto al SubPanel
        subPanel.setLayout(cardLayout = new CardLayout());
        subPanel.setBackground(Color.BLACK);
        subPanel.setPreferredSize(new Dimension(
                PanelInformacion.VENTANASINMENU.getAncho() - 15,
                PanelInformacion.VENTANASINMENU.getAlto()));
        //Añadimos los paneles
        addPanels();
        menuLateral.setVisible(true);
        //mostrarPanel("Torneos");
        //mostrarPanel("Clasificacion");
        mostrarPanel("Futuros Eventos");
    }

    /**
     * Metodo que crea todos los paneles que van a ser usados
     *
     */
    private void newPanels(){
        subPanel = new JPanel();
        calendario = new PanelCalendario();
        clasificacion = new PanelClasificacion();
        torneos = new PanelListaTorneos();
        resultados = new PanelResultados();
        crearTorneo = new PanelCrearTorneo();
        torneosOrganizador = new PanelMisTorneos();
        participantesOrganizador = new PanelParticipantes();
        partidosOrganizador = new PanelPartidos();
        menuLateral = new PanelMenu();
        eventos = new PanelFuturosEventos();
    }

    private void addPanels(){
        this.add(menuLateral);//Agregamos el menu lateral

        //Agregamos al SubPanel todos los otros JPanels
        subPanel.add(calendario, "Calendario");
        subPanel.add(clasificacion,  "Clasificacion");
        subPanel.add(torneos, "Torneos");
        subPanel.add(resultados,  "Resultados");
        subPanel.add(crearTorneo,  "Crear Torneo");
        subPanel.add(participantesOrganizador,  "Participantes");
        subPanel.add(partidosOrganizador,  "Partidos");
        subPanel.add(torneosOrganizador,   "Torneos Organizador");
        subPanel.add(eventos,  "Futuros Eventos");

        //Y añadimos el SubPanel
        this.add(subPanel);
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
     * Metodo usado para mostrar una de las pestañas
     * @param id es la pestaña que se desea mostrar
     */
    public void mostrarPanel(String id) {

        cardLayout.show(subPanel, id);

        switch (id) {
            case "Torneos": torneos.setVisible(true);
            break;
            case "Resultados": resultados.setVisible(true);
            break;
            case "Crear Torneo": crearTorneo.setVisible(true);
            break;
            case "Participantes": participantesOrganizador.setVisible(true);
            break;
            case "Partidos": partidosOrganizador.setVisible(true);
            break;
            case "Calendario": calendario.setVisible(true);
            break;
            case "Clasificacion": clasificacion.setVisible(true);
            break;
            case "Torneos Organizador": torneosOrganizador.setVisible(true);
            break;
            case "Futuros Eventos": eventos.setVisible(true);
            break;
        }
    }

    public void setTorneoController(TorneoController torneoController) {
        this.torneoController = torneoController;
    }

    public void setDeporteController(DeporteController deporteController) {
        this.deporteController = deporteController;
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
