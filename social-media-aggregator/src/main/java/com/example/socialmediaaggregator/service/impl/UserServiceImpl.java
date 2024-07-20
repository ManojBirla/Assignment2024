package com.example.socialmediaaggregator.service.impl;

import com.example.socialmediaaggregator.model.User;
import com.example.socialmediaaggregator.model.Influencer;
import com.example.socialmediaaggregator.repository.UserRepository;
import com.example.socialmediaaggregator.repository.InfluencerRepository;
import com.example.socialmediaaggregator.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private InfluencerRepository influencerRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long id, User userDetails) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setUsername(userDetails.getUsername());
        user.setDarkMode(userDetails.isDarkMode());
        user.setPreferredPlatforms(userDetails.getPreferredPlatforms());
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User updateUserPreferredPlatforms(Long id, String platforms) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setPreferredPlatforms(platforms);
        return userRepository.save(user);
    }

    @Override
    public User updateUserDarkMode(Long id, boolean darkMode) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setDarkMode(darkMode);
        return userRepository.save(user);
    }

    @Override
    public User followInfluencer(Long userId, Long influencerId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Influencer influencer = influencerRepository.findById(influencerId).orElseThrow(() -> new RuntimeException("Influencer not found"));
        user.getInfluencers().add(influencer);
        return userRepository.save(user);
    }

    @Override
    public List<Map<String, String>> getFeeds(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        List<Map<String, String>> feeds = new ArrayList<>();

        for (Influencer influencer : user.getInfluencers()) {
            Map<String, String> feed = new HashMap<>();
            feed.put("influencer", influencer.getName());
            feed.put("profileUrl", influencer.getProfileUrl());
            feed.put("latestFeed", "Sample feed data from various social media platforms.");
            feeds.add(feed);
        }

        return feeds;
    }
}
