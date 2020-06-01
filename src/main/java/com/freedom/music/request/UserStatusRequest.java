package com.freedom.music.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import static com.freedom.music.common.constants.StringConstants.*;

@Data
public class UserStatusRequest {

    @NotBlank(message = INVALID_USER_STATUS)
    @Pattern(regexp = "ACTIVE|INACTIVE", message = INVALID_USER_STATUS)
    private String status;

    private Long userId;
}