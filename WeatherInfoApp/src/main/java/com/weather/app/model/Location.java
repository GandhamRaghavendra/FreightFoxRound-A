package com.weather.app.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "location")
public class Location {

	@Id
	private int pincode;

	@Column(name = "lat", precision = 9, scale = 6)
	private Double latitude;

	@Column(name = "lon", precision = 9, scale = 6)
	private Double longitude;
	
	private String contry;
	
	@OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
	private List<WeatherInfo> weatherInfoList = new ArrayList<>();
}
