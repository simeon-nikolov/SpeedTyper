package speedtyper.model;

public enum RoomStatus {
	AVAIBLE, STARTED, FINISHED;

	@Override
	public String toString() {
		return super.toString().toLowerCase();
	}
}
