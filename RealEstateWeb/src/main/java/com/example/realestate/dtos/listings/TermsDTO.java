package com.example.realestate.dtos.listings;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TermsDTO {
	@NotNull(message = "Izaberite datum useljenja")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateAvailable;
	private boolean deposit;
	private boolean forStudents;
	private boolean forWorkers;
	private boolean smokingAllowed;
	private boolean petsAllowed;
}
