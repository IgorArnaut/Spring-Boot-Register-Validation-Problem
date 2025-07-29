package com.example.realestate.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.realestate.dtos.users.AppUserRegisterDTO;
import com.example.realestate.models.AppUser;
import com.example.realestate.models.Listing;
import com.example.realestate.services.UserService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/korisnici")
public class AuthController {
	@Autowired
	private UserService userServ;

	@GetMapping("/registracija")
	public String registerForm(Model model) {
		model.addAttribute("userDTO", AppUserRegisterDTO.builder().build());
		return "users/register.html";
	}

	// Teddy Smith
	@PostMapping("/registracija")
	public String register(@ModelAttribute @Valid AppUserRegisterDTO userDTO, BindingResult result,
		Model model) {
		if (result.hasErrors()) {
			System.out.println(result.hasErrors());
			model.addAttribute("userDTO", userDTO);
			result.getFieldErrors()
				.forEach(error -> System.out.println(error.getField() + ": " + error.getDefaultMessage()));
			System.out.println();
			return "users/register";
		}

		AppUser existingUserEmai = userServ.findByEmail(userDTO.getEmail());
		AppUser existingUserPhoneNum = userServ.findByPhoneNum(userDTO.getPhoneNum());

		if (existingUserEmai != null && existingUserEmai.getEmail() != null && !existingUserEmai.getEmail().isEmpty())
			return "redirect:/register?fail";

		if (existingUserPhoneNum != null && existingUserPhoneNum.getPhoneNum() != null
			&& !existingUserPhoneNum.getPhoneNum().isEmpty())
			return "redirect:/register?fail";

		userServ.register(userDTO);
		return "redirect:/korisnici/uspeh";
	}

	@GetMapping("/uspeh")
	public String successPage() {
		return "users/success";
	}

	@GetMapping("/prijava")
	public String loginPage() {
		return "users/login";
	}

	@GetMapping("/{id}")
	public String profilePage(@PathVariable Long id, Model model) {
		AppUser user = userServ.findById(id);
		List<Listing> listings = userServ.findListingsByUserId(id);
		model.addAttribute("listings", listings);
		model.addAttribute("user", user);
		return "users/profile";
	}
}
