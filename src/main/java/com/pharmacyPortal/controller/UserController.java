package com.pharmacyPortal.controller;

import com.pharmacyPortal.entity.User;
import com.pharmacyPortal.payloads.ApiResponse;
import com.pharmacyPortal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;
    @PostMapping("/addUser")
    public ResponseEntity<User>createUser(@RequestBody User user){
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        return new ResponseEntity<>(this.userService.addUser(user), HttpStatus.CREATED);
    }
    @PutMapping("/updateUser/{userId}")
    public ResponseEntity<User>updateUser(@RequestBody User user,@PathVariable Integer userId){
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        return new ResponseEntity<>(this.userService.updateUser(user,userId),HttpStatus.OK);
    }

    @GetMapping("/getUserById/{userId}")
    public ResponseEntity<User>getUserById(@PathVariable Integer userId){
        return new ResponseEntity<>(this.userService.getUserById(userId),HttpStatus.OK);
    }

    @GetMapping("/getAllCustomer")
    public ResponseEntity<List<User>> getAllUser(){
        return new ResponseEntity<>(this.userService.getAllCustomer(),HttpStatus.OK);
    }

    @GetMapping("/getAllDistributor")
    public ResponseEntity<List<User>> getAllDistributor(){
        return new ResponseEntity<>(this.userService.getAllDistributor(),HttpStatus.OK);
    }

    @GetMapping("/findUserByEmail/{email}")
    public ResponseEntity<User>getUserByEmail(@PathVariable String email){
        return new ResponseEntity<>(this.userService.findUserByEmail(email),HttpStatus.OK);
    }

    @DeleteMapping("/deleteUserById/{userId}")
    public ResponseEntity<ApiResponse> deleteUserById(@PathVariable Integer userId){
        this.userService.deleteUserById(userId);
        return new ResponseEntity<>(new ApiResponse("User Deleted Successfully with id "+userId,true),HttpStatus.OK);
    }
}
