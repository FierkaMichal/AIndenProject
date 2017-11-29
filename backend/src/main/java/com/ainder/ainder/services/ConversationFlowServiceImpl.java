package com.ainder.ainder.services;

import com.ainder.ainder.entities.Conversation;
import com.ainder.ainder.entities.ConversationFlow;
import com.ainder.ainder.repositories.ConversationFlowRepository;
import com.ainder.ainder.services.interfaces.ConversationFlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ConversationFlowServiceImpl implements ConversationFlowService {

    private final ConversationFlowRepository conversationFlowRepository;

    @Autowired
    public ConversationFlowServiceImpl(ConversationFlowRepository conversationFlowRepository) {
        this.conversationFlowRepository = conversationFlowRepository;
    }

    @Override
    public void deleteAllMessagesByUsersId(long id) {
        conversationFlowRepository.deleteAllMessagesByUsersId(id);
    }

    @Override
    public void deleteConversationFlowsByConversationByIdConversation(List<Conversation> c) {
        conversationFlowRepository.deleteConversationFlowsByConversationByIdConversation(c);
    }

    @Override
    public List<ConversationFlow> getAllMessagesByUsers(Long p_sender, Long p_receiver) {
        return conversationFlowRepository.getAllMessagesByUsers(p_sender, p_receiver);
    }

    @Transactional
    public void save(ConversationFlow conversationFlow) {
        conversationFlowRepository.save(conversationFlow);
    }
}
