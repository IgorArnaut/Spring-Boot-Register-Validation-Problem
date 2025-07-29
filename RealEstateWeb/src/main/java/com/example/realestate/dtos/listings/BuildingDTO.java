package com.example.realestate.dtos.listings;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class BuildingDTO {
	@NotNull(message = "Godina izgradnje ne sme biti prazna")
	private Integer yearConstructed;
	@NotNull(message = "Broj stanova ne sme biti prazan")
	private Integer numOfFloors;
	private boolean parking;
	private boolean garage;
	private boolean elevator;
	private boolean cctv;
	private boolean intercom;
}
