package com.gestion.personnel.dto;

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
public class DirectionDto {
	private Integer idDirection;

	private String nomDirection;

	private String description;
	private Integer entrepriseId;
}
