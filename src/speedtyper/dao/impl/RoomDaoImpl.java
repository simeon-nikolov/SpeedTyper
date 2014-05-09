package speedtyper.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import speedtyper.dao.RoomDao;
import speedtyper.model.RoomModel;

@Repository
public class RoomDaoImpl implements RoomDao {
	@Autowired
	private SessionFactory session;
	
	@Override
	public void add(RoomModel room) {
		session.getCurrentSession().save(room);
	}

	@Override
	public void update(RoomModel room) {
		session.getCurrentSession().update(room);
	}

	@Override
	public void delete(int roomId) {
		session.getCurrentSession().delete(this.getRoom(roomId));
	}

	@Override
	public RoomModel getRoom(int roomId) {
		return (RoomModel)session.getCurrentSession().get(RoomModel.class, roomId);
	}

	@Override
	public List<RoomModel> getAllRooms() {
		return session.getCurrentSession().createQuery("from rooms").list();
	}

}
