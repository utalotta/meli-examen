package org.rebeldes.fuegodequasar.dto;

import java.util.List;

public class TopSecretRequest {
    private String name;
    private float distance;
    private List<String> message;

    public String getName() { return name; }
    public float getDistance() { return distance; }
    public List<String> getMessage() { return message; }

    public void setName(String name) { this.name = name; }
    public void setDistance(float distance) { this.distance = distance; }
    public void setMessage(List<String> message) { this.message = message; }
}
