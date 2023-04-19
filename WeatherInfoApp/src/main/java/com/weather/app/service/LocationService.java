package com.weather.app.service;

import java.util.Optional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.weather.app.model.Location;

public interface LocationService {
	
	/**
	 * 
	 * @param pincod
	 * @return This method returns Optional<Location> Object.
	 */
	Optional<Location> getLocationEntity(Integer pincode);
	
	
	
	/**
	 * 
	 * @param pincod
	 * @return Location Object which will have Latitude and Longitude info.
	 * 
	 * This method will create and return Location object.
	 * 
	 * For creating locations object we are using Open Weather API service.
	 * @throws JsonProcessingException 
	 * @throws JsonMappingException 
	 */
	Location saveLocationEntity(Integer pincode) throws JsonMappingException, JsonProcessingException;
}
