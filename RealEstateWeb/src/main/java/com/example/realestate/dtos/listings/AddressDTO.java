package com.example.realestate.dtos.listings;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AddressDTO {
	private String city;
	@NotBlank(message = "Naziv ulice ne sme biti prazan")
	private String street;
	@NotNull(message = "Broj ulice ne sme biti prazan")
	private Integer streetNum;
}
