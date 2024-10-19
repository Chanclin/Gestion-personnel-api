package com.gestion.personnel.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

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
@Table(name = "entreprise")
public class Entreprise {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@Column(name = "id_entreprise")
	private Integer idEntreprise;

	@Column(nullable = false)
	private String nomEntreprise;

	private String adresseEntreprise;

	@Column(unique = true)
	private String emailEntreprise;

	@OneToMany(mappedBy = "entreprise", cascade = CascadeType.ALL) // une enterprise peut avoir plusieurs directions
	private List<Direction> directions;

}
