package speedtyper.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import speedtyper.dao.UserDao;
import speedtyper.model.User;

@Repository
public class UserDaoImpl implements UserDao {
	@Autowired
	private SessionFactory session;

	@Override
	public void add(User user) {
		session.getCurrentSession().save(user);
	}

	@Override
	public void edit(User user) {
		session.getCurrentSession().update(user);
	}

	@Override
	public void delete(int userId) {
		session.getCurrentSession().delete(this.getUser(userId));
	}

	@Override
	public User getUser(int userId) {
		return (User)session.getCurrentSession().get(User.class, userId);
	}

	@Override
	public List<User> getAllUsers() {
		return session.getCurrentSession().createQuery("from User").list();
	}

}
