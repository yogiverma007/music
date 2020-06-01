package com.freedom.music.service;

import com.freedom.music.datasource.model.Comments;
import com.freedom.music.datasource.model.Music;
import com.freedom.music.datasource.model.User;
import com.freedom.music.datasource.repository.CommentsRepository;
import com.freedom.music.datasource.repository.MusicRepository;
import com.freedom.music.datasource.repository.UserRepository;
import com.freedom.music.dto.CommentsDTO;
import com.freedom.music.exception.ValidationException;
import com.freedom.music.request.CommentsRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.freedom.music.common.constants.CommentsStatusEnum.ACTIVE;
import static com.freedom.music.common.constants.StringConstants.*;

@Service
public class CommentsService {

    private final UserRepository userRepository;
    private final MusicRepository musicRepository;
    private final CommentsRepository commentsRepository;

    public CommentsService(UserRepository userRepository, MusicRepository musicRepository,
                           CommentsRepository commentsRepository) {
        this.userRepository = userRepository;
        this.musicRepository = musicRepository;
        this.commentsRepository = commentsRepository;
    }

    public Long addComments(CommentsRequest commentsRequest) {
        String musicId = commentsRequest.getMusicId();
        String userId = commentsRequest.getUserId();
        doValidations(commentsRequest, musicId, userId);

        Comments comments = convertToComments(commentsRequest);
        commentsRepository.save(comments);
        return comments.getId();
    }

    private void doValidations(CommentsRequest commentsRequest, String musicId, String userId) {
        User user = userRepository.findById(Long.parseLong(userId)).orElseThrow(() -> new ValidationException(INVALID_USER_ID));

        Music music = musicRepository.findById(Long.parseLong(musicId)).orElseThrow(() -> new ValidationException(INVALID_MUSIC_ID));

        if (commentsRequest.getParentId() != null && Long.parseLong(commentsRequest.getParentId()) != 0) {
            commentsRepository.findById(Long.parseLong(commentsRequest.getParentId())).orElseThrow(() -> new ValidationException(INVALID_PARENT_COMMENT_ID));
        }
    }

    public List<CommentsDTO> getCommentsListFromMusicId(Long musicId) {
        List<Comments> commentsList = commentsRepository.findByMusicId(musicId.toString());
        List<CommentsDTO> commentsDTOList = new ArrayList<>();
        commentsList.forEach(comments -> {
            CommentsDTO commentsDTO = new CommentsDTO();
            commentsDTO.setId(comments.getId());
            commentsDTO.setData(comments.getData());
            commentsDTO.setMusicId(comments.getMusicId());
            commentsDTO.setParentCommentId(comments.getParentId());
            commentsDTO.setStatus(comments.getStatus());
            commentsDTO.setUserId(comments.getUserId());
            commentsDTOList.add(commentsDTO);
        });

        return commentsDTOList;
    }

    private Comments convertToComments(CommentsRequest commentsRequest) {
        Comments comments = new Comments();
        comments.setData(commentsRequest.getData());
        comments.setMusicId(commentsRequest.getMusicId());
        comments.setParentId(commentsRequest.getParentId());
        comments.setStatus(ACTIVE.name());
        comments.setUserId(commentsRequest.getUserId());

        return comments;
    }
}
