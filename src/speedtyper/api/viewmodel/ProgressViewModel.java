package speedtyper.api.viewmodel;

public class ProgressViewModel {
	private int currentWordIndex;
	private String status;
	
	public ProgressViewModel() {}

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
