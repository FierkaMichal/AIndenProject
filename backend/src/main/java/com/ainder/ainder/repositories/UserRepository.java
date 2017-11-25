package com.ainder.ainder.repositories;

import com.ainder.ainder.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByLogin(String username);

    @Query(value = "select u.* from T_USER u where u.ID_USER in (select m.ID_USER1 from T_MATCH m where m.ID_USER = :p_idUser and m.ACCEPTED = 'Y')", nativeQuery = true)
    List<User> findMatchedUsersByUserId(@Param("p_idUser") Long p_idUser);
}
