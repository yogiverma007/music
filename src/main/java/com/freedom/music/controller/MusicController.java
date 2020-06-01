package com.freedom.music.controller;

import com.freedom.music.request.MusicDTO;
import com.freedom.music.request.MusicStatusRequest;
import com.freedom.music.response.MusicDataResponse;
import com.freedom.music.response.MusicResponse;
import com.freedom.music.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.freedom.music.common.constants.StringConstants.SUCCESS;

@RestController
@RequestMapping("/music")
public class MusicController {

    private final MusicService musicService;

    @Autowired
    public MusicController(MusicService musicService) {
        this.musicService = musicService;
    }

    @PostMapping("/add")
    public MusicResponse addMusic(@RequestBody @Validated MusicDTO musicDTO) {
        long musicId = musicService.addMusic(musicDTO);
        MusicResponse baseResponse = createSuccessResponse(musicId);

        return baseResponse;
    }

    @PostMapping("/updateStatus")
    public MusicDTO updateMusicStatus(@RequestBody @Validated MusicStatusRequest musicStatusRequest) {
        MusicDTO musicDTO = musicService.updateMusicStatus(musicStatusRequest);

        return musicDTO;
    }

    @GetMapping("/getData")
    public MusicDataResponse getMusicData(@RequestParam("musicId") Long musicId) {
        return musicService.getCommentsListFromMusicId(musicId);
    }

    private MusicResponse createSuccessResponse(long musicId) {
        MusicResponse baseResponse = new MusicResponse();
        baseResponse.setStatus(SUCCESS);
        baseResponse.setMessage("Music added successfully");
        baseResponse.setResponseCode("00");
        baseResponse.setMusicId(musicId);
        return baseResponse;
    }

}