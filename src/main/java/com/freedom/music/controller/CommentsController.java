package com.freedom.music.controller;

import com.freedom.music.request.CommentsRequest;
import com.freedom.music.response.CommentsResponse;
import com.freedom.music.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.freedom.music.common.constants.StringConstants.SUCCESS;

@RestController
@RequestMapping("/comments")
public class CommentsController {

    private final CommentsService commentsService;

    @Autowired
    public CommentsController(CommentsService commentsService) {
        this.commentsService = commentsService;
    }

    @PostMapping("/add")
    public CommentsResponse addComment(@RequestBody @Validated CommentsRequest commentsRequest) {

        return createSuccessResponse(commentsService.addComments(commentsRequest));
    }

    private CommentsResponse createSuccessResponse(Long musicId) {
        CommentsResponse baseResponse = new CommentsResponse();
        baseResponse.setStatus(SUCCESS);
        baseResponse.setMessage("Comment added successfully");
        baseResponse.setResponseCode("00");
        baseResponse.setCommentsId(musicId.toString());
        return baseResponse;
    }

}