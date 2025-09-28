package com.atsiaras.matchodds.services.impl;

import com.atsiaras.matchodds.dtos.MatchDTO;
import com.atsiaras.matchodds.entities.Match;
import com.atsiaras.matchodds.exceptions.ResourceNotFoundException;
import com.atsiaras.matchodds.mappers.MatchMapper;
import com.atsiaras.matchodds.repositories.MatchRepository;
import com.atsiaras.matchodds.services.MatchService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MatchServiceImpl implements MatchService {

    private final MatchRepository  matchRepository;
    private final MatchMapper matchMapper;

    public MatchServiceImpl(MatchRepository matchRepository, MatchMapper matchMapper) {
        this.matchRepository = matchRepository;
        this.matchMapper = matchMapper;
    }

    @Override
    public List<MatchDTO> getAllMatches() {
        return matchRepository.findAll().stream().map(matchMapper::matchEntityToDto).collect(Collectors.toList());
    }

    @Override
    public MatchDTO getMatchById(Long id) {
        Match match = matchRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Match with id " + id + " not found"));
        return matchMapper.matchEntityToDto(match);
    }

    @Override
    public MatchDTO createMatch(MatchDTO matchDTO) {
        Match match = matchMapper.matchDtoToEntity(matchDTO);
        return matchMapper.matchEntityToDto(matchRepository.save(match));
    }

    @Override
    public MatchDTO updateMatchById(Long id, MatchDTO matchDTO) {
        Match existingMatch = matchRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Match with id " + id + " not found"));

        matchMapper.updateMatchFromDto(matchDTO, existingMatch);
        return matchMapper.matchEntityToDto(matchRepository.save(existingMatch));
    }

    @Override
    public void deleteMatchById(Long id) {
        Match existingMatch = matchRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Match with id " + id + " not found"));

        matchRepository.delete(existingMatch);
    }
}
