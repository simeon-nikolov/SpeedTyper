package speedtyper.model;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="highscores")
public class Highscore {
	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	@ManyToOne
	@JoinColumn(name="room_id")
	private Room room;
	@Column(name="tiem_started")
	private Date timeStarted;
	@Column(name="time_to_finish")
	private int timeToFinish;
	
	public Highscore() {
		this(0, null, null, null, 0);
	}
	
	public Highscore(int id, User user, Room room, Date timeStarted,
			int timeToFinish) {
		super();
		this.id = id;
		this.user = user;
		this.room = room;
		this.timeStarted = timeStarted;
		this.timeToFinish = timeToFinish;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
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
