package com.atsiaras.matchodds.services;

import com.atsiaras.matchodds.dtos.MatchOddsDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MatchOddsService {
    List<MatchOddsDTO> getOddsByMatchId(Long matchId);
    MatchOddsDTO getOddsById(Long matchId, Long oddsId);
    MatchOddsDTO createOdds(Long matchId, MatchOddsDTO dto);
    MatchOddsDTO updateOddsById(Long matchId, Long oddsId, MatchOddsDTO dto);
    void deleteOddsById(Long matchId, Long oddsId);
}
