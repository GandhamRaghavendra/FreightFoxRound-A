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
import com.weather.app.model.ResponseDto;
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
	public ResponseEntity<ResponseDto> getWeatherInfo(@Valid @RequestBody RequestDto dto) throws JsonMappingException, JsonProcessingException, WeatherInfoException, LocationException{
		
		// First We will check the given PIN is present in DB or not
		
		Optional<Location> loc = locationService.getLocationEntity(dto.getPincode());
		
		ResponseDto responseDto = new ResponseDto();
		
		if(loc.isPresent()) {
			
			Location location = loc.get();
			
			responseDto.setLocation(location);
			
			Optional<WeatherInfo> weatherInfo = weatherInfoService.getWeatherInfoIfPresent(dto);
			
			if(weatherInfo.isEmpty()) {
				
				WeatherInfo weatherInfo2 = weatherInfoService.getWeatherInfo(dto);
				
				responseDto.setWeatherInfo(weatherInfo2);
				
			}else {
				
				WeatherInfo info = weatherInfo.get();
				
				responseDto.setWeatherInfo(info);
			}	
			
		}else {
			
			Location saveLocationEntity = locationService.saveLocationEntity(dto.getPincode());
			
			WeatherInfo weatherInfo3 = weatherInfoService.getWeatherInfo(dto);
			
		    responseDto.setLocation(saveLocationEntity);
		    
		    responseDto.setWeatherInfo(weatherInfo3);			
		}
		
		return new ResponseEntity<>(responseDto,HttpStatus.OK);
	}
	
	
	
	
	@PostMapping("/testSaveLocation")
	public ResponseEntity<Location> saveLocationControllerTest(@Valid @RequestBody RequestDto dto) throws JsonMappingException, JsonProcessingException, LocationException{
		
		Location loc = locationService.saveLocationEntity(dto.getPincode());
		
		return new ResponseEntity<Location>(loc,HttpStatus.OK);
	}
	
	@PostMapping("/testSaveWeatherInfo")
	public ResponseEntity<WeatherInfo> saveWeatherInfoTest(@Valid @RequestBody RequestDto dto) throws JsonMappingException, JsonProcessingException, WeatherInfoException{
		WeatherInfo res = weatherInfoService.getWeatherInfo(dto);
		
		return new ResponseEntity<WeatherInfo>(res,HttpStatus.OK);
	}
}
