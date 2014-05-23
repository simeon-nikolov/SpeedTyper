package speedtyper.model;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="highscores")
public class HighscoreModel {
	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="user_id")
	private UserModel user;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="room_id")
	private RoomModel room;
	@Column(name="words_per_minute")
	private int wordsPerMinute;
	@Column(name="time_started")
	private Date timeStarted;
	@Column(name="time_to_finish")
	private int timeToFinish;
	
	public HighscoreModel() {}
	public HighscoreModel(UserModel user, RoomModel room, int wordsPerMinute,
			Date timeStarted, int timeToFinish) {
		this.user = user;
		this.room = room;
		this.wordsPerMinute = wordsPerMinute;
		this.timeStarted = timeStarted;
		this.timeToFinish = timeToFinish;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
	
	public int getWordsPerMinute() {
		return wordsPerMinute;
	}
	
	public void setWordsPerMinute(int wordsPerMinute) {
		this.wordsPerMinute = wordsPerMinute;
	}

	public Date getTimeStarted() {
		return timeStarted;
	}

	public void setTimeStarted(Date timeStarted) {
		this.timeStarted = timeStarted;
	}

	public int getTimeToFinish() {
		return timeToFinish;
	}

	public void setTimeToFinish(int timeToFinish) {
		this.timeToFinish = timeToFinish;
	}
	
}
