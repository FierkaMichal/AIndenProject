package com.ainder.ainder.services;

import com.ainder.ainder.entities.Conversation;
import com.ainder.ainder.entities.User;
import com.ainder.ainder.repositories.ConversationRepository;
import com.ainder.ainder.services.interfaces.ConversationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ConversationServiceImpl implements ConversationService {

    private final ConversationRepository conversationRepository;

    @Autowired
    public ConversationServiceImpl(ConversationRepository conversationRepository) {
        this.conversationRepository = conversationRepository;
    }

    @Override
    public List<Conversation> findConversationByUser(long one) {
        return conversationRepository.findConversationByUser(one);
    }

    @Override
    public Conversation findConversationByUsers(long one, long two) {
        return conversationRepository.findConversationByUsers(one,two);
    }

    @Override
    public void deleteConversationByUserOneOrUserTwo(User one, User two) {
        conversationRepository.deleteConversationByUserOneOrUserTwo(one,two);
    }

    @Override
    public void deleteRozmowy(Long idUser) {
        conversationRepository.deleteRozmowy(idUser);
    }

    @Transactional
    public void save(Conversation conversation) {
        conversationRepository.save(conversation);
    }

    public List<Conversation> findAll() {
        return conversationRepository.findAll();
    }
}
