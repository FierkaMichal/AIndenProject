package com.ainder.ainder.repositories;

import com.ainder.ainder.entities.Match;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface MatchRepository extends CrudRepository<Match, Long> {

    @Query("select m from Match m where m.userInviter.idUser = :person and m.userInvited.idUser = :me")
    Match getMatchByUserId(@Param("me") Long me, @Param("person") Long person);

    @Query("update Match m set m.accepted = 'Y' where m.idMatch = :id")
    void updateMatch(@Param("id") Long id);
}
