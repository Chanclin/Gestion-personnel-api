package com.gestion.personnel.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestion.personnel.dto.DirectionDto;
import com.gestion.personnel.models.Direction;
import com.gestion.personnel.services.DirectionServiceImpl;

@RestController
@RequestMapping("/api/directions")
public class DirectionControlleur {
	@Autowired
	private DirectionServiceImpl directionService;

	// Lister toutes les directions
	@GetMapping
	public ResponseEntity<List<DirectionDto>> listerDirections() {
		return ResponseEntity.ok(directionService.listerDirections());
	}

	// Créer une nouvelle direction
	@PostMapping
	public ResponseEntity<Direction> creerDirection(@RequestBody DirectionDto directionDto) {
		return ResponseEntity.ok(directionService.creerDirection(directionDto));
	}

	// Modifier une direction par ID
	@PutMapping("/modifier/{id}")
	public DirectionDto modifierDirection(@PathVariable Integer id, @RequestBody DirectionDto directionDto) {
		return directionService.modifierDirection(id, directionDto);
	}

	// Supprimer une entreprise par ID
	@DeleteMapping("/supprimer/{id}")
	public void supprimerDirection(@PathVariable Integer id) {
		directionService.supprimerDirectionParId(id);
	}

	@GetMapping("/lister/{id}")
	public ResponseEntity<DirectionDto> obtenirDirection(@PathVariable Integer id) {
		return ResponseEntity.ok(directionService.obtenirDirectionParId(id));
	}

}
