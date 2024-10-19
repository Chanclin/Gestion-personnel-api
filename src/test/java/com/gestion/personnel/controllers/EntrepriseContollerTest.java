package com.gestion.personnel.controllers;

import com.gestion.personnel.dto.EntrepriseDto;
import com.gestion.personnel.models.Entreprise;
import com.gestion.personnel.services.EntrepriseServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class EntrepriseControllerTest {

    @InjectMocks
    private EntrepriseController entrepriseController;

    @Mock
    private EntrepriseServiceImpl entrepriseService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void listerEntreprisesTest() {
        // Given
        List<EntrepriseDto> entreprises = new ArrayList<>();
        entreprises.add(new EntrepriseDto(1, "Entreprise 1", "Adresse 1", "email1@gmail.com", new ArrayList<>()));
        entreprises.add(new EntrepriseDto(2, "Entreprise 2", "Adresse 2", "email2@gmail.com", new ArrayList<>()));

        when(entrepriseService.listerEntreprises()).thenReturn(entreprises);

        // When
        ResponseEntity<List<EntrepriseDto>> response = entrepriseController.listerEntreprises();

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
        assertEquals("Entreprise 1", response.getBody().get(0).getNomEntreprise());
        verify(entrepriseService, times(1)).listerEntreprises();
    }

    @Test
    void creerEntrepriseTest() {
        // Given
        EntrepriseDto entrepriseDto = new EntrepriseDto(null, "Nouvelle Entreprise", "Nouvelle Adresse", "nouvelle@gmail.com", new ArrayList<>());
        Entreprise savedEntreprise = new Entreprise(1, "Entreprise 1", "Adresse 1", "email1@gmail.com", new ArrayList<>()); // Objet Entreprise

        when(entrepriseService.creerEntreprise(any(EntrepriseDto.class))).thenReturn(savedEntreprise);

        // When
        ResponseEntity<Entreprise> response = entrepriseController.creerEntreprise(entrepriseDto);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Entreprise 1", response.getBody().getNomEntreprise());
        verify(entrepriseService, times(1)).creerEntreprise(any(EntrepriseDto.class));
    }

    @Test
    void modifierEntrepriseTest() {
        // Given
        Integer id = 1;
        EntrepriseDto entrepriseDto = new EntrepriseDto(id, "Entreprise Modifiée", "Adresse Modifiée", "modifiee@gmail.com", new ArrayList<>());
        Entreprise updatedEntreprise = new Entreprise(id, "Entreprise Modifiée", "Adresse Modifiée", "modifiee@gmail.com", new ArrayList<>()); // Objet Entreprise

        // Simule le comportement du service pour retourner l'entité Entreprise
        when(entrepriseService.modifierEntreprise(eq(id), any(EntrepriseDto.class))).thenReturn(updatedEntreprise); // Retourne Entreprise

        // When
        Entreprise result = entrepriseController.modifierEntreprise(id, entrepriseDto);

        // Then
        assertNotNull(result);
        assertEquals("Entreprise Modifiée", result.getNomEntreprise());
        verify(entrepriseService, times(1)).modifierEntreprise(eq(id), any(EntrepriseDto.class));
    }


    @Test
    void supprimerEntrepriseTest() {
        // Given
        Integer id = 1;

        // When
        entrepriseController.supprimerEntreprise(id);

        // Then
        verify(entrepriseService, times(1)).supprimerEntrepriseParId(id);
    }

    @Test
    void obtenirEntrepriseTest() {
        // Given
        Integer id = 1;
        EntrepriseDto entrepriseDto = new EntrepriseDto(id, "Entreprise Trouvée", "Adresse Trouvée", "trouvee@gmail.com", new ArrayList<>());
        when(entrepriseService.obtenirEntrepriseParId(id)).thenReturn(entrepriseDto);

        // When
        ResponseEntity<EntrepriseDto> response = entrepriseController.obtenirEntreprise(id);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Entreprise Trouvée", response.getBody().getNomEntreprise());
        verify(entrepriseService, times(1)).obtenirEntrepriseParId(id);
    }
}
