package com.ainder.ainder.controllers;

import com.ainder.ainder.config.CustomUserDetails;
import com.ainder.ainder.entities.Match;
import com.ainder.ainder.entities.User;
import com.ainder.ainder.restPOJO.Error;
import com.ainder.ainder.restPOJO.UserResponse;
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

    @RequestMapping(path = "/next_user", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Object getUser(@RequestParam("last_user_id") Long lastUserId, @RequestParam("distKm") Long km, @RequestParam("lon") Double lon, @RequestParam("lat") Double lat) {

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
            if (distance(user.getLastLatitude(), me.getLastLatitude(), user.getLastLongitude(), me.getLastLongitude()) <= km * 1000) {
                return new ResponseEntity<>(RestController.userToUserResponse(user), HttpStatus.OK);
            }
        }

        return new ResponseEntity<>(new Error("There are no users in your area."), HttpStatus.OK);
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
}
