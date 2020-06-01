package com.freedom.music.controller;

import com.freedom.music.request.LikesRequest;
import com.freedom.music.response.LikesResponse;
import com.freedom.music.service.LikesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.freedom.music.common.constants.StringConstants.SUCCESS;

@RestController
@RequestMapping("/likes")
public class LikesController {

    private final LikesService likesService;

    @Autowired
    public LikesController(LikesService likesService) {
        this.likesService = likesService;
    }

    @PostMapping("/add")
    public LikesResponse addComment(@RequestBody @Validated LikesRequest commentsRequest) {

        return createSuccessResponse(likesService.addlikes(commentsRequest));
    }

    private LikesResponse createSuccessResponse(Long likeId) {
        LikesResponse baseResponse = new LikesResponse();
        baseResponse.setStatus(SUCCESS);
        baseResponse.setMessage("Likes added successfully");
        baseResponse.setResponseCode("00");
        baseResponse.setLikeId(likeId);
        return baseResponse;
    }
}