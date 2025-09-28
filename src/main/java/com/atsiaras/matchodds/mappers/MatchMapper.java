package com.atsiaras.matchodds.mappers;

import com.atsiaras.matchodds.dtos.MatchDTO;
import com.atsiaras.matchodds.entities.Match;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface MatchMapper {
    Match matchDtoToEntity(MatchDTO matchDTO);
    MatchDTO matchEntityToDto(Match matchEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateMatchFromDto(MatchDTO matchDTO, @MappingTarget Match match);
}
