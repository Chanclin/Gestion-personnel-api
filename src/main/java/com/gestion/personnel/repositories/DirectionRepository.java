package com.gestion.personnel.repositories;

import com.gestion.personnel.models.Direction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectionRepository extends JpaRepository<Direction, Integer> {

}
