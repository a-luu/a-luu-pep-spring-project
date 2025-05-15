package com.example.controller;

import com.example.entity.Account;
import com.example.service.AccountService;
import com.example.entity.Message;
import com.example.service.MessageService;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */

 @RestController
public class SocialMediaController {
    private AccountService accountService;
    private MessageService messageService;
    
    @Autowired
    public SocialMediaController(AccountService accountService, MessageService messageService){
        this.messageService = messageService;
        this.accountService = accountService;
    }

    @PostMapping(value = "/register")
    public ResponseEntity<Account> userRegistration(@RequestBody Account account){
        Account register = accountService.addAccount(account);
        if(account.getPassword().length() < 4 || account.getUsername() == ""){
            return ResponseEntity.status(400).body(null);
        }else if (register == null){
            return ResponseEntity.status(409).body(null);
        }else{
            return ResponseEntity.ok(register);
        }
    }

    @PostMapping(value = "/login")
    public ResponseEntity<Account> userLogin(@RequestBody Account account){
        Optional <Account> user = accountService.login(account);
        if (user == null)
            return ResponseEntity.status(401).body(null);
        return ResponseEntity.ok(user.get());
    }

    @PostMapping(value = "/messages")
    public ResponseEntity<Message> createMessage(@RequestBody Message message){
        Message review = messageService.addMessage(message);
        if (review == null)
            return ResponseEntity.status(400).body(null);
        return ResponseEntity.ok(review);
    }

    @GetMapping("/messages")
    public List<Message> retrieveAllMessages(){
        return messageService.getAllMessages();
    }

    @GetMapping("/messages/{messageId}")
    public ResponseEntity<Message> retrieveMessageByMessageId(@PathVariable Integer messageId){
        Optional<Message> message = messageService.getMessageFromId(messageId);
        if (message == null)
            return ResponseEntity.ok(null);
        return ResponseEntity.ok(message.get());
    }

    @DeleteMapping("/messages/{messageId}")
    public ResponseEntity<String> deleteMessageByMessageId(@PathVariable Integer messageId){
        boolean a = messageService.deleteMessage(messageId);
        if (a == true)
            return ResponseEntity.ok("1");
        return ResponseEntity.ok(null);
    }

    @PatchMapping("/messages/{messageId}")
    public ResponseEntity<String> updateMessage(@PathVariable Integer messageId, @RequestBody Message message){
        Message a = messageService.updateMessage(messageId, message);
        if (a == null)
            return ResponseEntity.status(400).body(null);
        return ResponseEntity.ok("1");
    }

    @GetMapping("accounts/{accountId}/messages")
    public List<Message> retrieveMessagesFromUser(@PathVariable Integer accountId){
        return messageService.getAllMessagesFromUser(accountId);
    }


}
