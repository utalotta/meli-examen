package org.rebeldes.fuegodequasar.service;

import java.util.Arrays;

import org.springframework.stereotype.Service;

@Service
public class MensajeService {	
	
	public String getMessage(String[][] messages) {
		final int filasEsperadas = 3;

	    // validaciones basicas
	    if (messages == null || messages.length != filasEsperadas) {
	        return "";
	    }

	    int columnas = messages[0].length;
	    for (String[] fila : messages) {
	        if (fila == null || fila.length != columnas) {
	            return "";
	        }
	    }

	    // intentamos alinear las filas
	    String[][] alineado = alinearMensajes(messages);
	    if (alineado == null) return "";

	    // reconstruir mensaje
	    StringBuilder resultado = new StringBuilder();
	    for (int c = 0; c < columnas; c++) {
	        String palabra = "";
	        for (int f = 0; f < filasEsperadas; f++) {
	            String actual = alineado[f][c];
	            if (!actual.isEmpty()) {
	                if (palabra.isEmpty()) {
	                    palabra = actual;
	                } else if (!palabra.equals(actual)) {
	                    return ""; // conflicto entre palabras
	                }
	            }
	        }
	        if (!palabra.isEmpty()) {
	            if (resultado.length() > 0) resultado.append(" ");
	            resultado.append(palabra);
	        }
	    }

	    return resultado.toString();
    }
	
	private String[][] alinearMensajes(String[][] messages) {
	    int filas = messages.length;
	    int columnas = messages[0].length;

	    // generar todas las combinaciones de offsets posibles
	    for (int offset1 = 0; offset1 <= columnas; offset1++) {
	        for (int offset2 = 0; offset2 <= columnas; offset2++) {

	            String[][] alineado = new String[filas][columnas];
	            boolean ok = true;

	            // fila 0: sin desplazamiento, la tomamos como refe
	            System.arraycopy(messages[0], 0, alineado[0], 0, columnas);
	            // fila 1
	            ok &= aplicarDesplazamiento(messages[1], alineado[1], offset1);
	            // fila 2
	            ok &= aplicarDesplazamiento(messages[2], alineado[2], offset2);

	            if (!ok) continue;

	            if (validarAlineacion(alineado)) {
	                return alineado;
	            }
	        }
	    }

	    return null; // por aca sale si no encuentra ninguna alineacion valida
	}

	private boolean aplicarDesplazamiento(String[] origen, String[] destino, int offset) {
	    if (offset > destino.length) return false;

	    Arrays.fill(destino, "");

	    for (int i = 0; i < origen.length; i++) {
	        int j = i + offset;
	        if (j >= destino.length) break;
	        destino[j] = origen[i];
	    }

	    return true;
	}

	private boolean validarAlineacion(String[][] alineado) {
	    int filas = alineado.length;
	    int columnas = alineado[0].length;

	    for (int c = 0; c < columnas; c++) {
	        String palabra = null;
	        for (int f = 0; f < filas; f++) {
	            String actual = alineado[f][c];
	            if (!actual.isEmpty()) {
	                if (palabra == null) {
	                    palabra = actual;
	                } else if (!palabra.equals(actual)) {
	                    return false; // conflicto
	                }
	            }
	        }
	    }
	    return true;
	}

}
