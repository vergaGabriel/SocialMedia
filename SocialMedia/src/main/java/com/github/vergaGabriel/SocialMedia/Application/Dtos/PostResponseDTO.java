package com.github.vergaGabriel.SocialMedia.Application.Dtos;

import com.github.vergaGabriel.SocialMedia.Domain.Models.Post;

import java.time.LocalDateTime;

public class PostResponseDTO {
    private String name;
    private String content;
    private LocalDateTime creationDate;
    private String authorName;

    public PostResponseDTO(Post post) {
        this.name = post.getName();
        this.content = post.getContent();
        this.creationDate = post.getCreationDate();
        this.authorName = post.getAuthor().getName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
}
