package com.gestion.personnel.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestion.personnel.models.Utilisateur;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer>{
	Optional<Utilisateur> findByEmail(String email);

	Utilisateur findByNom(String nom);
}
