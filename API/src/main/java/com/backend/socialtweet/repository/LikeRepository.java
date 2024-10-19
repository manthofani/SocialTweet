package com.backend.socialtweet.repository;

import com.backend.socialtweet.model.Like;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    Page<Like> findLikesByUser_Id(Long userId, Pageable page);

    Page<Like> findLikesByTweet_Id(Long tweetId, Pageable page);

    Optional<Like> findLikeByUser_IdAndTweet_Id(Long userId, Long tweetId);
}