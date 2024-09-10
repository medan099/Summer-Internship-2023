package com.example.backgeotracker.Services;

import com.example.backgeotracker.Entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface userService extends UserDetailsService {


    Optional<User> getUserById(Long id);
    Optional<User> getUserByEmail(String email);
    User updateUser(User user, User updatedUser);
    Optional<User> getUserByResetToken(String resetToken);
    Page<User> findAll(Pageable pageable);

    void deleteUserById(Long id);
}
