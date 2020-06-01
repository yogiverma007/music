package com.freedom.music.datasource.repository;

import com.freedom.music.datasource.model.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface LikesRepository extends JpaRepository<Likes, Long>, JpaSpecificationExecutor<Likes> {

    List<Likes> findByMusicId(Long musicId);
}