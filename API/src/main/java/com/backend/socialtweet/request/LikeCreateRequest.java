package com.backend.socialtweet.request;

import lombok.Getter;

@Getter
public class LikeCreateRequest {
    private long userId;
    private long tweetId;
}