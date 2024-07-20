package com.example.socialmediaaggregator.service.impl;

import com.example.socialmediaaggregator.model.Influencer;
import com.example.socialmediaaggregator.repository.InfluencerRepository;
import com.example.socialmediaaggregator.service.InfluencerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InfluencerServiceImpl implements InfluencerService {

    @Autowired
    private InfluencerRepository influencerRepository;

    @Override
    public List<Influencer> getAllInfluencers() {
        return influencerRepository.findAll();
    }

    @Override
    public Optional<Influencer> getInfluencerById(Long id) {
        return influencerRepository.findById(id);
    }

    @Override
    public Influencer createInfluencer(Influencer influencer) {
        return influencerRepository.save(influencer);
    }

    @Override
    public Influencer updateInfluencer(Long id, Influencer influencerDetails) {
        Influencer influencer = influencerRepository.findById(id).orElseThrow(() -> new RuntimeException("Influencer not found"));
        influencer.setName(influencerDetails.getName());
        influencer.setProfileUrl(influencerDetails.getProfileUrl());
        return influencerRepository.save(influencer);
    }

    @Override
    public void deleteInfluencer(Long id) {
        influencerRepository.deleteById(id);
    }
}
