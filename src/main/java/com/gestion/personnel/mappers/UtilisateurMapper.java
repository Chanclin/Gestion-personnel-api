package com.gestion.personnel.mappers;

import com.gestion.personnel.dto.UtilisateurDto;
import com.gestion.personnel.models.Utilisateur;

public interface UtilisateurMapper {
		UtilisateurDto ToUtilisateurDto(Utilisateur utilisateur);
		
		Utilisateur ToUtilisateur(UtilisateurDto utilisateurDto);
		
}
