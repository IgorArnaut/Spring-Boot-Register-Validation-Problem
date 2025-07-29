package com.example.realestate.dtos.listings;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ListingDTO {
	@NotBlank(message = "Naslov oglasa ne sme biti prazan")
	private String title;
	@NotBlank(message = "Opis oglasa ne sme biti prazan")
	private String description;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}
