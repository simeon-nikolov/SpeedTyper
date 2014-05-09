package speedtyper.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import speedtyper.dao.HighscoreDao;
import speedtyper.model.HighscoreModel;

@Repository
public class HighscoreDaoImpl implements HighscoreDao {
	@Autowired
	private SessionFactory session;
	
	@Override
	public void add(HighscoreModel highscore) {
		session.getCurrentSession().save(highscore);
	}

	@Override
	public void update(HighscoreModel highscore) {
		session.getCurrentSession().update(highscore);
	}

	@Override
	public void delete(int id) {
		session.getCurrentSession().delete(this.getHighscoreById(id));
	}

	@Override
	public HighscoreModel getHighscoreByUserId(int userId) {
		return null; // TO DO ...
	}

	@Override
	public HighscoreModel getHighscoreById(int id) {
		return (HighscoreModel)session.getCurrentSession().get(HighscoreModel.class, id);
	}

	@Override
	public List<HighscoreModel> getAll() {
		return session.getCurrentSession().createQuery("from highscores").list();
	}

}
