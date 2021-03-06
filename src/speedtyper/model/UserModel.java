package speedtyper.model;

import java.util.Collection;

import javax.persistence.*;

import org.hibernate.validator.constraints.Email;

@Entity
@Table(name="users")
public class UserModel {
	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@Column
	private String username;
	@Column
	private String password;
	@Column(name="session_key")
	private String sessionKey;
	@Column
	@Email
	private String email;
	@Column(name="words_per_minute")
	private int wordsPerMinute;
	@ManyToMany
	@JoinTable(name="users_rooms",
	    joinColumns=@JoinColumn(name="user_id"),
	    inverseJoinColumns=@JoinColumn(name="room_id"))
	private Collection<RoomModel> rooms;

	public UserModel() {}
	public UserModel(String username, String password, String sessionKey,
			String email, int wordsPerMinute) {
		this.username = username;
		this.password = password;
		this.sessionKey = sessionKey;
		this.email = email;
		this.wordsPerMinute = wordsPerMinute;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getSessionKey() {
		return sessionKey;
	}

	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getWordsPerMinute() {
		return wordsPerMinute;
	}

	public void setWordsPerMinute(int wordsPerMinute) {
		this.wordsPerMinute = wordsPerMinute;
	}

}
