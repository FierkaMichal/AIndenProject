package com.ainder.ainder.repositories;

import com.ainder.ainder.entities.ConversationFlow;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ConversationFlowRepository extends CrudRepository<ConversationFlow, Long> {

//    @Query("select cf from ConversationFlow cf join cf.conversationByIdConversation c where c.userTwo = :p_sender and c.userOne = :p_receiver order by cf.time")
//    List<ConversationFlow> getAllMessagesByUsers(@Param("p_sender") long p_sender, @Param("p_receiver") long p_receiver);

    //SELECT cf.* FROM T_CONVERSATION_FLOW cf WHERE ID_CONVERSATION = (SELECT ID_CONVERSATION FROM T_CONVERSATION WHERE ((ID_USER1 = :user1 AND ID_USER = :user2) OR (ID_USER = :user1 AND ID_USER1 = :user2)));

    @Query("SELECT cf FROM ConversationFlow cf WHERE cf.conversationByIdConversation.idConversation = (SELECT c.idConversation FROM Conversation c WHERE ((c.userOne.idUser = :user1 AND c.userTwo.idUser = :user2) OR (c.userTwo.idUser = :user1 AND c.userOne.idUser= :user2)))")
    List<ConversationFlow> getAllMessagesByUsers(@Param("user1") long user1, @Param("user2") long user2);

    @Query("DELETE FROM ConversationFlow cf WHERE cf.conversationByIdConversation.idConversation = :id")
    void deleteAllMessagesByUsersId(@Param("id")long id);


}
