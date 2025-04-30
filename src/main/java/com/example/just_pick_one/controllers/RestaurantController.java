package com.example.just_pick_one.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/restaurants")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class RestaurantController {

    @Value("${google.api.key}")
    private String apiKey;

    private final WebClient webClient;

    public RestaurantController(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://maps.googleapis.com/maps/api/place/textsearch/json").build();
    }

    @GetMapping
    public Mono<String> searchRestaurants(@RequestParam String query) {
        System.out.println("API Key: " + apiKey);
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("query", "restaurants " + query) // Prepend "restaurants" to the query
                        .queryParam("key", apiKey)
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class);
    }
}
