package com.gestiontorneos.controller;

import com.gestiontorneos.gui.organizador.PanelCrearPartido;
import com.gestiontorneos.model.participante.Participante;
import com.gestiontorneos.model.torneo.Torneo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Controlador asociado originalmente a la creación manual de partidos.
 * <p>
 * Esta clase fue diseñada inicialmente para permitir que el usuario añadiera
 * partidos manualmente a un torneo desde la interfaz gráfica. Sin embargo, dicha
 * funcionalidad fue finalmente descartada en la versión final de la aplicación,
 * ya que los partidos pasaron a generarse principalmente a través del calendario
 * automático del torneo.
 * </p>
 * <p>
 * A pesar de ello, la clase se mantuvo porque resultó útil como espacio para
 * albergar métodos, validaciones y conexiones entre la vista {@link PanelCrearPartido}
 * y el {@link TorneoController}, algunas de las cuales sí pueden ser aprovechadas
 * por la versión final o por futuras extensiones del sistema.
 * </p>
 * <p>
 * Por este motivo, el controlador representa una base reutilizable para posibles
 * funciones relacionadas con partidos, aunque la creación manual como flujo
 * principal no forme parte de la aplicación final.
 * </p>
 */
public class CrearPartidoController {

    /**
     * Panel gráfico originalmente utilizado para introducir los datos del partido.
     * <p>
     * Aunque la creación manual de partidos fue descartada como funcionalidad final,
     * este panel puede seguir sirviendo como punto de entrada para futuras operaciones
     * relacionadas con partidos.
     * </p>
     */
    private PanelCrearPartido panelCrearPartido;

    /**
     * Controlador que administra los torneos, participantes y partidos.
     * <p>
     * Permite conectar las operaciones de esta clase con la lógica principal del
     * sistema.
     * </p>
     */
    private TorneoController torneoController;

    /**
     * Construye un controlador asociado al panel de creación de partidos.
     * <p>
     * Originalmente este constructor preparaba el flujo para añadir partidos
     * manualmente. Aunque ese flujo no quedó como parte principal de la aplicación
     * final, se conserva la inicialización de eventos porque la clase puede seguir
     * funcionando como base para operaciones futuras relacionadas con partidos.
     * </p>
     *
     * @param panelCrearPartido panel de creación de partido.
     * @param torneoController controlador que gestiona los torneos.
     */
    public CrearPartidoController(PanelCrearPartido panelCrearPartido, TorneoController torneoController) {
        this.panelCrearPartido = panelCrearPartido;
        this.torneoController = torneoController;
        manejarEventos();
    }

    /**
     * Configura los eventos de la interfaz gráfica.
     * <p>
     * Los eventos fueron pensados inicialmente para manejar la creación manual de
     * partidos y la cancelación del formulario. Aunque el flujo manual fue descartado,
     * esta estructura queda disponible como base para reutilizar o extender el
     * comportamiento del controlador.
     * </p>
     */
    private void manejarEventos() {
        panelCrearPartido.getBotonCrear().addActionListener(e -> crearPartido());
        panelCrearPartido.getBotonCancelar().addActionListener(e -> cancelar());
        //panelCrearPartido.getBotonSiguiente().addActionListener(e -> siguiente());
    }

    /**
     * Intenta crear un partido a partir de los datos ingresados en el formulario.
     * <p>
     * Este método pertenece al diseño original de la clase, donde se contemplaba
     * añadir partidos manualmente a un torneo. En la versión final, dicha función
     * fue descartada como flujo principal, pero el método se conserva porque contiene
     * validaciones útiles: comprobación de campos vacíos, existencia del torneo,
     * existencia de participantes y prevención de enfrentamientos contra el mismo
     * participante.
     * </p>
     * <p>
     * Estas validaciones pueden servir como base para futuras versiones o para otras
     * funciones relacionadas con la administración de partidos.
     * </p>
     */
    private void crearPartido() {
        String nombreTorneo = panelCrearPartido.getNombreTorneo();
        String nombreLocal = panelCrearPartido.getNombreParticipanteLocal();
        String nombreVisitante = panelCrearPartido.getNombreParticipanteVisitante();
        String estadoSeleccionado = panelCrearPartido.getEstadoPartido();

        if (nombreTorneo.isEmpty() || nombreLocal.isEmpty() || nombreVisitante.isEmpty()) {
            panelCrearPartido.mostrarMensaje("Todos los campos deben estar completos.");
            return;
        }

        if (nombreLocal.equalsIgnoreCase(nombreVisitante)) {
            panelCrearPartido.mostrarMensaje("El participante local y el visitante no pueden ser el mismo.");
            return;
        }

        Torneo torneo = torneoController.buscarTorneo(nombreTorneo);
        if (torneo == null) {
            panelCrearPartido.mostrarMensaje("El torneo '" + nombreTorneo + "' no existe.");
            return;
        }

        List<Participante> participantes = torneoController.listarParticipantes(nombreTorneo);
        Participante local = null;
        Participante visitante = null;
        for (Participante p : participantes) {
            if (p.getNombre().equalsIgnoreCase(nombreLocal)) local = p;
            if (p.getNombre().equalsIgnoreCase(nombreVisitante)) visitante = p;
        }
        if (local == null) {
            panelCrearPartido.mostrarMensaje("Participante local '" + nombreLocal + "' no existe en el torneo.");
            return;
        }
        if (visitante == null) {
            panelCrearPartido.mostrarMensaje("Participante visitante '" + nombreVisitante + "' no existe en el torneo.");
            return;
        }

        boolean exito = torneoController.crearPartido(local, visitante, nombreTorneo, 1);
        if (exito) {
            panelCrearPartido.mostrarMensaje("Partido creado exitosamente!");
            panelCrearPartido.limpiarFormulario();
        } else {
            panelCrearPartido.mostrarMensaje("Error al crear el partido.");
        }
    }

    /**
     * Cancela la operación actual y limpia el panel.
     * <p>
     * Este método se conserva como parte de la utilidad general del controlador,
     * permitiendo reiniciar la vista asociada aunque la creación manual de partidos
     * no sea una funcionalidad principal de la versión final.
     * </p>
     */
    private void cancelar() {
        panelCrearPartido.limpiarPanel();
    }

    /**
     * Método reservado para una posible navegación futura dentro del formulario.
     * <p>
     * Se mantiene como parte de la estructura original del controlador, dejando una
     * base para ampliar el flujo de interacción si en versiones futuras se decide
     * recuperar o rediseñar la gestión manual de partidos.
     * </p>
     */
    private void siguiente() {

    }
}