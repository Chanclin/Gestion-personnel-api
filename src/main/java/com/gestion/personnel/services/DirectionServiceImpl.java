package com.gestion.personnel.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gestion.personnel.dto.DirectionDto;
import com.gestion.personnel.mappers.DirectionMapper;
import com.gestion.personnel.models.Direction;
import com.gestion.personnel.models.Entreprise;
import com.gestion.personnel.repositories.DirectionRepository;
import com.gestion.personnel.repositories.EntrepriseRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class DirectionServiceImpl implements DirectionService {

	private final DirectionRepository directionRepository;

	private final DirectionMapper directionMapper;

	private final EntrepriseRepository entrepriseRepository;

	// Créer une nouvelle direction
	// public Direction creerDirection(Direction direction) {
	// return directionRepository.save(direction);
	// }
	// public Direction creerDirection(DirectionDto directionDto) {
	// return directionRepository.save(directionMapper.ToDirection(directionDto));
	// }
	public Direction creerDirection(DirectionDto directionDto) {
		Entreprise entreprise = entrepriseRepository.findById(directionDto.getEntrepriseId())
				.orElseThrow(() -> new RuntimeException("Entreprise non trouvée"));

		Direction direction = directionMapper.ToDirection(directionDto);
		direction.setEntreprise(entreprise); // Associer l'entreprise à la direction

		return directionRepository.save(direction);
	}

	// Lister toutes les directions
	// public List<Direction> listerDirections() {
	// return directionRepository.findAll();
	// }
	public List<DirectionDto> listerDirections() {
		List<Direction> directions = directionRepository.findAll();
		return directionMapper.ToDtoList(directions);
	}

	// Obtenir une direction par ID
	// public Optional<Direction> obtenirDirectionParId(Integer id) {
	// return directionRepository.findById(id);
	// }
	public DirectionDto obtenirDirectionParId(Integer id) {
		return directionRepository.findById(id).map(directionMapper::ToDirectionDto).orElse(new DirectionDto());
	}

	// Modifier une direction
	public Direction modifierDirection(Integer id, Direction directionDetails) {
		Direction direction = directionRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Direction non trouvée"));
		direction.setNomDirection(directionDetails.getNomDirection());
		direction.setDescription(directionDetails.getDescription());
		return directionRepository.save(direction);
	}

	// public DirectionDto modifierDirection(Integer id, DirectionDto directionDto)
	// {
	// Direction direction = directionRepository.findById(id)
	// .orElseThrow(() -> new RuntimeException("Direction non trouvée"));

	// Mise à jour des propriétés de l'entité avec les valeurs du DTO
	// direction.setNomDirection(directionDto.getNomDirection());
	// direction.setDescription(directionDto.getDescription());
	// Direction directionMiseAJour = directionRepository.save(direction);

	// return directionMapper.ToDirectionDto(directionMiseAJour);
	// }
	public DirectionDto modifierDirection(Integer id, DirectionDto directionDto) {
		Direction direction = directionRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Direction non trouvée"));

		// Récupérer l'entreprise associée
		Entreprise entreprise = entrepriseRepository.findById(directionDto.getEntrepriseId()).orElseThrow(
				() -> new RuntimeException("Entreprise non trouvée pour l'ID : " + directionDto.getEntrepriseId()));

		// Mettre à jour les propriétés de la direction
		direction.setNomDirection(directionDto.getNomDirection());
		direction.setDescription(directionDto.getDescription());
		direction.setEntreprise(entreprise);

		Direction directionMiseAJour = directionRepository.save(direction);
		return directionMapper.ToDirectionDto(directionMiseAJour);
	}

	// Supprimer une direction
	public void supprimerDirectionParId(Integer id) {
		directionRepository.deleteById(id);
	}

}
