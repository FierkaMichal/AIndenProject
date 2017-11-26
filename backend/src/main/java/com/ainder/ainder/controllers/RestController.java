package com.ainder.ainder.controllers;

import com.ainder.ainder.config.CustomUserDetails;
import com.ainder.ainder.entities.Conversation;
import com.ainder.ainder.entities.ConversationFlow;
import com.ainder.ainder.entities.Match;
import com.ainder.ainder.entities.User;
import com.ainder.ainder.restPOJO.*;
import com.ainder.ainder.restPOJO.Error;
import com.ainder.ainder.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Time;
import java.util.LinkedList;
import java.util.List;

//import java.util.Date;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private ConversationFlowServiceImpl conversationFlowService;

    @Autowired
    private RoleServiceImpl roleService;

    @Autowired
    private MatchServiceImpl matchService;

    @Autowired
    private ConversationServiceImpl conversationService;

    @Autowired
    private TokenStore tokenStore;

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

        userService.save(new User(0l, registration.getName(), registration.getSurname(), registration.getLogin(), registration.getPassword(), roleService.getRoleById(2l)));

        return new ResponseEntity<>(new Error(), HttpStatus.OK);
    }

    @RequestMapping(path = "/rest/logOut", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public void logout(@RequestParam ("access_token") String accessToken){
        tokenStore.removeAccessToken(tokenStore.readAccessToken(accessToken));
    }

    @RequestMapping(path = "/like", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Error> postLike(@RequestParam("user_id") Long userId) {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.getUserByLogin(userDetails.getUsername());

        Match m = matchService.getMatchByUserId(user.getIdUser(), userId);
        if(m == null) {
            Match newMatch = new Match("N", user, userService.getUserById(userId));
            matchService.save(newMatch);
        } else {
            matchService.updateMatch(m.getIdMatch());
        }

        return new ResponseEntity<>(new Error(), HttpStatus.OK);
    }

    @RequestMapping(path = "/message", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Error> sendMessage(@RequestBody Message message) {

        Conversation c = conversationService.findConversationByUsers(message.getMyId(), message.getOtherPersonId());

        if(c == null) {
            c = new Conversation(0l,userService.getUserById(message.getMyId()), userService.getUserById(message.getOtherPersonId()));
            conversationService.save(c);
        }

        ConversationFlow cf = new ConversationFlow(0l, new Time(System.currentTimeMillis()), message.getMessage(), c, userService.getUserById(message.getMyId()));
        conversationFlowService.save(cf);

        return new ResponseEntity<>(new Error(), HttpStatus.OK);
    }

    @RequestMapping(path = "/messages", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Object getMessages(@RequestParam("user_id") Long userId) {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.getUserByLogin(userDetails.getUsername());

        List<ConversationFlow> messageList = conversationFlowService.getAllMessagesByUsers(user.getIdUser(), userId);
        LinkedList<Message> messageResponseList = new LinkedList<>();

        for (ConversationFlow conversationFlow : messageList) {
            messageResponseList.add(new Message(user.getIdUser(), userId, conversationFlow.getTime().toString() ,conversationFlow.getMessage()));
        }

        if (messageResponseList == null || messageResponseList.size() < 1) {
            return new ResponseEntity<>(new Error("You do not have any messages from that user ;("), HttpStatus.OK);
        }

        MessageArray ma = new MessageArray();
        ma.setMessageList(messageResponseList);

        return new ResponseEntity<>(ma, HttpStatus.OK);
    }

    @RequestMapping(path = "/next_user", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Object getUser(@RequestParam("last_user_id") Long lastUserId, @RequestParam("distKm") Long km ) {

        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User me = userService.getUserByLogin(userDetails.getUsername());

        List <User> biggerUserList = userService.getUserBiggerThanGivenId(lastUserId);
        List <User> matchedUserInvitedList = userService.findMatchedInvitedUsersByUserId(me.getIdUser());
        List <User> matchedUserReceivedList = userService.findMatchedReceivedUsersByUserId(me.getIdUser());

        biggerUserList.removeAll(matchedUserInvitedList);
        biggerUserList.removeAll(matchedUserReceivedList);

        for (User user : biggerUserList) {
            if (distance(user.getLastLatitude(), me.getLastLatitude(), user.getLastLongitude(), me.getLastLongitude()) <= km * 1000) {
                return new ResponseEntity<>(new UserResponse(user.getIdUser(), user.getName(), user.getSurname(), user.getDescription(), user.getPhoto(), user.getLastLongitude(), user.getLastLatitude()), HttpStatus.OK);
            }
        }

        return new ResponseEntity<>(new Error("There are no users in your area."), HttpStatus.OK);
    }

    @RequestMapping(path = "/rest/user", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Object getUser(@RequestParam("login") String login) {
        User u = userService.getUserByLogin(login);
        if(u != null) {
            UserResponse ur = new UserResponse(u.getIdUser(),u.getName(),u.getSurname(),u.getDescription(),u.getPhoto(),u.getLastLongitude(),u.getLastLongitude());
            return new ResponseEntity<>(ur , HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new Error("User with that login does not exist."), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(path = "/me", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Object getMe() {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User u = userService.getUserByLogin(userDetails.getUsername());

        if(u != null) {
            UserResponse ur = new UserResponse(u.getIdUser(),u.getName(),u.getSurname(),u.getDescription(),u.getPhoto(),u.getLastLongitude(),u.getLastLongitude());
            return new ResponseEntity<>(ur , HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new Error("You don;t exist."), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(path = "/action", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Error> adminAction(@RequestBody Action action) {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.getUserByLogin(userDetails.getUsername());

        Error error = null;

//        if (!user.getRole().getName().contains("ADMIN")) {
//            error = new Error("Only admin can access this method.");
//        } else

        if (user.getRole().getName().contains("USER") && user.getIdUser() != action.getUserId()) {
            error = new Error("You can not modify other user profile.");
        }

        if (error != null) {
            return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
        }

        switch (action.getAction()) {
            case "DELETE":
                userService.deleteByIdUser(action.getUserId());
                break;
            case "CHANGE_NAME":
                userService.updateUserName(action.getNewValue(), action.getUserId());
                break;
            case "CHANGE_SURNAME":
                userService.updateUserSurname(action.getNewValue(), action.getUserId());
                break;
            case "CHANGE_DESCRIPTION":
                userService.updateUserDescription(action.getNewValue(), action.getUserId());
                break;
            case "CHANGE_PICTURE":
                userService.updateUserPicture(action.getNewValue(), action.getUserId());
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

    @RequestMapping(path = "/users_matched", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Object getMatchedUsers() {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User me = userService.getUserByLogin(userDetails.getUsername());

        List<User> matchedInvitedUserList = userService.findMatchedInvitedUsersByUserId(me.getIdUser()); //select
        List<User> matchedReceivedUserList = userService.findMatchedReceivedUsersByUserId(me.getIdUser()); //select
        LinkedList<UserResponse> matchedUserListResponse = new LinkedList<>();

        for (User user : matchedInvitedUserList) {
            matchedUserListResponse.add(new UserResponse(user.getIdUser(), user.getName(), user.getSurname(), user.getDescription(), user.getPhoto(), user.getLastLongitude(), user.getLastLatitude()));
        }

        for (User user : matchedReceivedUserList) {
            matchedUserListResponse.add(new UserResponse(user.getIdUser(), user.getName(), user.getSurname(), user.getDescription(), user.getPhoto(), user.getLastLongitude(), user.getLastLatitude()));
        }


        if (matchedUserListResponse == null || matchedUserListResponse.size() < 1) {
            return new ResponseEntity<>(new Error("You do not have any matched users ;("), HttpStatus.OK);
        }

        UserArray ua = new UserArray();
        ua.setUser(matchedUserListResponse);

        return new ResponseEntity<>(ua, HttpStatus.OK);
    }
}
