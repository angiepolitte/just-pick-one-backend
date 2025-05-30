package com.example.just_pick_one.controllers;

import com.example.just_pick_one.configs.WebClientConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/restaurants")
@CrossOrigin(origins = "https://forkandfate.com/", allowCredentials = "true")
//@CrossOrigin(origins = "https://genuine-churros-6c788c.netlify.app", allowCredentials = "true")
public class RestaurantController {

    @Value("${google.api.key}")
    private String apiKey;


    private final WebClient textSearchClient;
    private final WebClient nearbySearchClient;

    public RestaurantController(
            @Qualifier("textSearchClient") WebClient textSearchClient,
            @Qualifier("nearbySearchClient")WebClient nearbySearchClient) {
    this.textSearchClient = textSearchClient;
    this.nearbySearchClient = nearbySearchClient;
    }

    @GetMapping
    public Mono<String> searchRestaurants(@RequestParam String query) {
        return textSearchClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("query", "restaurants " + query)
                        .queryParam("key", apiKey)
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class);
    }

    @GetMapping("/location")
    public Mono<String> getPlacesByLocation(@RequestParam double lat, @RequestParam double lng) {
        return nearbySearchClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("location", lat + "," + lng)
                        .queryParam("radius", "5000")
                        .queryParam("type", "restaurant")
                        .queryParam("key", apiKey)
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class);
    }
}


//    private final WebClient webClient;
//
//    public RestaurantController(WebClient.Builder webClientBuilder) {
//        this.webClient = webClientBuilder.baseUrl("https://maps.googleapis.com/maps/api/place/textsearch/json").build();
//    }
//
//    @GetMapping
//    public Mono<String> searchRestaurants(@RequestParam String query) {
//        System.out.println("API Key: " + apiKey);
//        return webClient.get()
//                .uri(uriBuilder -> uriBuilder
//                        .queryParam("query", "restaurants " + query) // Prepend "restaurants" to the query
//                        .queryParam("key", apiKey)
//                        .build())
//                .accept(MediaType.APPLICATION_JSON)
//                .retrieve()
//                .bodyToMono(String.class);
//    }
//
//    @GetMapping("/restaurants/location")
//    public Mono<String> getPlacesByLocation(@RequestParam double lat, @RequestParam double lng) {
//        return webClient.get()
//                .uri(uriBuilder -> uriBuilder
//                        .replaceQuery("")
//                        .queryParam("location", lat + "," + lng)
//                        .queryParam("radius", "5000") // Radius in meters
//                        .queryParam("type", "restaurant")
//                        .queryParam("key", apiKey)
//                        .build())
//                .accept(MediaType.APPLICATION_JSON)
//                .retrieve()
//                .bodyToMono(String.class);
//    }
//}
