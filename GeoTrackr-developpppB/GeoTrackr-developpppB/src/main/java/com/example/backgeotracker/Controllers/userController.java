package com.example.backgeotracker.Controllers;
import com.example.backgeotracker.Entities.User;
import com.example.backgeotracker.Repositories.userRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.example.backgeotracker.Services.userService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
@CrossOrigin("*")

public class userController {
    private final PasswordEncoder passwordEncoder ;

    @Autowired
    private userService userService;
    @Autowired
    private userRepository userRepository;
    @PatchMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        Optional<User> userOptional = userService.getUserById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            User updated = userService.updateUser(user, updatedUser);
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }


    }

    @GetMapping("/listUsers/{page}/{size}")
    public List<User> listUsers(@PathVariable int page, @PathVariable int size) {

        Pageable pageable = PageRequest.of(page , size);
        Page<User> userPage = userService.findAll(pageable);
        return userPage.getContent();
    }


    @GetMapping("/userCount")
    public ResponseEntity<Long> getUserCount() {
        long totalUsers = userRepository.count();
        return ResponseEntity.ok(totalUsers);
    }

    @GetMapping("/byEmail")
    public ResponseEntity<User> getUserByEmail(@RequestParam String email) {
        Optional<User> userOptional = userService.getUserByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return ResponseEntity.ok(user);
        } else {
            // Handle the case when the user with the given email is not found.
            // You can return ResponseEntity.notFound().build(); or an appropriate response.
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        Optional<User> userOptional = userService.getUserById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            userService.deleteUserById(user.getId());
            return ResponseEntity.noContent().build();
        } else {

            return ResponseEntity.notFound().build();
        }
    }
}
