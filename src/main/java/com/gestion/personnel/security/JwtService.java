package com.gestion.personnel.security;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import org.springframework.stereotype.Service;

import com.gestion.personnel.models.Utilisateur;
import com.gestion.personnel.services.UtilisateurService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class JwtService {

	private final String ENCRYPTION_KEY = "8iQmYQUm3TlYt2AYix/zYSGlLHSuxElZXC5WErU1bRCcxhiUb0FN7si87VYYRYpR"; // Clé de
																												// chiffrement
	private UtilisateurService utilisateurService;

	private Date getExpirationDateFromToken(String token) {
		return this.getClaim(token, Claims::getExpiration);
	}

	private <T> T getClaim(String token, Function<Claims, T> function) {
		Claims claims = getAllClaims(token);
		return function.apply(claims);
	}

	private Claims getAllClaims(String token) {
		return Jwts.parserBuilder().setSigningKey(this.getKey()).build().parseClaimsJws(token).getBody();
	}

	// Génère le JWT pour l'utilisateur en fonction de son nom d'utilisateur
	public Map<String, String> generate(String username) {
		Utilisateur utilisateur = (Utilisateur) this.utilisateurService.loadUserByUsername(username);
		return this.generateJwt(utilisateur);
	}

	// Génère le JWT avec les informations de l'utilisateur
	private Map<String, String> generateJwt(Utilisateur utilisateur) {

		// Temps actuel et expiration dans 30 minutes
		final long currentTime = System.currentTimeMillis();
		final long expirationTime = currentTime + 30 * 60 * 1000;

		// Définir les claims
		final Map<String, Object> claims = Map.of("nom", utilisateur.getNom(), Claims.EXPIRATION,
				new Date(expirationTime), Claims.SUBJECT, utilisateur.getEmail());

		String jwt = Jwts.builder().setIssuedAt(new Date(currentTime)) // Date d'émission
				.setExpiration(new Date(expirationTime)) // Date d'expiration
				.setSubject(utilisateur.getEmail()) // Sujet du token
				.setClaims(claims) // Ajouter les claims
				.signWith(getKey(), SignatureAlgorithm.HS256) // Signer le token avec la clé secrète
				.compact();

		// Retourner le token sous forme de Map (peut aussi retourner directement le
		// token si besoin)
		return Map.of("token", jwt);
	}

	// Méthode pour récupérer la clé de chiffrement
	private Key getKey() {
		// Décoder la clé secrète et générer une clé de type `Key`
		byte[] keyBytes = Decoders.BASE64.decode(ENCRYPTION_KEY);
		return Keys.hmacShaKeyFor(keyBytes);
	}

	public String extractUsername(String token) {
		return this.getClaim(token, Claims::getSubject);
	}

	public boolean isTokenExpired(String token) {
		Date expirationDate = getExpirationDateFromToken(token);
		return expirationDate.before(new Date());
	}
}
