package com.gestion.personnel.mappers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gestion.personnel.dto.DirectionDto;
import com.gestion.personnel.dto.EntrepriseDto;
import com.gestion.personnel.models.Direction;
import com.gestion.personnel.models.Entreprise;

@Component
public class EntrepriseMapperImpl implements EntrepriseMappper{

    @Autowired
    private DirectionMapper directionMapper;

    @Override
    public EntrepriseDto ToEntrepriseDto(Entreprise entreprise) {
        if ( entreprise == null ) {
            return null;
        }

        EntrepriseDto.EntrepriseDtoBuilder entrepriseDto = EntrepriseDto.builder();

        entrepriseDto.adresseEntreprise( entreprise.getAdresseEntreprise() );
        entrepriseDto.directions( directionMapper.ToDtoList( entreprise.getDirections() ) );
        entrepriseDto.emailEntreprise( entreprise.getEmailEntreprise() );
        entrepriseDto.idEntreprise( entreprise.getIdEntreprise() );
        entrepriseDto.nomEntreprise( entreprise.getNomEntreprise() );

        return entrepriseDto.build();
    }

    @Override
    public Entreprise ToEntreprise(EntrepriseDto entrepriseDto) {
        if ( entrepriseDto == null ) {
            return null;
        }

        Entreprise.EntrepriseBuilder entreprise = Entreprise.builder();

        entreprise.adresseEntreprise( entrepriseDto.getAdresseEntreprise() );
        entreprise.directions( directionDtoListToDirectionList( entrepriseDto.getDirections() ) );
        entreprise.emailEntreprise( entrepriseDto.getEmailEntreprise() );
        entreprise.idEntreprise( entrepriseDto.getIdEntreprise() );
        entreprise.nomEntreprise( entrepriseDto.getNomEntreprise() );

        return entreprise.build();
    }

    @Override
    public List<EntrepriseDto> ToDtoList(List<Entreprise> entreprises) {
        if ( entreprises == null ) {
            return null;
        }

        List<EntrepriseDto> list = new ArrayList<EntrepriseDto>( entreprises.size() );
        for ( Entreprise entreprise : entreprises ) {
            list.add( ToEntrepriseDto( entreprise ) );
        }

        return list;
    }

    protected List<Direction> directionDtoListToDirectionList(List<DirectionDto> list) {
        if ( list == null ) {
            return null;
        }

        List<Direction> list1 = new ArrayList<Direction>( list.size() );
        for ( DirectionDto directionDto : list ) {
            list1.add( directionMapper.ToDirection( directionDto ) );
        }

        return list1;
    }
}
