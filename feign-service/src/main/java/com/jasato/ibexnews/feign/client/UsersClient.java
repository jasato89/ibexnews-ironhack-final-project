package com.jasato.ibexnews.feign.client;

import com.jasato.ibexnews.feign.controller.dtos.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@FeignClient(name = "security", url =  "https://ibexnews-users.herokuapp.com/")
public interface UsersClient {

    @PostMapping("/authenticate")
    ResponseEntity<?> authenticate(@RequestBody UserLoginDTO user);

    @PostMapping("/create-user")
    void createUser(@RequestBody UserDTO user);

    @GetMapping("/get-user/")
    UserDTO getUser(@RequestHeader(name = "Authorization") String token);

    @GetMapping("/add-company/{company}")
    void addCompany(@RequestHeader(name = "Authorization") String token, @PathVariable("company") String ibexCompany);

    @DeleteMapping("/remove-company/{company}")
    void removeCompany(@RequestHeader(name = "Authorization") String token, @PathVariable("company") String ibexCompany);
    }
