package speedtyper.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import speedtyper.dao.HighscoreDao;
import speedtyper.model.HighscoreModel;
import speedtyper.model.UserModel;

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
	public HighscoreModel getHighscore(int userId, int roomId) {
		Query query = session.getCurrentSession().createQuery(
				"select highscore from HighscoreModel highscore "
				+ "where highscore.user.id = :userId "
				+ "and highscore.room.id = :roomId");
		query.setString("userId", userId + "");
		query.setString("roomId", roomId + "");
		query.setMaxResults(1);
		List result = query.list();
		if (result == null || result.size() == 0) {
			return null;
		}
		return (HighscoreModel) result.get(0);
	}
	
	@Override
	public List<HighscoreModel> getHighscoreByUserId(int userId) {
		Query query = session.getCurrentSession().createQuery(
				"from HighscoreModel highscore "
				+ "where highscore.user.id = :userId");
		query.setString("userId", userId + "");
		query.setMaxResults(1);
		List<HighscoreModel> result = query.list();
		if (result == null || result.size() == 0) {
			return null;
		}
		return  result;
	}

	@Override
	public HighscoreModel getHighscoreById(int id) {
		return (HighscoreModel)session.getCurrentSession().get(HighscoreModel.class, id);
	}

	@Override
	public List<HighscoreModel> getTop100() {
		return session.getCurrentSession()
				.createQuery("from HighscoreModel highscore order by highscore.wordsPerMinute desc")
				.setMaxResults(100).list();
	}

}
