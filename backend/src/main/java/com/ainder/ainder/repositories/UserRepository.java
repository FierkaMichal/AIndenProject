package com.ainder.ainder.repositories;

import com.ainder.ainder.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByLogin(String username);
}
