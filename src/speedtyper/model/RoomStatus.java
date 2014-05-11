package speedtyper.model;

public enum RoomStatus {
	AVAIBLE, FULL, STARTED, FINISHED;

	@Override
	public String toString() {
		return super.toString().toLowerCase();
	}
}
