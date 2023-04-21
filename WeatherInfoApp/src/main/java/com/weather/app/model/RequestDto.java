package com.weather.app.model;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestDto {

	@NotNull(message = "Pincode must not be null")
	@Pattern(regexp = "^[0-9]{6}$", message = "Pincode must be 6 digits")
	private String pincode;

	@NotNull(message = "Date must not be null")
	@PastOrPresent(message = "Date must be in the past or present")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate date;
}
