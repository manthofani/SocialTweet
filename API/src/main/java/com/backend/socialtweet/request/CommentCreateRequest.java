package com.backend.socialtweet.request;

import lombok.Getter;

@Getter
public class CommentCreateRequest {
    private String text;
    private Long userId;
    private Long tweetId;
}