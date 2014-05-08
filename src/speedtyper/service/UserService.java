package speedtyper.service;

import java.util.List;

import speedtyper.model.UserModel;

public interface UserService {
	public void add(UserModel user);
	public void edit(UserModel user);
	public void delete(int userId);
	public UserModel getUser(int userId);
	public List<UserModel> getAllUsers();
}
