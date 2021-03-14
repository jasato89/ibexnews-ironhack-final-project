package com.jasato.ibexnews.feign.controller.impl;

import com.jasato.ibexnews.feign.client.*;
import com.jasato.ibexnews.feign.controller.dtos.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin("*")
public class UsersController {

    @Autowired
    UsersClient usersClient;

    @PostMapping("/authenticate")
    ResponseEntity<?> authenticate(@RequestBody UserLoginDTO user) {
        return usersClient.authenticate(user);
    }

    @PostMapping("/create-user")
    void createUser(@RequestBody UserDTO user) {
        usersClient.createUser(user);
    }

    @GetMapping("/get-user")
    UserDTO getUser(@RequestHeader(name = "Authorization") String token) {
       return usersClient.getUser(token);

    }

    @GetMapping("/add-company/{company}")
    void addCompany(@RequestHeader(name = "Authorization") String token, @PathVariable("company") String ibexCompany) {
         usersClient.addCompany(token, ibexCompany);
    }



    @DeleteMapping("/remove-company/{company}")
    void removeCompany(@RequestHeader(name = "Authorization") String token, @PathVariable("company") String ibexCompany){
        usersClient.removeCompany(token, ibexCompany);
    }


}
