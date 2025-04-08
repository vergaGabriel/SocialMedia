package com.github.vergaGabriel.SocialMedia.Interface.Controllers;

import com.github.vergaGabriel.SocialMedia.Application.Dtos.UserDto;
import com.github.vergaGabriel.SocialMedia.Application.Services.UserService;
import com.github.vergaGabriel.SocialMedia.Domain.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return userService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{id}/update")
    public ResponseEntity<String> putUser(@PathVariable Long id, UserDto userDto) {
        try {
            userService.putUser(id, userDto);
            return ResponseEntity.ok("User updated with successfully!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.ok("User deleted with successfully!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
