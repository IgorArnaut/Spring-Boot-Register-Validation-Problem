package com.example.realestate.services;

import com.example.realestate.dtos.listings.TermsDTO;
import com.example.realestate.models.Terms;

public interface TermsService {
	Terms findOrCreate(TermsDTO termsDTO);

	void delete(long id);

	void update(long id, TermsDTO termsDTO);

	TermsDTO toDTO(Terms terms);
}
