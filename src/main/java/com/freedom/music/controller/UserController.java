package com.freedom.music.controller;

import com.freedom.music.request.UserDTO;
import com.freedom.music.request.UserStatusRequest;
import com.freedom.music.response.UserResponse;
import com.freedom.music.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.freedom.music.common.constants.StringConstants.SUCCESS;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    public UserResponse addUser(@RequestBody @Validated UserDTO userDTO) {
        long userId = userService.addUser(userDTO);
        UserResponse baseResponse = createSuccessResponse(userId);

        return baseResponse;
    }

    @PostMapping("/updateStatus")
    public UserDTO updateUserStatus(@RequestBody @Validated UserStatusRequest userStatusRequest) {
        UserDTO userDTO = userService.updateUserStatus(userStatusRequest);

        return userDTO;
    }

    private UserResponse createSuccessResponse(long userId) {
        UserResponse baseResponse = new UserResponse();
        baseResponse.setStatus(SUCCESS);
        baseResponse.setMessage("User added successfully");
        baseResponse.setResponseCode("00");
        baseResponse.setUserId(userId);
        return baseResponse;
    }
}