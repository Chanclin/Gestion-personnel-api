package com.gestion.personnel.services;

import com.gestion.personnel.models.Direction;
import com.gestion.personnel.models.Entreprise;
import com.gestion.personnel.repositories.EntrepriseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EntrepriseService {

    @Autowired
    private EntrepriseRepository entrepriseRepository;

    // Créer une nouvelle entreprise avec ses directions
    public Entreprise creerEntreprise(Entreprise entreprise) {
    	// Pour chaque direction, on assigne l'entreprise correspondante
        if (entreprise.getDirections() != null) {
            for (Direction direction : entreprise.getDirections()) {
                direction.setEntreprise(entreprise);  // Associer l'entreprise à la direction
            }
        }

        // Sauvegarder l'entreprise avec ses directions
        return entrepriseRepository.save(entreprise);
    }

    // Lister toutes les entreprises
    public List<Entreprise> listerEntreprises() {
        return entrepriseRepository.findAll();
    }

    // Obtenir une entreprise par ID
    public Optional<Entreprise> obtenirEntrepriseParId(Integer id) {
        return entrepriseRepository.findById(id);
    }

    // Modifier une entreprise avec ses directions
    public Entreprise modifierEntreprise(Integer id, Entreprise entrepriseDetails) {
        Entreprise entreprise = entrepriseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entreprise non trouvée"));

        entreprise.setNomEntreprise(entrepriseDetails.getNomEntreprise());
        entreprise.setAdresseEntreprise(entrepriseDetails.getAdresseEntreprise());
        entreprise.setEmailEntreprise(entrepriseDetails.getEmailEntreprise());

        // Met à jour les directions si présentes
        if (entrepriseDetails.getDirections() != null) {
            entreprise.setDirections(entrepriseDetails.getDirections());
        }

        return entrepriseRepository.save(entreprise);
    }

    // Supprimer une entreprise et ses directions associées
    public void supprimerEntreprise(Integer id) {
        entrepriseRepository.deleteById(id);
    }
}
