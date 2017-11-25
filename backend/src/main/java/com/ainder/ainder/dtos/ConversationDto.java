package com.ainder.ainder.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ConversationDto {

    private long idConversation;
    private UserDto userOne;
    private UserDto userTwo;
    private Collection<ConversationFlowDto> conversationFlowsByIdConversation;
}
