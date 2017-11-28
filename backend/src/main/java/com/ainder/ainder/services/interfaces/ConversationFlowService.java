package com.ainder.ainder.services.interfaces;

import com.ainder.ainder.entities.ConversationFlow;

import java.util.List;

public interface ConversationFlowService {

    List<ConversationFlow> getAllMessagesByUsers(Long p_sender, Long p_receiver);

    void deleteAllMessagesByUsersId(long id);
}
