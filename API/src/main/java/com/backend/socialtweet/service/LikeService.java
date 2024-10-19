package com.backend.socialtweet.service;

import com.backend.socialtweet.dto.LikeDto;
import com.backend.socialtweet.exc.NotFoundException;
import com.backend.socialtweet.model.Like;
import com.backend.socialtweet.model.Tweet;
import com.backend.socialtweet.model.User;
import com.backend.socialtweet.repository.LikeRepository;
import com.backend.socialtweet.request.LikeCreateRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final LikeRepository likeRepository;
    private final UserService userService;
    private final TweetService tweetService;
    private final ModelMapper modelMapper;

    public LikeDto create(LikeCreateRequest request) {
        User user = userService.findUserById(request.getUserId());
        Tweet tweet = tweetService.findTweetById(request.getTweetId());
        Like like = Like.builder()
                .tweet(tweet)
                .user(user).build();
        if (!checkUserLikedForThisTweet(request.getUserId(), request.getTweetId()))
            return modelMapper.map(likeRepository.save(like), LikeDto.class);
        return null;
    }

    public void deleteById(Long id) {
        Like toBeDeletedLike = findById(id);
        likeRepository.delete(toBeDeletedLike);
    }

    protected Like findById(Long id) {
        return likeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Like not found"));
    }

    protected boolean checkUserLikedForThisTweet(Long userId, Long tweetId) {
        return likeRepository.findLikeByUser_IdAndTweet_Id(userId, tweetId).isPresent();
    }

    public Page<LikeDto> getTweetsLikesByTweetId(Long id, Pageable page) {
        Tweet result = tweetService.findTweetById(id);
        return likeRepository.findLikesByTweet_Id(result.getId(), page)
                .map(x -> modelMapper.map(x, LikeDto.class));
    }

    public Page<LikeDto> getUsersLikesByUserId(Long id, Pageable page) {
        User result = userService.findUserById(id);
        return likeRepository.findLikesByUser_Id(result.getId(), page)
                .map(x -> modelMapper.map(x, LikeDto.class));
    }
}