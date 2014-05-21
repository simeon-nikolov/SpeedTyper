package speedtyper.model;

import javax.persistence.*;

@Entity
@Table(name = "users_rooms")
public class ProgressModel {
	@ManyToOne
	@JoinTable(name="users",
	    joinColumns=@JoinColumn(name="user_id"),
	    inverseJoinColumns=@JoinColumn(name="id"))
	private UserModel user;
	@ManyToOne
	@JoinTable(name="rooms",
	    joinColumns=@JoinColumn(name="room_id"),
	    inverseJoinColumns=@JoinColumn(name="id"))
	private RoomModel room;
	@Column(name = "current_word_index")
	private int currentWordIndex;
	@Column(name = "game_status")
	private String gameStatus;

	public ProgressModel() {
	}

	public ProgressModel(UserModel user, RoomModel room, int currentWordIndex,
			String gameStatus) {
		super();
		this.user = user;
		this.room = room;
		this.currentWordIndex = currentWordIndex;
		this.gameStatus = gameStatus;
	}
	
	public UserModel getUser() {
		return user;
	}
	
	public void setUser(UserModel user) {
		this.user = user;
	}

	public RoomModel getRoom() {
		return room;
	}
	
	public void setRoom(RoomModel room) {
		this.room = room;
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
