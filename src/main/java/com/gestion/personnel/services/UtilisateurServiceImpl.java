package com.gestion.personnel.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.gestion.personnel.dto.UtilisateurDto;
import com.gestion.personnel.models.Utilisateur;
import com.gestion.personnel.repositories.UtilisateurRepository;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Service
public class UtilisateurServiceImpl implements UtilisateurService, UserDetailsService {

	@Autowired
    private UtilisateurRepository utilisateurRepository;
	
	@Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public Utilisateur inscription(UtilisateurDto utilisateurDto) {
        // Validation de l'email
        if (!isValidEmail(utilisateurDto.getEmail())) {
            throw new IllegalArgumentException("Email invalide : " + utilisateurDto.getEmail());
        }

        // Vérifier si l'email est déjà utilisé
        if (utilisateurExiste(utilisateurDto.getEmail())) {
            throw new RuntimeException("Votre email est déjà utilisé");
        }

        // Chiffrer le mot de passe
        String encryptedPassword = passwordEncoder.encode(utilisateurDto.getPassword());

        // Convertir UtilisateurDto en entité Utilisateur
        Utilisateur utilisateur = Utilisateur.builder()
                .nom(utilisateurDto.getNom())
                .email(utilisateurDto.getEmail())
                .password(encryptedPassword) // Utiliser le mot de passe chiffré
                .actif(true)
                .build();

        // Sauvegarder l'entité utilisateur
        return utilisateurRepository.save(utilisateur); // Retourner l'utilisateur enregistré
    }

    private boolean isValidEmail(String email) {
        return email != null && email.contains("@");
    }

    private boolean utilisateurExiste(String email) {
        return utilisateurRepository.findByEmail(email).isPresent();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return utilisateurRepository
                .findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Aucun utilisateur ne correspond à cet identifiant"));
    }
}
