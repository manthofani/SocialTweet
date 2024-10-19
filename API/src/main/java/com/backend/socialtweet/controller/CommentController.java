package com.backend.socialtweet.controller;

import com.backend.socialtweet.dto.CommentDto;
import com.backend.socialtweet.request.CommentCreateRequest;
import com.backend.socialtweet.request.UpdateCommentRequest;
import com.backend.socialtweet.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    ResponseEntity<CommentDto> create(@RequestBody CommentCreateRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(commentService.create(request));
    }

    @GetMapping("/{id}")
    ResponseEntity<CommentDto> getById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(commentService.getById(id));
    }

    @PutMapping("/{id}")
    ResponseEntity<CommentDto> updateById(@PathVariable Long id,
                                                 @RequestBody UpdateCommentRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(commentService.updateById(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        commentService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}