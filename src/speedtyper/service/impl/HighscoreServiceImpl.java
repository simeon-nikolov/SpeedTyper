package speedtyper.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import speedtyper.dao.HighscoreDao;
import speedtyper.model.HighscoreModel;

@Service
public class HighscoreServiceImpl implements HighscoreDao {

	@Autowired
	private HighscoreDao highscoreDao;
	
	@Transactional
	public void add(HighscoreModel highscore) {
		this.highscoreDao.add(highscore);
	}

	@Transactional
	public void edit(HighscoreModel highscore) {
		this.highscoreDao.edit(highscore);
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
