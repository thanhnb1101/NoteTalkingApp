package net.notetalking.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import net.notetalking.model.User;
import net.notetalking.service.LoginService;
import net.notetalking.service.UserService;

@Controller
public class UserController extends BaseController{
	@Autowired
	UserService userService;
	
	@Autowired
	LoginService loginService;
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<User> register(@RequestBody User request) throws Exception {
		User response = userService.register(request);
		return new ResponseEntity<User>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ResponseEntity login(@RequestParam(required = true) String userName, @RequestParam(required = true) String password) throws Exception {
		return response(loginService.login(userName, password));
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public boolean logout(HttpServletRequest request, HttpServletResponse response) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication != null) {
			new SecurityContextLogoutHandler().logout(request, response, authentication);
			return true;
		}
		return false;
	}
}
