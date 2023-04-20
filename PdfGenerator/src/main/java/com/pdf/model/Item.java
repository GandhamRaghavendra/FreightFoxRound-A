package com.pdf.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {	
	@NotBlank(message = "Item name must not be blank")
    private String name;

    @NotBlank(message = "Item quantity must not be blank")
    private String quantity;

    @NotNull(message = "Item rate must not be null")
    @Positive(message = "Item rate must be a positive number")
    private double rate;

    @NotNull(message = "Item amount must not be null")
    @Positive(message = "Item amount must be a positive number")
    private double amount;
}
