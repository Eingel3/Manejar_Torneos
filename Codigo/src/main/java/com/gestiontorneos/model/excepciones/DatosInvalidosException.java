package com.gestiontorneos.model.excepciones;

/**
 * Excepción utilizada cuando se reciben datos inválidos al crear o modificar
 * entidades del modelo.
 * <p>
 * Al extender de {@link RuntimeException}, no es obligatorio capturarla de forma
 * explícita. Se usa principalmente para validar entradas como nombres vacíos,
 * fechas incorrectas, participantes inválidos o valores nulos.
 * </p>
 *
 * @see RuntimeException
 */
public class DatosInvalidosException extends RuntimeException { //Excepción para cuando en la creación de una clase sus datos son ingresados erróneamente

    /**
     * Crea una nueva excepción con el mensaje indicado.
     *
     * @param message descripción del error ocurrido.
     */
    public DatosInvalidosException(String message) {
        super(message);
    }
}