package com.backend.socialtweet.service;

import com.backend.socialtweet.dto.RetweetDto;
import com.backend.socialtweet.exc.NotFoundException;
import com.backend.socialtweet.model.Retweet;
import com.backend.socialtweet.model.Tweet;
import com.backend.socialtweet.model.User;
import com.backend.socialtweet.repository.RetweetRepository;
import com.backend.socialtweet.repository.TweetRepository;
import com.backend.socialtweet.request.RetweetCreateRequest;
import com.backend.socialtweet.request.UpdateRetweetRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RetweetService {
    private final RetweetRepository retweetRepository;
    private final TweetRepository tweetRepository;
    private final UserService userService;
    private final TweetService tweetService;
    private final ModelMapper modelMapper;

    public RetweetDto create(RetweetCreateRequest request) {
        User user = userService.findUserById(request.getUserId());
        Tweet tweet = tweetService.findTweetById(request.getTweetId());
        Retweet retweet = Retweet.builder()
                .text(request.getText())
                .user(user)
                .tweet(tweet).build();
        return modelMapper.map(retweetRepository.save(retweet), RetweetDto.class);
    }

    public RetweetDto getRetweetById(Long id) {
        return modelMapper.map(findRetweetById(id), RetweetDto.class);
    }

    public RetweetDto updateRetweetById(Long id, UpdateRetweetRequest request) {
        Retweet result = findRetweetById(id);
        result.setText(request.getText());
        return modelMapper.map(retweetRepository.save(result), RetweetDto.class);
    }

    public void deleteRetweetById(Long id) {
        Retweet result = findRetweetById(id);
        retweetRepository.delete(result);
    }

    protected Retweet findRetweetById(Long id) {
        return retweetRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Retweet not found!"));
    }

    public Page<RetweetDto> getTweetsRetweetsByTweetId(Long id, Pageable page) {
        Tweet inDB = tweetService.findTweetById(id);
        return retweetRepository.findRetweetsByTweet_Id(inDB.getId(), page)
                .map(x -> modelMapper.map(x, RetweetDto.class));
    }
}