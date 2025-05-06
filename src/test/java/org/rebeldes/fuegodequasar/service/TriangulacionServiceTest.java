package org.rebeldes.fuegodequasar.service;

import org.junit.jupiter.api.Test;
import org.rebeldes.fuegodequasar.model.Posicion;

import static org.junit.jupiter.api.Assertions.*;

class TriangulacionServiceTest {

	private final TriangulacionService ubicacionService = new TriangulacionService();
	private final MensajeService mensajeService = new MensajeService();
	
    @Test
    void testReconstruccionMensajeBasico() {
        String[][] mensajes = {
            {"este", "", "", "mensaje"},
            {"este", "", "un", ""},
            {"", "es", "", "mensaje"}
        };

        String esperado = "este es un mensaje";
        String obtenido = mensajeService.getMessage(mensajes);

        assertEquals(esperado, obtenido);
    }

    @Test
    void testReconstruccionConDesfase() {
        String[][] mensajes = {
            {"", "necesito", ""},
            {"", "", "hola"},
            {"ayuda", "", "necesito"}
        };

        String esperado = "hola necesito ayuda";
        String obtenido = mensajeService.getMessage(mensajes);

        assertEquals(esperado, obtenido);
    }

    @Test
    void testMensajeIncompleto() {
        String[][] mensajes = {
            {"", "", "", "", ""},
            {"", "", "", "", ""},
            {"", "", "", "", ""}
        };

        String esperado = "";
        String obtenido = mensajeService.getMessage(mensajes);

        assertEquals(esperado, obtenido);
    }

    @Test
    void testConPalabrasDuplicadas() {
        String[][] mensajes = {
            {"", "mensaje", "mensaje"},
            {"este", "", "mensaje"},
            {"", "", ""}
        };

        String esperado = "este mensaje mensaje";
        String obtenido = mensajeService.getMessage(mensajes);

        assertEquals(esperado, obtenido);
    }
    
    @Test
    void testTriangulacion() {
        double[] distancias = {670.82, 200.0, 400.0}; 

        Posicion posicion = ubicacionService.getLocation(distancias);

        // ponemos una posicion de la nave conocida para las distancias dada
        assertNotNull(posicion);
        assertEquals(100.0, posicion.getX(), 0.1);
        assertEquals(100.0, posicion.getY(), 0.1); // con un error de 0.1
    }
}
