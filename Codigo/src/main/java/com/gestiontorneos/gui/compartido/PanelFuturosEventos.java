package com.gestiontorneos.gui.compartido;
import javax.swing.*;
import java.awt.*;

/**
 * Representa la vista grafica que muestra los siguientes eventos
 */
public class PanelFuturosEventos extends JPanel{
    JPanel futurosTorneos;
    JPanel futurosPartidos;
    int cantidadPartidos;
    int cantidadTorneos;

    public PanelFuturosEventos(){
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.setBackground(Color.pink);
        this.setBorder(BorderFactory.createLineBorder(Color.white));
        this.setPreferredSize(new Dimension(PanelInformacion.VENTANASINMENU.getAncho(), PanelInformacion.VENTANASINMENU.getAlto()));
        cantidadPartidos = 1;
        cantidadTorneos = 1;

        iniciarPaneles();
    }

    private void iniciarPaneles() {
        futurosTorneos = new JPanel();
        futurosPartidos = new JPanel();

        futurosPartidos.setLayout(new GridBagLayout());
        futurosTorneos.setLayout(new GridBagLayout());

        futurosTorneos.setBackground(Color.white);
        futurosPartidos.setBackground(Color.cyan);

        futurosTorneos.setBorder(BorderFactory.createLineBorder(Color.yellow));
        futurosPartidos.setBorder(BorderFactory.createLineBorder(Color.yellow));

        futurosTorneos.setPreferredSize(new Dimension((
                PanelInformacion.VENTANASINMENU.getAncho()/2) - 15,
                PanelInformacion.VENTANASINMENU.getAlto()));
        futurosPartidos.setPreferredSize(new Dimension((
                PanelInformacion.VENTANASINMENU.getAncho()/2) - 15,
                PanelInformacion.VENTANASINMENU.getAlto()));

        this.add(futurosTorneos);
        this.add(futurosPartidos);
    }
    public void setCantidadPartidos (int cantidadPartidos) {
        if (cantidadPartidos > 0) {
            this.cantidadPartidos = cantidadPartidos;
        }
    }
    public void setCantidadTorneos(int cantidadTorneos) {
        if (cantidadTorneos > 0) {
            this.cantidadTorneos = cantidadTorneos;
        }
    }

    public void setFuturoTorneo(int id, String datos){

        JLabel datosLabel = new JLabel(datos);
        datosLabel.setBackground(Color.white);
        datosLabel.setForeground(Color.black);
        datosLabel.setOpaque(true);
        JPanel datosPanel = new JPanel();
        datosPanel.setLayout(new BoxLayout(datosPanel, BoxLayout.Y_AXIS));
        datosPanel.setBackground(Color.white);
        datosPanel.add(datosLabel);

    }

    public void setFuturoPartido(int id, String datos){
        JLabel datosLabel = new JLabel(datos);
        datosLabel.setBackground(Color.white);
        datosLabel.setForeground(Color.black);
        datosLabel.setOpaque(true);
        JPanel datosPanel = new JPanel();
        datosPanel.setLayout(new BoxLayout(datosPanel, BoxLayout.Y_AXIS));
        datosPanel.setBackground(Color.cyan);
        datosPanel.add(datosLabel);
    }
}
