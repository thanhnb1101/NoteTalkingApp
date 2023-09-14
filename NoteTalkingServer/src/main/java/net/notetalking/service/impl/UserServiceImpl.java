package net.notetalking.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.notetalking.model.User;
import net.notetalking.repository.UserRepository;
import net.notetalking.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepository;

//	@Autowired
//	PasswordEncoder passwordEncoder;

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}


	@Override
	public User getById(long id) {
		return userRepository.findById(id).orElse(null);
	}


//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		User user = userRepository.findByUserName(username);
//		if (user == null) {
//			throw new UsernameNotFoundException("User not exists by Username");
//		}
//
//		Set<GrantedAuthority> authorities = Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));
//
//		return new org.springframework.security.core.userdetails.User(username, user.getPwd(), authorities);
//	}

	@Override
	public User getByUserName(String userName) {
		return userRepository.findByUserName(userName);
	}

}
