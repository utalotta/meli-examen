package org.rebeldes.fuegodequasar.controller;

import org.rebeldes.fuegodequasar.dto.*;
import org.rebeldes.fuegodequasar.model.Posicion;
import org.rebeldes.fuegodequasar.service.TriangulacionService;
import org.rebeldes.fuegodequasar.service.MensajeService;
import org.rebeldes.fuegodequasar.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topsecret")
public class TopSecretController {
	@Autowired
    private TriangulacionService triangulacionService;
	
	@Autowired
	private MensajeService mensajeService;

    @PostMapping
    public TopSecretResponse procesarTopSecret(@RequestBody TopSecretRequestWraper data) {
    	List<TopSecretRequest> satelites = data.getSatellites();
    	
        double[] distancias = new double[satelites.size()];
        String[][] mensajes = new String[satelites.size()][];

        for (int i = 0; i < satelites.size(); i++) {
            distancias[i] = satelites.get(i).getDistance();
            List<String> mensajeList = satelites.get(i).getMessage();
            mensajes[i] = mensajeList.toArray(new String[0]);
        }

        Posicion posicion = triangulacionService.getLocation(distancias);
        String mensaje = mensajeService.getMessage(mensajes);

        if (posicion == null || mensaje == null || mensaje.isEmpty()) {
            throw new NotFoundException("No se pudo determinar la posicion o el mensaje.");
        }
        
        return new TopSecretResponse(posicion, mensaje);
    }
}
