package com.ainder.ainder.services.interfaces;

import com.ainder.ainder.entities.Conversation;
import com.ainder.ainder.entities.User;

import java.util.List;

public interface ConversationService {
    Conversation findConversationByUsers( long one, long two);

    List<Conversation> findConversationByUser(long one);

    void deleteConversationByUserOneOrUserTwo(User one, User two);

    void deleteRozmowy(Long idUser);

    List<Conversation> findAll();
}
