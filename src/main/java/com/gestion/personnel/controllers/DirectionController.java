package com.gestion.personnel.controllers;

import com.gestion.personnel.models.Direction;
import com.gestion.personnel.services.DirectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/directions")
public class DirectionController {

    @Autowired
    private DirectionService directionService;

    // Lister toutes les direction
    @GetMapping("/lister")
    public ResponseEntity<List<Direction>> listerDirections() {
    	List<Direction> directions = directionService.listerDirections();
    	return ResponseEntity.ok(directions);
    }

    // Créer une nouvelle direction
    @PostMapping("/creer")
    public ResponseEntity<Direction> creerDirection(@RequestBody Direction direction) {
    	Direction nouvelledirection = directionService.creerDirection(direction);
        return ResponseEntity.ok(nouvelledirection);
    }


    // Modifier une direction par ID
    @PutMapping("/modifier/{id}")
    public ResponseEntity<Direction> modifierDirection(@PathVariable Integer id, @RequestBody Direction directionDetails) {
    	Direction directionModifiee = directionService.modifierDirection(id, directionDetails);
        return ResponseEntity.ok(directionModifiee);
    }

    // Supprimer une direction par ID
    @DeleteMapping("/supprimer/{id}")
    public ResponseEntity<Void> supprimerDirection(@PathVariable Integer id) {
    	directionService.supprimerDirection(id);
        return ResponseEntity.noContent().build();
    }

}
