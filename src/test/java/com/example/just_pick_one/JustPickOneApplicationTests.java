package com.example.just_pick_one;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class JustPickOneApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void apiKeyShouldBeSet() {
		String apiKey = System.getenv("GOOGLE_API_KEY");
		assertNotNull(apiKey, "GOOGLE_API_KEY is not set");
	}


}
