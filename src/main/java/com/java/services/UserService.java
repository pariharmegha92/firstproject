package com.java.services;

import com.java.dao.UserRepository;
import com.java.entities.EmployeeDetails;
import com.java.entities.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {


    @Autowired
    UserRepository userRepository;

    public ResponseEntity addUser(String name, String mobile, String address) {
        UserDetails userDetails = new UserDetails();
        userDetails.setUsername(name);
        userDetails.setMobile(mobile);
        userDetails.setAddress(address);
        UserDetails user = userRepository.save(userDetails);
        if (user != null) {
            return new ResponseEntity(user, HttpStatus.OK);
        } else {
            return new ResponseEntity("User not save in database! Please check code..", HttpStatus.NO_CONTENT);
        }

    }

    public void deleteUser(int userId) {
        userRepository.deleteById(userId);
    }

    public ResponseEntity updateUser(int userId, String name, String city) {
        if (userRepository.existsById(userId)) {
            UserDetails userDetails = userRepository.findById(userId).get();
            userDetails.setUsername(name);
            userDetails.setAddress(city);
            UserDetails user = userRepository.save(userDetails);
            return new ResponseEntity(user, HttpStatus.OK);
        } else {
            return new ResponseEntity("Id doesn't exists!!", HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity getAllUsers() {
        List<UserDetails> allUser = userRepository.findAll();
        return new ResponseEntity(allUser, HttpStatus.OK);

    }

    public ResponseEntity getUserById(int userId) {
        if (userRepository.existsById(userId)) {
            UserDetails userDetails = userRepository.findById(userId).get();
            return new ResponseEntity(userDetails, HttpStatus.OK);
        } else {
            System.out.println("user not available");
            return new ResponseEntity("id not found in databse", HttpStatus.NOT_FOUND);
        }
  
    }

    public ResponseEntity findByName(String name) {
        List<UserDetails> userDetails = userRepository.findByUsername(name);
        if (!userDetails.isEmpty()) {
            return new ResponseEntity(userDetails, HttpStatus.OK);
        } else {
            return new ResponseEntity(userDetails, HttpStatus.NO_CONTENT);
        }
    }

}
