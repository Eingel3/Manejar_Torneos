package com.gestiontorneos.gui;
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
    private final int ANCHO = 1200; //Ancho del panel en pixeles
    private final int ALTO = 800; //Alto del panel en pixeles

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

    /**
     * Crea e inicializa la ventana principal de la aplicación.
     * <p>
     * Configura las dimensiones de la ventana, el color de fondo y registra el
     * listener de mouse. Además, crea una instancia compartida del modelo
     * </p>
     */
    public VentanaPrincipal() {

    }


}
