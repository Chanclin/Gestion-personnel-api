package com.gestion.personnel.controllers;

import com.gestion.personnel.dto.DirectionDto;
import com.gestion.personnel.dto.EntrepriseDto;
import com.gestion.personnel.models.Direction;
import com.gestion.personnel.models.Entreprise;
import com.gestion.personnel.services.EntrepriseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/api/entreprises")
public class EntrepriseController {

    @Autowired
    private EntrepriseServiceImpl entrepriseService;

    // Lister toutes les entreprises
    @GetMapping("/api/entreprises")
    public ResponseEntity<List<EntrepriseDto>> listerEntreprises() {
    	//List<Entreprise> entreprises = entrepriseService.listerEntreprises();
    	//return ResponseEntity.ok(entreprises);
    	return ResponseEntity.ok(entrepriseService.listerEntreprises()); 
    }

    // Créer une nouvelle entreprise
    @PostMapping("/api/entreprises")
    //public ResponseEntity<Entreprise> creerEntreprise(@RequestBody Entreprise entreprise) {
     //   Entreprise nouvelleEntreprise = entrepriseService.creerEntreprise(entreprise);
     //   return ResponseEntity.ok(nouvelleEntreprise);
   // }
    public ResponseEntity<Entreprise> creerEntreprise(@RequestBody EntrepriseDto entrepriseDto) {
		return ResponseEntity.ok(entrepriseService.creerEntreprise(entrepriseDto));
	}


    // Modifier une entreprise par ID
    @PutMapping("/api/entreprises/{idEntreprise}")
    //public ResponseEntity<Entreprise> modifierEntreprise(@PathVariable Integer id, @RequestBody Entreprise entrepriseDetails) {
     //   Entreprise entrepriseModifiee = entrepriseService.modifierEntreprise(id, entrepriseDetails);
       // return ResponseEntity.ok(entrepriseModifiee);
   // }
    public Entreprise modifierEntreprise(@PathVariable Integer id, @RequestBody EntrepriseDto entrepriseDto) {
		return entrepriseService.modifierEntreprise(id, entrepriseDto);
	}

    // Supprimer une entreprise par ID
    @DeleteMapping("/api/entreprises/{idEntreprise}")
   // public ResponseEntity<Void> supprimerEntreprise(@PathVariable Integer id) {
   //entrepriseService.supprimerEntreprise(id);
        //return ResponseEntity.noContent().build();
    //}
    public void supprimerEntreprise(@PathVariable Integer id) {
    	entrepriseService.supprimerEntrepriseParId(id);
	}
    
    @GetMapping("api/entreprises/{idEntreprise}")
	public ResponseEntity<EntrepriseDto> obtenirEntreprise(@PathVariable Integer id) {
		return ResponseEntity.ok(entrepriseService.obtenirEntrepriseParId(id));
	}


}
