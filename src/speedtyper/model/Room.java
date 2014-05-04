package speedtyper.model;

import java.util.Collection;

import javax.persistence.*;

@Entity
public class Room {
	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@Column
	private int status;
	@Column(name="participants_count")
	private int participantsCount;
	@ManyToMany
	@JoinTable(name="users_rooms",
	          joinColumns=@JoinColumn(name="room_id"),
	          inverseJoinColumns=@JoinColumn(name="user_id"))
	private Collection users;
	@ManyToOne
	@JoinColumn(name="text_id")
	private Text text;
	
	public Room () {
		this(0, 0, 0, null, null);
	}
	
	public Room(int id, int status, int participantsCount, Collection users, Text text) {
		super();
		this.id = id;
		this.status = status;
		this.participantsCount = participantsCount;
		this.users = users;
		this.text = text;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getParticipantsCount() {
		return participantsCount;
	}

	public void setParticipantsCount(int participantsCount) {
		this.participantsCount = participantsCount;
	}

	public Collection getUsers() {
		return users;
	}

	public void setUsers(Collection users) {
		this.users = users;
	}
	
	public Text getText() {
		return text;
	}

	public void setText(Text text) {
		this.text = text;
	}
	
}
