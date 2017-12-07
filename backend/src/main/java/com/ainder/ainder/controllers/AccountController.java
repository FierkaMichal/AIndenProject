package com.ainder.ainder.controllers;

import com.ainder.ainder.entities.Role;
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

        Role r1 = new Role("USER");
        Role r2 = new Role("ADMIN");
        roleService.save(r1);
        roleService.save(r2);

//        Image picture = new Image();
//
//        File file = new File("plik.png");
//        byte[] picInBytes = new byte[(int) file.length()];
//        FileInputStream fileInputStream = null;
//        try {
//            fileInputStream = new FileInputStream(file);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        try {
//            fileInputStream.read(picInBytes);
//            fileInputStream.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        picture.setPicture(picInBytes);



        if (userService.getUserByLogin(registration.getLogin()) != null) {
            return new ResponseEntity<>(new Error("UserResponse with that login already exists."), HttpStatus.CONFLICT);
        }

        //userService.save(new User((long)(userService.findAll().size()+1), registration.getName(), registration.getSurname(), registration.getLogin(), registration.getPassword(), 0d,0d,roleService.getRoleById(2l)));
        User user = new User();
        user.setName(registration.getName());
        user.setSurname(registration.getSurname());
        user.setLogin(registration.getLogin());
        user.setPassword(registration.getPassword());
        user.setLastLongitude(0d);
        user.setLastLatitude(0d);
        user.setRole(roleService.getRoleById(1l));
        userService.save(user);
        return new ResponseEntity<>(new Error(), HttpStatus.OK);
    }

    @RequestMapping(path = "*rest/logout", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
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

    @RequestMapping(path = "*/rest/user/id", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Object getUser(@RequestParam("user_id") Long id) {
        User u = userService.getUserById(id);

        if (u != null) {

            UserResponse ur = ControllersUtils.userToUserResponse(u);
            return new ResponseEntity<>(ur, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
    }
}
