package com.ainder.ainder.repositories;

import com.ainder.ainder.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByLogin(String username);

    @Query("select u from User u where u.idUser in (select m.userInvited.idUser from Match m where m.userInviter.idUser = :p_idUser and m.accepted = 'Y')")
    List<User> findMatchedUsersByUserId(@Param("p_idUser") long p_idUser);

//    SELECT cf.*
//    from T_CONVERSATION_FLOW cf,
//    T_CONVERSATION c
//    where c.ID_USER = cf.ID_USER
//    and c.ID_USER = :p_sender
//    and c.ID_USER1 = :p_receiver
//    ORDER BY cf.TIME;
//
//    INSERT INTO T_MATCH m (ID_USER, ID_USER1, ACCEPTED) VALUES (:p_myId, :p_otherPersonId, 'N');
//
//    INSERT INTO T_CONVERSATION_FLOW (ID_CONVERSATION, ID_USER, TIME, MESSAGE)
//    SELECT c.ID_CONVERSATION, :p_otherPersonId, :p_time, :p_message
//    FROM T_CONVERSATION c
//    WHERE c.ID_USER1 = :p_myId
//    and c.ID_USER = :p_otherPersonId;
//
//    UPDATE T_USER u set u.DESCRIPTION = :p_newDescription WHERE u.ID_USER = :p_myId
}
