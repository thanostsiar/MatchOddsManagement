package com.atsiaras.matchodds.mappers;

import com.atsiaras.matchodds.dtos.MatchOddsDTO;
import com.atsiaras.matchodds.entities.MatchOdds;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MatchOddsMapper {
    MatchOdds matchOddsDtoToMatchOddsEntity(MatchOddsDTO matchOddsDTO);
    MatchOddsDTO matchOddsEntityToMatchOddsDTO(MatchOdds matchOddsEntity);
}
