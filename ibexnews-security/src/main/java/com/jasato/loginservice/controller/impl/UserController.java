package com.jasato.loginservice.controller.impl;


import com.jasato.loginservice.controller.dto.*;
import com.jasato.loginservice.repositories.*;
import com.jasato.loginservice.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.transaction.*;

@RestController
@CrossOrigin("*")
public class UserController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;

    @PostMapping("/authenticate")
    ResponseEntity<?> authenticate(@RequestBody UserLoginDTO user) throws Exception {
        return userService.authenticate(user);
    }

    @PostMapping("/create-user")
    @ResponseStatus(HttpStatus.OK)
    void createUser(@RequestBody UserDTO user) {
        userService.createUser(user);

    }

    @GetMapping("/get-user/")
    UserDTO getUser(@RequestHeader(name = "Authorization") String token) {
        return userService.getUser(token);

    }

    @GetMapping("/add-company/{company}")
    void addCompany(@RequestHeader(name = "Authorization") String token, @PathVariable("company") String ibexCompany) {
        userService.addCompany(token, ibexCompany);

    }


    @DeleteMapping("/remove-company/{company}")
    void removeCompany(@RequestHeader(name = "Authorization") String token, @PathVariable("company") String ibexCompany) {
                userService.removeCompany(token, ibexCompany);
    }

}
