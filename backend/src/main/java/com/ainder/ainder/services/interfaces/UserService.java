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

    void updateUserName(String newValue, Long userId);

    void updateUserSurname(String newValue, Long userId);

    void updateUserPicture(String newValue, Long userId);

    void updateLat(Double newValue, Long userId);

    void updateLon(Double newValue, Long userId);

    void updateId(Long newValue, Long userId);

    void deleteByIdUser(Long lond);

    List<User> findAll();
}
