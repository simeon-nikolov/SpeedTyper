package speedtyper.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import speedtyper.dao.ProgressDao;
import speedtyper.model.ProgressModel;
import speedtyper.service.ProgressService;

@Service
public class ProgressServiceImpl implements ProgressService {
	@Autowired
	private ProgressDao progressDao;

	@Transactional
	public void update(ProgressModel progress) {
		this.progressDao.update(progress);
	}

	@Transactional
	public ProgressModel getGameProgress(int userId, int roomId) {
		return this.progressDao.getGameProgress(userId, roomId);
	}

	@Transactional
	public List<ProgressModel> getGameByRoom(int roomId) {
		return this.progressDao.getGameProgressbyRoom(roomId);
	}

}
