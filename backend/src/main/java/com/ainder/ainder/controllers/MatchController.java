package com.ainder.ainder.controllers;

import com.ainder.ainder.config.CustomUserDetails;
import com.ainder.ainder.entities.Conversation;
import com.ainder.ainder.entities.Image;
import com.ainder.ainder.entities.Match;
import com.ainder.ainder.entities.User;
import com.ainder.ainder.restPOJO.Error;
import com.ainder.ainder.restPOJO.UserArray;
import com.ainder.ainder.restPOJO.UserResponse;
import com.ainder.ainder.services.ConversationServiceImpl;
import com.ainder.ainder.services.ImageServiceImpl;
import com.ainder.ainder.services.MatchServiceImpl;
import com.ainder.ainder.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Michał on 2017-11-26.
 */
import org.springframework.core.io.Resource;

@org.springframework.web.bind.annotation.RestController
public class MatchController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private MatchServiceImpl matchService;

    @Autowired
    private ConversationServiceImpl conversationService;

    @Autowired
    private ImageServiceImpl imageService;

    @RequestMapping(path = "*rest/like", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Error> postLike(@RequestParam("userId") Long userId) {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.getUserByLogin(userDetails.getUsername());

        Match m = matchService.getMatchByUserId(user.getIdUser(), userId);
        if(m == null) {
            Match newMatch = new Match(matchService.findAll().size()+1l, "N", user, userService.getUserById(userId));
            matchService.save(newMatch);
        } else {
            matchService.updateMatch(m.getIdMatch());

            Conversation c; //= new Conversation(conversationService.findAll().size()+1l, user, userService.getUserById(userId));
            c = new Conversation();
            c.setUserOne(user);
            c.setUserTwo(userService.getUserById(userId));

            conversationService.save(c);
        }

        return new ResponseEntity<>(new Error(), HttpStatus.OK);
    }

    @RequestMapping(path = "rest/matcher/getNext", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Object getUser(@RequestParam("lastId") Long lastUserId, @RequestParam("distKm") Long km, @RequestParam("longitude") Double lon, @RequestParam("latitude") Double lat) {

        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User me = userService.getUserByLogin(userDetails.getUsername());

        userService.updateLat(lat,me.getIdUser());
        userService.updateLon(lon,me.getIdUser());

        List<User> biggerUserList = userService.getUserBiggerThanGivenId(lastUserId);
        List <User> matchedUserInvitedList = userService.findMatchedInvitedUsersByUserId(me.getIdUser());
        List <User> matchedUserReceivedList = userService.findMatchedReceivedUsersByUserId(me.getIdUser());

        biggerUserList.removeAll(matchedUserInvitedList);
        biggerUserList.removeAll(matchedUserReceivedList);

        for (User user : biggerUserList) {
            if (ControllersUtils.distance(user.getLastLatitude(), me.getLastLatitude(), user.getLastLongitude(), me.getLastLongitude()) <= km * 1000 && user.getIdUser() != me.getIdUser()) {
                List<Image> images = imageService.getImagesByUser(user);
                return new ResponseEntity<>(ControllersUtils.userToUserResponse(user, images), HttpStatus.OK);
            }
        }

        return new ResponseEntity<>(new UserResponse(), HttpStatus.OK);
    }

    @RequestMapping(path = "*rest/userMatches", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserArray> getMatchedUsers() {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User me = userService.getUserByLogin(userDetails.getUsername());

        List<User> matchedInvitedUserList = userService.findMatchedInvitedUsersByUserId(me.getIdUser()); //select
        List<User> matchedReceivedUserList = userService.findMatchedReceivedUsersByUserId(me.getIdUser()); //select
        LinkedList<UserResponse> matchedUserListResponse = new LinkedList<>();

        for (User user : matchedInvitedUserList) {
            List<Image> images = imageService.getImagesByUser(user);
            matchedUserListResponse.add(ControllersUtils.userToUserResponse(user, images));
        }

        for (User user : matchedReceivedUserList) {
            List<Image> images = imageService.getImagesByUser(user);
            matchedUserListResponse.add(ControllersUtils.userToUserResponse(user, images));
        }

        if (matchedUserListResponse == null || matchedUserListResponse.size() < 1) {
            return new ResponseEntity<>(new UserArray(), HttpStatus.OK);
        }

        UserArray ua = new UserArray();
        ua.setUser(matchedUserListResponse);

        return new ResponseEntity<>(ua, HttpStatus.OK);
    }


}
