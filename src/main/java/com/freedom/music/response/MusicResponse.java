package com.freedom.music.response;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
public class MusicResponse extends BaseResponse {

    private long musicId;
}