package com.java.controller;

import com.java.entities.EmployeeDetails;
import com.java.entities.UserDetails;
import com.java.services.EmployeeService;
import com.java.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MyController {

    @Autowired
    UserService userService;

    @GetMapping("/home")
    public String home() {
        String message = "hello world";
        return message;
    }

    @GetMapping("/myName")
    public String name() {
        String message = "i am megha";
        return message;
    }

    @GetMapping("/viewHome")
    public String htmlHomePage() {
        return "home/login";
    }

    @PostMapping("/add-User")
    public ResponseEntity addUserDetails(@RequestParam(name = "uname") String username, @RequestParam(name = "mobile") String mobile, @RequestParam(name = "address") String address) {
        if (username.isEmpty()) {
            return new ResponseEntity("Please give user name!!", HttpStatus.NOT_ACCEPTABLE);
        }
        ResponseEntity responseEntity = userService.addUser(username, mobile, address);
        return responseEntity;
    }

    @PostMapping("/update-user")
    public ResponseEntity updateUser(@RequestParam(name = "userId") int id, @RequestParam(name = "username") String username, @RequestParam(name = "city") String city) {
        if (id <= 0) {
            return new ResponseEntity("Please give proper id!!", HttpStatus.NOT_ACCEPTABLE);
        }
        return userService.updateUser(id, username, city);
    }

    @GetMapping("/allUser")
    public ResponseEntity listOfAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/getUserById")
    public ResponseEntity getUserById(@RequestParam(name = "userId") int id) {
        if (id <= 0) {
            return new ResponseEntity("please give proper id", HttpStatus.OK);
        } else {
            System.out.println("given id is not in databse");
            return userService.getUserById(id);
        }
    }

    @GetMapping("/getByName")
    public ResponseEntity getByName(@RequestParam(name = "username") String name) {
        if (!name.isEmpty()) {
            return userService.findByName(name);
        } else {
            return new ResponseEntity("first give name", HttpStatus.OK);
        }
    }

}
