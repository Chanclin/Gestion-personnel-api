package com.gestion.personnel.repositories;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
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
	@Order(1)
	public void saveEmployeeTest(){

	    // Action
	    Entreprise entreprise = Entreprise.builder()
	            .nomEntreprise("Sam")
	            .adresseEntreprise("Curran")
	            .emailEntreprise("sam@gmail.com")
	            .build();

	    entrepriseRepository.save(entreprise);

	    // simulation de l'erreur
	    Assertions.assertThat(entreprise.getNomEntreprise()).isEqualTo("Sam");
	}
	
	@Test
	@Order(2)
	public void saveEmployeewithoutNameTest(){

	    // Action
	    Entreprise entreprise = Entreprise.builder()
	            .adresseEntreprise("Curran")
	            .emailEntreprise("sam@gmail.com")
	            .build();

	    assertThrows(RuntimeException.class, () -> {
	    	entrepriseRepository.save(entreprise);
	    });
	    }

	@Test
	@Order(3)
	public void updateEntrepriseTest() {
	    // Sauvegarde d'une entreprise
	    Entreprise entreprise = Entreprise.builder()
	            .nomEntreprise("Old Name")
	            .adresseEntreprise("Old Address")
	            .emailEntreprise("old@gmail.com")
	            .build();
	    entrepriseRepository.save(entreprise);

	    // Mise à jour des informations
	    entreprise.setNomEntreprise("New Name");
	    entreprise.setAdresseEntreprise("New Address");
	    entreprise.setEmailEntreprise("new@gmail.com");
	    Entreprise updatedEntreprise = entrepriseRepository.save(entreprise);

	    // Vérification
	    Assertions.assertThat(updatedEntreprise.getNomEntreprise()).isEqualTo("New Name");
	    Assertions.assertThat(updatedEntreprise.getAdresseEntreprise()).isEqualTo("New Address");
	    Assertions.assertThat(updatedEntreprise.getEmailEntreprise()).isEqualTo("new@gmail.com");
	}
	
	@Test
	@Order(4)
	public void deleteEntrepriseTest() {
	    // Sauvegarde d'une entreprise
	    Entreprise entreprise = Entreprise.builder()
	            .nomEntreprise("Delete Me")
	            .adresseEntreprise("To Be Deleted")
	            .emailEntreprise("delete@gmail.com")
	            .build();
	    entrepriseRepository.save(entreprise);

	    // Suppression
	    entrepriseRepository.delete(entreprise);

	    // Vérification
	    Assertions.assertThat(entrepriseRepository.findById(entreprise.getIdEntreprise())).isEmpty();
	}

	@Test
	@Order(5)
	public void getEntrepriseByIdTest() {
	    // Sauvegarde d'une entreprise
	    Entreprise entreprise = Entreprise.builder()
	            .nomEntreprise("Find Me")
	            .adresseEntreprise("Address")
	            .emailEntreprise("findme@gmail.com")
	            .build();
	    Entreprise savedEntreprise = entrepriseRepository.save(entreprise);

	    // Récupération par ID
	    Entreprise foundEntreprise = entrepriseRepository.findById(savedEntreprise.getIdEntreprise()).orElse(null);

	    // Vérification
	    Assertions.assertThat(foundEntreprise).isNotNull();
	    Assertions.assertThat(foundEntreprise.getNomEntreprise()).isEqualTo("Find Me");
	}

	@Test
	@Order(6)
	public void getAllEntreprisesTest() {
	    // Sauvegarde de plusieurs entreprises
	    Entreprise entreprise1 = Entreprise.builder()
	            .nomEntreprise("Entreprise 1")
	            .adresseEntreprise("Adresse 1")
	            .emailEntreprise("email1@gmail.com")
	            .build();
	    Entreprise entreprise2 = Entreprise.builder()
	            .nomEntreprise("Entreprise 2")
	            .adresseEntreprise("Adresse 2")
	            .emailEntreprise("email2@gmail.com")
	            .build();
	    entrepriseRepository.save(entreprise1);
	    entrepriseRepository.save(entreprise2);

	    // Récupération de toutes les entreprises
	    Iterable<Entreprise> entreprises = entrepriseRepository.findAll();

	    // Vérification
	    Assertions.assertThat(entreprises).hasSize(2);
	    Assertions.assertThat(entreprises).extracting(Entreprise::getNomEntreprise)
	            .contains("Entreprise 1", "Entreprise 2");
	}

	@Test
	@Order(6)
	public void saveEntrepriseWithDuplicateEmailTest() {
	    // Sauvegarde de la première entreprise
	    Entreprise entreprise1 = Entreprise.builder()
	            .nomEntreprise("Entreprise 1")
	            .adresseEntreprise("Adresse 1")
	            .emailEntreprise("duplicate@gmail.com")
	            .build();
	    entrepriseRepository.save(entreprise1);

	    // Tentative de sauvegarde d'une deuxième entreprise avec le même email
	    Entreprise entreprise2 = Entreprise.builder()
	            .nomEntreprise("Entreprise 2")
	            .adresseEntreprise("Adresse 2")
	            .emailEntreprise("duplicate@gmail.com")
	            .build();

	    // S'attend à une exception pour la duplication d'email
	    assertThrows(RuntimeException.class, () -> {
	        entrepriseRepository.save(entreprise2);
	    });
	}


 
}