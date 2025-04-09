package com.github.vergaGabriel.SocialMedia.Application.Services;

import com.github.vergaGabriel.SocialMedia.Application.Dtos.PostDto;
import com.github.vergaGabriel.SocialMedia.Domain.Models.Post;
import com.github.vergaGabriel.SocialMedia.Domain.Models.User;
import com.github.vergaGabriel.SocialMedia.Infrastructure.Repositories.IPostRepository;
import com.github.vergaGabriel.SocialMedia.Infrastructure.Repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    private IPostRepository postRepository;

    @Autowired
    private IUserRepository userRepository;

    public void likePost(Long postId, Long userId) {
        Optional<Post> postOpt = postRepository.findById(postId);
        Optional<User> userOpt = userRepository.findById(userId);

        if (postOpt.isEmpty() || userOpt.isEmpty()) {
            throw new IllegalArgumentException("User or Post not found.");
        }

        Post post = postOpt.get();
        User user = userOpt.get();

        user.getPostsLikes().add(post);
        userRepository.save(user);
    }

    public void postPost(PostDto postDto) {
        Post post = new Post();
        post.setName(postDto.getName());
        post.setContent(postDto.getContent());
        post.setCreationDate(LocalDateTime.now());
        post.setAuthor(userRepository.findById(postDto.getAuthorId()).orElseThrow());

        postRepository.save(post);
    }

    public List<Post> getAll() {
        return postRepository.findAll();
    }

    public Optional<Post> getById(Long id) {
        return postRepository.findById(id);
    }
}
