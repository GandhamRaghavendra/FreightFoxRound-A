package com.weather.app.model;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "weather_info")
public class WeatherInfo {
    
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer weatherId;
	
    @Column(name = "date",unique = true)
    private LocalDate date;

    @Column(name = "sunrise")
    private String sunrise;

    @Column(name = "sunset")
    private String sunset;

    @Column(name = "moonrise")
    private String moonrise;

    @Column(name = "moonset")
    private String moonset;

    @Column(name = "max_temp_c")
    private Integer maxTempC;

    @Column(name = "min_temp_c")
    private Integer minTempC;

    @Column(name = "avg_temp_c")
    private Integer avgTempC;

    @Column(name = "total_snow_cm")
    private Double totalSnowCm;

    @Column(name = "sun_hour")
    private Double sunHour;
    
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "location_id")
    private Location location;
}
