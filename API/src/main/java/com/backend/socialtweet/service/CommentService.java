package com.backend.socialtweet.service;

import com.backend.socialtweet.dto.CommentDto;
import com.backend.socialtweet.exc.NotFoundException;
import com.backend.socialtweet.model.Comment;
import com.backend.socialtweet.model.Tweet;
import com.backend.socialtweet.model.User;
import com.backend.socialtweet.repository.CommentRepository;
import com.backend.socialtweet.request.CommentCreateRequest;
import com.backend.socialtweet.request.UpdateCommentRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserService userService;
    private final TweetService tweetService;
    private final ModelMapper modelMapper;

    public CommentDto create(CommentCreateRequest request) {
        User user = userService.findUserById(request.getUserId());
        Tweet tweet = tweetService.findTweetById(request.getTweetId());
        Comment comment = Comment.builder()
                .text(request.getText())
                .user(user)
                .tweet(tweet).build();
        return modelMapper.map(commentRepository.save(comment), CommentDto.class);
    }

    public CommentDto getById(Long id) {
        return modelMapper.map(findById(id), CommentDto.class);
    }

    public CommentDto updateById(Long id, UpdateCommentRequest request) {
        Comment result = findById(id);
        result.setText(request.getText());
        return modelMapper.map(commentRepository.save(result), CommentDto.class);
    }

    public void deleteById(Long id) {
        Comment result = findById(id);
        commentRepository.delete(result);
    }

    protected Comment findById(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Comment not found!"));
    }

    public Page<CommentDto> getCommentsById(Long id, Pageable page) {
        Tweet result = tweetService.findTweetById(id);
        return commentRepository.findCommentsByTweet_Id(result.getId(), page)
                .map(x -> modelMapper.map(x, CommentDto.class));
    }
}