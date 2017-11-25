package com.ainder.ainder.services.interfaces;

import com.ainder.ainder.entities.User;

import java.util.List;

public interface UserService {

    User getUserById(Long id);

    User getUserByLogin(String login);

    List<User> findMatchedUsersByUserId(String p_idUser);
}
