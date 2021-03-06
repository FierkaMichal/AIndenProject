package com.ainder.ainder.services;

import com.ainder.ainder.entities.Role;
import com.ainder.ainder.repositories.RoleRepository;
import com.ainder.ainder.services.interfaces.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role getRoleById(Long id) {
        return roleRepository.findOne(id);
    }

    @Override
    public List<Role> findMatchedUsersByUserId(String p_idUser) {
        return roleRepository.findMatchedUsersByUserId(p_idUser);
    }

    public void save(Role role){
        roleRepository.save(role);
    }

}
