package net.notetalking.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import net.notetalking.service.LoginService;

@Service("loginService")
public class LoginServiceImpl implements LoginService {
	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	AuthenticationManager authenticationManager;

	@Override
	public boolean login(String userName, String pwd) throws Exception {
		// Xác thực từ username và password.
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(userName, pwd));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return true;
	}

}
