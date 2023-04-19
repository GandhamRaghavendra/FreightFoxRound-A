package com.weather.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weather.app.model.Location;

public interface LocationRepo extends JpaRepository<Location, Integer>{

	Optional<Location> findByPincode(String pincode);
}
