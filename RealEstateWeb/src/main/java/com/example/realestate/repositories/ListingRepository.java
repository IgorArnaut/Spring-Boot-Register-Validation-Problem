package com.example.realestate.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.realestate.models.Listing;

public interface ListingRepository
	extends JpaRepository<Listing, Long>, JpaSpecificationExecutor<Listing> {
	int countByApartmentId(long apartmentId);

	int countByTermsId(long termsId);

	List<Listing> findByPosterId(long id);
}
