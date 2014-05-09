package speedtyper.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import speedtyper.dao.HighscoreDao;
import speedtyper.model.HighscoreModel;
import speedtyper.service.HighscoreService;

@Service
public class HighscoreServiceImpl implements HighscoreService {

	@Autowired
	private HighscoreDao highscoreDao;
	
	@Transactional
	public void add(HighscoreModel highscore) {
		this.highscoreDao.add(highscore);
	}

	@Transactional
	public void update(HighscoreModel highscore) {
		this.highscoreDao.update(highscore);
	}

	@Transactional
	public void delete(int id) {
		this.highscoreDao.delete(id);
	}

	@Transactional
	public HighscoreModel getHighscoreByUserId(int userId) {
		return this.highscoreDao.getHighscoreByUserId(userId);
	}

	@Transactional
	public HighscoreModel getHighscoreById(int id) {
		return this.highscoreDao.getHighscoreById(id);
	}

	@Transactional
	public List<HighscoreModel> getAll() {
		return this.highscoreDao.getAll();
	}

}
