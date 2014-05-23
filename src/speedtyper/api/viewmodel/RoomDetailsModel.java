package speedtyper.api.viewmodel;

import java.util.List;

public class RoomDetailsModel {
	private int id;
	private String name;
	private String status;
	private String creator;
	private List<ParticipantModel> participants;
	private int participantsCount;
	private int maxParticipants;
	private String text;
	private int countdown;

	public RoomDetailsModel() {
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public List<ParticipantModel> getParticipants() {
		return participants;
	}

	public void setParticipants(List<ParticipantModel> participants) {
		this.participants = participants;
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

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getCountdown() {
		return countdown;
	}

	public void setCountdown(int countdown) {
		this.countdown = countdown;
	}
	
}
