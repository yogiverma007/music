package com.freedom.music.response;

import lombok.Data;

@Data
public class BaseResponse {

    private String status;
    private String responseCode;
    private String message;
}