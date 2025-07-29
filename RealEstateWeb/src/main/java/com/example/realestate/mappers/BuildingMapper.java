package com.example.realestate.mappers;

import org.springframework.stereotype.Component;

import com.example.realestate.dtos.listings.BuildingDTO;
import com.example.realestate.models.Building;

@Component
public class BuildingMapper implements Mapper<Building, BuildingDTO> {
	@Override
	public Building toEntity(BuildingDTO buildingDTO) {
		return Building.builder()
			.yearConstructed(buildingDTO.getYearConstructed())
			.numOfFloors(buildingDTO.getNumOfFloors())
			.elevator(buildingDTO.isElevator())
			.parking(buildingDTO.isParking())
			.garage(buildingDTO.isGarage())
			.cctv(buildingDTO.isCctv())
			.intercom(buildingDTO.isIntercom())
			.build();
	}

	@Override
	public BuildingDTO toDTO(Building building) {
		return BuildingDTO.builder()
			.yearConstructed(building.getYearConstructed())
			.numOfFloors(building.getNumOfFloors())
			.elevator(building.isElevator())
			.parking(building.isParking())
			.garage(building.isGarage())
			.cctv(building.isCctv())
			.intercom(building.isIntercom())
			.build();
	}
}
