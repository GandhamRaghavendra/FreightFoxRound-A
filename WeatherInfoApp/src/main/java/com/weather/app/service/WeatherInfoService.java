package com.weather.app.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.weather.app.exception.WeatherInfoException;
import com.weather.app.model.RequestDto;
import com.weather.app.model.WeatherInfo;

public interface WeatherInfoService {
	WeatherInfo saveWeatherInfo(RequestDto dto) throws JsonMappingException, JsonProcessingException, WeatherInfoException;
}
