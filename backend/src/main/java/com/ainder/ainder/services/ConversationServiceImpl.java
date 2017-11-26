package com.ainder.ainder.services;

import com.ainder.ainder.entities.Conversation;
import com.ainder.ainder.repositories.ConversationRepository;
import com.ainder.ainder.services.interfaces.ConversationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConversationServiceImpl implements ConversationService {

    private final ConversationRepository conversationRepository;

    @Autowired
    public ConversationServiceImpl(ConversationRepository conversationRepository) {
        this.conversationRepository = conversationRepository;
    }

    @Override
    public Conversation findConversationByUsers(long one, long two) {
        return conversationRepository.findConversationByUsers(one,two);
    }

    public void save(Conversation conversation) {
        conversationRepository.save(conversation);
    }
}
