package com.ainder.ainder.services.interfaces;

import com.ainder.ainder.entities.Match;

public interface MatchService {

    Match getMatchByUserId(Long me, Long person);
    void updateMatch(Long id);
}
