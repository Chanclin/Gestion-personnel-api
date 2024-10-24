package com.gestion.personnel.services;

import com.gestion.personnel.dto.UtilisateurDto;
import com.gestion.personnel.models.Utilisateur;

public interface UtilisateurService{
	Utilisateur inscription(UtilisateurDto utilisateurDto);

}
