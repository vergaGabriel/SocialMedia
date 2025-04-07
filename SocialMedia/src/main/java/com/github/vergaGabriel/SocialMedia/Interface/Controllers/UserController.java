package com.github.vergaGabriel.SocialMedia.Interface.Controllers;

import com.github.vergaGabriel.SocialMedia.Application.Dtos.UserDto;
import com.github.vergaGabriel.SocialMedia.Application.Services.UserService;
import com.github.vergaGabriel.SocialMedia.Domain.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/{followerId}/follow/{followedId}")
    public ResponseEntity<String> followUser(
            @PathVariable Long followerId,
            @PathVariable Long followedId) {
        try {
            userService.followUser(followerId, followedId);
            return ResponseEntity.ok("User " + followerId + " now follows user " + followedId);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/post")
    public ResponseEntity<String> postUser(@RequestBody UserDto userDto) {
        try {
            userService.postUser(userDto);
            return ResponseEntity.ok("User created with successfully!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
