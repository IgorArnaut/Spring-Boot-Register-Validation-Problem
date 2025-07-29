package com.example.realestate.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.realestate.dtos.listings.ListingDTO;
import com.example.realestate.mappers.ListingMapper;
import com.example.realestate.models.Apartment;
import com.example.realestate.models.AppUser;
import com.example.realestate.models.Listing;
import com.example.realestate.models.Terms;
import com.example.realestate.repositories.ListingRepository;
import com.example.realestate.repositories.UserRepository;
import com.example.realestate.security.SecurityUtil;
import com.example.realestate.services.ListingService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ListingServiceImpl implements ListingService {
	@Autowired
	private ListingRepository listingRepo;
	// Teddy Smith
	@Autowired
	private UserRepository userRepo;

	@Autowired
	private ListingMapper mapper;

	@Override
	public Page<Listing> findAll(int pageNum, int pageSize) {
		Pageable pageable = PageRequest.of(pageNum, pageSize).withSort(Sort.by("createdAt").descending());
		return listingRepo.findAll(pageable);
	}

	@Override
	public Listing findById(long id) {
		// 1 SQL injection
		return listingRepo.findById(id)
			.orElseThrow(() -> new EntityNotFoundException("Oglas sa šifrom " + id + " ne postoji"));
	}

	@Override
	public void create(Apartment apartment, Terms terms, ListingDTO listingDTO) {
		String email = SecurityUtil.getSessionUser();
		AppUser user = userRepo.findByEmail(email);

		listingRepo.save(Listing.builder()
			.title(listingDTO.getTitle())
			.description(listingDTO.getDescription())
			.apartment(apartment)
			.terms(terms)
			.poster(user)
			.build());
	}

	@Override
	public void update(long id, ListingDTO listingDTO) {
		Listing listing = findById(id);
		listingRepo.save(listing
			.setTitle(listingDTO.getTitle())
			.setDescription(listingDTO.getDescription()));
	}

	@Override
	public void delete(long id) {
		Listing listing = findById(id);
		listingRepo.delete(listing);
	}

	@Override
	public Page<Listing> filter(Specification<Listing> specification, int pageNum, int pageSize) {
		Pageable pageable = PageRequest.of(pageNum, pageSize).withSort(Sort.by("createdAt").descending());
		return listingRepo.findAll(specification, pageable);
	}

	@Override
	public ListingDTO toDTO(Listing listing) {
		return mapper.toDTO(listing);
	}
}
