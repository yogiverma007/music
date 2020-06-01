package com.freedom.music.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class LikesDTO {

    private Long id;
    private Long userId;
    private Long musicId;
    private String status;

}