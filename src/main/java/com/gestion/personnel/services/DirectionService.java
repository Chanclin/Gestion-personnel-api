package com.gestion.personnel.services;

import com.gestion.personnel.models.Direction;
import com.gestion.personnel.repositories.DirectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DirectionService {

    @Autowired
    private DirectionRepository directionRepository;

    // Créer une nouvelle entreprise
    public Direction creerDirection( Direction  direction) {
        return  directionRepository.save( direction);
    }

    // Lister toutes les  direction
    public List< Direction> listerDirections() {
        return  directionRepository.findAll();
    }

    // Obtenir une  direction par ID
    public Optional< Direction> obtenirDirectionParId(Integer id) {
        return  directionRepository.findById(id);
    }

    // Modifier une  direction
    public  Direction modifierDirection(Integer id,  Direction  directionDetails) {
    	 Direction  direction =  directionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(" Direction non trouvée"));
    	 direction.setNomDirection(directionDetails.getNomDirection());
    	 direction.setDescriptionDirection(directionDetails.getDescriptionDirection());
        return directionRepository.save(direction);
    }

    // Supprimer une direction
    public void supprimerDirection(Integer id) {
    	directionRepository.deleteById(id);
    }
}
