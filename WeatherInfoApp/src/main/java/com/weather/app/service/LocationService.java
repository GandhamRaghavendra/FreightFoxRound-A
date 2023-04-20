package com.weather.app.service;

import java.util.Optional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.weather.app.exception.LocationException;
import com.weather.app.model.Location;

public interface LocationService {
	
	/**
	 * This Method will check the given pincode location is  present in DB or not.
	 * 
	 * @param Pincode Of provided location
	 * 
	 * @return This method returns Optional<Location> Object.
	 */
	Optional<Location> getLocationEntityFromDB(String pincode);
	
	
	
	/**
	 * 
	 * 
	 * This method will create and return Location object.
	 * 
	 * For creating locations object we are using Open Weather API service.
	 * 
	 * @param pincod
	 * 
	 * @return Location Object which will have Latitude and Longitude info.
	 * 
	 * @throws JsonProcessingException 
	 * @throws JsonMappingException 
	 * @throws LocationException 
	 */
	Location getLocationEntity(String pincode) throws JsonMappingException, JsonProcessingException, LocationException;
}
