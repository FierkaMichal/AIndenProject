package com.ainder.ainder.services.interfaces;

import com.ainder.ainder.entities.User;

import java.util.List;

public interface UserService {

    User getUserById(Long id);

    User getUserByLogin(String login);

    List<User> findMatchedInvitedUsersByUserId(Long p_idUser);

    List<User> findMatchedReceivedUsersByUserId(Long p_idUser);

    List<User> getUserBiggerThanGivenId(Long id);

    void updateUserDescription(String p_newDescription, Long p_idUser);

    Long getNextSeriesId();
}
