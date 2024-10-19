package com.backend.socialtweet.service;

import com.backend.socialtweet.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static org.apache.http.entity.ContentType.*;

@Service
@RequiredArgsConstructor
public class FileService {
    private final UserService userService;
    private final Path root = Paths.get("upload");

    public void uploadImage(String username, MultipartFile file) {
        if (file.isEmpty())
            throw new IllegalStateException("File is empty");
        if (!Arrays.asList(IMAGE_JPEG.getMimeType(), IMAGE_PNG.getMimeType(), IMAGE_GIF.getMimeType())
                .contains(file.getContentType()))
            throw new IllegalStateException("File should be an image [" + file.getContentType() + "]");

        String fileName = String.format("%s-%s",UUID.randomUUID() , Objects.requireNonNull(file.getOriginalFilename()).replaceAll("\\s+",""));
        save(file, fileName);
        userService.updateUserProfileImage(fileName, username);

    }

    public void save(MultipartFile file, String filename) {
        try {
            Files.copy(file.getInputStream(),this.root.resolve(filename));
        } catch (IOException e) {
            if(e instanceof FileAlreadyExistsException){
                throw new RuntimeException("File is exists");
            }
            throw new RuntimeException("File could not be saved", e);
        }
    }

    public Resource downloadImage(String username) {
        try {
            User inDB = userService.findUserByUsername(username);
            Path file = root.resolve(inDB.getProfileImageLink());
            Resource resource = new UrlResource(file.toUri());
            if(resource.exists()){
                return resource;
            } else {
                throw new RuntimeException("File not found or Error!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

    }
}
