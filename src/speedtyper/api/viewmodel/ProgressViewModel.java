package speedtyper.api.viewmodel;

public class ProgressViewModel {
	private int userId;
	private String username;
	private int currentWordIndex;
	private String status;
	
	public ProgressViewModel() {}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public int getCurrentWordIndex() {
		return currentWordIndex;
	}

	public void setCurrentWordIndex(int currentWordIndex) {
		this.currentWordIndex = currentWordIndex;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
