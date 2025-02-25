package com.revature.P1DemoBackend.service.implementation;

import com.revature.P1DemoBackend.data.UserRepository;
import com.revature.P1DemoBackend.dto.LoginDTO;
import com.revature.P1DemoBackend.dto.UserDTO;
import com.revature.P1DemoBackend.exception.UserNotFoundException;
import com.revature.P1DemoBackend.mapper.UserMapper;
import com.revature.P1DemoBackend.model.User;
import com.revature.P1DemoBackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService {
    private  UserRepository userRepository;
    private UserMapper userMapper;
@Autowired
    public UserServiceImplementation(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public Optional<UserDTO> registerUser(User user) throws IllegalArgumentException {
        //check for some validation
        if(user.getUsername().isEmpty() && user.getFirstName().isEmpty() && user.getLastName().isEmpty() && user.getPassword().isEmpty()){
            throw new IllegalArgumentException("Ensure you filled out all fields");
        }
        //check for passsword length
        if(user.getPassword().length()<=6){
            throw new IllegalArgumentException("Your password is under 6 digits!");
        }
        //check for existing username
        User existingUser=userRepository.findByUsername(user.getUsername());
        if(existingUser!=null){
            throw new UserNotFoundException("The username is not available!");
        }else{
            User userCreated=userRepository.save(user);
            return Optional.of(userMapper.userToUserDto(userCreated));
        }
    }

    @Override
    public Optional<UserDTO> login(LoginDTO loginDTO) throws UserNotFoundException, IllegalArgumentException {
        //check if the username is null, of if it is only empty string
        if(loginDTO.getUsername()==null || loginDTO.getUsername().isBlank()){
            throw new IllegalArgumentException("Username cannot be empty!");
        }
        // same check for password
        if(loginDTO.getPassword()==null || loginDTO.getPassword().isBlank()){
            throw new IllegalArgumentException("Password cannot be empty!");
        }
        User userRetreived=userRepository.findByUsernameAndPassword(loginDTO.getUsername(),loginDTO.getPassword());
        //check if the username doesn't exist and return user not found
        if(userRetreived==null){
            throw new UserNotFoundException("User account not found, please register!");
        }
        return Optional.of(userMapper.userToUserDto(userRetreived));
    }

    @Override
    public Optional<List<UserDTO>> getAllUsers() {
        List<UserDTO> usersDtoList=userRepository.findAll().stream().map(userMapper::userToUserDto).toList();
        return Optional.of(usersDtoList);
    }

    @Override
    public boolean deleteUser(Long userId) throws UserNotFoundException {
        boolean isDeleted=false;
        Optional<User> userToDelete=userRepository.findById(userId);
        if(userToDelete.isPresent()){
            userRepository.delete(userToDelete.get());
            isDeleted=true;
            return isDeleted;
        }else{
            throw new UserNotFoundException("User not found!");
        }
    }

    //update the role from user to manager vice versa
    @Override
    public Optional<UserDTO> updateUserPartially(User user) {
        Optional<User> userToUpdate=userRepository.findById(user.getUserId());
        if(userToUpdate.isPresent()){
            userToUpdate.get().setRole(user.getRole());
            User userUpdated=userRepository.save(userToUpdate.get());
            return Optional.of((userMapper.userToUserDto(userUpdated)));
        }
        return Optional.empty();
        //throw new UserNotFoundException("User not found!");
    }
}
