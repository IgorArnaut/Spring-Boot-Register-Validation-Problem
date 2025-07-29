package com.example.realestate.dtos.users;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AppUserLoginDTO {
	@Email(message = "Imejl adresa mora biti u tačnom formatu")
	@NotBlank(message = "Imejl adresa ne sme biti prazna")
	private String email;
	@NotBlank(message = "Lozinka ne sme biti prazna")
	private String password;
}
