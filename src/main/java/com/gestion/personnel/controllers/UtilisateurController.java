package com.gestion.personnel.controllers;

import java.util.Map;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestion.personnel.dto.AuthentificationDTO;
import com.gestion.personnel.dto.UtilisateurDto;
import com.gestion.personnel.security.JwtService;
import com.gestion.personnel.services.UtilisateurService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class UtilisateurController {
	
	private AuthenticationManager authenticationManager;
	private UtilisateurService utilisateurService;
	private JwtService jwtService;

    @PostMapping(path = "inscription")
    public void inscription(@RequestBody UtilisateurDto utilisateurDto) {
        log.info("Inscription");
        this.utilisateurService.inscription(utilisateurDto);
    }
    
    @PostMapping(path= "connexion")
    public Map<String, String> connexion(@RequestBody AuthentificationDTO authentificationDTO){
    	final Authentication authentication = authenticationManager.authenticate(
    			new UsernamePasswordAuthenticationToken(authentificationDTO.username(), authentificationDTO.password())
    			);
    	if (authentication.isAuthenticated()) {
    		return this.jwtService.generate(authentificationDTO.username());
    	}
    	log.info("resultat", authentication.isAuthenticated());
    	return null;
    	
    	
    }
}