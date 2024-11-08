package com.gestion.personnel.services;

import java.util.List;

import com.gestion.personnel.dto.DirectionDto;
import com.gestion.personnel.models.Direction;

public interface DirectionService {
	Direction creerDirection(DirectionDto directionDto);

	List<DirectionDto> listerDirections();

	DirectionDto obtenirDirectionParId(Integer id);

	Direction modifierDirection(Integer id, Direction directionDetails);

	void supprimerDirectionParId(Integer id);
}
