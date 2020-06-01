package com.freedom.music.service;

import com.freedom.music.datasource.model.Likes;
import com.freedom.music.datasource.model.Music;
import com.freedom.music.datasource.repository.LikesRepository;
import com.freedom.music.datasource.repository.MusicRepository;
import com.freedom.music.datasource.repository.UserRepository;
import com.freedom.music.dto.LikesDTO;
import com.freedom.music.exception.ValidationException;
import com.freedom.music.request.MusicDTO;
import com.freedom.music.request.MusicStatusRequest;
import com.freedom.music.response.MusicDataResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.freedom.music.common.constants.StringConstants.*;

@Service
public class MusicService {

    private final MusicRepository musicRepository;
    private final UserRepository userRepository;
    private final CommentsService commentsService;
    private final LikesRepository likesRepository;

    @Autowired
    public MusicService(MusicRepository musicRepository, UserRepository userRepository, CommentsService commentsService, LikesRepository likesRepository) {
        this.musicRepository = musicRepository;
        this.userRepository = userRepository;
        this.commentsService = commentsService;
        this.likesRepository = likesRepository;
    }

    public long addMusic(MusicDTO musicDTO) {
        userRepository.findById(Long.parseLong(musicDTO.getUserId())).orElseThrow(() -> new ValidationException(INVALID_USER_ID));
        Music music = convertToMusicFromRequest(musicDTO);

        musicRepository.save(music);
        return music.getId();
    }

    public MusicDTO updateMusicStatus(MusicStatusRequest musicRequest) {
        Music music = musicRepository.findById(Long.parseLong(musicRequest.getMusicId())).orElseThrow(() -> new ValidationException(MUSIC_NOT_FOUND));
        if (!music.getUserId().equalsIgnoreCase(musicRequest.getUserId())) {
            throw new ValidationException(UNAUTHORIZED_USER_ID);
        }

        music.setStatus(musicRequest.getStatus());
        musicRepository.save(music);

        return convertToMusicFromRequest(music);
    }

    private Music convertToMusicFromRequest(MusicDTO musicDTO) {

        Music music = new Music();
        music.setUserId(musicDTO.getUserId());
        music.setName(musicDTO.getName());
        music.setType(musicDTO.getType());
        music.setDescription(musicDTO.getDescription());
        music.setLocation(musicDTO.getLocation());
        music.setStatus(INACTIVE_MUSIC);

        return music;
    }

    private MusicDTO convertToMusicFromRequest(Music music) {

        MusicDTO musicDTO = new MusicDTO();
        musicDTO.setUserId(music.getUserId());
        musicDTO.setName(music.getName());
        musicDTO.setType(music.getType());
        musicDTO.setDescription(music.getDescription());
        musicDTO.setLocation(music.getLocation());
        musicDTO.setStatus(music.getStatus());

        return musicDTO;
    }

    public MusicDataResponse getCommentsListFromMusicId(Long musicId) {

        musicRepository.findById(musicId).orElseThrow(() -> new ValidationException(MUSIC_NOT_FOUND));

        MusicDataResponse musicDataResponse = new MusicDataResponse();
        musicDataResponse.setMusicId(musicId);
        musicDataResponse.setCommentsList(commentsService.getCommentsListFromMusicId(musicId));
        musicDataResponse.setLikesList(getLikesDTOList(musicId));

        musicDataResponse.setMessage("Success");
        musicDataResponse.setResponseCode("00");
        musicDataResponse.setStatus("SUCCESS");

        return musicDataResponse;
    }

    private List<LikesDTO> getLikesDTOList(Long musicId) {
        List<Likes> likesList = likesRepository.findByMusicId(musicId);
        List<LikesDTO> likesDTOList = new ArrayList<>();
        likesList.forEach(likes -> {
            LikesDTO likesDTO = new LikesDTO();
            likesDTO.setId(likes.getId());
            likesDTO.setMusicId(likes.getMusicId());
            likesDTO.setUserId(likes.getUserId());
            likesDTO.setStatus(likes.getStatus());
            likesDTOList.add(likesDTO);
        });
        return likesDTOList;
    }
}