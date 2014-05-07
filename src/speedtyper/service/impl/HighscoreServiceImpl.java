package speedtyper.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import speedtyper.dao.HighscoreDao;
import speedtyper.model.Highscore;

@Service
public class HighscoreServiceImpl implements HighscoreDao {

	@Autowired
	private HighscoreDao highscoreDao;
	
	@Transactional
	public void add(Highscore highscore) {
		this.highscoreDao.add(highscore);
	}

	@Transactional
	public void edit(Highscore highscore) {
		this.highscoreDao.edit(highscore);
	}

	@Transactional
	public void delete(int id) {
		this.highscoreDao.delete(id);
	}

	@Transactional
	public Highscore getHighscoreByUserId(int userId) {
		return this.highscoreDao.getHighscoreByUserId(userId);
	}

	@Transactional
	public Highscore getHighscoreById(int id) {
		return this.highscoreDao.getHighscoreById(id);
	}

	@Transactional
	public List<Highscore> getAll() {
		return this.highscoreDao.getAll();
	}

}
