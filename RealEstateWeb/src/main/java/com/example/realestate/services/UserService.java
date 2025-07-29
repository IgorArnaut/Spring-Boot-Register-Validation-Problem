package com.example.realestate.services;

import java.util.List;

import com.example.realestate.dtos.users.AppUserRegisterDTO;
import com.example.realestate.models.AppUser;
import com.example.realestate.models.Listing;

public interface UserService {
	AppUser findByEmail(String email);

	AppUser findByPhoneNum(String phoneNum);

	AppUser findById(long id);

	void register(AppUserRegisterDTO userDTO);

	List<Listing> findListingsByUserId(long id);
}
