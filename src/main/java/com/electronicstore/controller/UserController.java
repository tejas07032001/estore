package com.electronicstore.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.electronicstore.dtos.ApiResponseMessage;
import com.electronicstore.dtos.UserDto;
import com.electronicstore.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    //Create
    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){

        UserDto user = userService.createUser(userDto);

        return new ResponseEntity<>(user , HttpStatus.CREATED);
    }

    //update

    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("userId") String userId,@Valid @RequestBody UserDto userDto){

        UserDto updateUser = userService.updateUser(userDto, userId);

        return new ResponseEntity<>(updateUser,HttpStatus.OK);

    }

    //delete
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponseMessage> deleteUser(@PathVariable String userId){     //here first we use String to send message then we shifted ti apiresponsemessage class

        userService.deleteUser(userId);

        ApiResponseMessage message = ApiResponseMessage.builder()
                .message("User is deleted")
                .success(true)
                .status(HttpStatus.OK)
                .build();


        return new ResponseEntity<>(message,HttpStatus.OK);
    }


    //get all

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(){

        return new ResponseEntity<>(userService.getAllUser(),HttpStatus.OK);
    }


    //get single

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable String userId){

        return new ResponseEntity<>(userService.getUserById(userId),HttpStatus.OK);
    }

    // get by email
    @GetMapping("/email/{email}")
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable String email){

        return new ResponseEntity<>(userService.getUserByEmail(email),HttpStatus.OK);
    }

    // SEARCH USER
    @GetMapping("/search/{keywords}")
    public ResponseEntity<List<UserDto>> searchUser(@PathVariable String keywords){

        return new ResponseEntity<>(userService.searchUser(keywords),HttpStatus.OK);


    }

}
