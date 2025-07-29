package com.example.realestate.mappers;

import org.springframework.stereotype.Component;

import com.example.realestate.dtos.listings.TermsDTO;
import com.example.realestate.models.Terms;

@Component
public class TermsMapper implements Mapper<Terms, TermsDTO> {
	@Override
	public Terms toEntity(TermsDTO termsDTO) {
		return Terms.builder()
			.dateAvailable(termsDTO.getDateAvailable())
			.deposit(termsDTO.isDeposit())
			.forStudents(termsDTO.isForStudents())
			.forWorkers(termsDTO.isForWorkers())
			.smokingAllowed(termsDTO.isSmokingAllowed())
			.petsAllowed(termsDTO.isPetsAllowed())
			.build();
	}

	@Override
	public TermsDTO toDTO(Terms terms) {
		return TermsDTO.builder()
			.dateAvailable(terms.getDateAvailable())
			.deposit(terms.isDeposit())
			.forStudents(terms.isForStudents())
			.forWorkers(terms.isForWorkers())
			.smokingAllowed(terms.isSmokingAllowed())
			.petsAllowed(terms.isPetsAllowed())
			.build();
	}
}
