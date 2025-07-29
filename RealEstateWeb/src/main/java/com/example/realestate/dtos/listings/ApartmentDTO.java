package com.example.realestate.dtos.listings;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ApartmentDTO {
	@NotNull(message = "Sprat ne sme biti prazan")
	private Integer floor;
	@NotNull(message = "Površina ne sme biti prazna")
	private Integer area;
	@NotNull(message = "Cena ne sme biti prazna")
	private Integer price;
	@NotNull(message = "Broj soba ne sme biti prazan")
	private Integer numOfRooms;
	private String state;
	private String heating;
	private String equipment;
}
