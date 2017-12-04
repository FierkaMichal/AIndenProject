package com.ainder.ainder.controllers;

import com.ainder.ainder.config.CustomUserDetails;
import com.ainder.ainder.entities.Conversation;
import com.ainder.ainder.entities.Role;
import com.ainder.ainder.entities.User;
import com.ainder.ainder.restPOJO.Error;
import com.ainder.ainder.restPOJO.UserArray;
import com.ainder.ainder.restPOJO.UserResponse;
import com.ainder.ainder.services.ConversationFlowServiceImpl;
import com.ainder.ainder.services.ConversationServiceImpl;
import com.ainder.ainder.services.MatchServiceImpl;
import com.ainder.ainder.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Michał on 2017-11-26.
 */

@org.springframework.web.bind.annotation.RestController
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private MatchServiceImpl matchService;

    @Autowired
    private ConversationServiceImpl conversationService;

    @Autowired
    private ConversationFlowServiceImpl conversationFlowService;

    @RequestMapping(path = "/me", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Object getMe() {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User u = userService.getUserByLogin(userDetails.getUsername());

        if (u != null) {
            UserResponse ur = ControllersUtils.userToUserResponse(u);
            return new ResponseEntity<>(ur, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new Error("You don;t exist."), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(path = "**/rest/user/edit", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Error> editUser(@RequestBody UserResponse userResponse) {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User me = userService.getUserByLogin(userDetails.getUsername());

        Error error = null;

        if (me.getRole().getName().contains("USER") && me.getIdUser() != userResponse.getUserId()) {
            error = new Error("You can not modify other user profile.");
        }
        if (error != null) {
            return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
        }
        User u = userService.getUserById(userResponse.getUserId());

        if (!u.getName().equals(userResponse.getName())) {
            userService.updateUserName(userResponse.getName(), userResponse.getUserId());
        }
        if (!u.getSurname().equals(userResponse.getSurname())) {
            userService.updateUserSurname(userResponse.getSurname(), userResponse.getUserId());
        }
        if (userResponse.getDescription() == null || u.getDescription() == null || (userResponse.getDescription() != null && u.getDescription() != null && !u.getDescription().equals(userResponse.getDescription()))) {
            userService.updateUserDescription(userResponse.getDescription(), userResponse.getUserId());
        }
        if (userResponse.isAdmin()){
            userService.updateRole(new Role(2L, "ADMIN"), userResponse.getUserId());
        } else {
            userService.updateRole(new Role(1L, "USER"), userResponse.getUserId());
        }

        StringBuilder photo = new StringBuilder();
        if (userResponse.getAvatar() != null) {
            photo.append(userResponse.getAvatar() + " ");
        }
        for (String s : userResponse.getPhotoArray()) {
            photo.append(s + " ");
        }
        if (photo == null || u.getPhoto() == null || (photo != null && u.getPhoto() != null && !u.getPhoto().equals(photo))) {
            userService.updateUserPicture(photo.toString(), userResponse.getUserId());
        }
//        switch (action.getAction()) {
//            case "DELETE":
//                userService.deleteByIdUser(action.getUserId());
//                break;
//            case "CHANGE_NAME":
//                userService.updateUserName(action.getNewValue(), action.getUserId());
//                break;
//            case "CHANGE_SURNAME":
//                userService.updateUserSurname(action.getNewValue(), action.getUserId());
//                break;
//            case "CHANGE_DESCRIPTION":
//                userService.updateUserDescription(action.getNewValue(), action.getUserId());
//                break;
//            case "CHANGE_PICTURE":
//                userService.updateUserPicture(action.getNewValue(), action.getUserId());
//                break;
//        }


        return new ResponseEntity<>(new Error(), HttpStatus.OK);
    }

    @RequestMapping(path = "*/rest/user/delete", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Error> deleteUser(@RequestParam("userId") Long userId) {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User me = userService.getUserByLogin(userDetails.getUsername());

        Error error = null;

        if (me.getRole().getName().contains("USER") && me.getIdUser() != userId) {
            error = new Error("You can not delete other user profile.");
        }
        if (error != null) {
            return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
        }

        List<Conversation> conversationList = conversationService.findConversationByUser(userId);
        conversationFlowService.deleteConversationFlowsByConversationByIdConversation(conversationList);

        conversationService.deleteConversationByUserOneOrUserTwo(userService.getUserById(userId), userService.getUserById(userId));
//        conversationService.deleteRozmowy(userId);

        matchService.deleteByUserInviterOrUserInvited(userService.getUserById(userId), userService.getUserById(userId));
//
//
        userService.deleteUserByIdUser(userId);
//        userService.updateId(userId * -1, userId);

        return new ResponseEntity<>(new Error(), HttpStatus.OK);
    }

    @RequestMapping(path = "*/rest/user/getAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Object findAllUsers() {

        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User me = userService.getUserByLogin(userDetails.getUsername());

        LinkedList<UserResponse> usersListResponse = new LinkedList<>();

        Error error = null;
        if (me.getRole().getName().contains("USER")) {
            error = new Error("You can not view all users.");
        }

        if(error != null) {
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);  //forbidden?
        }

        List<User> users = userService.findAll();

        for(User user : users) {
            usersListResponse.add(ControllersUtils.userToUserResponse(user));
        }

//        if(usersListResponse.size() < 1) {
//            //return new ResponseEntity<>(new UserArray(), HttpStatus.OK);
//        }

        UserArray ua = new UserArray();
        ua.setUser(usersListResponse);

        return new ResponseEntity<>(ua, HttpStatus.OK);
    }


//    CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//    User me = userService.getUserByLogin(userDetails.getUsername());
//
//    List<User> matchedInvitedUserList = userService.findMatchedInvitedUsersByUserId(me.getIdUser()); //select
//    List<User> matchedReceivedUserList = userService.findMatchedReceivedUsersByUserId(me.getIdUser()); //select
//    LinkedList<UserResponse> matchedUserListResponse = new LinkedList<>();
//
//        for (User user : matchedInvitedUserList) {
//        matchedUserListResponse.add(ControllersUtils.userToUserResponse(user));
//    }
//
//        for (User user : matchedReceivedUserList) {
//        matchedUserListResponse.add(ControllersUtils.userToUserResponse(user));
//    }
//
//        if (matchedUserListResponse == null || matchedUserListResponse.size() < 1) {
//        return new ResponseEntity<>(new UserArray(), HttpStatus.OK);
//    }
//
//    UserArray ua = new UserArray();
//        ua.setUser(matchedUserListResponse);
//
//        return new ResponseEntity<>(ua, HttpStatus.OK);
}
