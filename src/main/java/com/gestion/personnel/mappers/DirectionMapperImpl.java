package com.gestion.personnel.mappers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.gestion.personnel.dto.DirectionDto;
import com.gestion.personnel.models.Direction;
import com.gestion.personnel.models.Entreprise;

@Component
public class DirectionMapperImpl implements DirectionMapper{
	@Override
    public DirectionDto ToDirectionDto(Direction direction) {
        if ( direction == null ) {
            return null;
        }

        DirectionDto.DirectionDtoBuilder directionDto = DirectionDto.builder();

        directionDto.entrepriseId( directionEntrepriseIdEntreprise( direction ) );
        directionDto.description( direction.getDescription() );
        directionDto.idDirection( direction.getIdDirection() );
        directionDto.nomDirection( direction.getNomDirection() );

        return directionDto.build();
    }

    @Override
    public Direction ToDirection(DirectionDto directionDto) {
        if ( directionDto == null ) {
            return null;
        }

        Direction.DirectionBuilder direction = Direction.builder();

        direction.entreprise( directionDtoToEntreprise( directionDto ) );
        direction.description( directionDto.getDescription() );
        direction.idDirection( directionDto.getIdDirection() );
        direction.nomDirection( directionDto.getNomDirection() );

        return direction.build();
    }

    @Override
    public List<DirectionDto> ToDtoList(List<Direction> directions) {
        if ( directions == null ) {
            return null;
        }

        List<DirectionDto> list = new ArrayList<DirectionDto>( directions.size() );
        for ( Direction direction : directions ) {
            list.add( ToDirectionDto( direction ) );
        }

        return list;
    }

    private Integer directionEntrepriseIdEntreprise(Direction direction) {
        if ( direction == null ) {
            return null;
        }
        Entreprise entreprise = direction.getEntreprise();
        if ( entreprise == null ) {
            return null;
        }
        Integer idEntreprise = entreprise.getIdEntreprise();
        if ( idEntreprise == null ) {
            return null;
        }
        return idEntreprise;
    }

    protected Entreprise directionDtoToEntreprise(DirectionDto directionDto) {
        if ( directionDto == null ) {
            return null;
        }

        Entreprise.EntrepriseBuilder entreprise = Entreprise.builder();

        entreprise.idEntreprise( directionDto.getEntrepriseId() );

        return entreprise.build();
    }
}
