package com.example.just_pick_one.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean("textSearchClient")
    public WebClient textSearchClient() {
        return WebClient.builder()
                .baseUrl("https://maps.googleapis.com/maps/api/place/textsearch/json")
                .build();
    }

    @Bean("nearbySearchClient")
    public WebClient nearbySearchClient() {
        return WebClient.builder()
                .baseUrl("https://maps.googleapis.com/maps/api/place/nearbysearch/json")
                .build();
    }

}
