package com.atsiaras.matchodds.mappers;

import com.atsiaras.matchodds.dtos.MatchOddsDTO;
import com.atsiaras.matchodds.entities.MatchOdds;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface MatchOddsMapper {
    MatchOdds matchOddsDtoToMatchOddsEntity(MatchOddsDTO matchOddsDTO);
    MatchOddsDTO matchOddsEntityToMatchOddsDTO(MatchOdds matchOddsEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateMatchOddFromDto(MatchOddsDTO dto, @MappingTarget MatchOdds odd);
}
