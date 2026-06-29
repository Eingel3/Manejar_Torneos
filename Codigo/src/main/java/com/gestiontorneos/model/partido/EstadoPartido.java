package com.gestiontorneos.model.partido;

public enum EstadoPartido { //Enum para el estado del partido
    PENDIENTE, //Para los partidos que no han empezado
    EN_CURSO, //Para los partidos que estan en curso
    FINALIZADO, //Para los partidos que han finalizado
    CANCELADO //Para los partidos que han sido cancelados
}
