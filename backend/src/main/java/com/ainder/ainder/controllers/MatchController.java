package com.ainder.ainder.controllers;

import com.ainder.ainder.config.CustomUserDetails;
import com.ainder.ainder.entities.Match;
import com.ainder.ainder.entities.User;
import com.ainder.ainder.restPOJO.Error;
import com.ainder.ainder.services.MatchServiceImpl;
import com.ainder.ainder.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by Michał on 2017-11-26.
 */

@org.springframework.web.bind.annotation.RestController
public class MatchController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private MatchServiceImpl matchService;

    @RequestMapping(path = "*/rest/like", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Error> postLike(@RequestParam("userId") Long userId) {
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
                return new ResponseEntity<>(ControllersUtils.userToUserResponse(user), HttpStatus.OK);
            }
        }

        return new ResponseEntity<>(new Error("There are no users in your area."), HttpStatus.NOT_FOUND);
    }
}
