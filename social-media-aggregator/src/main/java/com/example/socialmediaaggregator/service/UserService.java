package com.example.socialmediaaggregator.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.example.socialmediaaggregator.model.User;

public interface UserService {
	
	List<User> getAllUsers();
	
    Optional<User> getUserById(Long id);
    
    User createUser(User user);
    
    User updateUser(Long id, User userDetails);
    
    void deleteUser(Long id);
    
    User updateUserPreferredPlatforms(Long id, String platforms);
    
    User updateUserDarkMode(Long id, boolean darkMode);
    
    public User followInfluencer(Long userId, Long influencerId);
    
    public List<Map<String, String>> getFeeds(Long userId);

}
