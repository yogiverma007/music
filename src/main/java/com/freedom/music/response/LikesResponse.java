package com.freedom.music.response;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
public class LikesResponse extends BaseResponse {

    private Long likeId;
}