package com.revature.P1DemoBackend.controller;

import com.revature.P1DemoBackend.dto.LoginDTO;
import com.revature.P1DemoBackend.dto.UserDTO;
import com.revature.P1DemoBackend.model.User;
import com.revature.P1DemoBackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@CrossOrigin(value = "http://localhost:5173", allowCredentials = "false") //Allows request that came from somewhere
public class AuthenticationController {
    private   UserService userService;
@Autowired
    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }

    //This is the endpoint that handle user registration
    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUserHandler(@RequestBody User user){
        Optional<UserDTO> userCreated=userService.registerUser(user);
        if(userCreated.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(userCreated.get());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //This is the endpoint that handle user login
    @PostMapping("/login")
    public ResponseEntity<UserDTO> loginHandler(@RequestBody LoginDTO loginDTO){
        Optional<UserDTO> loggedInUser=userService.login(loginDTO);
        return ResponseEntity.status(HttpStatus.OK).body(loggedInUser.get());

    }
}
