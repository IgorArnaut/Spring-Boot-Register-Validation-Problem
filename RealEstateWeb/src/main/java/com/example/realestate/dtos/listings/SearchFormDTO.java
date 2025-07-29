package com.example.realestate.dtos.listings;

import lombok.Data;

@Data
public class SearchFormDTO {
	private String city;
	private Integer numOfRooms;
	private Integer priceFrom;
	private Integer priceTo;
	private Integer m2From;
	private Integer m2To;
}
