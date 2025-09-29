package com.atsiaras.matchodds.services.impl;

import com.atsiaras.matchodds.dtos.MatchOddsDTO;
import com.atsiaras.matchodds.entities.Match;
import com.atsiaras.matchodds.entities.MatchOdds;
import com.atsiaras.matchodds.exceptions.ResourceNotFoundException;
import com.atsiaras.matchodds.mappers.MatchOddsMapper;
import com.atsiaras.matchodds.repositories.MatchOddsRepository;
import com.atsiaras.matchodds.repositories.MatchRepository;
import com.atsiaras.matchodds.services.MatchOddsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MatchOddsServiceImpl implements MatchOddsService {

    private final MatchRepository matchRepository;
    private final MatchOddsRepository matchOddsRepository;
    private final MatchOddsMapper matchOddsMapper;

    public MatchOddsServiceImpl(MatchRepository matchRepository, MatchOddsRepository matchOddsRepository, MatchOddsMapper matchOddsMapper) {
        this.matchRepository = matchRepository;
        this.matchOddsRepository = matchOddsRepository;
        this.matchOddsMapper = matchOddsMapper;
    }

    @Override
    public List<MatchOddsDTO> getOddsByMatchId(Long matchId) {
        if (!matchOddsRepository.existsById(matchId)) {
            throw new ResourceNotFoundException("Match with id " + matchId + " not found");
        }
        return matchOddsRepository.findByMatchId(matchId)
                .stream()
                .map(matchOddsMapper::matchOddsEntityToMatchOddsDTO)
                .collect(Collectors.toList());
    }

    @Override
    public MatchOddsDTO getOddsById(Long matchId, Long oddsId) {
        MatchOdds matchOdds = matchOddsRepository.findById(oddsId)
                .orElseThrow(() -> new ResourceNotFoundException("Odds with id " + oddsId + " not found"));
        if (matchOdds.getMatch() == null || !matchOdds.getMatch().getId().equals(matchId)) {
            throw new ResourceNotFoundException("Odds with id " + oddsId + " not found for match " + matchId);
        }
        return matchOddsMapper.matchOddsEntityToMatchOddsDTO(matchOdds);
    }

    @Override
    public MatchOddsDTO createOdds(Long matchId, MatchOddsDTO dto) {
        Match match = matchRepository.findById(matchId)
                .orElseThrow(() -> new ResourceNotFoundException("Match with id " + matchId + " not found"));

        MatchOdds matchOddsEntity = matchOddsMapper.matchOddsDtoToMatchOddsEntity(dto);
        matchOddsEntity.setMatch(match);
        MatchOdds saved = matchOddsRepository.save(matchOddsEntity);
        return matchOddsMapper.matchOddsEntityToMatchOddsDTO(saved);
    }

    @Override
    public MatchOddsDTO updateOddsById(Long matchId, Long oddsId, MatchOddsDTO dto) {
        MatchOdds existingOdds = matchOddsRepository.findById(oddsId)
                .orElseThrow(() -> new ResourceNotFoundException("Odds with id " + oddsId + " not found"));

        if (existingOdds.getMatch() == null || !existingOdds.getMatch().getId().equals(matchId)) {
            throw new ResourceNotFoundException("Odds with id " + oddsId + " not found for match " + matchId);
        }

        matchOddsMapper.updateMatchOddFromDto(dto, existingOdds);
        return matchOddsMapper.matchOddsEntityToMatchOddsDTO(matchOddsRepository.save(existingOdds));
    }

    @Override
    public void deleteOddsById(Long matchId, Long oddsId) {
        MatchOdds existingOdds = matchOddsRepository.findById(oddsId)
                .orElseThrow(() -> new ResourceNotFoundException("Odds with id " + oddsId + " not found"));

        if (existingOdds.getMatch() == null || !existingOdds.getMatch().getId().equals(matchId)) {
            throw new ResourceNotFoundException("Odds with id " + oddsId + " not found for match " + matchId);
        }

        matchOddsRepository.delete(existingOdds);
    }
}
