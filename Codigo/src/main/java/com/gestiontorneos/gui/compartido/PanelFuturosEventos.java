package com.gestiontorneos.gui.compartido;

import javax.swing.*;
import java.awt.*;

/**
 * Panel gráfico encargado de mostrar próximos torneos y próximos partidos.
 * <p>
 * La vista se divide en dos secciones: una para futuros torneos y otra para
 * futuros partidos. Cada evento se muestra dentro de una fila administrada
 * mediante {@link GridBagLayout}.
 * </p>
 *
 * @see JPanel
 * @see GridBagLayout
 */
public class PanelFuturosEventos extends JPanel {

    JPanel futurosTorneos;
    JPanel futurosPartidos;
    int cantidadPartidos;
    int cantidadTorneos;

    /**
     * Crea e inicializa el panel de futuros eventos.
     * <p>
     * Configura el layout principal, colores, borde, tamaño preferido y valores
     * iniciales para la cantidad de torneos y partidos. Después inicializa los
     * paneles internos.
     * </p>
     */
    public PanelFuturosEventos() {
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.setBackground(Color.pink);
        this.setBorder(BorderFactory.createLineBorder(Color.white));
        this.setPreferredSize(new Dimension(PanelInformacion.VENTANASINMENU.getAncho(), PanelInformacion.VENTANASINMENU.getAlto()));
        cantidadPartidos = 1;
        cantidadTorneos = 1;

        iniciarPaneles();
    }

    /**
     * Inicializa los paneles internos destinados a mostrar torneos y partidos
     * futuros.
     * <p>
     * Ambos paneles usan {@link GridBagLayout}, colores de fondo diferenciados,
     * bordes y dimensiones ajustadas al tamaño disponible de la ventana sin menú.
     * </p>
     */
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
                PanelInformacion.VENTANASINMENU.getAncho() / 2) - 15,
                PanelInformacion.VENTANASINMENU.getAlto()));
        futurosPartidos.setPreferredSize(new Dimension((
                PanelInformacion.VENTANASINMENU.getAncho() / 2) - 15,
                PanelInformacion.VENTANASINMENU.getAlto()));

        this.add(futurosTorneos);
        this.add(futurosPartidos);
    }

    /**
     * Define la cantidad máxima de partidos futuros que se mostrarán.
     * <p>
     * Si el valor recibido es mayor o igual a cero, se actualiza la cantidad y
     * se ajustan las filas visibles del panel de partidos.
     * </p>
     *
     * @param cantidadPartidos cantidad de partidos futuros permitidos.
     */
    public void setCantidadPartidos(int cantidadPartidos) {
        if (cantidadPartidos >= 0) {
            this.cantidadPartidos = cantidadPartidos;
            ajustarFilas(cantidadPartidos, futurosPartidos);
        }
    }

    /**
     * Define la cantidad máxima de torneos futuros que se mostrarán.
     * <p>
     * Si el valor recibido es mayor o igual a cero, se actualiza la cantidad y
     * se ajustan las filas visibles del panel de torneos.
     * </p>
     *
     * @param cantidadTorneos cantidad de torneos futuros permitidos.
     */
    public void setCantidadTorneos(int cantidadTorneos) {
        if (cantidadTorneos >= 0) {
            this.cantidadTorneos = cantidadTorneos;
            ajustarFilas(cantidadTorneos, futurosTorneos);
        }
    }

    /**
     * Ajusta la cantidad de filas visibles dentro de un panel objetivo.
     * <p>
     * Recorre los componentes del panel y elimina aquellos que se encuentren en
     * una fila superior a la cantidad permitida.
     * </p>
     *
     * @param cantidadFilas número máximo de filas permitidas.
     * @param panelObjetivo panel cuyo contenido será ajustado.
     */
    private void ajustarFilas(int cantidadFilas, JPanel panelObjetivo) {
        //Creamos las variables que almacenaran el layout y los constraints que vamos a usar
        GridBagLayout gbl = (GridBagLayout) panelObjetivo.getLayout();
        GridBagConstraints gbc = new GridBagConstraints();

        //Revisamos cada uno de los componentes del panelObjetivo
        for (Component componente : panelObjetivo.getComponents()) {
            gbc = gbl.getConstraints(componente); //Obtenemos los constraints del componente

            if (gbc.gridy > cantidadFilas) { //Ahora, si el componente pertenece a una fila mayor que cantidadFilas
                panelObjetivo.remove(componente);
            }
        }
    }

    /**
     * Agrega o reemplaza visualmente un torneo futuro en una posición concreta.
     * <p>
     * Si el identificador recibido supera la cantidad máxima configurada, el
     * evento se coloca en la posición cero.
     * </p>
     *
     * @param id posición o fila donde se mostrará el torneo.
     * @param datos texto descriptivo del torneo futuro.
     */
    public void setFuturoTorneo(int id, String datos) {
        if (cantidadTorneos < id) { //Si hay un error respecto al id, se cambia al evento mas antiguo o el evento 0
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
        GridBagConstraints gbc = new GridBagConstraints();

        for (Component comp : futurosTorneos.getComponents()) { //Revisamos cada componente
            gbc = layout.getConstraints(comp); //Asignamos el gbc segun los datos del componente

            if (gbc.gridx == 0 && gbc.gridy == id) { //Revisamos que el componente esté dentro de la celda que queremos cambiar
                futurosTorneos.remove(comp); //Y removemos el componente para reemplazarlo
                break;
            }
        }

        //Si resulta que en el lugar de la celda que se quiere colocar un nuevo evento no existe un evento anterior entonces asignamos los valores del gbc de forma manual
        gbc.gridx = 0;
        gbc.gridy = id;

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

    /**
     * Agrega o reemplaza visualmente un partido futuro en una posición concreta.
     * <p>
     * Si el identificador recibido supera la cantidad máxima configurada, el
     * evento se coloca en la posición cero.
     * </p>
     *
     * @param id posición o fila donde se mostrará el partido.
     * @param datos texto descriptivo del partido futuro.
     */
    public void setFuturoPartido(int id, String datos) {
        if (cantidadPartidos < id) { //Si hay un error respecto al id, se cambia al evento mas antiguo o el evento 0
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
        GridBagConstraints gbc = new GridBagConstraints();

        for (Component comp : futurosPartidos.getComponents()) { //Revisamos cada componente
            gbc = layout.getConstraints(comp); //Asignamos el gbc segun los datos del componente

            if (gbc.gridx == 0 && gbc.gridy == id) { //Revisamos que el componente esté dentro de la celda que queremos cambiar
                futurosPartidos.remove(comp); //Y removemos el componente para reemplazarlo
                break;
            }
        }

        //Si resulta que en el lugar de la celda que se quiere colocar un nuevo evento no existe un evento anterior entonces asignamos los valores del gbc de forma manual
        gbc.gridx = 0;
        gbc.gridy = id;

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