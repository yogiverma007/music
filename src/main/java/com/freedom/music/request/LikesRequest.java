package com.freedom.music.request;

import lombok.Data;

@Data
public class LikesRequest {

    private Long userId;
    private Long musicId;
    private String status;
}