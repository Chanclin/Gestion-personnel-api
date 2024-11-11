package com.gestion.personnel.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestion.personnel.dto.AuthentificationDTO;
import com.gestion.personnel.dto.UtilisateurDto;
import com.gestion.personnel.models.Utilisateur;
import com.gestion.personnel.repositories.UtilisateurRepository;
import com.gestion.personnel.security.JwtService;
import com.gestion.personnel.services.UtilisateurService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/utilisateur/")
public class UtilisateurController {
	
	private AuthenticationManager authenticationManager;
	private UtilisateurService utilisateurService;
	private JwtService jwtService;

    /*@PostMapping(path = "inscription")
    public void inscription(@RequestBody UtilisateurDto utilisateurDto) {
        log.info("Inscription");
        this.utilisateurService.inscription(utilisateurDto);
    }*/
	/*@PostMapping(path = "inscription")
	public Map<String, Object> inscription(@RequestBody UtilisateurDto utilisateurDto) {
	    log.info("Inscription");
	    
	    Map<String, Object> response = new HashMap<>();
	    try {
	        this.utilisateurService.inscription(utilisateurDto);
	        response.put("success", true);
	        response.put("message", "Inscription réussie");
	    } catch (Exception e) {
	        log.error("Erreur lors de l'inscription", e);
	        response.put("success", false);
	        response.put("message", "Échec de l'inscription");
	    }
	    return response;
	}*/
	@PostMapping(path = "inscription")
	public ResponseEntity<Map<String, Object>> inscription(@RequestBody UtilisateurDto utilisateurDto) {
	    log.info("Inscription");

	    Map<String, Object> response = new HashMap<>();
	    try {
	        this.utilisateurService.inscription(utilisateurDto);
	        response.put("success", true);
	        response.put("message", "Inscription réussie");
	        return ResponseEntity.ok(response);  // Renvoie une réponse HTTP 200 avec le JSON
	    } catch (Exception e) {
	        log.error("Erreur lors de l'inscription", e);
	        response.put("success", false);
	        response.put("message", "Échec de l'inscription");
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response); // Renvoie HTTP 500 en cas d'erreur
	    }
	}
    
    @PostMapping(path= "connexion")
    public Map<String, String> connexion(@RequestBody AuthentificationDTO authentificationDTO){
    	final Authentication authentication = authenticationManager.authenticate(
    			new UsernamePasswordAuthenticationToken(authentificationDTO.username(), authentificationDTO.password())
    			);
    	if (authentication.isAuthenticated()) {
    		return this.jwtService.generate(authentificationDTO.username());
    		
    	}
    	
    	/*if (authentication.isAuthenticated()) {
            // Générer le token JWT
            String token = jwtService.generate(authentificationDTO.username()).get("token");

            // Récupérer l'utilisateur par email
            Utilisateur utilisateur = utilisateurRepository.findByEmail(authentificationDTO.username())
                    .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

            // Créer un DTO avec les informations de l'utilisateur
            UtilisateurDto utilisateurDTO = new UtilisateurDto(utilisateur.getNom(), utilisateur.getEmail());

            // Préparer la réponse avec le token et le DTO
            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("utilisateur", utilisateurDTO); // Inclure le DTO avec les informations nécessaires
            return response;
        }*/
       
    	log.info("resultat", authentication.isAuthenticated());
    	return null;
    	
    	
    }
}