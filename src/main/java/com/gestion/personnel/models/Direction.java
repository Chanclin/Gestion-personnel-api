package com.gestion.personnel.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    private String descriptionDirection;
    
    @ManyToOne
    @JoinColumn(name = "entreprise_id", nullable = false)
    @JsonBackReference
    private Entreprise entreprise;

}

