package com.example.socialmediaaggregator.service;

import com.example.socialmediaaggregator.model.Influencer;

import java.util.List;
import java.util.Optional;

public interface InfluencerService {
	
	List<Influencer> getAllInfluencers();
    Optional<Influencer> getInfluencerById(Long id);
    Influencer createInfluencer(Influencer influencer);
    Influencer updateInfluencer(Long id, Influencer influencerDetails);
    void deleteInfluencer(Long id);

}
