package com.ainder.ainder.repositories;

import com.ainder.ainder.entities.Match;
import com.ainder.ainder.entities.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MatchRepository extends CrudRepository<Match, Long> {

    @Query("select m from Match m where (m.userInviter.idUser = :person and m.userInvited.idUser = :me) or (m.userInviter.idUser = :me and m.userInvited.idUser = :person)")
    Match getMatchByUserId(@Param("me") Long me, @Param("person") Long person);

    @Transactional
    @Modifying
    @Query("update Match m set m.accepted = 'Y' where m.idMatch = :id")
    void updateMatch(@Param("id") Long id);


    @Transactional
    @Modifying
    void deleteByUserInviterOrUserInvited(User o, User k);

    @Override
    List<Match> findAll();

//    @Transactional
//    void deleteByIdUser(Long id);
}
