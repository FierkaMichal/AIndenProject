package com.ainder.ainder.controllers;

import com.ainder.ainder.config.CustomUserDetails;
import com.ainder.ainder.entities.Conversation;
import com.ainder.ainder.entities.ConversationFlow;
import com.ainder.ainder.entities.User;
import com.ainder.ainder.restPOJO.Error;
import com.ainder.ainder.restPOJO.Message;
import com.ainder.ainder.restPOJO.MessageArray;
import com.ainder.ainder.services.ConversationFlowServiceImpl;
import com.ainder.ainder.services.ConversationServiceImpl;
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

import java.sql.Time;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Michał on 2017-11-26.
 */

@org.springframework.web.bind.annotation.RestController
public class MessageController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private ConversationServiceImpl conversationService;

    @Autowired
    private ConversationFlowServiceImpl conversationFlowService;

    @RequestMapping(path = "*/rest/message", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
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

    @RequestMapping(path = "*/rest/messages", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Object getMessages(@RequestParam("personId") Long userId) {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.getUserByLogin(userDetails.getUsername());

        List<ConversationFlow> messageList = conversationFlowService.getAllMessagesByUsers(user.getIdUser(), userId);
        LinkedList<Message> messageResponseList = new LinkedList<>();

        for (ConversationFlow conversationFlow : messageList) {
            messageResponseList.add(new Message(user.getIdUser(), userId, conversationFlow.getTime().toString() ,conversationFlow.getMessage()));
        }

        if (messageResponseList == null || messageResponseList.size() < 1) {
            return new ResponseEntity<>(new ArrayList(), HttpStatus.OK);
        }

        MessageArray ma = new MessageArray();
        ma.setMessageList(messageResponseList);

        return new ResponseEntity<>(ma, HttpStatus.OK);
    }
}
