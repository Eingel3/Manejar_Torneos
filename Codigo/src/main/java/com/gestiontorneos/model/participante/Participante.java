package com.gestiontorneos.model.participante;
import java.util.Objects;
import java.util.UUID;
import com.gestiontorneos.model.excepciones.DatosInvalidosException;

public abstract class Participante {

    private final String id;
    private final String nombre;
    private final String contacto;

    protected Participante(String nombre, String rut, String contacto) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new DatosInvalidosException("El nombre del participante no puede estar vacío");
        }
        this.id = UUID.randomUUID().toString(); //Id para diferenciar entre participantes de mismo nombre
        this.nombre = nombre.trim();
        this.contacto = contacto;
    }

    public String getRut() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getContacto() {
        return contacto;
    }

    public abstract int getCantidadIntegrantes();

    @Override
    public String toString() {
        return nombre;
    }

    @Override
    public boolean equals(Object o) { //Validar igualdad entre participantes
        if (!(o instanceof Participante)) { //Validamos que el objeto a comparar sea de la clase Participante
            return false;
        }
        Participante otro = (Participante) o; //Convertimos el objeto a comparar a la clase Participante
        return this.id.equals(otro.id); //Comparamos id
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


}

