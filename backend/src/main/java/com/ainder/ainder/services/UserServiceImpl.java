package com.ainder.ainder.services;

import com.ainder.ainder.entities.Role;
import com.ainder.ainder.entities.User;
import com.ainder.ainder.repositories.UserRepository;
import com.ainder.ainder.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    public User getUserByLogin(String login) {
        return userRepository.findByLogin(login);
    }

//    @Override
//    public List<User> findMatchedUsersByUserId(Long p_idUser) {
//
//            return userRepository.findMatchedUsersByUserId(p_idUser);
//
//    }

    @Override
    public Long getNextSeriesId() {
        return userRepository.getNextSeriesId();
    }

    @Override
    public List<User> findMatchedInvitedUsersByUserId(Long p_idUser) {
        return userRepository.findMatchedInvitedUsersByUserId(p_idUser);
    }

    @Override
    public List<User> findMatchedReceivedUsersByUserId(Long p_idUser) {
        return userRepository.findMatchedReceivedUsersByUserId(p_idUser);
    }

    @Override
    public List<User> getUserBiggerThanGivenId(Long id) {
        return userRepository.getUserBiggerThanGivenId(id);
    }

    @Override
    public void updateUserDescription(String p_newDescription, Long p_idUser) {
        userRepository.updateUserDescription(p_newDescription, p_idUser);
    }

    @Override
    public void updateUserName(String newValue, Long userId) {
        userRepository.updateUserName(newValue, userId);
    }

    @Override
    public void updateUserSurname(String newValue, Long userId) {
        userRepository.updateUserSurname(newValue, userId);
    }

    @Override
    public void updateUserPicture(String newValue, Long userId) {
        userRepository.updateUserPicture(newValue, userId);
    }

    @Override
    public void updateLat(Double newValue, Long userId) {
        userRepository.updateLat(newValue,userId);
    }

    @Override
    public void updateLon(Double newValue, Long userId) {
        userRepository.updateLon(newValue,userId);
    }

    @Override
    public void updateId(Long newValue, Long userId) {
        userRepository.updateId(newValue, userId);
    }

    @Override
    public void updateRole(Role newValue, Long userId) {
        userRepository.updateRole(newValue, userId);
    }

    @Override
    public void deleteUserByIdUser(Long userId) {
        userRepository.deleteUserByIdUser(userId);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public void save(User user) {
        user.setPassword(passwordEncoder().encode(user.getPassword()));
        userRepository.save(user);
    }
}
