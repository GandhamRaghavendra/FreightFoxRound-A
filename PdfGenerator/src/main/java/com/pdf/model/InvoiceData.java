package com.pdf.model;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class InvoiceData {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "invoiceNoSeq")
	@SequenceGenerator(name = "invoiceNoSeq", initialValue = 101, allocationSize = 1)
	private Integer invoiceNo;
	
	@NotBlank(message = "Seller name must not be blank")
	private String seller;

	
	@NotNull(message = "Seller GSTIN must not be null")
//	@Pattern(regexp = "\\d{2}[A-Z]{5}\\d{4}[A-Z]{1}\\d{1}[A-Z]{1}[A-Z\\d]{1}", message = "Invalid Seller GSTIN")
	@Size(max = 15,min = 15,message = "Invalid Seller GSTIN")
	private String sellerGstin;

	
	@NotBlank(message = "Seller address must not be blank")
	@Size(max = 500, message = "Seller address must be less than or equal to 500 characters")
	private String sellerAddress;

	
	@NotBlank(message = "Buyer name must not be blank")
	private String buyer;

	
	@NotNull(message = "Buyer GSTIN must not be null")
//	@Pattern(regexp = "\\d{2}[A-Z]{5}\\d{4}[A-Z]{1}\\d{1}[A-Z]{1}[A-Z\\d]{1}", message = "Invalid Buyer GSTIN")
	@Size(max = 15,min = 15,message = "Invalid Buyer GSTIN")
	private String buyerGstin;

	
	@NotBlank(message = "Buyer address must not be blank")
	@Size(max = 500, message = "Buyer address must be less than or equal to 500 characters")
	private String buyerAddress;

	
	@NotNull(message = "Items list must not be null")
	@Size(min = 1, message = "At least one item must be present")
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "invoiceData")
	private List<Item> items;
}
