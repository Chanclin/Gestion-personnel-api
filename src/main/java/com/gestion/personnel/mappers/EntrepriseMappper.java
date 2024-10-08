package com.gestion.personnel.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import com.gestion.personnel.dto.EntrepriseDto;
import com.gestion.personnel.models.Entreprise;

@Mapper(componentModel = "spring", uses = {DirectionMapper.class})
public interface EntrepriseMappper {
	EntrepriseDto ToEntrepriseDto(Entreprise entreprise);

	Entreprise ToEntreprise(EntrepriseDto entrepriseDto);

	List<EntrepriseDto> ToDtoList(List<Entreprise> entreprises);
}
