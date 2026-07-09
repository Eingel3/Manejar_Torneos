package com.gestiontorneos.gui;
import com.gestiontorneos.controller.*;
import com.gestiontorneos.gui.compartido.*;
import com.gestiontorneos.gui.organizador.PanelCrearParticipante;
import com.gestiontorneos.gui.organizador.PanelCrearTorneo;
import com.gestiontorneos.gui.organizador.PanelParticipantes;
import com.gestiontorneos.gui.organizador.PanelCrearPartido;
import com.gestiontorneos.gui.organizador.PanelRegistrarResultado;
import com.gestiontorneos.model.participante.Participante;
import com.gestiontorneos.model.partido.Partido;
import com.gestiontorneos.model.torneo.Torneo;
import java.util.List;

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
    private PanelInicio panelInicio; //Instancia de segmento del panel correspondiente a la pestaña de inicio
    private PanelClasificacion clasificacion; //Instancia de segmento del panel correspondiente a la clasificacion
    private PanelListaTorneos torneos; //Instancia de segmento del panel correspondiente a la lista de los torneos
    private PanelResultados resultados; //Instancia de segmento del panel correspondiente a los resultados

    //Paneles unicamente para usuarios tipo organizador
    private PanelCrearTorneo crearTorneo; //Instancia de segmento del panel correspondiente a la opcion de crear torneos
    private PanelParticipantes participantesOrganizador; //Instancia de segmento del panel correspondiente a los participantes para que el organizador los pueda editar
    private PanelCrearPartido partidosOrganizador; //Instancia de segmento del panel correspondiente a los partidos para que el organizador pueda editarlos
    private PanelMenu menuLateral; //Instancia de segmento del panel correspondiente al menu lateral que contiene los botones que dirigen a cada panel
    private PanelFuturosEventos eventos; //Instancia de segmento del panel correspondiente a un panel que contiene la visualizacion de futuros eventos
    private JPanel subPanel;
    private CardLayout cardLayout;
    private PanelCrearParticipante crearParticipante;
    private PanelRegistrarResultado registrarResultado;
    private PanelBracket panelBracket;

    private TorneoController torneoController;
    private DeporteController deporteController;
    private CrearParticipanteController crearParticipanteController;

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
        new PanelMenuController(menuLateral, this);

    }
    public void inicializarControladores() {
        if (torneoController != null) {
           // new CrearPartidoController(partidosOrganizador, torneoController); Desactivado porque la creación de partidos maualemente rompía torneos de eliminación
            new ResultadoController(registrarResultado, torneoController);
            crearParticipanteController = new CrearParticipanteController(crearParticipante, torneoController);
            configurarListenersTorneos();
        }
    }


    public void actualizarPanelInicio() {
        if (torneoController == null) return;
        List<Torneo> lista = torneoController.listaTorneos();

        JPanel bienvenida = new JPanel();
        bienvenida.add(new JLabel("Bienvenido a Gestión de Torneos"));
        panelInicio.setBienvenida(bienvenida);
        JPanel torneoCard = new JPanel();

        //ultimo torneo
        if (!lista.isEmpty()) {
            Torneo t = lista.get(lista.size() - 1);
            torneoCard.add(new JLabel(t.getNombre() + " | " + t.getDeporte().getNombre() + " | " + t.getEstado()));
        } else {
            torneoCard.add(new JLabel("No hay torneos creados"));
        }
        panelInicio.setTorneoReciente(torneoCard);

        //evento futuro
        JPanel futuroCard = new JPanel();
        if (!lista.isEmpty()) {
            Torneo t = lista.get(lista.size() - 1);
            List<Partido> pendientes = t.getCalendario().getPendientes();
            if (!pendientes.isEmpty()) {
                Partido p = pendientes.get(0);
                futuroCard.add(new JLabel(p.getLocal().getNombre() + " vs " + p.getVisitante().getNombre()));
            } else {
                futuroCard.add(new JLabel("No hay partidos pendientes"));
            }
        } else {
            futuroCard.add(new JLabel("Sin eventos"));
        }
        panelInicio.setFuturoEvento(futuroCard);

        //lider
        JPanel leaderCard = new JPanel();
        if (!lista.isEmpty()) {
            Participante lider = lista.get(lista.size() - 1).getClasificacion().getLider();
            if (lider != null) {
                leaderCard.add(new JLabel("Líder: " + lider.getNombre()));
            } else {
                leaderCard.add(new JLabel("Sin líder definido"));
            }
        } else {
            leaderCard.add(new JLabel("Sin clasificación"));
        }
        panelInicio.setPuesto1(leaderCard);


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
        clasificacion = new PanelClasificacion();
        torneos = new PanelListaTorneos();
        resultados = new PanelResultados();
        crearTorneo = new PanelCrearTorneo();
        participantesOrganizador = new PanelParticipantes();
        partidosOrganizador = new PanelCrearPartido();
        menuLateral = new PanelMenu();
        eventos = new PanelFuturosEventos();
        panelInicio = new PanelInicio();
        crearParticipante = new PanelCrearParticipante();
        registrarResultado = new PanelRegistrarResultado();
        panelBracket = new PanelBracket();
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
        subPanel.add(clasificacion, "Clasificacion");
        subPanel.add(torneos, "Torneos");
        subPanel.add(resultados,  "Resultados");
        subPanel.add(crearTorneo,  "Crear Torneo");
        subPanel.add(participantesOrganizador,  "Participantes");
        subPanel.add(partidosOrganizador,  "Partidos");
        subPanel.add(eventos,  "Futuros Eventos");
        subPanel.add(panelInicio, "Inicio");
        subPanel.add(crearParticipante, "Crear Participante");
        subPanel.add(registrarResultado, "Registrar Resultado");
        subPanel.add(panelBracket, "Bracket");

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

    public void actualizarTorneos(){
        torneos.actualizarLista(torneoController.listaTorneos());
    }

    public void actualizarClasificacion(Torneo torneo) {
        if (torneo != null && torneo.getClasificacion() != null) {
            List<Participante> tabla = torneo.getClasificacion().getTablaOrdenada();
            clasificacion.actualizarClasificacion(tabla, torneo.getClasificacion());
        }
    }

    public TorneoController getTorneoController() { return torneoController; }

    public PanelRegistrarResultado getRegistrarResultado(){
        return registrarResultado;
    }

    public PanelResultados getResultados() {
        return resultados;
    }
    public PanelClasificacion getClasificacion() {return clasificacion;}
    public PanelCrearPartido getPartidosOrganizador() {
        return partidosOrganizador;
    }
    public void configurarListenersTorneos() {
        ListaTorneosBotonesController botonesCtrl = new ListaTorneosBotonesController(this, torneoController);
        torneos.agregarListener(botonesCtrl);
    }

    /**
     * Getter del PanelBracket
     * @return panelBracket quien es el PanelBracket de this
     */
    public PanelBracket getPanelBracket() {
        return panelBracket;
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
     * Maneja el evento de click del mouse sobre la ventana principal.
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

    public PanelCrearTorneo getCrearTorneo() {
        return crearTorneo;
    }
    public PanelCrearParticipante getCrearParticipante() {
        return crearParticipante;
    }

    public void refrescarBotonSiguienteParticipante() {
        if (crearParticipanteController != null) {
            crearParticipanteController.configurarBotonSiguiente();
        }
    }

    public void actualizarFuturosEventos(){
        if (torneoController == null) return;
        List<Torneo> lista = torneoController.listaTorneos();

        int idxTorneo = 0;
        int idxPartido = 0;

        for (Torneo t : lista) {
            if ("INSCRIPCION".equals(t.getEstado()) || "EN_CURSO".equals(t.getEstado())) {
                String datos = t.getNombre() + " | " + t.getDeporte().getNombre() + " | " + t.getEstado();
                eventos.setFuturoTorneo(idxTorneo, datos);
                idxTorneo++;
            }
            List<Partido> pendientes = t.getCalendario().getPendientes();
            for (Partido p : pendientes) {
                String datos = t.getNombre() + ": " + p.getLocal().getNombre() + " vs " + p.getVisitante().getNombre();
                eventos.setFuturoPartido(idxPartido, datos);
                idxPartido++;
            }

        }

    }
}