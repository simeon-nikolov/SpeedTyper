package speedtyper.api.controller;

import org.springframework.beans.factory.annotation.Autowired;

import speedtyper.model.UserModel;
import speedtyper.service.UserService;

public class UserAuthenticator {
	@Autowired
	private static UserService  userService;
	
	public static boolean isAuthenticated(String sessionKey) {
		UserModel user = userService.getUserBySessionKey(sessionKey);
		
		if (user == null) {
			return false;
		}
		
		return true;
	}
}
