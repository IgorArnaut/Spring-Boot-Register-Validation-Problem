package com.example.realestate.security;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

// Teddy Smith
public class SecurityUtil {
	public static String getSessionUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			String currentEmail = authentication.getName();
			return currentEmail;
		}

		return null;
	}
}
