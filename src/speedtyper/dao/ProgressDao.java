package speedtyper.dao;

import java.util.List;

import speedtyper.model.ProgressModel;

public interface ProgressDao {
	public void update(ProgressModel progres);
	public ProgressModel getGameProgress(int userId, int roomId);
	public List<ProgressModel> getGameProgressbyRoom(int roomId);
}
