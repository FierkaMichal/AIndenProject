package com.ainder.ainder.services.interfaces;

import com.ainder.ainder.entities.User;

public interface UserService {

    User getUserById(Long id);

    User getUserByLogin(String login);
}
