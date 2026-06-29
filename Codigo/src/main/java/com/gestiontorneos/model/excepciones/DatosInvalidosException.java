package com.gestiontorneos.model.excepciones;

public class DatosInvalidosException extends RuntimeException { //Excepción para cuando en la creación de una clase sus datos son ingresados erróneamente
    public DatosInvalidosException(String message) {
        super(message);
    }
}
