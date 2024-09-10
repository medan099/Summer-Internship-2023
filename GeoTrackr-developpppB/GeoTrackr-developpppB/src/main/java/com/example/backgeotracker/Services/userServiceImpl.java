package com.example.backgeotracker.Services;

import com.example.backgeotracker.Entities.User;
import com.example.backgeotracker.Repositories.userRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@Transactional
public class userServiceImpl implements userService {
@Autowired
    private  PasswordEncoder passwordEncoder ;
    private final userRepository userRepository;
    public userServiceImpl(userRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }
    @Override
    public Optional<User> getUserByResetToken(String resetToken) {
        return userRepository.findByResetToken(resetToken);
    }
    @Override
    public User updateUser(User user, User updatedUser) {
        if (updatedUser.getRole() != null) {
            user.setRole(updatedUser.getRole());
        }
        if (updatedUser.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        }
        if (updatedUser.getNom() != null) {
            user.setNom(updatedUser.getNom());
        }
        if (updatedUser.getPrenom() != null) {
            user.setPrenom(updatedUser.getPrenom());
        }
        if (updatedUser.getUsername() != null) {
            user.setUsername(updatedUser.getUsername());
        }
        if (updatedUser.getEmail() != null) {
            user.setEmail(updatedUser.getEmail());
        }

        // Update other attributes

        return userRepository.save(user);
    }
    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
