package com.freedom.music.response;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
public class UserResponse extends BaseResponse {

    private long userId;
}