package com.booksapi.controller;

import com.booksapi.config.JwtTokenUtil;
import com.booksapi.model.dto.UserDto;
import com.booksapi.model.entities.BooksUser;
import com.booksapi.payload.JwtRequest;
import com.booksapi.payload.LoginAPIResponse;
import com.booksapi.service.UserDetailsService;
import com.booksapi.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
public class JwtController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper mapper;

    @PostMapping(value = "/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        final String finalToken = "Bearer " + token;
        return ResponseEntity.ok(new LoginAPIResponse(finalToken, "User Login Successfully!", HttpStatus.OK));

    }

    @PostMapping(value = "/register")
    public ResponseEntity<?> saveUser(@RequestBody BooksUser booksUser) throws Exception {
        return ResponseEntity.ok(userService.createUser(mapper.map(booksUser, UserDto.class)));
    }

    @GetMapping(value = "api/v1/users-all")
    public ResponseEntity<?> fetchAllUsers() throws Exception {
        System.out.println("Fetch All Users Called from Controller");
        return ResponseEntity.ok(userService.getAllUsers());
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    @PutMapping(value = "/user/update/{user_id}")
    private ResponseEntity<?> updateUserInfo(UserDto userDto, @PathVariable("user_id") int userid) {
        try {
            UserDto update = userService.update(userDto, userid);
            return new ResponseEntity<>(update, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
