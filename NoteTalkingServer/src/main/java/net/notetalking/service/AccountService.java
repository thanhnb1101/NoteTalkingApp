package net.notetalking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import net.notetalking.model.User;
import net.notetalking.security.CustomUserDetails;

@Service
public class AccountService implements UserDetailsService {
	@Autowired
	@Lazy
    private UserService accountService;

    @Override
    public UserDetails loadUserByUsername(String username) {
        // Kiểm tra xem user có tồn tại trong database không?
        User account = accountService.getByUserName(username);
        if (account == null) {
            throw new UsernameNotFoundException(username);
        }
        return new CustomUserDetails(account);
    }

	public UserDetails loadUserById(String userId) {
		User account = accountService.getById(Long.parseLong(userId));
        if (account == null) {
            throw new UsernameNotFoundException(userId);
        }
        return new CustomUserDetails(account);
	}
}
