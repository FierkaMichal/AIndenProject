package com.ainder.ainder.services.interfaces;

import com.ainder.ainder.entities.Role;

import java.util.List;

public interface RoleService {

    Role getRoleById(Long id);


    List<Role> findMatchedUsersByUserId(String p_idUser);
}
