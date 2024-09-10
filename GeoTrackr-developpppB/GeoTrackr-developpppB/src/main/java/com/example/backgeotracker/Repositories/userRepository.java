package com.example.backgeotracker.Repositories;
import com.example.backgeotracker.Entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface    userRepository extends JpaRepository<User,Long> {
    Optional<User> findById(Long id);

    Optional<User> findByResetToken(String resetToken);

    Optional<User> findByEmail(String email);
}
