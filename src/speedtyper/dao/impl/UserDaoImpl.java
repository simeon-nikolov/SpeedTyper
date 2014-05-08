package speedtyper.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import speedtyper.dao.UserDao;
import speedtyper.model.UserModel;

@Repository
public class UserDaoImpl implements UserDao {
	@Autowired
	private SessionFactory session;

	@Override
	public void add(UserModel user) {
		session.getCurrentSession().save(user);
	}

	@Override
	public void edit(UserModel user) {
		session.getCurrentSession().update(user);
	}

	@Override
	public void delete(int userId) {
		session.getCurrentSession().delete(this.getUser(userId));
	}

	@Override
	public UserModel getUser(int userId) {
		return (UserModel)session.getCurrentSession().get(UserModel.class, userId);
	}

	@Override
	public List<UserModel> getAllUsers() {
		return session.getCurrentSession().createQuery("from User").list();
	}

}
