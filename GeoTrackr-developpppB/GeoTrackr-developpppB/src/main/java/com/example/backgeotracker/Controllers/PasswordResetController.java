package com.example.backgeotracker.Controllers;
import com.example.backgeotracker.Services.userService;
import com.example.backgeotracker.Entities.PasswordResetRequest;
import com.example.backgeotracker.Entities.User;
import com.example.backgeotracker.Services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import com.example.backgeotracker.Repositories.userRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Optional;


@CrossOrigin("*")
@RestController
public class PasswordResetController {
    @Autowired
    private  PasswordEncoder passwordEncoder ;
    @Autowired
    private userService userService;

    @Autowired
    private EmailService emailService;
    @Autowired
    private userRepository userRepository;

    @PostMapping("/resetPassword")
    public ResponseEntity<?> resetPassword(@RequestBody PasswordResetRequest resetRequest) {

        String userEmail = resetRequest.getEmail();
        Optional<User> mailUser=userRepository.findByEmail(userEmail)  ;
        if (mailUser.isPresent()) {
            User user=mailUser.get();
            String token = user.getResetToken();
            String resetLink = "http://localhost:4200/reset_password?token="+token;
            String emailBody = "Click the link below to reset your password:\n" + resetLink;
            emailService.sendEmail(resetRequest.getEmail(), "Password Reset Request", emailBody);
        }
        // Return an appropriate response (e.g., success message or error message)
        return ResponseEntity.ok().build();
    }
    @PostMapping("/changePass")
    public User changePassword(@RequestBody PasswordResetRequest passwordResetRequest){
        String newPass=passwordResetRequest.getNewPass();
        String resetToken=passwordResetRequest.getResetToken();
        Optional <User> userOptional = userService.getUserByResetToken(resetToken);
        if (userOptional.isPresent()){
            User user = userOptional.get();
            user.setPassword(passwordEncoder.encode(newPass));
            return userRepository.save(user);
        }
        return null;
    }
}

