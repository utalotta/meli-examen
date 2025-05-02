package org.rebeldes.fuegodequasar.service;

import org.rebeldes.fuegodequasar.model.Posicion;
import org.springframework.stereotype.Service;

@Service
public class TriangulacionService {
	
    private final Posicion[] satelitesPos = new Posicion[] {
            new Posicion(-500, -200),
            new Posicion(100, -100),
            new Posicion(500, 100) };
    
    private Posicion triangulacion(double[] distancias) {
    	double x = 0;
        double y = 0;

        return new Posicion(x, y);
    }
    
    public Posicion getLocation(double[] distances) {
        if (distances.length != 3) throw new IllegalArgumentException("Se requieren 3 distancias");
        
        return triangulacion(distances);
    }
    
}
