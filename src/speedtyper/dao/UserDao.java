package speedtyper.dao;

import java.util.List;

import speedtyper.model.UserModel;

public interface UserDao {
	public void add(UserModel user);
	public void edit(UserModel user);
	public void delete(int userId);
	public UserModel getUserById(int userId);
	public UserModel getUserByUsername(String username);
	public UserModel getUserBySessionKey(String sessionKey);
	public UserModel getUserByEmail(String email);
	public List<UserModel> getAllUsers();
	
}
