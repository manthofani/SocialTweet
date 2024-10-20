package com.backend.socialtweet.repository;

import com.backend.socialtweet.model.Retweet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RetweetRepository extends JpaRepository<Retweet, Long> {
    Page<Retweet> findRetweetsByTweet_Id(Long id, Pageable page);
}
