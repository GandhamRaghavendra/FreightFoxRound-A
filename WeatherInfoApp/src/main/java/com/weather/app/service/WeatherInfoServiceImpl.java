package com.weather.app.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.weather.app.exception.WeatherInfoException;
import com.weather.app.model.Location;
import com.weather.app.model.RequestDto;
import com.weather.app.model.WeatherInfo;
import com.weather.app.repository.LocationRepo;
import com.weather.app.repository.WeatherInfoRepo;

@Service
public class WeatherInfoServiceImpl implements WeatherInfoService {

	@Autowired
	private WeatherInfoRepo weatherInfoRepo;

	@Autowired
	private LocationRepo locationRepo;

	@Autowired
	private RestTemplate restTemplate;

	
	@Override
	public WeatherInfo getWeatherInfo(RequestDto dto)
			throws JsonMappingException, JsonProcessingException, WeatherInfoException {

		Optional<Location> loc = locationRepo.findByPincode(dto.getPincode());

		/*
		 * World Weather API:-
		 * (http://api.worldweatheronline.com/premium/v1/past-weather.ashx
		 * ?q=Latitude,Longitude &date=YYYY/MM/DD &key=Personal API key &format=json);
		 */

		Location location = loc.get();

		String url = "http://api.worldweatheronline.com/premium/v1/past-weather.ashx" + "?q=" + location.getLatitude()
				+ "," + location.getLongitude() + "&date=" + dto.getDate() + "&key=3d39530c9e9b4146a6c71400231904"
				+ "&format=json";

		String json = restTemplate.getForObject(url, String.class);

		ObjectMapper objectMapper = new ObjectMapper();

		JsonNode readTree = objectMapper.readTree(json);

		if (readTree.path("data").path("weather") != null) {
			String datestr = readTree.path("data").path("weather").get(0).path("date").asText();

			String sunrise = readTree.path("data").path("weather").get(0).path("astronomy").get(0).path("sunrise")
					.asText();

			String sunset = readTree.path("data").path("weather").get(0).path("astronomy").get(0).path("sunset")
					.asText();

			String moonrise = readTree.path("data").path("weather").get(0).path("astronomy").get(0).path("moonrise")
					.asText();

			String moonset = readTree.path("data").path("weather").get(0).path("astronomy").get(0).path("moonset")
					.asText();

			int maxTemp = readTree.path("data").path("weather").get(0).path("maxtempC").asInt();

			int minTemp = readTree.path("data").path("weather").get(0).path("mintempC").asInt();

			int avgTemp = readTree.path("data").path("weather").get(0).path("avgtempC").asInt();

			double snow_cm = readTree.path("data").path("weather").get(0).path("totalSnow_cm").asDouble();

			double sunHour = readTree.path("data").path("weather").get(0).path("sunHour").asDouble();

			WeatherInfo weatherInfoObj = new WeatherInfo();

			weatherInfoObj.setDate(LocalDate.parse(datestr));

			weatherInfoObj.setSunrise(sunrise);

			weatherInfoObj.setSunset(sunset);

			weatherInfoObj.setMoonrise(moonrise);

			weatherInfoObj.setMoonset(moonset);

			weatherInfoObj.setMaxTempC(maxTemp);

			weatherInfoObj.setMinTempC(minTemp);

			weatherInfoObj.setAvgTempC(avgTemp);

			weatherInfoObj.setTotalSnowCm(snow_cm);

			weatherInfoObj.setSunHour(sunHour);

			weatherInfoObj.setLocation(location);

			return weatherInfoRepo.save(weatherInfoObj);
		}

		String errorMessage = readTree.path("data").path("error").get(0).path("msg").asText();

		throw new WeatherInfoException(errorMessage);
	}

	@Override
	public Optional<WeatherInfo> getWeatherInfoIfPresent(RequestDto dto) {

		List<WeatherInfo> weatherInfoList = weatherInfoRepo.findByDate(dto.getDate());

		if (!weatherInfoList.isEmpty()) {
			
			for (WeatherInfo info : weatherInfoList) {
				
				if (info.getLocation().getPincode().equals(dto.getPincode())) {
					
					return Optional.of(info);
					
				}
			}
		}
		
		return Optional.empty();
	}

}
