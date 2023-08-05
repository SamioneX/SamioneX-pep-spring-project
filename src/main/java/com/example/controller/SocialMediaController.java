package com.example.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.account.Account;
import com.example.account.AccountService;
import com.example.message.Message;
import com.example.message.MessageService;


/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 *
 * You are expected to have at least the following methods: getAllMessages, getMessageByID, getMessageByAccount, registerAccount, newMessage, login, deleteMessage.
 * 
 * These methods should follow proper naming conventions, and include appropriate arguments. Refer to the tests provided for more details.
 */
@RestController
public class SocialMediaController {
    @Autowired(required = true)
    MessageService messageService;
    
    @Autowired(required = true)
    AccountService accountService;

    @GetMapping("messages")
    List<Message> getAllMessages() {
        return messageService.getAllMessages();
    }

    @GetMapping("/messages/{message_id}")
    Message getMessageByID(@PathVariable("message_id") int messageId) {
        return messageService.findMessageById(messageId);
    }

    @GetMapping("/accounts/{account_id}/messages")
    List<Message> getMessageByAccount(@PathVariable("account_id") int accountId) {
        return messageService.findAllMessagesByAUser(accountId);
    }

    
    @PostMapping("/messages")
    ResponseEntity<Message> newMessage(@RequestBody Message message) {
        Message msg = messageService.addMessage(message);
        if (msg != null) {
            return ResponseEntity.ok(msg);
        } else {
            return ResponseEntity.status(400).build();
        }
    }
    
    // required to pass tests: Tests expect the message content in the request parameters.
    @PostMapping("/messages/create-with-args")
    ResponseEntity<Message> newMessage(@RequestParam int posted_by, @RequestParam String message_text, @RequestParam long time_posted_epoch) {
        Message msg = messageService.addMessage(posted_by, message_text, time_posted_epoch);
        if (msg != null) {
            return ResponseEntity.ok(msg);
        } else {
            return ResponseEntity.status(400).build();
        }
    }

    @DeleteMapping("/messages/{message_id}")
    Message deleteMessage(@PathVariable("message_id") int messageId) {
        return messageService.deleteMessageById(messageId);
    }

    @PatchMapping("/messages/{message_id}")
    ResponseEntity<Message> updateMessage(@PathVariable("message_id") int messageId, @RequestBody Message message) {
        Message msg = messageService.updateMessageById(messageId, message);
        if (msg != null) {
            return ResponseEntity.ok(msg);
        } else {
            return ResponseEntity.status(400).build();
        }
    }
    
    @PostMapping("/register")
    ResponseEntity<Account> registerAccount(@RequestBody Account account) {
        Account acc = accountService.addAccount(account);
        if (acc != null) {
            return ResponseEntity.ok(acc);
        } else {
            return ResponseEntity.status(400).build();
        }
    }

    // required to pass tests: Tests expect the account info in the request parameters.
    @PostMapping("/register-with-args")
    ResponseEntity<Account> registerAccount(@RequestParam String username, @RequestParam String password) {
        Account acc = accountService.addAccount(username, password);
        if (acc != null) {
            return ResponseEntity.ok(acc);
        } else {
            return ResponseEntity.status(400).build();
        }
    }


    @GetMapping("/login")
    ResponseEntity<Account> login(@RequestBody Account account) {
        Account acc = accountService.login(account);
        if (acc != null) {
            return ResponseEntity.ok(acc);
        } else {
            return ResponseEntity.status(401).build();
        }
    }
    
    // required to pass tests: Tests expect the account info in the request parameters.
    @GetMapping("/login-with-args")
    ResponseEntity<Account> login(@RequestParam String username, @RequestParam String password) {
        Account acc = accountService.login(username, password);
        if (acc != null) {
            return ResponseEntity.ok(acc);
        } else {
            return ResponseEntity.status(401).build();
        }
    }

    @PostMapping("/login")
    ResponseEntity<Account> loginUsingPostRequest(@RequestBody Account account) {
        Account acc = accountService.login(account);
        if (acc != null) {
            return ResponseEntity.ok(acc);
        } else {
            return ResponseEntity.status(401).build();
        }
    }
}
