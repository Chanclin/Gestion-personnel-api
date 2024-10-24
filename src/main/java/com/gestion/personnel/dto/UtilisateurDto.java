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
public class UtilisateurDto {
private Integer idUtlisateur;
	
	private String password;
	
	private String nom;
	
	private String email;
	
	private boolean actif = false;

}
