package com.backend.socialtweet.request;

import lombok.Getter;

@Getter
public class UpdateTweetRequest {
    private String text;
    private Integer status;
}