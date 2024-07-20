package com.example.socialmediaaggregator.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private boolean darkMode;
    private String preferredPlatforms;

    @ManyToMany
    @JoinTable(
        name = "user_influencers",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "influencer_id")
    )
    private Set<Influencer> influencers = new HashSet<>();

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isDarkMode() {
        return darkMode;
    }

    public void setDarkMode(boolean darkMode) {
        this.darkMode = darkMode;
    }

    public String getPreferredPlatforms() {
        return preferredPlatforms;
    }

    public void setPreferredPlatforms(String preferredPlatforms) {
        this.preferredPlatforms = preferredPlatforms;
    }

    public Set<Influencer> getInfluencers() {
        return influencers;
    }

    public void setInfluencers(Set<Influencer> influencers) {
        this.influencers = influencers;
    }
}
