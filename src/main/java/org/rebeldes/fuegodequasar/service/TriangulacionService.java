package org.rebeldes.fuegodequasar.service;

import org.rebeldes.fuegodequasar.model.Posicion;
import org.springframework.stereotype.Service;
import com.lemmingapex.trilateration.NonLinearLeastSquaresSolver;
import com.lemmingapex.trilateration.TrilaterationFunction;
import org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer;

@Service
public class TriangulacionService {
	
	/*private final Posicion[] satelitesPos = new Posicion[] {
            new Posicion(-500, -200),
            new Posicion(100, -100),
            new Posicion(500, 100) };
            */
    
    private Posicion triangulacion(double[] distancias) {
    	double[][] satelitesPosiciones = {
    		    {-500, -200},
    		    {100, -100},
    		    {500, 100}
    		};
    	
        TrilaterationFunction funcionTriangulacion = new TrilaterationFunction(satelitesPosiciones, distancias);
        NonLinearLeastSquaresSolver resolucion = new NonLinearLeastSquaresSolver(funcionTriangulacion, new LevenbergMarquardtOptimizer());

        double resultadoX = resolucion.solve().getPoint().toArray()[0];
        double resultadoY = resolucion.solve().getPoint().toArray()[1];
        
        return new Posicion(resultadoX, resultadoY);
    }
    
    public Posicion getLocation(double[] distances) {
        if (distances.length != 3) throw new IllegalArgumentException("Se requieren 3 distancias");
        
        return triangulacion(distances);
    }
    
}
