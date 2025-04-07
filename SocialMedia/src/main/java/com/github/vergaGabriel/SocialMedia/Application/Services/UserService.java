package com.github.vergaGabriel.SocialMedia.Application.Services;

import com.github.vergaGabriel.SocialMedia.Application.Dtos.UserDto;
import com.github.vergaGabriel.SocialMedia.Domain.Models.User;
import com.github.vergaGabriel.SocialMedia.Infrastructure.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void followUser(Long followerId, Long followedId) {
        if (followerId.equals(followedId)) {
            throw new IllegalArgumentException("A user cannot follow themselves.");
        }

        Optional<User> followerOpt = userRepository.findById(followerId);
        Optional<User> followedOpt = userRepository.findById(followedId);

        if (followerOpt.isEmpty() || followedOpt.isEmpty()) {
            throw new IllegalArgumentException("User not found.");
        }

        User follower = followerOpt.get();
        User followed = followedOpt.get();

        follower.getFollowing().add(followed);
        userRepository.save(follower); // Persist the change
    }

    public void postUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getName());
        user.setCourse(userDto.getCourse());
        user.setCreationDate(LocalDateTime.now());
        userRepository.save(user);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public Optional<User> getById(Long id) {
        return userRepository.findById(id);
    }

    public void putUser(User newUser, Long id) {
        User oldUser = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        oldUser.setName(newUser.getName());
        oldUser.setEmail(newUser.getEmail());
        oldUser.setCourse(newUser.getCourse());

        userRepository.save(oldUser);
    }

    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        userRepository.delete(user);
    }
}
