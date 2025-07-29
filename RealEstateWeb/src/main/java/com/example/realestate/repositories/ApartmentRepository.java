package com.example.realestate.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.realestate.models.Apartment;
import com.example.realestate.models.Building;

public interface ApartmentRepository extends JpaRepository<Apartment, Long> {
	Apartment findByFloorAndAreaAndPriceAndNumOfRoomsAndStateAndHeatingAndEquipmentAndBuilding(
		int floor,
		int area,
		int price,
		int numOfRooms,
		String state,
		String heating,
		String equipment,
		Building building);

	default Apartment find(
		int floor,
		int area,
		int price,
		int numOfRooms,
		String state,
		String heating,
		String equipment,
		Building building) {
		return findByFloorAndAreaAndPriceAndNumOfRoomsAndStateAndHeatingAndEquipmentAndBuilding(
			floor,
			area,
			price,
			numOfRooms,
			state,
			heating,
			equipment,
			building);
	}

	int countByBuildingId(long buildingId);
}
