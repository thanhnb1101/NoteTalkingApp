package net.notetalking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.notetalking.model.LoginObj;
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
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity login(@RequestBody LoginObj request) throws Exception {
		return response(loginService.login(request.getUserName(), request.getPwd()));
	}
}
