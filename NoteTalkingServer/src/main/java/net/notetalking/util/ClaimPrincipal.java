package net.notetalking.util;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import net.notetalking.security.CustomUserDetails;

public class ClaimPrincipal {
	
	public String getLoggedInUserName() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			return ((CustomUserDetails) principal).getUsername();
		}

		return principal.toString();
	}
	
	public Long getLoggedInUserId() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			return ((CustomUserDetails) principal).getUserId();
		}

		return null;
	}

}
