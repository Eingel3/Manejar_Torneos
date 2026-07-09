package com.gestiontorneos.gui.compartido;

import com.gestiontorneos.gui.factory.PanelTarjeta;
import com.gestiontorneos.gui.factory.SubPanel;
import com.gestiontorneos.model.torneo.Torneo;

import javax.swing.*;
import java.awt.*;

/**
 * Panel gráfico correspondiente a la pantalla de inicio de la aplicación.
 * <p>
 * Esta vista funciona como una página principal o resumen general del sistema.
 * Está compuesta por varias tarjetas visuales destinadas a mostrar información
 * relevante para el usuario, como un mensaje de bienvenida, el torneo más
 * reciente, futuros eventos, el primer puesto de una clasificación e información
 * adicional.
 * </p>
 * <p>
 * La clase utiliza {@link PanelTarjeta} para mantener un diseño visual uniforme
 * entre las distintas secciones del panel. Cada sección puede actualizarse
 * reemplazando su contenido mediante los métodos {@code set}.
 * </p>
 *
 * @see JPanel
 * @see PanelTarjeta
 * @see PanelInformacion
 */
public class PanelInicio extends JPanel {

    /**
     * Tarjeta visual destinada a mostrar un mensaje de bienvenida.
     */
    JPanel bienvenida;

    /**
     * Tarjeta visual destinada a mostrar información del torneo más reciente.
     */
    JPanel torneoReciente;

    /**
     * Tarjeta visual destinada a mostrar información sobre un futuro evento.
     */
    JPanel futuroEvento;

    /**
     * Tarjeta visual destinada a mostrar información del primer puesto.
     */
    JPanel puesto1;

    /**
     * Tarjeta visual destinada a mostrar información general adicional.
     */
    JPanel informacion;

    /**
     * Fábrica utilizada para crear paneles tipo tarjeta.
     */
    PanelTarjeta creadorTarjeta;

    /**
     * Crea e inicializa el panel de inicio.
     * <p>
     * Configura el color de fondo, el layout vertical, el borde y las dimensiones
     * del panel. Luego construye la estructura interna mediante
     * {@link #iniciarEstructura()}.
     * </p>
     */
    public PanelInicio() {
        this.setBackground(Color.CYAN);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        this.setPreferredSize(new Dimension(
                PanelInformacion.VENTANASINMENU.getAncho(),
                PanelInformacion.VENTANASINMENU.getAlto()));
        iniciarEstructura();
    }

    /**
     * Inicializa la estructura visual del panel de inicio.
     * <p>
     * Crea las tarjetas que componen la pantalla principal y las agrega al panel
     * con espacios verticales entre cada una para mejorar la distribución visual.
     * </p>
     */
    private void iniciarEstructura(){
        this.creadorTarjeta = new PanelTarjeta();

        this.bienvenida = creadorTarjeta.crear();
        this.futuroEvento = creadorTarjeta.crear();
        this.torneoReciente = creadorTarjeta.crear();
        this.informacion = creadorTarjeta.crear();
        this.puesto1 = creadorTarjeta.crear();

        this.add(Box.createRigidArea(new Dimension(0, 10)));
        this.add(bienvenida);
        this.add(Box.createRigidArea(new Dimension(0, 40)));
        this.add(torneoReciente);
        this.add(Box.createRigidArea(new Dimension(0, 40)));
        this.add(futuroEvento);
        this.add(Box.createRigidArea(new Dimension(0, 40)));
        this.add(puesto1);
        this.add(Box.createRigidArea(new Dimension(0, 40)));
        this.add(informacion);

        revalidate();
        repaint();
    }

    /**
     * Reemplaza el contenido de la tarjeta de bienvenida.
     * <p>
     * Elimina cualquier componente anterior y agrega el panel recibido como nuevo
     * contenido de la sección.
     * </p>
     *
     * @param bienvenida panel que contiene el nuevo mensaje o contenido de bienvenida.
     */
    public void setBienvenida(JPanel bienvenida){
        this.bienvenida.removeAll();
        this.bienvenida.add(bienvenida);
        this.bienvenida.revalidate();
        this.bienvenida.repaint();
    }

    /**
     * Reemplaza el contenido de la tarjeta del torneo reciente.
     *
     * @param torneoReciente panel con la información del torneo más reciente.
     */
    public void setTorneoReciente(JPanel torneoReciente){
        this.torneoReciente.removeAll();
        this.torneoReciente.add(torneoReciente);
        this.torneoReciente.revalidate();
        this.torneoReciente.repaint();
    }

    /**
     * Reemplaza el contenido de la tarjeta de futuro evento.
     *
     * @param futuroEvento panel con la información del evento futuro.
     */
    public void setFuturoEvento(JPanel futuroEvento){
        this.futuroEvento.removeAll();
        this.futuroEvento.add(futuroEvento);
        this.futuroEvento.revalidate();
        this.futuroEvento.repaint();
    }

    /**
     * Reemplaza el contenido de la tarjeta del primer puesto.
     *
     * @param puesto1 panel con la información del participante ubicado en primer lugar.
     */
    public void setPuesto1(JPanel puesto1){
        this.puesto1.removeAll();
        this.puesto1.add(puesto1);
        this.puesto1.revalidate();
        this.puesto1.repaint();
    }

    /**
     * Reemplaza el contenido de la tarjeta de información general.
     *
     * @param informacion panel con información adicional para mostrar en el inicio.
     */
    public void setInformacion(JPanel informacion){
        this.informacion.removeAll();
        this.informacion.add(informacion);
        this.informacion.revalidate();
        this.informacion.repaint();
    }
}