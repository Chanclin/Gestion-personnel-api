package com.gestion.personnel.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EntrepriseDto {
	private Integer idEntreprise;

	private String nomEntreprise;

	private String adresseEntreprise;

	private String emailEntreprise;
	
	private List<DirectionDto> directions;
}
