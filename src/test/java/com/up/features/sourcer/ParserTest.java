package com.up.features.sourcer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.up.features.entities.Feature;

public class ParserTest {


	private Parser parser = new Parser();

	@Test
	void importData_validJson_featuresExtracted() throws IOException {
		ClassLoader classLoader = getClass().getClassLoader();
		// given
		URL fileUrl = classLoader.getResource("source-data-sample.json");
		// when
		List<Feature> result = parser.importData(fileUrl);

		// then
		assertEquals(2, result.size());
		Feature feature1 = result.get(0);
		Feature feature2 = result.get(1);

		assertEquals("39c2f29e-c0f8-4a39-a98b-deed547d6aea", feature1.getId());
		assertEquals(1554831167697l, feature1.getTimestamp());
		assertEquals(1554831167697l, feature1.getBeginViewingDate());
		assertEquals(1554831202043l, feature1.getEndViewingDate());
		assertEquals("Sentinel-1", feature1.getMissionName());
		assertNotNull(feature1.getQuicklook());
		assertEquals("cf5dbe37-ab95-4af1-97ad-2637aec4ddf0", feature2.getId());
	}
}
