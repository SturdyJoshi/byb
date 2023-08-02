package com.byb.bookYourBook.controller;

import com.byb.bookYourBook.dto.SignInDto;
import com.byb.bookYourBook.dto.UserDetailsDto;
import com.byb.bookYourBook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signin")
    ResponseEntity<Boolean> signin(@RequestBody SignInDto signInDto){
        return ResponseEntity.ok().body(userService.createUser(signInDto));
    }

    @GetMapping("/mydetails")
    ResponseEntity<UserDetailsDto> getDetails(@RequestParam String userEmail){
        return ResponseEntity.ok().body(userService.getUserDetails(userEmail));
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
