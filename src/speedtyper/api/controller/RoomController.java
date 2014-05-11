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

import speedtyper.api.viewmodel.RoomViewModel;
import speedtyper.model.RoomModel;
import speedtyper.model.UserModel;
import speedtyper.service.RoomService;
import speedtyper.service.UserService;
import speedtyper.service.impl.UserServiceImpl;

@Controller
@RequestMapping(value="/rooms")
public class RoomController {
	private static final String SESSION_KEY_PARAM_NAME = "sessionkey";
	
	@Autowired
	RoomService roomService;
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	@ResponseBody
	public List<RoomViewModel> all(@RequestHeader Map<String, String> headers) {
		String sessionKey = headers.get(SESSION_KEY_PARAM_NAME);
		List<RoomViewModel> rooms = null;
		
		if (this.isAuthenticated(sessionKey)) {
			List<RoomModel> roomsModel = roomService.getAvaibleRooms();
			rooms = listRoomModelToViewModel(roomsModel);
		} else {
			throw new IllegalArgumentException("User is not authenticated!");
		}
		
		return rooms;
	}
	
	private List<RoomViewModel> listRoomModelToViewModel(List<RoomModel> rooms) {
		List<RoomViewModel> roomsViewModel = new ArrayList<RoomViewModel>();
		for (RoomModel roomModel : rooms) {
			RoomViewModel roomVM = new RoomViewModel();
			roomVM.setId(roomModel.getId());
			roomVM.setCreator(roomModel.getCreator().getUsername());
			roomVM.setParticipants(roomModel.getParticipantsCount());
			roomVM.setMaxParticipants(roomModel.getMaxParticipants());
			roomVM.setStatus(roomModel.getStatus());
			roomVM.setText(roomModel.getText().getText());
			roomsViewModel.add(roomVM);
		}
		
		return roomsViewModel;
	}
	
	private boolean isAuthenticated(String sessionKey) {
		UserModel user = userService.getUserBySessionKey(sessionKey);
		
		if (user == null) {
			return false;
		}
		
		return true;
	}

}
