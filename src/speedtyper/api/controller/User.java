package speedtyper.api.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import speedtyper.service.UserService;

@Controller
@RequestMapping("/user")
public class User {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String register(@ModelAttribute User user, BindingResult result) {
		
		return "register";
	}
}