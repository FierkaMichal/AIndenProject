package com.ainder.ainder.services.interfaces;

import com.ainder.ainder.entities.Conversation;

import java.util.List;

public interface ConversationService {
    Conversation findConversationByUsers( long one, long two);

    List<Conversation> findConversationByUser(long one);

    void deleteRozmowy(Long idUser);
}
