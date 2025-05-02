package org.rebeldes.fuegodequasar.model;

public class Posicion {
    private double x;
    private double y;
    
    public Posicion(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Posicion() {}

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}