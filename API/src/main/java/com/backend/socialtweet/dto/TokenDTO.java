package com.backend.socialtweet.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TokenDTO {
    private String accessToken;
    private long userId;
    private String username;
}
