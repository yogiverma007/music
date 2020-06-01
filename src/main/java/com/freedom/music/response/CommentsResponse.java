package com.freedom.music.response;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
public class CommentsResponse extends BaseResponse {
    private String commentsId;

}