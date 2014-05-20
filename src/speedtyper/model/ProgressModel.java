package speedtyper.model;

import javax.persistence.*;

@Entity
@Table(name = "users_rooms")
public class ProgressModel {
	@Id
	@Column(name = "user_id")
	private int userId;
	@Id
	@Column(name = "room_id")
	private int roomId;
	@Column(name = "current_word_index")
	private int currentWordIndex;
	@Column(name = "game_status")
	private String gameStatus;

	public ProgressModel() {
	}

	public ProgressModel(int userId, int roomId, int currentWordIndex,
			String gameStatus) {
		super();
		this.userId = userId;
		this.roomId = roomId;
		this.currentWordIndex = currentWordIndex;
		this.gameStatus = gameStatus;
	}

	public int getUserId() {
		return userId;
	}

	public int getRoomId() {
		return roomId;
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
