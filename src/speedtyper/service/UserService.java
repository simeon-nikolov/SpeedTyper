package speedtyper.service;

import java.util.List;

import speedtyper.model.User;

public interface UserService {
	public void add(User user);
	public void edit(User user);
	public void delete(int userId);
	public User getUser(int userId);
	public List<User> getAllUsers();
}
