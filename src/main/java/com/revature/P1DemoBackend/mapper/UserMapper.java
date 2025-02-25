package com.revature.P1DemoBackend.mapper;

import com.revature.P1DemoBackend.dto.UserDTO;
import com.revature.P1DemoBackend.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    // Entity to DTO
    public UserDTO userToUserDto(User user) {
        if (user == null) {
            return null;
        }
        return new UserDTO(
                user.getUserId(),
                user.getFirstName(),
                user.getLastName(),
                user.getUsername(),
                user.getRole()
        );
    }

    // DTO to Entity
    public User userDtoToUser(UserDTO userDTO) {
        if (userDTO == null) {
            return null;
        }
        User user = new User();
        user.setUserId(userDTO.getUserId());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setUserName(userDTO.getUsername());
        user.setRole(userDTO.getRole());
        return user;
    }
}
