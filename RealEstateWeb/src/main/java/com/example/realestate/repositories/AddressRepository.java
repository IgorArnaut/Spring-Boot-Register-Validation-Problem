package com.example.realestate.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.realestate.models.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
	Address findByCityAndStreetAndStreetNum(String city, String street, int streetNum);
}
