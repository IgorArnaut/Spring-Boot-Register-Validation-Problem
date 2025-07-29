package com.example.realestate.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.realestate.dtos.listings.TermsDTO;
import com.example.realestate.mappers.TermsMapper;
import com.example.realestate.models.Terms;
import com.example.realestate.repositories.ListingRepository;
import com.example.realestate.repositories.TermsRepository;
import com.example.realestate.services.TermsService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TermsServiceImpl implements TermsService {
	@Autowired
	private TermsRepository termsRepository;
	@Autowired
	private ListingRepository listingRepository;

	@Autowired
	private TermsMapper mapper;

	@Override
	public Terms findOrCreate(TermsDTO termsDTO) {
		Terms terms = termsRepository.find(
			termsDTO.getDateAvailable(),
			termsDTO.isDeposit(),
			termsDTO.isForStudents(),
			termsDTO.isForWorkers(),
			termsDTO.isSmokingAllowed(),
			termsDTO.isPetsAllowed());

		if (terms != null)
			return terms;

		return termsRepository.save(mapper.toEntity(termsDTO));
	}

	@Override
	public void update(long id, TermsDTO termsDTO) {
		Terms terms = termsRepository.findById(id)
			.orElseThrow(() -> new EntityNotFoundException("Uslovi zakupa sa šifrom " + id + " ne postoje"));
		termsRepository.save(terms
			.setDateAvailable(termsDTO.getDateAvailable())
			.setDeposit(termsDTO.isDeposit())
			.setForStudents(termsDTO.isForStudents())
			.setForWorkers(termsDTO.isForWorkers())
			.setSmokingAllowed(termsDTO.isSmokingAllowed())
			.setPetsAllowed(termsDTO.isPetsAllowed()));
	}

	@Override
	public void delete(long id) {
		if (listingRepository.countByTermsId(id) == 0)
			termsRepository.deleteById(id);
	}

	@Override
	public TermsDTO toDTO(Terms terms) {
		return mapper.toDTO(terms);
	}
}
