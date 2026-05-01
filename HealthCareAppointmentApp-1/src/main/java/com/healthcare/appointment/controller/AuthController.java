package com.healthcare.appointment.controller;

import com.healthcare.appointment.dto.UserLoginDto;
import com.healthcare.appointment.dto.UserRegisterDto;
import com.healthcare.appointment.entity.User;
import com.healthcare.appointment.security.JwtUtil;
import com.healthcare.appointment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.healthcare.appointment.dto.ForgotPasswordDto;
import com.healthcare.appointment.dto.ResetPasswordDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserRegisterDto dto) {
        Set<String> roles = Collections.singleton(dto.getRole().toUpperCase());
        User user = userService.registerUser(dto.getUsername(), dto.getEmail(), dto.getPassword(), dto.getPhone(), roles);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody UserLoginDto dto) {
        Optional<User> userOpt = userService.findByUsername(dto.getUsername());
        if (userOpt.isPresent() && passwordEncoder.matches(dto.getPassword(), userOpt.get().getPassword())) {
            String token = jwtUtil.generateToken(userOpt.get().getUsername(), userOpt.get().getRoles());
            return ResponseEntity.ok(Collections.singletonMap("token", token));
        }
        return ResponseEntity.status(401).body("Invalid credentials");
    }
    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@Valid @RequestBody ForgotPasswordDto forgotPasswordDto) {
        String email = forgotPasswordDto.getEmail();

        // Simulate checking if user exists (in real case, check DB)
        if (!email.equalsIgnoreCase("test@example.com")) {
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("message", "User not found with email: " + email);
            return ResponseEntity.badRequest().body(error);
        }

        // Generate fake reset token (normally store in DB and email it)
        String resetToken = UUID.randomUUID().toString();

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Password reset link sent to your email");
        response.put("resetToken", resetToken);

        return ResponseEntity.ok(response);
    }
    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@Valid @RequestBody ResetPasswordDto resetPasswordDto) {
        try {
            User user = userService.resetPassword(resetPasswordDto.getToken(), resetPasswordDto.getNewPassword());
 
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Password reset successfully");
            response.put("user", Map.of(
                "id", user.getId(),
                "email", user.getEmail()
            ));
 
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }
    
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response) {
      
        return ResponseEntity.ok("Logged out successfully");
    }
 
}
