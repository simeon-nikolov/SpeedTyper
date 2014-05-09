package speedtyper.api.viewmodel;

public class UserProfileModel {
	private String username;
	private String email;
	private int wordsPerMinute;

	public UserProfileModel() {
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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
