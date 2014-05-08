package speedtyper.dao.impl;

import java.util.List;

import org.hibernate.Query;
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
		session.getCurrentSession().delete(this.getUserById(userId));
	}

	@Override
	public UserModel getUserById(int userId) {
		return (UserModel)session.getCurrentSession().get(UserModel.class, userId);
	}

	@Override
	public UserModel getUserByUsername(String username) {
		Query query = session.getCurrentSession().
				createQuery("SELECT * FROM users WHERE username = :username");
		query.setString("username", username);
		return (UserModel)query.uniqueResult();
	}

	@Override
	public UserModel getUserBySessionKey(String sessionKey) {
		Query query = session.getCurrentSession().
				createQuery("SELECT * FROM users WHERE session_key = :sessionKey");
		query.setString("sessionKey", sessionKey);
		return (UserModel)query.uniqueResult();
	}

	@Override
	public UserModel getUserByEmail(String email) {
		Query query = session.getCurrentSession().
				createQuery("SELECT * FROM users WHERE email = :email");
		query.setString("email", email);
		return (UserModel)query.uniqueResult();
	}
	
	@Override
	public List<UserModel> getAllUsers() {
		return session.getCurrentSession().createQuery("from users").list();
	}

}
