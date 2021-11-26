package com.up.features.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collection;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.up.features.entity.Feature;

@SpringBootTest
public class FeatureRepositoryTest {

	@Autowired
	FeatureRepository featureRepository;

	@Test
	void findById_exists_returned() {
		Optional<Feature> feature = featureRepository.findById("39c2f29e-c0f8-4a39-a98b-deed547d6aea");
		assertTrue(feature.isPresent());
	}

	@Test
	void findById_notExistent_emptyOptional() {
		Optional<Feature> feature = featureRepository.findById("i_do_not_exist");
		assertTrue(feature.isEmpty());
	}

	@Test
	void findAll() {
		Collection<Feature> features = featureRepository.findAll();
		assertNotNull(features);
		assertTrue(features.size() > 0);
	}
}
