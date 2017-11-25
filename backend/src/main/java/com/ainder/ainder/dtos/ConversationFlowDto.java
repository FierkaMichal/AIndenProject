package com.ainder.ainder.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConversationFlowDto {

    private long idConversationFlow;
    private Time time;
    private String message;
    private ConversationDto conversationByIdConversation;
    private UserDto userWriter;
}
