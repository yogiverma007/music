package com.freedom.music.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CommentsDTO {

    private Long id;
    private String userId;
    private String data;
    private String parentCommentId;
    private String musicId;
    private String status;

}