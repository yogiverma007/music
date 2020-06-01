package com.freedom.music.service;

import com.freedom.music.datasource.model.User;
import com.freedom.music.datasource.repository.UserRepository;
import com.freedom.music.exception.ValidationException;
import com.freedom.music.request.UserDTO;
import com.freedom.music.request.UserStatusRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.freedom.music.common.constants.StringConstants.USER_NOT_FOUND;
import static com.freedom.music.common.constants.UserStatusEnum.ACTIVE;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public long addUser(UserDTO userDTO) {
        User user = convertToUser(userDTO);
        userRepository.save(user);

        return user.getId();
    }


    public UserDTO updateUserStatus(UserStatusRequest userStatusRequest) {

        User user = userRepository.findById(userStatusRequest.getUserId()).orElseThrow(() -> new ValidationException(USER_NOT_FOUND));

        user.setStatus(userStatusRequest.getStatus());
        userRepository.save(user);

        return convertToUserRequest(user);
    }

    private User convertToUser(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setCity(userDTO.getCity());
        user.setState(userDTO.getState());
        user.setCountry(userDTO.getCountry());
        user.setContactNo(userDTO.getContactNo());
        user.setDescription(userDTO.getDescription());
        user.setEmail(userDTO.getEmail());
        user.setStatus(ACTIVE.name());
        user.setType(userDTO.getType());
        return user;
    }


    private UserDTO convertToUserRequest(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setName(user.getName());
        userDTO.setCity(user.getCity());
        userDTO.setState(user.getState());
        userDTO.setCountry(user.getCountry());
        userDTO.setContactNo(user.getContactNo());
        userDTO.setDescription(user.getDescription());
        userDTO.setEmail(user.getEmail());
        userDTO.setStatus(user.getStatus());
        userDTO.setType(user.getType());
        return userDTO;
    }
}