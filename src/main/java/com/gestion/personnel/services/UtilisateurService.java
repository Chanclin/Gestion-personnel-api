package com.gestion.personnel.services;

import org.springframework.security.core.userdetails.UserDetails;

import com.gestion.personnel.dto.UtilisateurDto;
import com.gestion.personnel.models.Utilisateur;

public interface UtilisateurService{
	Utilisateur inscription(UtilisateurDto utilisateurDto);

	UserDetails loadUserByUsername(String email);

	Utilisateur getByUsername(String username);

}
