package com.example.socialmediaaggregator.service.impl;

import com.example.socialmediaaggregator.model.Influencer;
import com.example.socialmediaaggregator.repository.InfluencerRepository;
import com.example.socialmediaaggregator.service.InfluencerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class InfluencerServiceImplTest {

    @Mock
    private InfluencerRepository influencerRepository;

    @InjectMocks
    private InfluencerServiceImpl influencerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllInfluencers() {
        List<Influencer> influencers = new ArrayList<>();
        influencers.add(new Influencer());
        when(influencerRepository.findAll()).thenReturn(influencers);

        List<Influencer> result = influencerService.getAllInfluencers();

        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    void getInfluencerById() {
        Influencer influencer = new Influencer();
        when(influencerRepository.findById(1L)).thenReturn(Optional.of(influencer));

        Optional<Influencer> result = influencerService.getInfluencerById(1L);

        assertTrue(result.isPresent());
    }

    @Test
    void createInfluencer() {
        Influencer influencer = new Influencer();
        when(influencerRepository.save(any(Influencer.class))).thenReturn(influencer);

        Influencer result = influencerService.createInfluencer(influencer);

        assertNotNull(result);
    }

    @Test
    void updateInfluencer() {
        Influencer influencer = new Influencer();
        when(influencerRepository.findById(1L)).thenReturn(Optional.of(influencer));
        when(influencerRepository.save(any(Influencer.class))).thenReturn(influencer);

        Influencer updatedInfluencer = new Influencer();
        updatedInfluencer.setName("newName");

        Influencer result = influencerService.updateInfluencer(1L, updatedInfluencer);

        assertNotNull(result);
        assertEquals("newName", result.getName());
    }

    @Test
    void deleteInfluencer() {
        doNothing().when(influencerRepository).deleteById(1L);

        influencerService.deleteInfluencer(1L);

        verify(influencerRepository, times(1)).deleteById(1L);
    }
}
