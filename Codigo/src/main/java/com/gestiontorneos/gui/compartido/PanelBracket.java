package com.gestiontorneos.gui.compartido;

import com.gestiontorneos.model.partido.Calendario;
import com.gestiontorneos.model.partido.EstadoPartido;
import com.gestiontorneos.model.partido.Partido;
import com.gestiontorneos.model.partido.Resultado;
import com.gestiontorneos.model.torneo.Torneo;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Panel para visualizar el bracket de un torneo de eliminación directa.
 */
public class PanelBracket extends JPanel {

    private JTextArea txtBracket;
    private Torneo torneoActual;

    public PanelBracket() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(
                PanelInformacion.VENTANASINMENU.getAncho(),
                PanelInformacion.VENTANASINMENU.getAlto()));

        JLabel lblTitulo = new JLabel("Bracket del Torneo");
        lblTitulo.setFont(new Font("Dialog", Font.BOLD, 24));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblTitulo, BorderLayout.NORTH);

        txtBracket = new JTextArea();
        txtBracket.setEditable(false);
        txtBracket.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(txtBracket);
        add(scrollPane, BorderLayout.CENTER);
    }

    /**
     * Actualiza la visualización del bracket con los datos del torneo proporcionado.
     *
     * @param torneo Torneo del cual se mostrará el bracket.
     */
    public void actualizarBracket(Torneo torneo) {
        this.torneoActual = torneo;
        if (torneo == null) {
            txtBracket.setText("No hay torneo seleccionado.");
            return;
        }

        String texto = "Torneo: " + torneo.getNombre() + "\n";

        Calendario calendario = torneo.getCalendario();
        int rondaMaxima = obtenerRondaMaxima(calendario);

        for (int ronda = 1; ronda <= rondaMaxima; ronda++) {
            List<Partido> partidosRonda = calendario.getPorRonda(ronda);

            texto += "Ronda " + ronda + ":\n";
            for (Partido p : partidosRonda) {
                String local = p.getLocal().getNombre();
                String visitante = p.getVisitante().getNombre();
                String resultado = "El partido está pendiente"; //Si no está cancelado ni finalizado, está pendiente
                if (p.getEstado() == EstadoPartido.FINALIZADO) {
                    Resultado res = p.getResultado();
                    resultado = "Puntos hechos por " + local + ": " + Integer.toString(res.getPuntosLocal()) + "\n" +
                            "Puntos hechos por " + visitante + ": " +  Integer.toString(res.getPuntosVisitante());
                } else if (p.getEstado() == EstadoPartido.CANCELADO) {
                    resultado = "El partido ha sido cancelado.";
                }
                texto += "  " + local + " vs " + visitante + " \n" + resultado + "\n";
            }
            texto += "\n";
        }

        txtBracket.setText(texto);
    }

    private int obtenerRondaMaxima(Calendario calendario) {
        int max = 0;
        for (Partido p : calendario.getPartidos()) {
            if (p.getRonda() > max) {
                max = p.getRonda();
            }
        }
        return max;
    }
}
