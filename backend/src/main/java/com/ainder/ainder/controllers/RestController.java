package com.ainder.ainder.controllers;

import com.ainder.ainder.entities.User;
import com.ainder.ainder.restPOJO.*;
import com.ainder.ainder.restPOJO.Error;
import com.ainder.ainder.services.RoleServiceImpl;
import com.ainder.ainder.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



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

    @RequestMapping(path = "/register", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Error> register(@RequestBody Registration registration) {

        if(userService.getUserByLogin(registration.getLogin()) != null) {
            return new ResponseEntity<>(new Error("User with that login already exists."), HttpStatus.CONFLICT);
        }

        userService.save(new User(registration.getName(),registration.getSurname(),registration.getLogin(), registration.getPassword(), roleService.getRoleById(2l)));

        return new ResponseEntity<>(new Error(), HttpStatus.OK);
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Login> login(@RequestBody Login login) {

        return new ResponseEntity<>(login, HttpStatus.OK);
    }

    @RequestMapping(path = "/description", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Description> changeDescription(@RequestBody Description description) {

        return new ResponseEntity<>(description, HttpStatus.OK);
    }

    @RequestMapping(path = "/like", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Like> postLike(@RequestBody Like like) {

        return new ResponseEntity<>(like, HttpStatus.OK);
    }

    @RequestMapping(path = "/message", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Message> sendMessage(@RequestBody Message message) {

        return new ResponseEntity<>(message, HttpStatus.OK);
    }


}
