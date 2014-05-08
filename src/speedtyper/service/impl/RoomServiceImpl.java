package speedtyper.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import speedtyper.dao.RoomDao;
import speedtyper.model.RoomModel;
import speedtyper.service.RoomService;

@Service
public class RoomServiceImpl implements RoomService {

	@Autowired
	private RoomDao roomDao;
	
	@Transactional
	public void add(RoomModel room) {
		this.roomDao.add(room);
	}

	@Transactional
	public void edit(RoomModel room) {
		this.roomDao.edit(room);
	}

	@Transactional
	public void delete(int roomId) {
		this.roomDao.delete(roomId);
	}

	@Transactional
	public RoomModel getRoom(int roomId) {
		return this.getRoom(roomId);
	}

	@Transactional
	public List<RoomModel> getAllRooms() {
		return this.getAllRooms();
	}

}
