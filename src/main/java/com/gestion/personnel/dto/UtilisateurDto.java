package com.gestion.personnel.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UtilisateurDto {
    private Integer idUtlisateur;
    private String password;
    private String nom;
    private String email;

    // Constructeur personnalisé avec nom et email uniquement
    public UtilisateurDto(String nom, String email) {
        this.nom = nom;
        this.email = email;
    }
}
