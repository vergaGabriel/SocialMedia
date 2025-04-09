package com.github.vergaGabriel.SocialMedia.Interface.Controllers;

import com.github.vergaGabriel.SocialMedia.Application.Dtos.PostDto;
import com.github.vergaGabriel.SocialMedia.Application.Dtos.PostResponseDTO;
import com.github.vergaGabriel.SocialMedia.Application.Services.PostService;
import com.github.vergaGabriel.SocialMedia.Domain.Models.Post;
import com.github.vergaGabriel.SocialMedia.Domain.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/post")
public class PostController {
    @Autowired
    private PostService postService;

    @PostMapping("/create")
    public ResponseEntity<String> createPost(@RequestBody PostDto postDto) {
        try {
            postService.postPost(postDto);
            return ResponseEntity.ok("Post created with successfully!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/{userId}/like/{postId}")
    public ResponseEntity<String> likePost(
            @PathVariable Long userId,
            @PathVariable Long postId) {
        try {
            postService.likePost(userId, postId);
            return ResponseEntity.ok("User " + userId + " liked post " + postId);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public List<PostResponseDTO> getAll() {
        List<Post> posts = postService.getAll();
        List<PostResponseDTO> dtoList = posts.stream()
                .map(PostResponseDTO::new)
                .collect(Collectors.toList());

        return dtoList;
    }

    @GetMapping("{id}/likes")
    public ResponseEntity<List<String>> getLikes(@PathVariable Long id) {
        Optional<Post> postOpt = postService.getById(id);
        if (postOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Post post = postOpt.get();
        List<String> followerNames = post.getLikes().stream()
                .map(User::getName)
                .collect(Collectors.toList());

        return ResponseEntity.ok(followerNames);
    }
}
