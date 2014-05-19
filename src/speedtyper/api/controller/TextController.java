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

import speedtyper.api.viewmodel.TextViewModel;
import speedtyper.model.TextModel;
import speedtyper.model.UserModel;
import speedtyper.service.TextService;
import speedtyper.service.UserService;

@Controller
@RequestMapping(value="/texts")
public class TextController {
	private static final String SESSION_KEY_PARAM_NAME = "sessionkey";
	
	@Autowired
	private TextService textService;
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	@ResponseBody
	public List<TextViewModel> all(@RequestHeader Map<String, String> headers) {
		String sessionKey = headers.get(SESSION_KEY_PARAM_NAME);
		List<TextViewModel> allTexts = null;
		
		if (this.isAuthenticated(sessionKey)) {
			allTexts = listTextModelToTextViewModel(textService.getAllTexts());
		}
		
		return allTexts;
	}
	
	private List<TextViewModel> listTextModelToTextViewModel(List<TextModel> texts) {
		List<TextViewModel> resultTexts = new ArrayList<TextViewModel>();
		for (TextModel text : texts) {
			TextViewModel viewText = new TextViewModel();
			viewText.setId(text.getId());
			viewText.setText(text.getText());
			resultTexts.add(viewText);
		}
		
		return resultTexts;
	}
	
	private boolean isAuthenticated(String sessionKey) {
		UserModel user = userService.getUserBySessionKey(sessionKey);

		if (user == null) {
			return false;
		}

		return true;
	}
}
