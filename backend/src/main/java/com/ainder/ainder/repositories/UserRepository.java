package com.ainder.ainder.repositories;

import com.ainder.ainder.entities.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByLogin(String username);

    @Query("select u from User u where u.idUser in (select m.userInvited.idUser from Match m where m.userInviter.idUser = :p_idUser and m.accepted = 'Y')")
    List<User> findMatchedInvitedUsersByUserId(@Param("p_idUser") long p_idUser);

    @Query("select u from User u where u.idUser in (select m.userInviter.idUser from Match m where m.userInvited.idUser = :p_idUser and m.accepted = 'Y')")
    List<User> findMatchedReceivedUsersByUserId(@Param("p_idUser") long p_idUser);

//    SELECT cf.*
//    from T_CONVERSATION_FLOW cf,
//    T_CONVERSATION c
//    where c.ID_USER = cf.ID_USER
//    and c.ID_USER = :p_sender
//    and c.ID_USER1 = :p_receiver
//    ORDER BY cf.TIME;


    //void insertDocumentByTaskId(@Param("idTask") Long id,@Param("description") String description,@Param("filepath") String filepath);
//    INSERT INTO T_MATCH m (ID_USER, ID_USER1, ACCEPTED) VALUES (:p_myId, :p_otherPersonId, 'N');
//
//    INSERT INTO T_CONVERSATION_FLOW (ID_CONVERSATION, ID_USER, TIME, MESSAGE)
//    SELECT c.ID_CONVERSATION, :p_otherPersonId, :p_time, :p_message
//    FROM T_CONVERSATION c
//    WHERE c.ID_USER1 = :p_myId
//    and c.ID_USER = :p_otherPersonId;

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("update User u set u.description = :p_newDescription where u.idUser = :p_idUser")
    void updateUserDescription(@Param("p_newDescription") String p_newDescription, @Param("p_idUser") long p_idUser);

    @Query("select u from User u where u.login = :login")
    void getUserByLogin(@Param("login") String login);

    @Query("select u from User u where u.idUser > :id")
    List<User> getUserBiggerThanGivenId(@Param("id") Long id);




//    UPDATE T_USER u set u.DESCRIPTION = :p_newDescription WHERE u.ID_USER = :p_myId
}
