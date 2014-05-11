package speedtyper.api.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import speedtyper.api.viewmodel.JsonResponse;
import speedtyper.api.viewmodel.RoomCreateModel;
import speedtyper.api.viewmodel.RoomDetailsModel;
import speedtyper.api.viewmodel.RoomViewModel;
import speedtyper.model.RoomModel;
import speedtyper.model.RoomStatus;
import speedtyper.model.TextModel;
import speedtyper.model.UserModel;
import speedtyper.service.RoomService;
import speedtyper.service.TextService;
import speedtyper.service.UserService;
import speedtyper.service.impl.UserServiceImpl;

@Controller
@RequestMapping(value = "/rooms")
public class RoomController {
	private static final String SESSION_KEY_PARAM_NAME = "sessionkey";

	@Autowired
	RoomService roomService;
	@Autowired
	private UserService userService;
	@Autowired
	private TextService textService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
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

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public RoomViewModel create(@RequestHeader Map<String, String> headers,
			@RequestBody RoomCreateModel room) {
		String sessionKey = headers.get(SESSION_KEY_PARAM_NAME);
		RoomViewModel roomViewModel = null;
		
		if (this.isAuthenticated(sessionKey)) {
			room.setCreatorId(userService.getUserBySessionKey(sessionKey).getId());
			RoomModel roomModel = this.roomCreateModelToRoomModel(room);
			roomService.add(roomModel);
			roomViewModel = roomModelToRoomViewModel(roomModel);
		} else {
			throw new IllegalArgumentException("User is not authenticated!");
		}
		
		return roomViewModel;
	}
	
	@RequestMapping(value="/join/{roomId}", method=RequestMethod.PUT)
	@ResponseBody
	public RoomDetailsModel join(@RequestHeader Map<String, String> headers, 
			@PathVariable int roomId) {
		String sessionKey = headers.get(SESSION_KEY_PARAM_NAME);
		RoomDetailsModel roomResult = null;
		
		if (isAuthenticated(sessionKey)) {
			RoomModel room = roomService.getRoom(roomId);
			if (room.getStatus().equals(RoomStatus.AVAIBLE.toString())) {
				UserModel user = userService.getUserBySessionKey(sessionKey);
				Collection<UserModel> usersCollection = room.getUsers();
				usersCollection.add(user);
				room.setUsers(usersCollection);
				room.setParticipantsCount(room.getParticipantsCount() + 1);
				
				if (room.getParticipantsCount() == room.getMaxParticipants()) {
					room.setStatus(RoomStatus.FULL.toString());
				}
				
				roomService.update(room);
				roomResult = roomModelToRoomDetailModel(room);
			} else {
				throw new IllegalArgumentException("Room is not avaible!");
			}
		} else {
			throw new IllegalArgumentException("User is not authenticated!");
		}
		
		return roomResult;
	}
	
	@RequestMapping(value="/leave/{roomId}", method=RequestMethod.PUT)
	@ResponseBody
	public JsonResponse leave(@RequestHeader Map<String, String> headers,
			@PathVariable int roomId) {
		String sessionKey = headers.get(SESSION_KEY_PARAM_NAME);
		
		if (isAuthenticated(sessionKey)) {
			UserModel user = userService.getUserBySessionKey(sessionKey);
			RoomModel room = roomService.getRoom(roomId);
			Collection<UserModel> users = room.getUsers();
			user = getUserFromRoom(user, room);
			if (users.remove(user)) {
				room.setUsers(users);
				room.setParticipantsCount(room.getParticipantsCount() - 1);
				room.setStatus(RoomStatus.AVAIBLE.toString());
				roomService.update(room);
			} else {
				throw new IllegalArgumentException("User does not belong to this room!");
			}
		} else {
			throw new IllegalArgumentException("User is not authenticated!");
		}
		
		return new JsonResponse("OK", "You have successfully left the room!");
	}
	
	@RequestMapping(value="/{roomId}/start", method=RequestMethod.PUT)
	@ResponseBody
	public JsonResponse start(@RequestHeader Map<String, String> headers,
			@PathVariable int roomId) {
		String sessionKey = headers.get(SESSION_KEY_PARAM_NAME);
		
		if (isAuthenticated(sessionKey)) {
			UserModel user = userService.getUserBySessionKey(sessionKey);
			RoomModel room = roomService.getRoom(roomId);
			
			if (room.getCreator().getId() != user.getId()) {
				throw new IllegalArgumentException("You don't have rights to start the game!");
			}
			
			room.setStatus(RoomStatus.STARTED.toString());
			roomService.update(room);
		} else {
			throw new IllegalArgumentException("User is not authenticated!");
		}
		
		return new JsonResponse("OK", "The game successfully started!");
	}

	private UserModel getUserFromRoom(UserModel user, RoomModel room) {
		for (UserModel userInRoom : room.getUsers()) {
			if (user.getId() == userInRoom.getId()) {
				user = userInRoom;
				break;
			}
		}
		
		return user;
	}

	private List<RoomViewModel> listRoomModelToViewModel(List<RoomModel> rooms) {
		List<RoomViewModel> roomsViewModel = new ArrayList<RoomViewModel>();
		for (RoomModel roomModel : rooms) {
			roomsViewModel.add(roomModelToRoomViewModel(roomModel));
		}

		return roomsViewModel;
	}
	
	private RoomDetailsModel roomModelToRoomDetailModel(RoomModel room) {
		RoomDetailsModel roomDM = new RoomDetailsModel();
		roomDM.setId(room.getId());
		roomDM.setCreator(room.getCreator().getUsername());
		roomDM.setMaxParticipants(room.getMaxParticipants());
		roomDM.setParticipantsCount(room.getParticipantsCount());
		roomDM.setStatus(room.getStatus());
		
		List<String> participants = new ArrayList<String>();
		
		for (UserModel user : room.getUsers()) {
			participants.add(user.getUsername());
		}
		
		roomDM.setParticipants(participants);
		roomDM.setText(room.getText().getText());
		
		return roomDM;
	}
	
	private RoomViewModel roomModelToRoomViewModel(RoomModel roomModel) {
		RoomViewModel roomVM = new RoomViewModel();
		
		roomVM.setId(roomModel.getId());
		roomVM.setCreator(roomModel.getCreator().getUsername());
		roomVM.setParticipants(roomModel.getParticipantsCount());
		roomVM.setMaxParticipants(roomModel.getMaxParticipants());
		roomVM.setStatus(roomModel.getStatus());
		roomVM.setText(roomModel.getText().getText());

		return roomVM;
	}
	
	private RoomModel roomCreateModelToRoomModel(RoomCreateModel room) {
		UserModel creator = userService.getUserById(room.getCreatorId());
		TextModel text = textService.getText(room.getTextId());
		Collection<UserModel> users = new ArrayList<UserModel>();
		users.add(creator);
		
		RoomModel roomModel = new RoomModel();
		
		roomModel.setCreator(creator);
		roomModel.setText(text);
		roomModel.setUsers(users);
		roomModel.setParticipantsCount(1);
		roomModel.setMaxParticipants(room.getMaxParticipants());
		roomModel.setStatus(RoomStatus.AVAIBLE.toString());
		
		return roomModel;
	}

	private boolean isAuthenticated(String sessionKey) {
		UserModel user = userService.getUserBySessionKey(sessionKey);

		if (user == null) {
			return false;
		}

		return true;
	}

}
