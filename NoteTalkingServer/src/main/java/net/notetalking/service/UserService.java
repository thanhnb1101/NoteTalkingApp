package net.notetalking.service;

import net.notetalking.model.User;


public interface UserService {
	User save(User user);

	User getById(long id);
	
	User getByUserName(String userName);
}
