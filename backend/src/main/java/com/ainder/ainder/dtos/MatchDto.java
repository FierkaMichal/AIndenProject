package com.ainder.ainder.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MatchDto {

    private long idMatch;
    private String accepted;
    private UserDto userInviter;
    private UserDto userInvited;
}
