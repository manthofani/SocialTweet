package com.backend.socialtweet.request;

import lombok.Getter;

@Getter
public class AuthRequest {
    private String username;
    private String password;
}