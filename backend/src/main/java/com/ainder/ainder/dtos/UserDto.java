package com.ainder.ainder.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private long idUser;
    private String name;
    private String surname;
    private String login;
    private String password;
    private String description;
    private String photo;
    private Long lastLongitude;
    private Long lastLatitude;
    private Collection<ConversationDto> conversationsByIdUserOne;
    private Collection<ConversationDto> conversationsByIdUserTwo;
    private Collection<ConversationFlowDto> conversationFlowsByUserWriter;
    private Collection<MatchDto> matchesByUserInviter;
    private Collection<MatchDto> matchesByIdUserInvited;
}
