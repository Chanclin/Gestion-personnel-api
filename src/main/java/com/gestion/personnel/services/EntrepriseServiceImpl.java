package com.gestion.personnel.services;

import com.gestion.personnel.dto.DirectionDto;

import com.gestion.personnel.dto.EntrepriseDto;
import com.gestion.personnel.mappers.DirectionMapper;
import com.gestion.personnel.mappers.EntrepriseMappper;
import com.gestion.personnel.models.Direction;
import com.gestion.personnel.models.Entreprise;
import com.gestion.personnel.repositories.EntrepriseRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
@RequiredArgsConstructor
@Service
public class EntrepriseServiceImpl implements EntrepriseService {

	private final EntrepriseRepository entrepriseRepository;

	private final EntrepriseMappper entrepriseMapper;

	private final DirectionMapper directionMapper;

	public Entreprise creerEntreprise(EntrepriseDto entrepriseDto) {
		// Mapper le DTO en entité Entreprise
		Entreprise entreprise = entrepriseMapper.ToEntreprise(entrepriseDto);

		// Pour chaque direction, associer l'entreprise correspondante
		if (entreprise.getDirections() != null) {
			for (Direction direction : entreprise.getDirections()) {
				direction.setEntreprise(entreprise); // Associer l'entreprise à la direction
			}
		}

		// Sauvegarder l'entreprise avec ses directions associées
		return entrepriseRepository.save(entreprise);
	}

	// Lister toutes les entreprises
	public List<EntrepriseDto> listerEntreprises() {
		// return entrepriseRepository.findAll();
		List<Entreprise> entreprises = entrepriseRepository.findAll();
		return entrepriseMapper.ToDtoList(entreprises);
	}

	// Obtenir une entreprise par ID
	// public Optional<Entreprise> obtenirEntrepriseParId(Integer id) {
	// return entrepriseRepository.findById(id);
	// }
	public EntrepriseDto obtenirEntrepriseParId(Integer id) {
		return entrepriseRepository.findById(id).map(entrepriseMapper::ToEntrepriseDto).orElse(new EntrepriseDto());
	}

	public Entreprise modifierEntreprise(Integer id, EntrepriseDto entrepriseDto) {
		// Récupérer l'entreprise existante
		Entreprise entreprise = entrepriseRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Entreprise non trouvée"));

		// Mise à jour des informations de l'entreprise
		entreprise.setNomEntreprise(entrepriseDto.getNomEntreprise());
		entreprise.setAdresseEntreprise(entrepriseDto.getAdresseEntreprise());
		entreprise.setEmailEntreprise(entrepriseDto.getEmailEntreprise());

		// Gestion des directions : remplacer les anciennes directions par les nouvelles
		entreprise.getDirections().clear(); // Vider les directions actuelles

		// Ajouter les nouvelles directions
		if (entrepriseDto.getDirections() != null) {
			for (DirectionDto directionDto : entrepriseDto.getDirections()) {
				Direction direction = directionMapper.ToDirection(directionDto); // Mapper le DTO vers l'entité
				direction.setEntreprise(entreprise); // Associer chaque direction à l'entreprise
				entreprise.getDirections().add(direction); // Ajouter la direction mise à jour
			}
		}

		// Sauvegarder l'entreprise et ses directions mises à jour
		return entrepriseRepository.save(entreprise);
	}

	// Supprimer une entreprise
	public void supprimerEntrepriseParId(Integer id) {
		entrepriseRepository.deleteById(id);
	}
}
