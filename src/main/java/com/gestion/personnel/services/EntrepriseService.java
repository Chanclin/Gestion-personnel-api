package com.gestion.personnel.services;

import java.util.List;

import com.gestion.personnel.dto.EntrepriseDto;
import com.gestion.personnel.models.Entreprise;

public interface EntrepriseService {
	Entreprise creerEntreprise(EntrepriseDto entrepriseDto);

	List<EntrepriseDto> listerEntreprises();

	EntrepriseDto obtenirEntrepriseParId(Integer id);

	Entreprise modifierEntreprise(Integer id, EntrepriseDto entrepriseDto);

	void supprimerEntrepriseParId(Integer id);
}
