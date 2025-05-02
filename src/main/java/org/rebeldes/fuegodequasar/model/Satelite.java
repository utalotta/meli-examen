package org.rebeldes.fuegodequasar.model;

public class Satelite {
    private String nombre;
    private Posicion posicion;

    public Satelite(String nombre, Posicion posicion) {
        this.nombre = nombre;
        this.posicion = posicion;
    }

    public Satelite() {}

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Posicion getPosicion() {
        return posicion;
    }

    public void setPosicion(Posicion posicion) {
        this.posicion = posicion;
    }
}