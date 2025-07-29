package com.example.realestate.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.realestate.dtos.users.AppUserRegisterDTO;
import com.example.realestate.models.AppUser;
import com.example.realestate.models.Listing;
import com.example.realestate.repositories.ListingRepository;
import com.example.realestate.repositories.UserRepository;
import com.example.realestate.services.UserService;

// Teddy Smith
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private ListingRepository listingRepo;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public AppUser findByEmail(String email) {
		return userRepo.findByEmail(email);
	}

	@Override
	public AppUser findByPhoneNum(String phoneNum) {
		return userRepo.findByPhoneNum(phoneNum);
	}

	@Override
	public AppUser findById(long id) {
		return userRepo.findById(id).get();
	}

	@Override
	public void register(AppUserRegisterDTO userDTO) {
		userRepo.save(AppUser.builder()
			.email(userDTO.getEmail())
			.password(passwordEncoder.encode(userDTO.getPassword1()))
			.firstName(userDTO.getFirstName())
			.lastName(userDTO.getLastName())
			.phoneNum(userDTO.getPhoneNum())
			.birthDate(userDTO.getBirthDate())
			.build());
	}

	@Override
	public List<Listing> findListingsByUserId(long id) {
		return listingRepo.findByPosterId(id);
	}
}
