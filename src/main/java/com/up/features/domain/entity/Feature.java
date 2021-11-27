package com.up.features.domain.entity;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
@JsonIgnoreProperties(value={ "quicklook" }, allowSetters = true)
public class Feature {

	@NonNull
	private String id;
	/**
	 * Timestamp in ms precision
	 */
	@NonNull
	private Long timestamp;

	/**
	 * Timestamp in ms precision
	 */
	@NonNull
	private Long beginViewingDate;

	/**
	 * Timestamp in ms precision
	 */
	@NonNull
	private Long endViewingDate;

	@NonNull
	private String missionName;

	/**
	 * Preview image, to be ignored during serialization.
	 */
	@Nullable
	private byte[] quicklook;
}
