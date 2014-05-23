package speedtyper.api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import speedtyper.api.viewmodel.HighscoreViewModel;
import speedtyper.model.HighscoreModel;
import speedtyper.model.UserModel;
import speedtyper.service.HighscoreService;
import speedtyper.service.UserService;

@Controller
@RequestMapping(value="/highscores")
public class HighscoreController {
	private static final String SESSION_KEY_PARAM_NAME = "sessionkey";
	
	@Autowired
	private HighscoreService highscoreService;
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ResponseBody
	public List<HighscoreViewModel> getHighscores(@RequestHeader Map<String, String> headers) {
		String sessionKey = headers.get(SESSION_KEY_PARAM_NAME);
		
		if (!isAuthenticated(sessionKey)) {
			throw new IllegalArgumentException("User is not authenticated!");
		}
		
		List<HighscoreModel> highscores = highscoreService.getTop100();
		List<HighscoreViewModel> result = listHighscoreModelToViewModel(highscores);
		return result;
	}

	private List<HighscoreViewModel> listHighscoreModelToViewModel(
			List<HighscoreModel> highscores) {
		List<HighscoreViewModel> result = new ArrayList<HighscoreViewModel>();
		
		for (HighscoreModel model : highscores) {
			HighscoreViewModel highscoreVm = new HighscoreViewModel();
			highscoreVm.setUsername(model.getUser().getUsername());
			highscoreVm.setWordsPerMinute(model.getWordsPerMinute());
			highscoreVm.setTimeToFinish(model.getTimeToFinish());
			result.add(highscoreVm);
		}
		return result;
	}

	private boolean isAuthenticated(String sessionKey) {
		UserModel user = userService.getUserBySessionKey(sessionKey);

		if (user == null) {
			return false;
		}

		return true;
	}
	
}
