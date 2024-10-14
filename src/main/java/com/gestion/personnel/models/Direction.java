package com.gestion.personnel.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
@Table(name = "direction")
public class Direction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_direction")
	private Integer idDirection;

	private String nomDirection;

	private String description;
	
	// Plusieurs directions peuvent appartenir à une seule entreprise
	@ManyToOne
	@JoinColumn(name= "id_entreprise", nullable = false)
	 @JsonBackReference
	private Entreprise  entreprise;
}
