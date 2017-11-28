package com.ainder.ainder.repositories;

import com.ainder.ainder.entities.Conversation;
import com.ainder.ainder.entities.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ConversationRepository extends CrudRepository<Conversation, Long> {

    @Query("select c from Conversation c where ((c.userOne.idUser = :one and c.userTwo.idUser = :two) or (c.userOne.idUser = :two and c.userTwo.idUser = :one))")
    Conversation findConversationByUsers(@Param("one") long one, @Param("two") long two);

    @Query("select c from Conversation c where c.userOne.idUser = :one or  c.userTwo.idUser = :one")
    List<Conversation> findConversationByUser(@Param("one") long one);

    @Query("DELETE from Conversation c where c.userTwo.idUser = :idUser or c.userOne.idUser = :idUser")
    @Transactional
    @Modifying
    void deleteRozmowy(@Param("idUser") Long idUser);

    @Transactional
    void deleteConversationByUserOneOrUserTwo(User one, User two);
}
