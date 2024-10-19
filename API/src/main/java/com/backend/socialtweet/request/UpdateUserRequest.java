package com.backend.socialtweet.request;

import lombok.Getter;

@Getter
public class UpdateUserRequest {
    private String name;
    private String bio;
    private String location;
    private String webSite;
}