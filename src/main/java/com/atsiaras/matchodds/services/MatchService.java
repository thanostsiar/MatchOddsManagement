package com.atsiaras.matchodds.services;

import com.atsiaras.matchodds.dtos.MatchDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MatchService {
    List<MatchDTO> getAllMatches();
    MatchDTO getMatchById(Long id);
    MatchDTO createMatch(MatchDTO matchDTO);
    MatchDTO updateMatchById(Long id, MatchDTO matchDTO);
    void deleteMatchById(Long id);
}
