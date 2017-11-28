package com.ainder.ainder.controllers;

import com.ainder.ainder.entities.User;
import com.ainder.ainder.restPOJO.Error;
import com.ainder.ainder.restPOJO.Registration;
import com.ainder.ainder.restPOJO.UserResponse;
import com.ainder.ainder.services.RoleServiceImpl;
import com.ainder.ainder.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Michał on 2017-11-26.
 */

@org.springframework.web.bind.annotation.RestController
public class AccountController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private RoleServiceImpl roleService;

    @Qualifier("getTokenStore")
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

        userService.save(new User(0l, registration.getName(), registration.getSurname(), registration.getLogin(), registration.getPassword(), 0d,0d,roleService.getRoleById(2l)));

        return new ResponseEntity<>(new Error(), HttpStatus.OK);
    }

    @RequestMapping(path = "/rest/logout", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Error> logout(@RequestParam("access_token") String accessToken){
        tokenStore.removeAccessToken(tokenStore.readAccessToken(accessToken));
        return new ResponseEntity<>(new Error(), HttpStatus.OK);
    }

    @RequestMapping(path = "*/rest/user/login", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Object getUser(@RequestParam("login") String login) {
        User u = userService.getUserByLogin(login);

        if (u != null) {

            UserResponse ur = ControllersUtils.userToUserResponse(u);
            return new ResponseEntity<>(ur, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
    }
}
