package com.up.features.controller;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.util.Collection;
import java.util.Optional;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import com.up.features.entity.Feature;
import com.up.features.repository.FeatureRepository;

import lombok.RequiredArgsConstructor;

@RequestMapping(path = "features")
@Controller
@RequiredArgsConstructor
public class FeatureController {

	// if logic becomes more complex we should replace this with a FeatureService or so,
	// but for now that would be just boilerplate code.
	private final FeatureRepository featureRepository;

	/**
	 * Retrieve all features we have so far.
	 * Depending on the amount of features we expect we could add pagination support here.
	 * @return all features.
	 */
	@GetMapping(path = "/")
	public ResponseEntity<Collection<Feature>> findAll() {
		Collection<Feature> features = featureRepository.findAll();
		return ResponseEntity.ok(features);
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<Feature> findById(@PathVariable("id") String id) {
		Feature feature = getFeature(id);
		return ResponseEntity.ok(feature);
	}

	@GetMapping(path ="/{id}/quicklook",
			produces = MediaType.IMAGE_PNG_VALUE)
	public @ResponseBody byte[] getQuicklookImage(@PathVariable("id") String id) {
		Feature feature = getFeature(id);
		// quicklook is optional for a features
		if (feature.getQuicklook() == null) {
			throw new ResponseStatusException(NOT_FOUND, "Feature '" + id + "' does not have a quicklook image");
		}
		return feature.getQuicklook();
	}


	private Feature getFeature(String id) {
		Optional<Feature> optFeature = featureRepository.findById(id);
		return optFeature.orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Unable to find feature with id " + id));
	}

}
