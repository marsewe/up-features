package com.up.features.domain.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.IMAGE_PNG;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(webEnvironment =
		SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class FeatureControllerTest {

	@Autowired
	private MockMvc mvc;

	@Test
	void getFeatureById_validId_featureReturned() throws Exception {
		String expectedJson = """
				{
				    "id": "39c2f29e-c0f8-4a39-a98b-deed547d6aea",
				    "timestamp": 1554831167697,
				    "beginViewingDate": 1554831167697,
				    "endViewingDate": 1554831202043,
				    "missionName": "Sentinel-1B"
				}
				    """;

		mvc.perform(get("/features/39c2f29e-c0f8-4a39-a98b-deed547d6aea")
						.contentType(APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
				.andExpect(content().json(expectedJson));
	}

	@Test
	void getFeatureById_inValidId_404Returned() throws Exception {

		mvc.perform(get("/features/i-do-not-exist")
						.contentType(APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}

	@Test
	void getFeatures() throws Exception {

		String expectedJson = """
				[
					{
					    "id": "39c2f29e-c0f8-4a39-a98b-deed547d6aea",
					    "timestamp": 1554831167697,
					    "beginViewingDate": 1554831167697,
					    "endViewingDate": 1554831202043,
					    "missionName": "Sentinel-1B"
					},
					{
					    "id": "cf5dbe37-ab95-4af1-97ad-2637aec4ddf0",
					    "timestamp": 1556904743783,
					    "beginViewingDate": 1556904743783,
					    "endViewingDate": 1556904768781,
					    "missionName": "Sentinel-1B"
					},
					{
					    "id": "b0d3bf6a-ff54-49e0-a4cb-e57dcb68d3b5",
					    "timestamp": 1558155148786,
					    "beginViewingDate": 1558155148786,
					    "endViewingDate": 1558155173785,
					    "missionName": "Sentinel-1A"
					}
				]
				""";

		mvc.perform(get("/features/")
						.contentType(APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
				.andExpect(content().json(expectedJson));
	}

	@Test
	void getQuicklookImage_invalidId_404() throws Exception {
		mvc.perform(get("/features/i-do-not-exist/quicklook")
						.contentType(APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}

	@Test
	void getQuicklookImage_noQuicklookImage_404() throws Exception {
		mvc.perform(get("/features/b0d3bf6a-ff54-49e0-a4cb-e57dcb68d3b5/quicklook")
						.contentType(APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}

	@Test
	void getQuicklookImage_validIdAndImage_200() throws Exception {
		mvc.perform(get("/features/39c2f29e-c0f8-4a39-a98b-deed547d6aea/quicklook")
						.contentType(APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(IMAGE_PNG));
	}

}
