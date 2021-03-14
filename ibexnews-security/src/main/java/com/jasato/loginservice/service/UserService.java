package com.jasato.loginservice.service;

import com.jasato.loginservice.controller.dto.*;
import com.jasato.loginservice.controller.impl.*;
import com.jasato.loginservice.models.User;
import com.jasato.loginservice.repositories.*;
import com.jasato.loginservice.util.*;
import io.jsonwebtoken.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.http.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.stereotype.*;
import org.springframework.web.server.*;

import javax.transaction.*;
import java.time.*;
import java.time.format.*;

@Service
public class UserService {
    @Autowired
    IbexCompanyRepository ibexCompanyRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    JwtUtil jwtUtil;
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private MyUserDetailsService userDetailsService;

    public ResponseEntity<?> authenticate(UserLoginDTO user) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));

        } catch (BadCredentialsException e) {
            logger.warn("Bad Credentials");
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Bad credentials");

        }
        logger.info("Creating user");
        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());
        String jwt = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    public void createUser(UserDTO user) {
        logger.info(user.getEmail());
        if (userRepository.findUserByUsername(user.getEmail()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already exists");
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");


            User user1 = new User(
                    user.getEmail(),
                    passwordEncoder.encode(user.getPassword()),
                    user.getName(),
                    LocalDate.parse(user.getDob(), formatter),
                    user.getJob());
            user1.setRole("USER");
            userRepository.save(user1);

        }
    }

    public UserDTO getUser(String token) {
        User user = retrieveUser(token);
        return new UserDTO(
                user.getUsername(),
                user.getPassword(),
                user.getName(),
                user.getDob().toString(),
                user.getJob(),
                user.getCompanies()
        );
    }

    public void addCompany(String token, String ibexCompany) {
        User user = retrieveUser(token);

        if (ibexCompanyRepository.findByNameAndUser(ibexCompany, user).isPresent()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Company is already in the database");
        } else{
            user.addIbexCompany(ibexCompany);
            userRepository.save(user);

        }


    }

    User retrieveUser(String token) {
        Claims claims = jwtUtil.extractAllClaims(token.substring(7));
        if (userRepository.findUserByUsername(claims.get("sub").toString()).isPresent()) {
            return userRepository.findUserByUsername(claims.get("sub").toString()).get();
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Invalid token");
        }
    }
    @Transactional
    @Modifying
    public void removeCompany(String token, String ibexCompany) {
        User user = retrieveUser(token);
      ibexCompanyRepository.deleteByNameAndUser(ibexCompany, user.getId());


    }
}
