package speedtyper.model;

public enum GameProgressStatus {
	NOT_STARTED, STARTED, TYPING, FINISHED;

	@Override
	public String toString() {
		return super.toString().toLowerCase();
	}
}
