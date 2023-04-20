package com.weather.app.service;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.weather.app.exception.LocationException;
import com.weather.app.model.Location;
import com.weather.app.repository.LocationRepo;

@Service
public class LocationServiceImpl implements LocationService {

	@Autowired
	private LocationRepo locationRepo;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public Optional<Location> getLocationEntityFromDB(String pincode) {
		
		return locationRepo.findByPincode(pincode);
		
	}

	@Override
	public Location getLocationEntity(String pincode) throws JsonMappingException, JsonProcessingException, LocationException {
		
		// Open Weather Geocoding API (http://api.openweathermap.org/geo/1.0/zip?zip={zip code},{country code}&appid={API key}).
		
		
		// Making url dynamic by adding pincode.
		String url = "http://api.openweathermap.org/geo/1.0/zip?zip="+pincode+",in&appid=a46268e8e1b2993a4bde28d893d2c744";
		
		
		// Taking use of RestTemplate to call the API.
		String json = restTemplate.getForObject(url, String.class);
		
		
		// Using ObjectMapper for reading JSON response.
		ObjectMapper objectMapper = new ObjectMapper();
		
		// Extracting content from json String.
		JsonNode readTree = objectMapper.readTree(json);
		
		// If there is any error or invalid request then we will get code.
		int statusCode = readTree.path("cod").asInt();
		
		
		if(statusCode == 0) {
			
			// Extracting Latitude from the response.
			double lat = readTree.path("lat").asDouble();
			
			// Extracting Longitude from the response.
			double lon = readTree.path("lon").asDouble();
			
			// Extracting CountryName from the response.
			String country = readTree.path("country").asText();
			
			// Creating and Location object.
			Location locationObj = new Location();
			
			// Setting all the Details to Location Entity
			locationObj.setPincode(pincode);
			locationObj.setContry(country);
			locationObj.setLatitude(lat);
			locationObj.setLongitude(lon);
			
			
			// Saving Location Entity using JPA Repository and returning.
			return locationRepo.save(locationObj);
		}
		
		throw new LocationException("Server Error..!");
	}

}
