package com.gestion.personnel.models;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.*;

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
@Entity
@Table(name = "utilisateur") 
public class Utilisateur implements UserDetails {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUtlisateur;
    
    private String password;
    
    private String nom;
    
    private String email;
    
    private boolean actif = false;

    // Retourne le mot de passe
    @Override
    public String getPassword() {
        return this.password;
    }

    // Retourne le nom de l'utilisateur comme identifiant (peut être l'email selon ta logique)
    @Override
    public String getUsername() {
        return this.nom;  // ou this.email si tu veux utiliser l'email pour l'authentification
    }

    // Retourne si le compte est activé
    @Override
    public boolean isEnabled() {
        return this.actif;
    }

    // Retourne si le compte est expiré (par défaut, retourne true)
    @Override
    public boolean isAccountNonExpired() {
        return true;  // Si tu gères l'expiration des comptes, change cela
    }

    // Retourne si le compte est verrouillé
    @Override
    public boolean isAccountNonLocked() {
        return true;  // Si tu gères le verrouillage des comptes, change cela
    }

    // Retourne si les identifiants de l'utilisateur ont expiré
    @Override
    public boolean isCredentialsNonExpired() {
        return true;  // Si tu gères l'expiration des credentials, change cela
    }

    // Retourne les rôles ou autorisations de l'utilisateur (vide si tu n'as pas de rôles)
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Si tu n'as pas de rôles pour l'utilisateur, tu peux retourner une liste vide
        return java.util.Collections.emptyList();
    }
}
