package com.backend.socialtweet.request;

import lombok.Getter;

@Getter
public class TweetCreateRequest {
    private String text;
    private Integer status;
    private Long userId;
}