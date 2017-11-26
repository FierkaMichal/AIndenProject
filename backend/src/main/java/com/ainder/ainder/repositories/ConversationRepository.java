package com.ainder.ainder.repositories;

import com.ainder.ainder.entities.Conversation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ConversationRepository extends CrudRepository<Conversation, Long> {

    @Query("select c from Conversation c where ((c.userOne.idUser = :one and c.userTwo.idUser = :two) or (c.userOne.idUser = :two and c.userTwo.idUser = :one))")
    Conversation findConversationByUsers(@Param("one") long one, @Param("two") long two);
}
