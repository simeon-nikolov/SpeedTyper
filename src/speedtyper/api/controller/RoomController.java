package speedtyper.api.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import speedtyper.api.viewmodel.JsonResponse;
import speedtyper.api.viewmodel.ParticipantModel;
import speedtyper.api.viewmodel.ProgressViewModel;
import speedtyper.api.viewmodel.RoomCreateModel;
import speedtyper.api.viewmodel.RoomDetailsModel;
import speedtyper.api.viewmodel.RoomViewModel;
import speedtyper.model.GameProgressStatus;
import speedtyper.model.HighscoreModel;
import speedtyper.model.ProgressModel;
import speedtyper.model.RoomModel;
import speedtyper.model.RoomStatus;
import speedtyper.model.TextModel;
import speedtyper.model.UserModel;
import speedtyper.service.HighscoreService;
import speedtyper.service.ProgressService;
import speedtyper.service.RoomService;
import speedtyper.service.TextService;
import speedtyper.service.UserService;

@Controller
@RequestMapping(value = "/rooms")
public class RoomController {
	private static final String SESSION_KEY_PARAM_NAME = "sessionkey";
	private static final int COUNTDOWN = 10;
	private static final int TEXT_MAX_CHARS = 23;
	private static Random randomGenerator = new Random();

	@Autowired
	private RoomService roomService;
	@Autowired
	private UserService userService;
	@Autowired
	private TextService textService;
	@Autowired
	private HighscoreService highscoreService;
	@Autowired
	private ProgressService progressService;

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
		
		if (!isAuthenticated(sessionKey)) {
			throw new IllegalArgumentException("User is not authenticated!");
		}
		
		List<TextModel> allTexts = textService.getAllTexts();
		int index = randomGenerator.nextInt(allTexts.size());
		TextModel text = allTexts.get(index);
		room.setTextId(text.getId());
		room.setCreatorId(userService.getUserBySessionKey(sessionKey).getId());
		RoomModel roomModel = this.roomCreateModelToRoomModel(room);
		roomService.add(roomModel);
		roomViewModel = roomModelToRoomViewModel(roomModel);
		
