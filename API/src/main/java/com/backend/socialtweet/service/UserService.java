package com.backend.socialtweet.service;

import com.backend.socialtweet.dto.UserDto;
import com.backend.socialtweet.exc.NotFoundException;
import com.backend.socialtweet.model.Role;
import com.backend.socialtweet.model.User;
import com.backend.socialtweet.repository.UserRepository;
import com.backend.socialtweet.request.RegisterRequest;
import com.backend.socialtweet.request.UpdateUserRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public UserDto create(RegisterRequest request) {
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .birthday(request.getBirthday())
                .role(Role.USER).build();
        return modelMapper.map(userRepository.save(user), UserDto.class);
    }

    public Page<UserDto> getUsers(Pageable page) {
        return userRepository.findAll(page)
                .map(x -> modelMapper.map(x, UserDto.class));
    }

    public UserDto getUserById(Long id) { // will be used
        User result = findUserById(id);
        return modelMapper.map(result, UserDto.class);
    }

    public UserDto getUserByUsername(String username) {
        return modelMapper.map(findUserByUsername(username), UserDto.class);
    }

    public UserDto updateUserById(Long id, UpdateUserRequest request) { // password and username will be added
        User result = findUserById(id);
        result.setName(Optional.ofNullable(request.getName()).orElse(result.getName()));
        result.setBio(Optional.ofNullable(request.getBio()).orElse(result.getBio()));
        result.setLocation(Optional.ofNullable(request.getLocation()).orElse(result.getLocation()));
        result.setWebSite(Optional.ofNullable(request.getWebSite()).orElse(result.getWebSite()));
        return modelMapper.map(userRepository.save(result), UserDto.class);
    }

    public void deleteUserById(Long id) {
        User result = findUserById(id);
        userRepository.delete(result);
    }

    public void updateUserProfileImage(String file, String username) {
        User result = findUserByUsername(username);
        result.setProfileImageLink(file);
        userRepository.save(result);
    }

    protected User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not exist!"));
    }

    protected User findUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new NotFoundException("User not exist!"));
    }
}