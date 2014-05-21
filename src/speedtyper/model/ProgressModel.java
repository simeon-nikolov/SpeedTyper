package speedtyper.model;

import javax.persistence.*;

@Entity
@Table(name = "users_rooms")
public class ProgressModel {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@Column(name="user_id")
	private int userId;
	@Column(name="room_id")
	private int roomId;
	@Column(name = "current_word_index")
	private int currentWordIndex;
	@Column(name = "game_status")
	private String gameStatus;

	public ProgressModel() {
	}

	public ProgressModel(int id, int userId, int roomId, 
			int currentWordIndex, String gameStatus) {
		super();
		this.id = id;
		this.userId = userId;
		this.roomId = roomId;
		this.currentWordIndex = currentWordIndex;
		this.gameStatus = gameStatus;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getUserId() {
		return userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getRoomId() {
		return roomId;
	}
	
	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public int getCurrentWordIndex() {
		return currentWordIndex;
	}

	public void setCurrentWordIndex(int currentWordIndex) {
		this.currentWordIndex = currentWordIndex;
	}

	public String getGameStatus() {
		return gameStatus;
	}

	public void setGameStatus(String gameStatus) {
		this.gameStatus = gameStatus;
	}

}
