package speedtyper.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import speedtyper.dao.HighscoreDao;
import speedtyper.model.Highscore;

@Repository
public class HighscoreDaoImpl implements HighscoreDao {
	@Autowired
	private SessionFactory session;
	
	@Override
	public void add(Highscore highscore) {
		session.getCurrentSession().save(highscore);
	}

	@Override
	public void edit(Highscore highscore) {
		session.getCurrentSession().update(highscore);
	}

	@Override
	public void delete(int id) {
		session.getCurrentSession().delete(this.getHighscoreById(id));
	}

	@Override
	public Highscore getHighscoreByUserId(int userId) {
		return null; // TO DO ...
	}

	@Override
	public Highscore getHighscoreById(int id) {
		return (Highscore)session.getCurrentSession().get(Highscore.class, id);
	}

	@Override
	public List<Highscore> getAll() {
		return session.getCurrentSession().createQuery("from Highscore").list();
	}

}
