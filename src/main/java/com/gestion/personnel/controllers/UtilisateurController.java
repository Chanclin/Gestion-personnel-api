package com.gestion.personnel.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
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
    
	@PostMapping(path = "connexion")
	public ResponseEntity<Map<String, Object>> connexion(@RequestBody AuthentificationDTO authentificationDTO) {
	    Map<String, Object> response = new HashMap<>();
	    log.info("Tentative de connexion pour l'utilisateur: {}", authentificationDTO.username());

	    final Authentication authentication = authenticationManager.authenticate(
	            new UsernamePasswordAuthenticationToken(authentificationDTO.username(), authentificationDTO.password())
	    );

	    if (authentication.isAuthenticated()) {
	        log.info("Authentification réussie pour l'utilisateur: {}", authentificationDTO.username());

	        // Générer le token
	        Map<String, String> token = jwtService.generate(authentificationDTO.username());
	        
	        // Récupérer l'utilisateur
	        Utilisateur utilisateur = utilisateurService.getByUsername(authentificationDTO.username());

	        // Ajouter le token et les informations utilisateur à la réponse
	        response.put("token", token.get("token"));
	       response.put("user", authentificationDTO.username());

	        return ResponseEntity.ok(response);
	    }

	    log.warn("Authentification échouée pour l'utilisateur: {}", authentificationDTO.username());
	    response.put("message", "Authentification échouée");
	    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
	}



    
    @GetMapping(path = "profile")
    public UtilisateurDto getUtilisateurProfile() {
        // Récupère le nom d'utilisateur du contexte de sécurité
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        // Récupère l'utilisateur par son nom d'utilisateur
        Utilisateur utilisateur = utilisateurService.getByUsername(username);
        // Retourne le DTO avec les informations utiles
        return new UtilisateurDto(utilisateur.getNom(), utilisateur.getEmail()); // Utilise le constructeur adapté
    }


}