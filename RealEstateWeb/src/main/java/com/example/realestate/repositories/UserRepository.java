package com.example.realestate.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.realestate.models.AppUser;

public interface UserRepository extends JpaRepository<AppUser, Long> {
	AppUser findByEmail(String email);

	AppUser findByPhoneNum(String phoneNum);

	AppUser findFirstByEmail(String email);
}
