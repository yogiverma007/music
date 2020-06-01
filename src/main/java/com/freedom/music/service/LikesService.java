package com.freedom.music.service;

import com.freedom.music.datasource.model.Likes;
import com.freedom.music.datasource.repository.LikesRepository;
import com.freedom.music.datasource.repository.MusicRepository;
import com.freedom.music.datasource.repository.UserRepository;
import com.freedom.music.exception.ValidationException;
import com.freedom.music.request.LikesRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.freedom.music.common.constants.StringConstants.INVALID_MUSIC_ID;
import static com.freedom.music.common.constants.StringConstants.INVALID_USER_ID;

@Service
public class LikesService {

    private final LikesRepository likesRepository;
    private final UserRepository userRepository;
    private final MusicRepository musicRepository;

    @Autowired
    public LikesService(LikesRepository likesRepository, UserRepository userRepository, MusicRepository musicRepository) {
        this.likesRepository = likesRepository;
        this.userRepository = userRepository;
        this.musicRepository = musicRepository;
    }

    public Long addlikes(LikesRequest likesRequest) {
        userRepository.findById(likesRequest.getUserId()).orElseThrow(() -> new ValidationException(INVALID_USER_ID));
        musicRepository.findById(likesRequest.getMusicId()).orElseThrow(() -> new ValidationException(INVALID_MUSIC_ID));
        likesRequest.getMusicId();
        likesRequest.getUserId();
        return likesRepository.save(convertToLikes(likesRequest)).getId();
    }

    private Likes convertToLikes(LikesRequest likesRequest) {
        Likes likes = new Likes();
        likes.setMusicId(likesRequest.getMusicId());
        likes.setStatus("ACTIVE");
        likes.setUserId(likesRequest.getUserId());
        return likes;
    }
}