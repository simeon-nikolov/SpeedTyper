package speedtyper.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import speedtyper.dao.ProgressDao;
import speedtyper.model.ProgressModel;

@Repository
public class ProgressDaoImpl implements ProgressDao {
	@Autowired
	private SessionFactory session;
	
	@Override
	public void update(ProgressModel progres) {
		this.session.getCurrentSession().update(progres);
	}

	@Override
	public ProgressModel getGameProgress(int userId, int roomId) {
		Query query = this.session.getCurrentSession().createQuery(
				"from ProgressModel progress where progress.userId = :userId "
				+ "and progress.roomId = :roomId");
		query.setString("userId", userId + "");
		query.setString("roomId", roomId + "");
		query.setMaxResults(1);
		List<ProgressModel> result = query.list();
		if (result == null || result.size() == 0) {
			return null;
		}
		return result.get(0);
	}

	@Override
	public List<ProgressModel> getGameProgressbyRoom(int roomId) {
		Query query = this.session.getCurrentSession().createQuery(
				"from ProgressModel progress where progress.roomId = :roomId");
		query.setString("roomId", roomId + "");
		List<ProgressModel> result = query.list();
		if (result == null || result.size() == 0) {
			return null;
		}
		return result;
	}

}
