package com.backend.socialtweet.controller;

import com.backend.socialtweet.dto.RetweetDto;
import com.backend.socialtweet.request.RetweetCreateRequest;
import com.backend.socialtweet.request.UpdateRetweetRequest;
import com.backend.socialtweet.service.RetweetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/retweets")
@RequiredArgsConstructor
public class RetweetController {
    private final RetweetService retweetService;

    @PostMapping
    ResponseEntity<RetweetDto> create(@RequestBody RetweetCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(retweetService.create(request));
    }

    @GetMapping("/{id}")
    ResponseEntity<RetweetDto> getById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(retweetService.getRetweetById(id));
    }

    @PutMapping("/{id}")
    ResponseEntity<RetweetDto> updateById(@PathVariable Long id,
                                                 @RequestBody UpdateRetweetRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(retweetService.updateRetweetById(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        retweetService.deleteRetweetById(id);
        return ResponseEntity.ok().build();
    }
}