package com.github.vergaGabriel.SocialMedia.Infrastructure.Repositories;

import com.github.vergaGabriel.SocialMedia.Domain.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  UserRepository extends JpaRepository<User, Long> {
}
