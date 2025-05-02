package org.rebeldes.fuegodequasar.dto;

import org.rebeldes.fuegodequasar.model.Posicion;

public class TopSecretResponse {
    private Posicion position;
    private String message;

    public TopSecretResponse(Posicion position, String message) {
        this.position = position;
        this.message = message;
    }

    public Posicion getPosition() { return position; }
    public String getMessage() { return message; }

    public void setPosition(Posicion position) { this.position = position; }
    public void setMessage(String message) { this.message = message; }
}
