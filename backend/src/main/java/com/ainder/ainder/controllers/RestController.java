package com.ainder.ainder.controllers;

import com.ainder.ainder.config.CustomUserDetails;
import com.ainder.ainder.entities.ConversationFlow;
import com.ainder.ainder.entities.User;
import com.ainder.ainder.restPOJO.*;
import com.ainder.ainder.restPOJO.Error;
import com.ainder.ainder.services.RoleServiceImpl;
import com.ainder.ainder.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import java.util.LinkedList;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private RoleServiceImpl roleService;

    @RequestMapping(path = "/hello", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getHello() {
        return "helloWorld";
    }

    @RequestMapping(path = "/private", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getPrivate() {
        return "private";
    }

    @RequestMapping(path = "/rest/signUp", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Error> register(@RequestBody Registration registration) {

        if (userService.getUserByLogin(registration.getLogin()) != null) {
            return new ResponseEntity<>(new Error("UserResponse with that login already exists."), HttpStatus.CONFLICT);
        }

        userService.save(new User(registration.getName(), registration.getSurname(), registration.getLogin(), registration.getPassword(), roleService.getRoleById(2l)));

        return new ResponseEntity<>(new Error(), HttpStatus.OK);
    }

    @RequestMapping(path = "/rest/signIn", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Login> login(@RequestBody Login login) {

        return new ResponseEntity<>(login, HttpStatus.OK);
    }


    @RequestMapping(path = "/like", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Like> postLike(@RequestBody Like like) {

        return new ResponseEntity<>(like, HttpStatus.OK);
    }

    @RequestMapping(path = "/message", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Message> sendMessage(@RequestBody Message message) {

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @RequestMapping(path = "/messages", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Object getMessages(@RequestBody GetMessages messages) {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.getUserByLogin(userDetails.getUsername());

        LinkedList <ConversationFlow> messageList = null; //select
        LinkedList <Message> messageResponseList = new LinkedList<>();

        for (ConversationFlow conversationFlow : messageList) {

        }

        if (messageResponseList == null || messageResponseList.size() < 1) {
            return new ResponseEntity<>(new Error("You do not have any messages from that user ;("), HttpStatus.OK);
        }

        MessageArray ma = new MessageArray();
        ma.setMessageList(messageResponseList);

        return new ResponseEntity<>(ma, HttpStatus.OK);
    }

    @RequestMapping(path = "/user", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Object getUser(@RequestBody UserByDistance userByDistance) {
        LinkedList<User> userList = null; //=users that i dont have match with

        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User me = userService.getUserByLogin(userDetails.getUsername());

        for (User user : userList) {
            if (distance(user.getLastLatitude(), me.getLastLatitude(), user.getLastLongitude(), me.getLastLongitude()) <= userByDistance.getDistance()) {
                return new ResponseEntity<>(new UserResponse(user.getIdUser(), user.getName(), user.getSurname(), user.getDescription(), user.getPhoto(), user.getLastLongitude(), user.getLastLatitude()), HttpStatus.OK);
            }
        }

        return new ResponseEntity<>(new Error("There are no users in your area."), HttpStatus.OK);
    }

    @RequestMapping(path = "/action", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Error> adminAction(@RequestBody Action action) {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.getUserByLogin(userDetails.getUsername());

        Error error = null;

        if (!(user.getRole().getName().equals("ADMIN"))) {
            error = new Error("Only admin can access this method.");
        } else if (user.getIdUser() != action.getUserId()) {
            error = new Error("You can not edit modify other user profile.");
        }

        if (error != null) {
            return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
        }

        switch (action.getAction()) {
            case "DELETE":
                break;
            case "CHANGE_NAME":
                break;
            case "CHANGE_SURNAME":
                break;
            case "CHANGE_DESCRIPTION":
                break;
            case "CHANGE_PICTURE":
                break;
        }


        return new ResponseEntity<>(new Error(), HttpStatus.OK);
    }

    private static double distance(double lat1, double lat2, double lon1, double lon2) {

        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters

        double height = 0;

        distance = Math.pow(distance, 2) + Math.pow(height, 2);

        return Math.sqrt(distance);
    }

    @RequestMapping(path = "/users", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Object getMatchedUsers() {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User me = userService.getUserByLogin(userDetails.getUsername());

        LinkedList<User> matchedUserList = null; //select
        LinkedList<UserResponse> matchedUserListResponse = new LinkedList<>();

        for (User user : matchedUserList) {

        }


        if (matchedUserList == null || matchedUserList.size() < 1) {
            return new ResponseEntity<>(new Error("You do not have any matched users ;("), HttpStatus.OK);
        }

        UserArray ua = new UserArray();
        ua.setUser(matchedUserListResponse);

        return new ResponseEntity<>(ua, HttpStatus.OK);
    }
}
