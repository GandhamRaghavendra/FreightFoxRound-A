package com.weather.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weather.app.model.Location;

public interface LocationRepo extends JpaRepository<Location, Integer>{

}
