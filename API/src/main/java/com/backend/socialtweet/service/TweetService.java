package com.backend.socialtweet.service;

import com.backend.socialtweet.dto.TweetDto;
import com.backend.socialtweet.exc.NotFoundException;
import com.backend.socialtweet.model.Tweet;
import com.backend.socialtweet.model.User;
import com.backend.socialtweet.repository.TweetRepository;
import com.backend.socialtweet.request.TweetCreateRequest;
import com.backend.socialtweet.request.UpdateTweetRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TweetService {
    private final TweetRepository tweetRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;

    public TweetDto create(TweetCreateRequest request) {
        User result = userService.findUserById(request.getUserId());
        Tweet tweet = Tweet.builder()
                .text(request.getText())
                .status(request.getStatus())
                .user(result).build();
        return modelMapper.map(tweetRepository.save(tweet), TweetDto.class);
    }

    public Page<TweetDto> getTweets(Pageable page) {
        return tweetRepository.findAll(page).map(x -> modelMapper.map(x, TweetDto.class));
    }

    public Page<TweetDto> getPublicTweets(Integer status, Pageable page) {
        return tweetRepository.findAllByStatus(status,page).map(x -> modelMapper.map(x, TweetDto.class));
    }

    public TweetDto getTweetById(Long id) {
        return modelMapper.map(findTweetById(id), TweetDto.class);
    }

    public TweetDto updateTweetById(Long id, UpdateTweetRequest request) {
        Tweet result = findTweetById(id);
        result.setText(request.getText());
        result.setStatus(request.getStatus());
        return modelMapper.map(tweetRepository.save(result), TweetDto.class);
    }

    public void deleteTweetById(Long id) {
        Tweet inDB = findTweetById(id);
        tweetRepository.delete(inDB);
    }

    protected Tweet findTweetById(Long id) {
        return tweetRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Tweet not found!"));
    }

    public Page<TweetDto> getUsersTweetsByUserId(Long id, Pageable page) {
        User inDB = userService.findUserById(id);
        return tweetRepository.findAllByUser_Id(inDB.getId(), page)
                .map(x -> modelMapper.map(x, TweetDto.class));
    }
}