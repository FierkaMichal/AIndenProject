package com.ainder.ainder.services.interfaces;

import com.ainder.ainder.entities.Conversation;

public interface ConversationService {
    Conversation findConversationByUsers( long one, long two);
}
