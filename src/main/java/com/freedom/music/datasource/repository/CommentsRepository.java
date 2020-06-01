package com.freedom.music.datasource.repository;

import com.freedom.music.datasource.model.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface CommentsRepository extends JpaRepository<Comments, Long>, JpaSpecificationExecutor<Comments> {

    List<Comments> findByMusicId(String musicId);

}