package com.example.realestate.repositories;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.realestate.models.Terms;

public interface TermsRepository extends JpaRepository<Terms, Long> {
	Terms findByDateAvailableAndDepositAndForStudentsAndForWorkersAndSmokingAllowedAndPetsAllowed(
		LocalDate dateAvailable,
		boolean deposit,
		boolean forStudents,
		boolean forWorkers,
		boolean smokingAllowed,
		boolean petsAllowed);

	default Terms find(
		LocalDate dateAvailable,
		boolean deposit,
		boolean forStudents,
		boolean forWorkers,
		boolean smokingAllowed,
		boolean petsAllowed) {
		return findByDateAvailableAndDepositAndForStudentsAndForWorkersAndSmokingAllowedAndPetsAllowed(
			dateAvailable,
			deposit,
			forStudents,
			forWorkers,
			smokingAllowed,
			petsAllowed);
	}
}
