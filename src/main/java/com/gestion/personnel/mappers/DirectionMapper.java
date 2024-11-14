package com.gestion.personnel.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.gestion.personnel.dto.DirectionDto;
import com.gestion.personnel.models.Direction;

@Mapper(componentModel = "spring")
public interface DirectionMapper {
    @Mapping(target = "entrepriseId", source = "entreprise.idEntreprise")
    DirectionDto ToDirectionDto(Direction direction);

    @Mapping(source = "entrepriseId", target = "entreprise.idEntreprise")
    Direction ToDirection(DirectionDto directionDto);

    List<DirectionDto> ToDtoList(List<Direction> directions);
}