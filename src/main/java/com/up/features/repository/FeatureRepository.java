package com.up.features.repository;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.repository.Repository;

import com.up.features.entity.Feature;

public interface FeatureRepository extends Repository<Feature, String> {

	Optional<Feature> findById(String id);

	Collection<Feature> findAll();

}
