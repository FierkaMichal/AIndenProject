package com.ainder.ainder.services;

import com.ainder.ainder.entities.Match;
import com.ainder.ainder.repositories.MatchRepository;
import com.ainder.ainder.services.interfaces.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MatchServiceImpl implements MatchService {

    private final MatchRepository matchRepository;

    @Autowired
    public MatchServiceImpl(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    @Override
    public Match getMatchByUserId(Long me, Long person) {
        return matchRepository.getMatchByUserId(me,person);
    }

    @Override
    public void updateMatch(Long id) {
        matchRepository.updateMatch(id);
    }

    public void save(Match match) {
        matchRepository.save(match);
    }
}
