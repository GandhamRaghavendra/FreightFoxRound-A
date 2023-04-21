package com.pdf.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
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
public class Item {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer itemId;
	
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
    
    @ManyToOne
    @JoinColumn(name = "invoiceId")
    @JsonIgnore
    private InvoiceData invoiceData;
}
