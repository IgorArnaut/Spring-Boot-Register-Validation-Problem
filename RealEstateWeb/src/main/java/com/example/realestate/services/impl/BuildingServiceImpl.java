package com.example.realestate.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.realestate.dtos.listings.BuildingDTO;
import com.example.realestate.mappers.BuildingMapper;
import com.example.realestate.models.Address;
import com.example.realestate.models.Building;
import com.example.realestate.repositories.ApartmentRepository;
import com.example.realestate.repositories.BuildingRepository;
import com.example.realestate.services.BuildingService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class BuildingServiceImpl implements BuildingService {
	@Autowired
	private BuildingRepository buildingRepo;
	@Autowired
	private ApartmentRepository apartmentRepo;

	@Autowired
	private BuildingMapper mapper;

	@Override
	public Building findOrCreate(Address address, BuildingDTO buildingDTO) {
		// 1 SQL injection
		Building building = buildingRepo.findByAddress(address);

		if (building != null)
			return building;

		building = mapper.toEntity(buildingDTO);
		building.setAddress(address);
		return buildingRepo.save(building);
	}

	@Override
	public void update(long id, BuildingDTO buildingDTO) {
		Building building = buildingRepo.findById(id)
			.orElseThrow(() -> new EntityNotFoundException("Zgrada sa šifrom " + id + " ne postoji"));
		buildingRepo.save(building
			.setYearConstructed(buildingDTO.getYearConstructed())
			.setNumOfFloors(buildingDTO.getNumOfFloors())
			.setElevator(buildingDTO.isElevator())
			.setParking(buildingDTO.isParking())
			.setGarage(buildingDTO.isGarage())
			.setCctv(buildingDTO.isCctv())
			.setIntercom(buildingDTO.isIntercom()));
	}

	@Override
	public void delete(long id) {
		if (apartmentRepo.countByBuildingId(id) == 0)
			buildingRepo.deleteById(id);
	}

	@Override
	public BuildingDTO toDTO(Building building) {
		return mapper.toDTO(building);
	}
}
