package net.notetalking.service;

import net.notetalking.model.LoginResponse;

public interface LoginService {
	LoginResponse login(String userName, String pwd) throws Exception;
}
