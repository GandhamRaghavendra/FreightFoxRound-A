package com.weather.app.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.weather.app.exception.LocationException;
import com.weather.app.exception.WeatherInfoException;
import com.weather.app.model.Location;
import com.weather.app.model.RequestDto;
import com.weather.app.model.WeatherInfo;
import com.weather.app.service.LocationService;
import com.weather.app.service.WeatherInfoService;

@RestController
public class MainController {

	@Autowired
	private LocationService locationService;
	
	@Autowired
	private WeatherInfoService weatherInfoService;
	
	
	
	@PostMapping("/weatherInfo")
	public String getWeatherInfo(@Valid @RequestBody RequestDto dto){
		
		// First We will check the given PIN is present in DB or not
		
		Optional<Location> loc = locationService.getLocationEntity(dto.getPincode());
		
		
		if(loc.isPresent()) {
			
			Optional<WeatherInfo> weatherInfo = weatherInfoService.getWeatherInfoIfPresent(dto);
			
			if(weatherInfo.isEmpty()) return "PIN is present but info not there..";
			
			else return "Both are present..";
			
		}
		else return "PIN Not Present";
	}
	
	@PostMapping("/testSaveLocation")
	public ResponseEntity<Location> saveLocationControllerTest(@Valid @RequestBody RequestDto dto) throws JsonMappingException, JsonProcessingException, LocationException{
		
		Location loc = locationService.saveLocationEntity(dto.getPincode());
		
		return new ResponseEntity<Location>(loc,HttpStatus.OK);
	}
	
	@PostMapping("/testSaveWeatherInfo")
	public ResponseEntity<WeatherInfo> saveWeatherInfoTest(@Valid @RequestBody RequestDto dto) throws JsonMappingException, JsonProcessingException, WeatherInfoException{
		WeatherInfo res = weatherInfoService.saveWeatherInfo(dto);
		
		return new ResponseEntity<WeatherInfo>(res,HttpStatus.OK);
	}
}
