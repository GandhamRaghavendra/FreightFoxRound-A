package com.weather.app.service;

import java.util.Optional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.weather.app.exception.WeatherInfoException;
import com.weather.app.model.RequestDto;
import com.weather.app.model.WeatherInfo;

public interface WeatherInfoService {
	
	
	
	/**
	 * 
	 * @param RequestDto
	 * @return
	 * @throws JsonMappingException
	 * @throws JsonProcessingException
	 * @throws WeatherInfoException
	 */
	WeatherInfo getWeatherInfo(RequestDto dto) throws JsonMappingException, JsonProcessingException, WeatherInfoException;
	
	
	
	/**
	 * 
	 * @param RequestDto
	 * @return
	 */
	Optional<WeatherInfo> getWeatherInfoIfPresent(RequestDto dto);
}
