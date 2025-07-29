package com.example.realestate.dtos.users;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AppUserRegisterDTO {
	@NotBlank(message = "Imejl adresa je obavezna")
	@Email(message = "Imejl adresa mora biti u tačnom formatu")
	private String email;
	@NotBlank(message = "Broj telefona je obavezan")
	@Pattern(regexp = "^\\+381 \\d{2} \\d{3} \\d{3,4}$", message = "Broj telefona mora biti u tačnom formatu")
	private String phoneNum;
	@NotBlank(message = "Ime je obavezno")
	private String firstName;
	@NotBlank(message = "Prezime je obavezno")
	private String lastName;
	@NotNull(message = "Datum rođenja je obavezan")
	private LocalDate birthDate;
	@NotBlank(message = "Lozinka je obavezna")
	@Size(min = 8, message = "Lozinka mora biti duga bar 8 karaktera")
	private String password1;
	@NotBlank(message = "Potvrda lozinke je obavezna")
	private String password2;
}
