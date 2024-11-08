package com.gestion.personnel.controllers;

import com.gestion.personnel.dto.EntrepriseDto;
import com.gestion.personnel.models.Entreprise;
import com.gestion.personnel.services.EntrepriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/entreprises")
public class EntrepriseController {

    @Autowired
    private EntrepriseService entrepriseService;

    // Lister toutes les entreprises
    @GetMapping 
    public ResponseEntity<List<EntrepriseDto>> listerEntreprises() {
    	return ResponseEntity.ok(entrepriseService.listerEntreprises()); 
    }

    // Créer une nouvelle entreprise
    @PostMapping
    public ResponseEntity<Entreprise> creerEntreprise(@RequestBody EntrepriseDto entrepriseDto) {
		return ResponseEntity.ok(entrepriseService.creerEntreprise(entrepriseDto));
	}

    // Modifier une entreprise par ID
    @PutMapping("/modifier/{id}")
    public Entreprise modifierEntreprise(@PathVariable Integer id, @RequestBody EntrepriseDto entrepriseDto) {
		return entrepriseService.modifierEntreprise(id, entrepriseDto);
	}

    // Supprimer une entreprise par ID
    @DeleteMapping("/supprimer/{id}")
    public void supprimerEntreprise(@PathVariable Integer id) {
    	entrepriseService.supprimerEntrepriseParId(id);
	}
    
    @GetMapping("/lister/{id}")
	public ResponseEntity<EntrepriseDto> obtenirEntreprise(@PathVariable Integer id) {
		return ResponseEntity.ok(entrepriseService.obtenirEntrepriseParId(id));
	}
    
}
