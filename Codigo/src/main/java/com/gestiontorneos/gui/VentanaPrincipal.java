package com.gestiontorneos.gui;
import com.gestiontorneos.controller.DeporteController;
import com.gestiontorneos.controller.PanelMenuController;
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
 * Panel principal de la interfaz gráfica de la aplicación de gestión de torneos.
 * <p>
 * Esta clase coordina el menú lateral y las diferentes vistas internas de la
 * aplicación mediante un {@link CardLayout}. Permite alternar entre secciones
 * como calendario, clasificación, torneos, resultados, creación de torneos,
 * participantes, partidos y futuros eventos.
 * </p>
 * <p>
 * También implementa {@link MouseListener}, dejando preparados los métodos
 * necesarios para gestionar eventos del mouse sobre el panel principal.
 * </p>
 *
 * @see JPanel
 * @see MouseListener
 * @see CardLayout
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
     * Configura el layout, tamaño, color de fondo y listener de mouse. También
     * instancia los paneles principales, configura el contenedor con
     * {@link CardLayout} y registra el controlador del menú lateral.
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

        //menuLateral.setVisible(true);
        //mostrarPanel("Torneos");
        //mostrarPanel("Clasificacion");
        //mostrarPanel("Futuros Eventos");
        //mostrarPanel("Crear Torneo");

        new PanelMenuController(menuLateral, this);
    }

    /**
     * Crea las instancias de todos los paneles utilizados por la ventana
     * principal.
     * <p>
     * Incluye paneles compartidos y paneles específicos para usuarios
     * organizadores.
     * </p>
     */
    private void newPanels() {
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

    /**
     * Agrega el menú lateral y los paneles internos al contenedor principal.
     * <p>
     * Los paneles internos se registran dentro del {@link CardLayout} usando un
     * identificador de texto.
     * </p>
     */
    private void addPanels() {
        this.add(menuLateral); //Agregamos el menu lateral

        //Agregamos al SubPanel todos los otros JPanels
        subPanel.add(calendario, "Calendario");
        subPanel.add(clasificacion, "Clasificacion");
        subPanel.add(torneos, "Torneos");
        subPanel.add(resultados,  "Resultados");
        subPanel.add(crearTorneo,  "Crear Torneo");
        subPanel.add(participantesOrganizador,  "Participantes");
        subPanel.add(partidosOrganizador,  "Partidos");
        subPanel.add(torneosOrganizador,   "Torneos Organizador");
        subPanel.add(eventos,  "Futuros Eventos");
        subPanel.add(resultados, "Inicio");

        //Y añadimos el SubPanel
        this.add(subPanel);
    }

    /**
     * Muestra la interfaz gráfica dentro de un {@link JFrame}.
     * <p>
     * Configura la operación de cierre, agrega este panel a la ventana, ajusta
     * su tamaño, centra la ventana y la hace visible.
     * </p>
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
     * Muestra uno de los paneles internos registrados en el {@link CardLayout}.
     *
     * @param id identificador del panel que se desea mostrar.
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
            case "Inicio": resultados.setVisible(true);
                break;
        }
    }

    /**
     * Asigna el controlador de torneos utilizado por la ventana principal.
     *
     * @param torneoController controlador de torneos.
     */
    public void setTorneoController(TorneoController torneoController) {
        this.torneoController = torneoController;
    }

    /**
     * Asigna el controlador de deportes utilizado por la ventana principal.
     *
     * @param deporteController controlador de deportes.
     */
    public void setDeporteController(DeporteController deporteController) {
        this.deporteController = deporteController;
    }

    /**
     * Dibuja los componentes gráficos del panel principal.
     * <p>
     * Invoca a la implementación de {@link JPanel#paintComponent(Graphics)} para
     * limpiar y repintar correctamente el componente.
     * </p>
     *
     * @param g contexto gráfico utilizado para pintar el componente.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g); //Limpia la pantalla y pinta el fondo blanco
    }

    /**
     * Maneja el evento de click del mouse sobre la ventana principal.
     * <p>
     * Actualmente obtiene las coordenadas del click y repinta la interfaz. Está
     * preparado para delegar acciones a los controladores correspondientes.
     * </p>
     *
     * @param e evento de mouse recibido.
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