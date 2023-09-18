package com.byb.bookYourBook.controller;

import com.byb.bookYourBook.entity.User;
import com.byb.bookYourBook.payload.UserDetailsDto;
import com.byb.bookYourBook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/mydetails")
    ResponseEntity<UserDetailsDto> getDetails(){
        return ResponseEntity.ok().body(userService.getUserDetails());
    }

    @GetMapping("/allUsers")
    ResponseEntity<List<UserDetailsDto>> getAllUsers(){
        return ResponseEntity.ok().body(userService.getAllUser());
    }

    @PutMapping("/update")
    ResponseEntity<UserDetailsDto> updateUser(@RequestBody UserDetailsDto userDetailsDto){
        return ResponseEntity.ok().body(userService.updateUserDetails(userDetailsDto));
    }
}
