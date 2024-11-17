package com.gestion.personnel.mappers;

import java.util.List;

import com.gestion.personnel.dto.DirectionDto;
import com.gestion.personnel.models.Direction;


public interface DirectionMapper {
    
    DirectionDto ToDirectionDto(Direction direction);
    
    Direction ToDirection(DirectionDto directionDto);

    List<DirectionDto> ToDtoList(List<Direction> directions);
}