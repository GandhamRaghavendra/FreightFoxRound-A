package com.weather.app.repository;


import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weather.app.model.WeatherInfo;

public interface WeatherInfoRepo extends JpaRepository<WeatherInfo, Integer>{
	
	List<WeatherInfo> findByDate(LocalDate date);
}
