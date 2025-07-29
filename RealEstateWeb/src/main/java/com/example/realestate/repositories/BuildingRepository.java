package com.example.realestate.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.realestate.models.Address;
import com.example.realestate.models.Building;

public interface BuildingRepository extends JpaRepository<Building, Long> {
	Building findByAddress(Address address);
}
