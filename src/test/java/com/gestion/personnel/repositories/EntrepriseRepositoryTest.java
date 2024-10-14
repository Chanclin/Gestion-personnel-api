package com.gestion.personnel.repositories;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.gestion.personnel.models.Entreprise;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EntrepriseRepositoryTest {
	
	@Autowired
	private EntrepriseRepository entrepriseRepository;
	
	@Test
	public void saveEmployeeTest(){

	    // Action
	    Entreprise entreprise = Entreprise.builder()
	            .nomEntreprise("Sam")
	            .adresseEntreprise("Curran")
	            .emailEntreprise("sam@gmail.com")
	            .build();

	    entrepriseRepository.save(entreprise);
	    

	    // Vérifie que l'ID est supérieur à 0
	    Assertions.assertThat(entreprise.getIdEntreprise()).isGreaterThan(18);

	    // simulation de l'erreur
	    Assertions.assertThat(entreprise.getNomEntreprise()).isEqualTo("Erreur Test");
	    
	 // Ajouter une vérification que l'entreprise est bien sauvegardée dans la base de données
	    Entreprise savedEntreprise = entrepriseRepository.findById(entreprise.getIdEntreprise()).orElse(null);
	    Assertions.assertThat(savedEntreprise).isNotNull();
	    Assertions.assertThat(savedEntreprise.getNomEntreprise()).isEqualTo("Sam");
	}

 
}