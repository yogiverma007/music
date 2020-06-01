package com.freedom.music.request;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import static com.freedom.music.common.constants.StringConstants.*;

@Data
public class CommentsRequest {

    @NotBlank(message = INVALID_USER_ID)
    @Pattern(regexp = "^\\d+$", message = INVALID_USER_ID)
    private String userId;

    @NotBlank(message = INVALID_COMMENTS_DATA)
    @Pattern(regexp = "^[a-zA-Z0-9 ]*$", message = INVALID_COMMENTS_DATA)
    @Length(min = 3, max = 1000, message = INVALID_COMMENTS_DATA)
    private String data;

    @NotBlank(message = INVALID_PARENT_COMMENT_ID)
    @Pattern(regexp = "^\\d+$", message = INVALID_PARENT_COMMENT_ID)
    private String parentId;

    @NotBlank(message = INVALID_MUSIC_ID)
    @Pattern(regexp = "^\\d+$", message = INVALID_MUSIC_ID)
    private String musicId;

    private String status;

}