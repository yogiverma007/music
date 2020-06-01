package com.freedom.music.response;

import com.freedom.music.dto.CommentsDTO;
import com.freedom.music.dto.LikesDTO;
import lombok.Data;

import java.util.List;

@Data
public class MusicDataResponse extends BaseResponse {

    private Long musicId;
    private List<LikesDTO> likesList;
    private List<CommentsDTO> commentsList;
}