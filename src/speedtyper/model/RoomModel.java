package speedtyper.model;

import java.util.Collection;

import javax.persistence.*;

@Entity
@Table(name="rooms")
public class RoomModel {
	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
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
	
	public RoomModel () {}
	public RoomModel(String status, int participantsCount, Collection<UserModel> users, 
			TextModel text) {
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
	
}
