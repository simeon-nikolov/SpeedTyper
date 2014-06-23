package speedtyper.api.viewmodel;

public class HighscoreViewModel {
	private String username;
	private int wordsPerMinute;
	private int timeToFinish;
	
	public HighscoreViewModel() {}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getWordsPerMinute() {
		return wordsPerMinute;
	}

	public void setWordsPerMinute(int wordsPerMinute) {
		this.wordsPerMinute = wordsPerMinute;
	}

	public int getTimeToFinish() {
		return timeToFinish;
	}

	public void setTimeToFinish(int timeToFinish) {
		this.timeToFinish = timeToFinish;
	}
	
}
