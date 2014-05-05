package speedtyper.dao;

import java.util.List;

import speedtyper.model.Room;

public interface RoomDao {
	public void add(Room room);
	public void edit(Room room);
	public void delete(int roomId);
	public Room getRoom(int roomId);
	public List<Room> getAllRooms();
}
