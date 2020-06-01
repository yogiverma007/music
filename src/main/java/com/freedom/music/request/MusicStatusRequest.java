package com.freedom.music.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import static com.freedom.music.common.constants.StringConstants.*;
import static com.freedom.music.common.constants.StringConstants.INVALID_MUSIC_ID;

@Data
public class MusicStatusRequest {

    @NotBlank(message = INVALID_USER_STATUS)
    @Pattern(regexp = "ACTIVE|INACTIVE", message = INVALID_USER_STATUS)
    private String status;

    @NotBlank(message = INVALID_USER_ID)
    @Pattern(regexp = "^\\d+$", message = INVALID_USER_ID)
    private String userId;

    @NotBlank(message = INVALID_MUSIC_ID)
    @Pattern(regexp = "^\\d+$", message = INVALID_MUSIC_ID)
    private String musicId;
}