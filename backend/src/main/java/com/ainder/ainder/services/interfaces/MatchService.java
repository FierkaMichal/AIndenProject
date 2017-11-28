package com.ainder.ainder.services.interfaces;

import com.ainder.ainder.entities.Match;
import com.ainder.ainder.entities.User;

public interface MatchService {

    Match getMatchByUserId(Long me, Long person);
    void updateMatch(Long id);


    void deleteByUserInviterOrUserInvited(User o, User k);
}
