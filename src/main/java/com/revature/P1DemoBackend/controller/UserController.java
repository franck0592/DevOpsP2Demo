package com.revature.P1DemoBackend.controller;


import com.revature.P1DemoBackend.dto.UserDTO;
import com.revature.P1DemoBackend.exception.UserNotFoundException;
import com.revature.P1DemoBackend.model.User;
import com.revature.P1DemoBackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@CrossOrigin //Allows request that came from somewhere
public class UserController {

    private UserService userService;
@Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsersHandler(){
       return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers().get());
    }
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUserHandler(@PathVariable Long userId) throws UserNotFoundException {
        boolean isDeleted=userService.deleteUser(userId);
        if (isDeleted){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        throw new UserNotFoundException("User not found!");
    }
    @PatchMapping
    public ResponseEntity<UserDTO> updateUserPartiallyHandler(@RequestBody User user) throws UserNotFoundException{

        Optional<UserDTO> userDtoUpdatedPartially=userService.updateUserPartially(user);

        if(userDtoUpdatedPartially.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(userDtoUpdatedPartially.get());
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }





}
