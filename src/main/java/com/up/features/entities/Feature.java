package com.up.features.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Feature {

	private String id;
	/**
	 * Timestamp in ms precision
	 */
	private Long timestamp;

	/**
	 * Timestamp in ms precision
	 */
	private Long beginViewingDate;

	/**
	 * Timestamp in ms precision
	 */
	private Long endViewingDate;

	private String missionName;

	/**
	 * Preview image, to be ignored during serialization.
	 */
	@JsonIgnoreProperties(allowSetters = true)
	private byte[] quicklook;
}
