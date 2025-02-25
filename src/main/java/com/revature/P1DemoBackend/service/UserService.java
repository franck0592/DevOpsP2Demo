package com.revature.P1DemoBackend.service;

import com.revature.P1DemoBackend.dto.LoginDTO;
import com.revature.P1DemoBackend.dto.UserDTO;
import com.revature.P1DemoBackend.exception.UserNotFoundException;
import com.revature.P1DemoBackend.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    //User tasks
    Optional<UserDTO> registerUser(User user) throws IllegalArgumentException;
    Optional<UserDTO> login(LoginDTO loginDTO) throws UserNotFoundException, IllegalArgumentException;
    //-----------------

    //Manager tasks
    Optional<List<UserDTO>> getAllUsers();
    boolean deleteUser(Long userId) throws UserNotFoundException;
    Optional<UserDTO> updateUserPartially(User user) throws UserNotFoundException;
    //----------------------------

}
