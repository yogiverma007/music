package com.freedom.music.request;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import static com.freedom.music.common.constants.StringConstants.*;

@Data
public class MusicDTO {

    @NotBlank(message = INVALID_USER_ID)
    @Pattern(regexp = "^\\d+$", message = INVALID_USER_ID)
    private String userId;

    @NotBlank(message = INVALID_NAME)
    @Length(min = 3, max = 100, message = INVALID_NAME)
    private String name;

    @NotBlank(message = INVALID_MUSIC_TYPE)
    @Pattern(regexp = "AUDIO|VIDEO|audio|video", message = INVALID_MUSIC_TYPE)
    private String type;

    private String status;

    @Length(min = 3, max = 500, message = INVALID_LOCATION)
    private String location;

    @NotBlank(message = INVALID_DESCRIPTION)
    @Pattern(regexp = "^[a-zA-Z0-9 ]*$", message = INVALID_DESCRIPTION)
    @Length(min = 3, max = 100, message = INVALID_DESCRIPTION)
    private String description;
}