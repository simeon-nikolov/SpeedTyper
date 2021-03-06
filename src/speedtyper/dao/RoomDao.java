package speedtyper.dao;

import java.util.List;

import speedtyper.model.RoomModel;

public interface RoomDao {
	public void add(RoomModel room);
	public void update(RoomModel room);
	public void delete(int roomId);
	public RoomModel getRoom(int roomId);
	public List<RoomModel> getAllRooms();
	public List<RoomModel> getAvaibleRooms();
}
