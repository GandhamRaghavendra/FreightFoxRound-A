package com.weather.app.service;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.weather.app.model.Location;
import com.weather.app.repository.LocationRepo;

@Service
public class LocationServiceImpl implements LocationService {

	@Autowired
	private LocationRepo locationRepo;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public Optional<Location> getLocationEntity(Integer pincode) {
		
		return locationRepo.findById(pincode);
		
	}

	@Override
	public Location saveLocationEntity(Integer pincode) throws JsonMappingException, JsonProcessingException {
		
		// Open Weather Geocoding API (http://api.openweathermap.org/geo/1.0/zip?zip={zip code},{country code}&appid={API key}).
		
		String url = "http://api.openweathermap.org/geo/1.0/zip?zip="+pincode+",in&appid=a46268e8e1b2993a4bde28d893d2c744";
		
		String json = restTemplate.getForObject(url, String.class);
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		JsonNode readTree = objectMapper.readTree(json);
		
		double lat = readTree.path("lat").asDouble();
		
		double lon = readTree.path("lon").asDouble();
		
		String country = readTree.path("country").asText();
		
		
		Location locationObj = new Location();
		
		locationObj.setPincode(pincode);
		locationObj.setContry(country);
		locationObj.setLatitude(lat);
		locationObj.setLongitude(lon);
		
		return locationRepo.save(locationObj);
	}

}
