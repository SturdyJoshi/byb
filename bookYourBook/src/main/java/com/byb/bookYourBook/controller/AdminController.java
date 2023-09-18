package com.byb.bookYourBook.controller;

import com.byb.bookYourBook.payload.SignInDto;
import com.byb.bookYourBook.payload.UserDetailsDto;
import com.byb.bookYourBook.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/delete/user/{email}")
    ResponseEntity<Boolean> deleteUser(@PathVariable String email){
        return ResponseEntity.ok().body(adminService.deleteUser(email));
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/delete/listing/{id}")
    ResponseEntity<Boolean> deleteUser(@PathVariable int id){
        return ResponseEntity.ok().body(adminService.deleteBookListing(id));
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/create/new")
    ResponseEntity<Boolean> createUser(@RequestBody SignInDto signInDto){
        return ResponseEntity.ok().body(adminService.createAdmin(signInDto));
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/allusers")
    ResponseEntity<List<UserDetailsDto>> getAllUsers(){
        return ResponseEntity.ok().body(adminService.getAllUsers());
    }
}