package speedtyper.api.controller;

import java.util.Random;

import javax.activity.InvalidActivityException;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import speedtyper.api.viewmodel.LoggedUserModel;
import speedtyper.api.viewmodel.UserRegisterModel;
import speedtyper.model.UserModel;
import speedtyper.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	private static final int MIN_USERNAME_LENGTH = 6;
	private static final int MAX_USERNAME_LENGTH = 30;
	private static final String VALID_USERNAME_CHARACTERS = "0123456789qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM_";
	private static final int MIN_PASSWORD_LENGTH = 6;
	private static final int MAX_PASSWORD_LENGTH = 30;
	private static final int SESSION_KEY_LENGTH = 50;
	private static final String SESSION_KEY_CHARACTERS = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM";
	private static Random randomGenerator = new Random();

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@ResponseBody
	public LoggedUserModel register(@RequestBody UserRegisterModel userRegModel, BindingResult result)
			throws InvalidActivityException {
		this.ValidateUsername(userRegModel.getUsername());
		this.ValidatePassword(userRegModel.getPassword());
		this.ValidateEmail(userRegModel.getEmail());

		UserModel user = userService.getUserByUsername(userRegModel
				.getUsername());

		if (user != null) {
			throw new InvalidActivityException("The username is already taken!");
		}

		String hashedPassword = BCrypt.hashpw(userRegModel.getPassword(),
				BCrypt.gensalt());
		userRegModel.setPassword(hashedPassword);
		user = this.userRegisterModelToUserModel(userRegModel);
		userService.add(user);
		user = userService.getUserByUsername(user.getUsername());
		user.setSessionKey(this.GenerateSessionKey(user.getId()));
		userService.edit(user);

		LoggedUserModel loggedUser = new LoggedUserModel();
		loggedUser.setUsername(user.getUsername());
		loggedUser.setSessionKey(user.getSessionKey());

		return loggedUser;
	}

	private void ValidateUsername(String username) {
		if (username == null) {
			throw new IllegalArgumentException("Username can not be null!");
		} else if (username.startsWith("_")) {
			throw new IllegalArgumentException(
					"Username can not begin with underscore!");
		} else if (username.length() < MIN_USERNAME_LENGTH) {
			throw new IllegalArgumentException("Username can not be less than "
					+ MIN_USERNAME_LENGTH + " characters!");
		} else if (username.length() > MAX_USERNAME_LENGTH) {
			throw new IllegalArgumentException("Username can not be more than "
					+ MAX_USERNAME_LENGTH + " characters!");
		} else {
			for (char character : username.toCharArray()) {
				if (!VALID_USERNAME_CHARACTERS.contains(character + "")) {
					throw new IllegalArgumentException(
							"Username must contain only latin letters (a-z, A-Z)"
									+ " and underscore \"_\"!");
				}
			}
		}
	}

	private void ValidatePassword(String password) {
		if (password == null) {
			throw new IllegalArgumentException("Password can not be null!");
		} else if (password.length() < MIN_PASSWORD_LENGTH) {
			throw new IllegalArgumentException("Password can not be less than "
					+ MIN_USERNAME_LENGTH + " characters!");
		} else if (password.length() > MAX_PASSWORD_LENGTH) {
			throw new IllegalArgumentException("Password can not be more than "
					+ MAX_PASSWORD_LENGTH + " characters!");
		}
	}

	private void ValidateEmail(String email) {
		EmailValidator validator = EmailValidator.getInstance();
		if (!validator.isValid(email)) {
			throw new IllegalArgumentException("Email addres is not valid!");
		}
	}

	private String GenerateSessionKey(int id) {
		StringBuilder sessionKey = new StringBuilder();
		sessionKey.append(id);
		while (sessionKey.length() < SESSION_KEY_LENGTH) {
			int index = randomGenerator
					.nextInt(SESSION_KEY_CHARACTERS.length());
			sessionKey.append(SESSION_KEY_CHARACTERS.charAt(index));
		}

		return sessionKey.toString();
	}

	private UserModel userRegisterModelToUserModel(
			UserRegisterModel userRegModel) {
		String sessionKey = null;
		int wordsPerMinute = 0;

		UserModel userModel = new UserModel(userRegModel.getUsername(),
				userRegModel.getPassword(), sessionKey,
				userRegModel.getEmail(), wordsPerMinute);

		return userModel;
	}
}