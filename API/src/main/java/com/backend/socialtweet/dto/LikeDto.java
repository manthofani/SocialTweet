package com.backend.socialtweet.dto;

import lombok.Data;

@Data
public class LikeDto {
    private long id;
    private long userId;
    private long tweetId;
}