package com.backend.socialtweet.controller;

import com.backend.socialtweet.dto.LikeDto;
import com.backend.socialtweet.request.LikeCreateRequest;
import com.backend.socialtweet.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/likes")
@RequiredArgsConstructor
public class LikeController {
    private final LikeService likeService;

    @PostMapping
    ResponseEntity<LikeDto> create(@RequestBody LikeCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(likeService.create(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        likeService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}