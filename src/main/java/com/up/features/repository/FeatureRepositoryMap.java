package com.up.features.repository;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.up.features.entities.Feature;
import com.up.features.sourcer.Parser;

import lombok.extern.slf4j.Slf4j;

/**
 * Feature repository using a simple (in-memory) map to hold all the features.
 */
@Slf4j
@Repository
public class FeatureRepositoryMap implements FeatureRepository {

	/**
	 * key: feature-id, value: feature-entity.
	 */
	private Map<String, Feature> featureMap = new HashMap<>();

	@Autowired
	public FeatureRepositoryMap(Parser parser) {
		ClassLoader classLoader = getClass().getClassLoader();
		try {
			List<Feature> features = parser.importData(classLoader.getResource("source-data.json"));
			this.featureMap = features
					.stream()
					.collect(Collectors.toMap(Feature::getId, Function.identity()));
		} catch(IOException e) {
			log.error("Import of json feature file failed", e);
		}
	}


	@Override
	public Optional<Feature> findById(String id) {
		return Optional.ofNullable(featureMap.get(id));
	}

	@Override
	public Collection<Feature> findAll() {
		return featureMap.values();
	}
}