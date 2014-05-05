package speedtyper.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import speedtyper.dao.RoomDao;
import speedtyper.model.Room;

@Repository
public class RoomDaoImpl implements RoomDao {
	@Autowired
	private SessionFactory session;
	
	@Override
	public void add(Room room) {
		session.getCurrentSession().save(room);
	}

	@Override
	public void edit(Room room) {
		session.getCurrentSession().update(room);
	}

	@Override
	public void delete(int roomId) {
		session.getCurrentSession().delete(this.getRoom(roomId));
	}

	@Override
	public Room getRoom(int roomId) {
		return (Room)session.getCurrentSession().get(Room.class, roomId);
	}

	@Override
	public List<Room> getAllRooms() {
		return session.getCurrentSession().createQuery("from Room").list();
	}

}
