package com.backend.socialtweet.controller;

import com.backend.socialtweet.dto.CommentDto;
import com.backend.socialtweet.dto.LikeDto;
import com.backend.socialtweet.dto.RetweetDto;
import com.backend.socialtweet.dto.TweetDto;
import com.backend.socialtweet.request.TweetCreateRequest;
import com.backend.socialtweet.request.UpdateTweetRequest;
import com.backend.socialtweet.service.CommentService;
import com.backend.socialtweet.service.LikeService;
import com.backend.socialtweet.service.RetweetService;
import com.backend.socialtweet.service.TweetService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/tweets")
@RequiredArgsConstructor
public class TweetController {
    private final TweetService tweetService;
    private final LikeService likeService;
    private final RetweetService retweetService;
    private final CommentService commentService;

    @PostMapping
    ResponseEntity<TweetDto> create(@RequestBody TweetCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tweetService.create(request));
    }

    @GetMapping
    ResponseEntity<Page<TweetDto>> getAll(@PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable page) {
        return ResponseEntity.status(HttpStatus.OK).body(tweetService.getTweets(page));
    }

    @GetMapping("/home")
    ResponseEntity<Page<TweetDto>> getPublic(@PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable page) {
        return ResponseEntity.status(HttpStatus.OK).body(tweetService.getPublicTweets(1,page));
    }

    @GetMapping("/{id}")
    ResponseEntity<TweetDto> getById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(tweetService.getTweetById(id));
    }

    @PatchMapping("/{id}")
    ResponseEntity<TweetDto> updateById(@PathVariable Long id,
                                             @RequestBody UpdateTweetRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(tweetService.updateTweetById(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        tweetService.deleteTweetById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/likes")
    ResponseEntity<Page<LikeDto>> getLikesById(@PathVariable Long id,
                                                          @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable page) {
        return ResponseEntity.status(HttpStatus.OK).body(likeService.getTweetsLikesByTweetId(id, page));
    }

    @GetMapping("/{id}/comments")
    ResponseEntity<Page<CommentDto>> getCommentsById(@PathVariable Long id,
                                                                @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable page) {
        return ResponseEntity.status(HttpStatus.OK).body(commentService.getCommentsById(id, page));
    }

    @GetMapping("/{id}/retweets")
    ResponseEntity<Page<RetweetDto>> getRetweetsById(@PathVariable Long id,
                                                                @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable page) {
        return ResponseEntity.status(HttpStatus.OK).body(retweetService.getTweetsRetweetsByTweetId(id, page));
    }
}