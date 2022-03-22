package com.lovjyot.flightReservation.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lovjyot.flightReservation.entities.User;
import com.lovjyot.flightReservation.repo.UserRepository;
import com.lovjyot.flightReservation.services.SecurityService;

@Controller
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	private SecurityService securityService;
	
	private static final Logger LOGGER= LoggerFactory.getLogger(UserController.class);

	@RequestMapping(value = "/showReg")
	public String showRegistrationPage() {
		LOGGER.info("Inside showRegistrationPage() method");
		return "login/registerUser";
	}

	@RequestMapping(value = "/registerUser", method = RequestMethod.POST)
	public String register(@ModelAttribute("user") User user) {
		LOGGER.info("Inside register() method"+user);
		user.setPassword(encoder.encode(user.getPassword()));
		userRepository.save(user);
		return "login/login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@RequestParam("username") String email, @RequestParam("password") String password,
			ModelMap modelMap) {
		
		LOGGER.error("ERROR");
		LOGGER.warn("WARN");
		LOGGER.info("INFO");
		LOGGER.debug("DEBUG");
		LOGGER.trace("TRACE");
		//User user = userRepository.findByEmail(email);
		
		boolean loginResponse = securityService.login(email, password);
		if (loginResponse) {
			return "findFlights";
		} else {
			modelMap.addAttribute("msg", "Invalid Username or Password. Please try again.");
			return "login/login";
		}
	}

	@RequestMapping(value = "showLogin", method = RequestMethod.GET)
	public String showLoginPage() {

		return "login/login";

	}

}
