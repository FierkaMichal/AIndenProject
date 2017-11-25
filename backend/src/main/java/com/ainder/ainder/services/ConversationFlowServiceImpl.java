package com.ainder.ainder.services;

import com.ainder.ainder.repositories.ConversationFlowRepository;
import com.ainder.ainder.services.interfaces.ConversationFlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConversationFlowServiceImpl implements ConversationFlowService {

    private final ConversationFlowRepository conversationFlowRepository;

    @Autowired
    public ConversationFlowServiceImpl(ConversationFlowRepository conversationFlowRepository) {
        this.conversationFlowRepository = conversationFlowRepository;
    }
}
