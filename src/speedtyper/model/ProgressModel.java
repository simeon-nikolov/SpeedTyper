package speedtyper.model;

import javax.persistence.*;

@Entity
@Table(name = "users_rooms")
public class ProgressModel {
	@Id
	@Column(name = "room_id")
	private int userId;
	@Id
	@Column(name = "room_id")
	private int roomId;
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
	@JoinColumns( {
        @JoinColumn(name="username", referencedColumnName = "username"),
        } )
	private UserModel user;
	@Column(name = "current_word_index")
	private int currentWordIndex;
	@Column(name = "game_status")
	private String gameStatus;

	public ProgressModel() {
	}

	public ProgressModel(int userId, int roomId,UserModel user, int currentWordIndex,
			String gameStatus) {
		super();
		this.userId = userId;
		this.roomId = roomId;
		this.user = user;
		this.currentWordIndex = currentWordIndex;
		this.gameStatus = gameStatus;
	}

	public int getUserId() {
		return userId;
	}

	public int getRoomId() {
		return roomId;
	}
	
	public UserModel getUser() {
		return user;
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
