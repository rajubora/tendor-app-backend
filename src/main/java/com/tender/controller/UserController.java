package com.tender.controller;

import com.tender.entity.User;
import com.tender.exception.InvalidRequestException;
import com.tender.model.LoginRequest;
import com.tender.model.LoginResponse;
import com.tender.service.UserService;
import com.tender.validator.RequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")

public class UserController {
@Autowired
UserService userService;
@PostMapping ("/add-user")

public User addUser(@RequestBody User user){
    //Validating User Request
    RequestValidator.validateAddUerRequest(user);

    // Saving to database
    userService.addUser(user);
    throw new InvalidRequestException("abcd");
    //return user;

}
@PostMapping("/login")
public LoginResponse login(@RequestBody LoginRequest request)

{
    return null;


}

}

