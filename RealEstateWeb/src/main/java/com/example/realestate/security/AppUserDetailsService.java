package com.example.realestate.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.realestate.models.AppUser;
import com.example.realestate.repositories.UserRepository;

// Teddy Smith
@Service
public class AppUserDetailsService implements UserDetailsService {
	@Autowired
	private UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AppUser user = userRepo.findFirstByEmail(username);

		if (user != null) {
			User authUser = new User(user.getEmail(), user.getPassword(),
				Arrays.asList(new SimpleGrantedAuthority("user")));
			return authUser;
		} else
			throw new UsernameNotFoundException("Nevažeći imejl i lozinka");
	}
}
