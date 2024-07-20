package com.example.socialmediaaggregator.service.impl;

import com.example.socialmediaaggregator.model.User;
import com.example.socialmediaaggregator.model.Influencer;
import com.example.socialmediaaggregator.repository.UserRepository;
import com.example.socialmediaaggregator.repository.InfluencerRepository;
import com.example.socialmediaaggregator.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private InfluencerRepository influencerRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllUsers() {
        List<User> users = new ArrayList<>();
        users.add(new User());
        when(userRepository.findAll()).thenReturn(users);

        List<User> result = userService.getAllUsers();

        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    void getUserById() {
        User user = new User();
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        Optional<User> result = userService.getUserById(1L);

        assertTrue(result.isPresent());
    }

    @Test
    void createUser() {
        User user = new User();
        when(userRepository.save(any(User.class))).thenReturn(user);

        User result = userService.createUser(user);

        assertNotNull(result);
    }

    @Test
    void updateUser() {
        User user = new User();
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);

        User updatedUser = new User();
        updatedUser.setUsername("newUsername");

        User result = userService.updateUser(1L, updatedUser);

        assertNotNull(result);
        assertEquals("newUsername", result.getUsername());
    }

    @Test
    void deleteUser() {
        doNothing().when(userRepository).deleteById(1L);

        userService.deleteUser(1L);

        verify(userRepository, times(1)).deleteById(1L);
    }

    @Test
    void updateUserPreferredPlatforms() {
        User user = new User();
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);

        User result = userService.updateUserPreferredPlatforms(1L, "Facebook,Twitter");

        assertNotNull(result);
        assertEquals("Facebook,Twitter", result.getPreferredPlatforms());
    }

    @Test
    void updateUserDarkMode() {
        User user = new User();
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);

        User result = userService.updateUserDarkMode(1L, true);

        assertNotNull(result);
        assertTrue(result.isDarkMode());
    }

    @Test
    void followInfluencer() {
        User user = new User();
        Influencer influencer = new Influencer();
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(influencerRepository.findById(1L)).thenReturn(Optional.of(influencer));
        when(userRepository.save(any(User.class))).thenReturn(user);

        User result = userService.followInfluencer(1L, 1L);

        assertNotNull(result);
        assertTrue(result.getInfluencers().contains(influencer));
    }

    @Test
    void getFeeds() {
        User user = new User();
        Influencer influencer = new Influencer();
        influencer.setName("Test Influencer");
        influencer.setProfileUrl("http://example.com");
        user.getInfluencers().add(influencer);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        List<Map<String, String>> result = userService.getFeeds(1L);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Test Influencer", result.get(0).get("influencer"));
        assertEquals("http://example.com", result.get(0).get("profileUrl"));
        assertEquals("Sample feed data from various social media platforms.", result.get(0).get("latestFeed"));
    }
}
