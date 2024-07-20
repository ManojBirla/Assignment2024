package com.example.socialmediaaggregator.controller;

import com.example.socialmediaaggregator.model.Influencer;
import com.example.socialmediaaggregator.service.InfluencerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/influencers")
@Tag(name = "Influencer API", description = "API for managing influencers")
public class InfluencerController {

    @Autowired
    private InfluencerService influencerService;

    @Operation(summary = "Get all influencers")
    @GetMapping
    public List<Influencer> getAllInfluencers() {
        return influencerService.getAllInfluencers();
    }

    @Operation(summary = "Get influencer by ID")
    @GetMapping("/{id}")
    public ResponseEntity<Influencer> getInfluencerById(@PathVariable Long id) {
        return influencerService.getInfluencerById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Create a new influencer")
    @PostMapping
    public Influencer createInfluencer(@RequestBody Influencer influencer) {
        return influencerService.createInfluencer(influencer);
    }

    @Operation(summary = "Update influencer by ID")
    @PutMapping("/{id}")
    public ResponseEntity<Influencer> updateInfluencer(@PathVariable Long id, @RequestBody Influencer influencerDetails) {
        return ResponseEntity.ok(influencerService.updateInfluencer(id, influencerDetails));
    }

    @Operation(summary = "Delete influencer by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInfluencer(@PathVariable Long id) {
        influencerService.deleteInfluencer(id);
        return ResponseEntity.noContent().build();
    }
}