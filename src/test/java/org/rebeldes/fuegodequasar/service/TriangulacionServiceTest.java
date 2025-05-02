package org.rebeldes.fuegodequasar.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TriangulacionServiceTest {

	private final TriangulacionService ubicacionService = new TriangulacionService();
	private final MensajeService mensajeService = new MensajeService();
	
    @Test
    void testReconstruccionMensajeBasico() {
        String[][] mensajes = {
            {"", "este", "", "mensaje"},
            {"este", "", "un", ""},
            {"", "", "es", "mensaje"}
        };

        String esperado = "este es un mensaje";
        String obtenido = mensajeService.getMessage(mensajes);

        assertEquals(esperado, obtenido);
    }

    @Test
    void testReconstruccionConEspaciosYDesfase() {
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
}
