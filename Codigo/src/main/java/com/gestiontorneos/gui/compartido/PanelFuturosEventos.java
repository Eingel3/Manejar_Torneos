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
        if (cantidadTorneos < id){ //Si hay un error respecto al id, se cambia al evento mas antiguo o el evento 0
            id = 0;
        }
        //Primero gestionamos el label con los datos y el JPanel que contendrá a los datos
        JLabel datosLabel = new JLabel(datos);
        datosLabel.setBackground(Color.white);
        datosLabel.setForeground(Color.black);
        datosLabel.setOpaque(true);
        JPanel datosPanel = new JPanel();
        datosPanel.setLayout(new BoxLayout(datosPanel, BoxLayout.Y_AXIS));
        datosPanel.setBackground(Color.white);
        datosPanel.add(datosLabel);

        //Ahora removemos el evento que ya esté en el id
        GridBagLayout layout = (GridBagLayout) futurosTorneos.getLayout(); //en el layout
        GridBagConstraints gbc;
        for (Component comp : futurosTorneos.getComponents()) { //Revisamos cada componente
            gbc = layout.getConstraints(comp); //Asignamos el gbc segun los datos del componente
            if (gbc.gridx == 0 && gbc.gridy == id) { //Revisamos que el componente esté dentro de la celda que queremos cambiar
                futurosTorneos.remove(comp); //Y removemos el componente para reemplazarlo
                break;
            }
        }

        //Si resulta que en el lugar de la celda que se quiere colocar un nuevo evento no existe un evento anterior entonces inicializamos el gbc de forma manual
        if (gbc == null) {
            gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = id;
        }

        //Lo siguiente maneja el como los componentes ocupan el espacio
        //En este caso, los paneles usan todo el espacio disponible
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;

        //Finalmente agregamos el datosPanel
        futurosTorneos.add(datosPanel, gbc);
        //Y revalidamos y repintamos para que los cambios sean correctamente actualizados
        futurosTorneos.revalidate();
        futurosTorneos.repaint();
    }

    public void setFuturoPartido(int id, String datos){
        if (cantidadPartidos < id){ //Si hay un error respecto al id, se cambia al evento mas antiguo o el evento 0
            id = 0;
        }
        //Primero gestionamos el label con los datos y el JPanel que contendrá a los datos
        JLabel datosLabel = new JLabel(datos);
        datosLabel.setBackground(Color.white);
        datosLabel.setForeground(Color.black);
        datosLabel.setOpaque(true);
        JPanel datosPanel = new JPanel();
        datosPanel.setLayout(new BoxLayout(datosPanel, BoxLayout.Y_AXIS));
        datosPanel.setBackground(Color.cyan);
        datosPanel.add(datosLabel);
        //Ahora removemos el evento que ya esté en el id
        GridBagLayout layout = (GridBagLayout) futurosPartidos.getLayout(); //en el layout
        GridBagConstraints gbc;
        for (Component comp : futurosPartidos.getComponents()) { //Revisamos cada componente
            gbc = layout.getConstraints(comp); //Asignamos el gbc segun los datos del componente
            if (gbc.gridx == 0 && gbc.gridy == id) { //Revisamos que el componente esté dentro de la celda que queremos cambiar
                futurosPartidos.remove(comp); //Y removemos el componente para reemplazarlo
                break;
            }
        }

        //Si resulta que en el lugar de la celda que se quiere colocar un nuevo evento no existe un evento anterior entonces inicializamos el gbc de forma manual
        if (gbc == null) {
            gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = id;
        }

        //Lo siguiente maneja el como los componentes ocupan el espacio
        //En este caso, los paneles usan todo el espacio disponible
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;

        //Finalmente agregamos el datosPanel
        futurosPartidos.add(datosPanel, gbc);
        //Y revalidamos y repintamos para que los cambios sean correctamente actualizados
        futurosPartidos.revalidate();
        futurosPartidos.repaint();
    }
}
