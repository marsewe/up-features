package com.up.features.sourcer;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.up.features.entities.Feature;

import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONArray;

/**
 * Parse the source-data.json into a structure usable for the app.
 */
@Service
@Slf4j
public class Parser {

	/**
	 * Where to find the feature object within the json, could also be moved to application.yaml config.
	 */
	private static final String FEATURE_PATH = "$[*]['features'][*]";

	private ObjectMapper objectMapper;

	public Parser() {
		ObjectMapper mapper = new ObjectMapper();
		SimpleModule module = new SimpleModule();
		module.addDeserializer(Feature.class, new FeatureDeserializer());
		mapper.registerModule(module);
		this.objectMapper = mapper;
	}



	public List<Feature> importData(URL sourceFile) throws IOException {
		DocumentContext jsonContext = JsonPath.parse(sourceFile);
		JSONArray featuresJson = jsonContext.read(FEATURE_PATH);
		log.debug("Read features, sample: {}", featuresJson.get(0));
		return objectMapper.readerForListOf(Feature.class).readValue(featuresJson.toJSONString());
	}
}
