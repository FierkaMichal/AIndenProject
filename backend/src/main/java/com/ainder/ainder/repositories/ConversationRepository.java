package com.ainder.ainder.repositories;

import com.ainder.ainder.entities.Conversation;
import org.springframework.data.repository.CrudRepository;

public interface ConversationRepository extends CrudRepository<Conversation, Long> {
}
