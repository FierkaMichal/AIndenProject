package com.ainder.ainder.repositories;

import com.ainder.ainder.entities.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoleRepository extends CrudRepository<Role, Long> {


    @Query(value = "select u.* from T_ROLE u where u.ROLE =:idUser", nativeQuery = true)
    List<Role> findMatchedUsersByUserId(@Param("idUser") String idUser);
}
