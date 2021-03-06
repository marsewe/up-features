package com.up.features.domain.sourcer;

import java.io.IOException;
import java.util.Base64;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.up.features.domain.entity.Feature;

/**
 * Deserialize a feature into our Feature entity
 */
public class FeatureDeserializer extends StdDeserializer<Feature> {

	public FeatureDeserializer() {
		this(null);
	}

	protected FeatureDeserializer(Class<?> vc) {
		super(vc);
	}


	@Override
	public Feature deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
		JsonNode node = jsonParser.getCodec().readTree(jsonParser);
		JsonNode properties = node.get("properties");
		String id = properties.get("id").asText();
		Long timestamp = properties.get("timestamp").asLong();
		JsonNode acquisition = properties.get("acquisition");
		Long beginViewingDate = acquisition.get("beginViewingDate").asLong();
		Long endViewingDate = acquisition.get("endViewingDate").asLong();
		String missionName = acquisition.get("missionName").asText();
		Feature.FeatureBuilder featureBuilder = Feature.builder()
				.id(id)
				.timestamp(timestamp)
				.missionName(missionName)
				.beginViewingDate(beginViewingDate)
				.endViewingDate(endViewingDate);
		if(properties.get("state").get("resources").get("quicklook").asBoolean(false)) {
			byte[] quickLook = Base64.getDecoder().decode(properties.get("quicklook").asText());
			featureBuilder.quicklook(quickLook);
		}
		return featureBuilder.build();
	}
}
