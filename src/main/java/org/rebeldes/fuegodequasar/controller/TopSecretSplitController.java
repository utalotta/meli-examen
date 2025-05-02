package org.rebeldes.fuegodequasar.controller;

import org.rebeldes.fuegodequasar.dto.TopSecretRequest;
import org.rebeldes.fuegodequasar.dto.TopSecretResponse;
import org.rebeldes.fuegodequasar.model.Posicion;
import org.rebeldes.fuegodequasar.service.TriangulacionService;
import org.rebeldes.fuegodequasar.service.MensajeService;
import org.rebeldes.fuegodequasar.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/topsecret_split")
public class TopSecretSplitController {
	
    @Autowired
    private TriangulacionService triangulacionService;
    
    @Autowired
    private MensajeService mensajeService;

    private final Map<String, TopSecretRequest> satelitesRecibidos = new ConcurrentHashMap<>();

    @PostMapping("/{nombre}")
    public void guardarSatelite(@PathVariable String nombre, @RequestBody TopSecretRequest satelite) {
    	satelite.setName(nombre);
        satelitesRecibidos.put(nombre.toLowerCase(), satelite);
    }

    @GetMapping
    public TopSecretResponse procesarSplit() {
        if (satelitesRecibidos.size() < 3)
            throw new NotFoundException("Faltan satélites. Recibidos: " + satelitesRecibidos.keySet());

        List<TopSecretRequest> satelites = new ArrayList<>(satelitesRecibidos.values());

        double[] distancias = new double[satelites.size()];
        String[][] mensajes = new String[satelites.size()][];

        for (int i = 0; i < satelites.size(); i++) {
            distancias[i] = satelites.get(i).getDistance();
            mensajes[i] = satelites.get(i).getMessage().toArray(new String[0]);
        }

        Posicion posicion = triangulacionService.getLocation(distancias);
        String mensaje = mensajeService.getMessage(mensajes);
        
        if (posicion == null || mensaje == null || mensaje.isEmpty()) {
            throw new NotFoundException("No se pudo determinar la posicion o el mensaje.");
        }

        return new TopSecretResponse(posicion, mensaje);
    }
}