		return roomViewModel;
	}
	
	@RequestMapping(value="/join/{roomId}", method=RequestMethod.PUT)
	@ResponseBody
	public RoomDetailsModel join(@RequestHeader Map<String, String> headers, 
			@PathVariable int roomId) {
		String sessionKey = headers.get(SESSION_KEY_PARAM_NAME);
		RoomDetailsModel roomResult = null;
		
		if (!isAuthenticated(sessionKey)) {
			throw new IllegalArgumentException("User is not authenticated!");
		}
		
		RoomModel room = roomService.getRoom(roomId);
		if (room.getStatus().equals(RoomStatus.AVAIBLE.toString())) {
			UserModel user = userService.getUserBySessionKey(sessionKey);
			Collection<UserModel> usersCollection = room.getUsers();
			
			if (!usersCollection.contains(user)) {
				usersCollection.add(user);
				room.setUsers(usersCollection);
				room.setParticipantsCount(room.getParticipantsCount() + 1);
			}
			
			if (room.getParticipantsCount() == room.getMaxParticipants()) {
				room.setStatus(RoomStatus.FULL.toString());
			}
			
			roomService.update(room);
			roomResult = roomModelToRoomDetailsModel(room);
		} else {
			throw new IllegalArgumentException("Room is not avaible!");
		}
		
		return roomResult;
	}
	
	@RequestMapping(value="/join/random", method=RequestMethod.PUT)
	@ResponseBody
	public RoomDetailsModel joinRandom(@RequestHeader Map<String, String> headers) {
		String sessionKey = headers.get(SESSION_KEY_PARAM_NAME);
		RoomDetailsModel roomResult = null;
		
		if (!isAuthenticated(sessionKey)) {
			throw new IllegalArgumentException("User is not authenticated!");
		}
		
		
		List<RoomModel> rooms = roomService.getAvaibleRooms();
		int index = randomGenerator.nextInt(rooms.size());
		int roomId = rooms.get(index).getId();
		
		RoomModel room = roomService.getRoom(roomId);
		if (room.getStatus().equals(RoomStatus.AVAIBLE.toString())) {
			UserModel user = userService.getUserBySessionKey(sessionKey);
			Collection<UserModel> usersCollection = room.getUsers();
			
			if (!usersCollection.contains(user)) {
				usersCollection.add(user);
				room.setUsers(usersCollection);
				room.setParticipantsCount(room.getParticipantsCount() + 1);
			}
			
			if (room.getParticipantsCount() == room.getMaxParticipants()) {
				room.setStatus(RoomStatus.FULL.toString());
			}
			
			roomService.update(room);
			roomResult = roomModelToRoomDetailsModel(room);
		} else {
			throw new IllegalArgumentException("Room is not avaible!");
		}
		
		return roomResult;
	}
	
	@RequestMapping(value = "/details/{roomId}", method = RequestMethod.GET)
	@ResponseBody
	public RoomDetailsModel getRoomDetails(@RequestHeader Map<String, String> headers,
			@PathVariable int roomId) {
		String sessionKey = headers.get(SESSION_KEY_PARAM_NAME);
		RoomDetailsModel roomResult = null;
		
		if (!isAuthenticated(sessionKey)) {
			throw new IllegalArgumentException("User is not authenticated!");
		}
		
		RoomModel room = roomService.getRoom(roomId);
		UserModel user = userService.getUserBySessionKey(sessionKey);
		Collection<UserModel> usersCollection = room.getUsers();
		user = this.getUserFromRoom(user, room);
		
		if (usersCollection.contains(user)) {
			roomResult = this.roomModelToRoomDetailsModel(room);
		} else {
			throw new IllegalArgumentException("User does not participate in this room!");
		}
		
		if (room.getStatus().equals(RoomStatus.STARTED.toString())) {
			long currentTime = new Date().getTime();
			long startTime = room.getStartTime().getTime();
			int countdown = (int) ((startTime - currentTime) / 1000);
			roomResult.setCountdown(countdown);
			ProgressModel progress = progressService.getGameProgress(user.getId(), roomId);
			progress.setGameStatus(GameProgressStatus.STARTED.toString());
			progressService.update(progress);
		}
	
		return roomResult;
	}
	
	@RequestMapping(value = "/{roomId}/leave", method = RequestMethod.PUT)
	@ResponseBody
	public JsonResponse leave(@RequestHeader Map<String, String> headers,
			@PathVariable int roomId) {
		String sessionKey = headers.get(SESSION_KEY_PARAM_NAME);
		
		if (!isAuthenticated(sessionKey)) {
			throw new IllegalArgumentException("User is not authenticated!");
		}
		
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
		
		return new JsonResponse("OK", "You have successfully left the room!");
	}
	
	@RequestMapping(value="/{roomId}/start", method=RequestMethod.PUT)
	@ResponseBody
	public RoomDetailsModel start(@RequestHeader Map<String, String> headers,
			@PathVariable int roomId) {
		String sessionKey = headers.get(SESSION_KEY_PARAM_NAME);
		
		if (!isAuthenticated(sessionKey)) {
			throw new IllegalArgumentException("User is not authenticated!");
		}
		
		UserModel user = userService.getUserBySessionKey(sessionKey);
		RoomModel room = roomService.getRoom(roomId);
		
		if (room.getCreator().getId() != user.getId()) {
			throw new IllegalArgumentException("You don't have rights to start the game!");
		}
		
		long currentTime = new Date().getTime();
		room.setStatus(RoomStatus.STARTED.toString());
		Timestamp startTime = new Timestamp(currentTime + (COUNTDOWN * 1000));
		room.setStartTime(startTime);
		roomService.update(room);
		
		ProgressModel progress = progressService.getGameProgress(user.getId(), roomId);
		progress.setGameStatus(GameProgressStatus.STARTED.toString());
		progressService.update(progress);
		
		RoomDetailsModel result = roomModelToRoomDetailsModel(room);
		result.setCountdown(COUNTDOWN);
		
		return result;
	}
	
	@RequestMapping(value = "/{roomId}/submit", method = RequestMethod.PUT)
	@ResponseBody
	public List<ProgressViewModel> submit(@RequestHeader Map<String, String> headers, 
			@PathVariable int roomId, @RequestBody String word) {
		String sessionKey = headers.get(SESSION_KEY_PARAM_NAME);
		
		if (!isAuthenticated(sessionKey)) {
			throw new IllegalArgumentException("User is not authenticated!");
		}
		
		UserModel user = userService.getUserBySessionKey(sessionKey);
		RoomModel room = roomService.getRoom(roomId);
		ProgressModel progress = progressService.
				getGameProgress(user.getId(), room.getId());
		TextModel text = room.getText();
		int wordIndex = progress.getCurrentWordIndex();
		String[] words = text.getText().split("\\s+");
		
		if (!word.equals(words[wordIndex])) {
			throw new IllegalArgumentException("The word does not match!");
		}
		
		if (progress.getCurrentWordIndex() == 0) {
			progress.setGameStatus(GameProgressStatus.TYPING.toString());
		}
		
		progress.setCurrentWordIndex(wordIndex + 1);
		
		if (progress.getCurrentWordIndex() == words.length) {
			progress.setGameStatus(GameProgressStatus.FINISHED.toString());
			this.finishGame(user, room, words);
		}
		
		progressService.update(progress);
		List<ProgressModel> gameProgresses = progressService.
				getGamePregressesByRoom(roomId);
		List<ProgressViewModel> result = listProgressModelToProgressVm(gameProgresses);
		
		return result;
	}
	
	@RequestMapping(value = "/{roomId}/progress", method = RequestMethod.GET)
	@ResponseBody
	public List<ProgressViewModel> progress(@RequestHeader Map<String, String> headers, 
			@PathVariable int roomId) {
		String sessionKey = headers.get(SESSION_KEY_PARAM_NAME);
		
		if (!isAuthenticated(sessionKey)) {
			throw new IllegalArgumentException("User is not authenticated!");
		}

		List<ProgressModel> gameProgresses = progressService.
				getGamePregressesByRoom(roomId);
		List<ProgressViewModel> result = listProgressModelToProgressVm(gameProgresses);
		
		return result;
	}
	
	private void finishGame(UserModel user, RoomModel room, String[] words) {		
		HighscoreModel highscore = new HighscoreModel();
		long currentTime = new Date().getTime();
		long startTime = room.getStartTime().getTime();
		int timeToFinish = (int) ((currentTime - startTime) / 1000L);
		int wordsPerMinute = getWordsPerMinute(timeToFinish, words);
		highscore.setRoom(room);
		highscore.setUser(user);
		highscore.setTimeStarted(new Date(startTime));
		highscore.setTimeToFinish(timeToFinish);
		highscore.setWordsPerMinute(wordsPerMinute);
		highscoreService.add(highscore);
		
		List<HighscoreModel> highscores = highscoreService.getHighscoreByUserId(user.getId());
		int averageWpm = 0;
		
		for (HighscoreModel stats : highscores) {
			averageWpm += stats.getWordsPerMinute();
		}
		
		averageWpm = averageWpm / highscores.size();
		user.setWordsPerMinute(averageWpm);
		userService.update(user);
	}
	
	// http://en.wikipedia.org/wiki/Words_per_minute
	private int getWordsPerMinute(int timeToFinish, String[] words) {
		int allChars = 0;
		
		for (String word : words) {
			allChars+=word.length();
		}
		
		int charsPerMinute = (allChars / timeToFinish) * 60;
		int wordsPerMinute = (int) Math.ceil(charsPerMinute / 5.0);
		
		return wordsPerMinute;
	}

	private List<ProgressViewModel> listProgressModelToProgressVm(List<ProgressModel> progresses) {
		List<ProgressViewModel> result = new ArrayList<ProgressViewModel>();
		
		for (ProgressModel progress : progresses) {
			ProgressViewModel progressVm = new ProgressViewModel();
			UserModel user = userService.getUserById(progress.getUserId());
			progressVm.setUserId(user.getId());
			progressVm.setUsername(user.getUsername());
			progressVm.setCurrentWordIndex(progress.getCurrentWordIndex());
			progressVm.setStatus(progress.getGameStatus());
			result.add(progressVm);
		}
		
		return result;
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
	
	private RoomDetailsModel roomModelToRoomDetailsModel(RoomModel room) {
		RoomDetailsModel roomDm = new RoomDetailsModel();
		roomDm.setId(room.getId());
		roomDm.setName(room.getName());
		roomDm.setCreator(room.getCreator().getUsername());
		roomDm.setMaxParticipants(room.getMaxParticipants());
		roomDm.setParticipantsCount(room.getParticipantsCount());
		roomDm.setStatus(room.getStatus());
		
		List<ParticipantModel> participants = new ArrayList<ParticipantModel>();
		
		for (UserModel user : room.getUsers()) {
			ParticipantModel participant = new ParticipantModel();
			participant.setUserId(user.getId());
			participant.setUsername(user.getUsername());
			participants.add(participant);
		}
		
		roomDm.setParticipants(participants);
		roomDm.setText(room.getText().getText());
		
		return roomDm;
	}
	
	private RoomViewModel roomModelToRoomViewModel(RoomModel roomModel) {
		RoomViewModel roomVm = new RoomViewModel();
		
		roomVm.setId(roomModel.getId());
		roomVm.setName(roomModel.getName());
		roomVm.setCreator(roomModel.getCreator().getUsername());
		roomVm.setParticipants(roomModel.getParticipantsCount());
		roomVm.setMaxParticipants(roomModel.getMaxParticipants());
		roomVm.setStatus(roomModel.getStatus());
		
		String text = "";
		
		if (roomModel.getText().getText().length() > TEXT_MAX_CHARS) {
			text = roomModel.getText().getText().
					substring(0, TEXT_MAX_CHARS - 3).concat("...");
		} else {
			text = roomModel.getText().getText();
		}
		
		roomVm.setText(text);

		return roomVm;
	}
	
	private RoomModel roomCreateModelToRoomModel(RoomCreateModel room) {
		UserModel creator = userService.getUserById(room.getCreatorId());
		TextModel text = textService.getText(room.getTextId());
		Collection<UserModel> users = new ArrayList<UserModel>();
		users.add(creator);
		
		RoomModel roomModel = new RoomModel();
		
		roomModel.setName(room.getName());
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
