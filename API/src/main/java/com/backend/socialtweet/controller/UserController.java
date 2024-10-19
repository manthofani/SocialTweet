package com.backend.socialtweet.controller;

import com.backend.socialtweet.dto.LikeDto;
import com.backend.socialtweet.dto.TweetDto;
import com.backend.socialtweet.dto.UserDto;
import com.backend.socialtweet.request.UpdateUserRequest;
import com.backend.socialtweet.service.FileService;
import com.backend.socialtweet.service.LikeService;
import com.backend.socialtweet.service.TweetService;
import com.backend.socialtweet.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final TweetService tweetService;
    private final LikeService likeService;
    private final FileService fileService;

    @GetMapping
    ResponseEntity<Page<UserDto>> get(@PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable page) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUsers(page));
    }

    @GetMapping("/{username}")
    ResponseEntity<UserDto> geByUsername(@PathVariable String username) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserByUsername(username));
    }

    @PutMapping("/{id}")
    ResponseEntity<UserDto> updateById(@PathVariable Long id,
                                           @Valid @RequestBody UpdateUserRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUserById(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/tweets")
    ResponseEntity<Page<TweetDto>> getTweetsByUserId(@PathVariable Long id,
                                                          @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable page) {
        return ResponseEntity.status(HttpStatus.OK).body(tweetService.getUsersTweetsByUserId(id, page));
    }

    @GetMapping("/{id}/likes")
    ResponseEntity<Page<LikeDto>> getLikesByUserId(@PathVariable Long id,
                                                        @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable page) {
        return ResponseEntity.status(HttpStatus.OK).body(likeService.getUsersLikesByUserId(id, page));
    }

    @PostMapping(
            value = "/{username}/image/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void> uploadImage(@PathVariable String username,
                                                @RequestParam("file") MultipartFile file) {
        fileService.uploadImage(username, file);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{username}/image/download")
    ResponseEntity<Resource> downloadImage(@PathVariable String username) {
        Resource file = fileService.downloadImage(username);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachtment; filename\"" + file.getFilename() + "\"").body(file);
    }
}