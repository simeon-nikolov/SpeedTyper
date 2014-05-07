package speedtyper.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import speedtyper.dao.UserDao;
import speedtyper.model.User;
import speedtyper.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Transactional
	public void add(User user) {
		this.userDao.add(user);
	}

	@Transactional
	public void edit(User user) {
		this.userDao.edit(user);
	}

	@Transactional
	public void delete(int userId) {
		this.userDao.delete(userId);
	}

	@Transactional
	public User getUser(int userId) {
		return this.userDao.getUser(userId);
	}

	@Transactional
	public List<User> getAllUsers() {
		return this.userDao.getAllUsers();
	}

}
