package com.example.socialmediaaggregator.controller;

import com.example.socialmediaaggregator.model.User;
import com.example.socialmediaaggregator.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@Tag(name = "User API", description = "API for managing users")
public class UserController {

    @Autowired
    private UserService userService;

    @Operation(summary = "Get all users")
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @Operation(summary = "Get user by ID")
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Create a new user")
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @Operation(summary = "Update user by ID")
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        return ResponseEntity.ok(userService.updateUser(id, userDetails));
    }

    @Operation(summary = "Delete user by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Update user preferred platforms")
    @PatchMapping("/{id}/platforms")
    public ResponseEntity<User> updateUserPreferredPlatforms(@PathVariable Long id, @RequestBody String platforms) {
        return ResponseEntity.ok(userService.updateUserPreferredPlatforms(id, platforms));
    }

    @Operation(summary = "Update user dark mode preference")
    @PatchMapping("/{id}/darkMode")
    public ResponseEntity<User> updateUserDarkMode(@PathVariable Long id, @RequestBody boolean darkMode) {
        return ResponseEntity.ok(userService.updateUserDarkMode(id, darkMode));
    }

    @Operation(summary = "Follow an influencer")
    @PostMapping("/{userId}/follow/{influencerId}")
    public ResponseEntity<User> followInfluencer(@PathVariable Long userId, @PathVariable Long influencerId) {
        return ResponseEntity.ok(userService.followInfluencer(userId, influencerId));
    }

    @Operation(summary = "Get feeds for user")
    @GetMapping("/{userId}/feeds")
    public ResponseEntity<List<Map<String, String>>> getFeeds(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getFeeds(userId));
    }
}
