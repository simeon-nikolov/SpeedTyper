package speedtyper.model;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="rooms")
public class RoomModel {
	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@Column
	private String name;
	@ManyToOne
    @JoinColumn(name="creator_id")
	private UserModel creator;
	@Column
	private String status;
	@Column(name="participants_count")
	private int participantsCount;
	@Column(name="max_participants")
	private int maxParticipants;
	@ManyToMany
	@JoinTable(name="users_rooms",
	          joinColumns=@JoinColumn(name="room_id"),
	          inverseJoinColumns=@JoinColumn(name="user_id"))
	private Collection<UserModel> users;
	@ManyToOne
	@JoinColumn(name="text_id")
	private TextModel text;
	@Column(name="starts_at")
	private Timestamp startTime;
	
	public RoomModel () {}
	public RoomModel(String status, int participantsCount, Collection<UserModel> users, 
			TextModel text, Timestamp startTime) {
		this.status = status;
		this.participantsCount = participantsCount;
		this.users = users;
		this.text = text;
		this.startTime = startTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public UserModel getCreator() {
		return creator;
	}

	public void setCreator(UserModel creator) {
		this.creator = creator;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getParticipantsCount() {
		return participantsCount;
	}

	public void setParticipantsCount(int participantsCount) {
		this.participantsCount = participantsCount;
	}
	
	public int getMaxParticipants() {
		return maxParticipants;
	}

	public void setMaxParticipants(int maxParticipants) {
		this.maxParticipants = maxParticipants;
	}

	public Collection<UserModel> getUsers() {
		return users;
	}

	public void setUsers(Collection<UserModel> users) {
		this.users = users;
	}
	
	public TextModel getText() {
		return text;
	}

	public void setText(TextModel text) {
		this.text = text;
	}
	
	public Timestamp getStartTime() {
		return startTime;
	}

	public void setStartTime(Timestamp time) {
		this.startTime = time;
	}
	
}
