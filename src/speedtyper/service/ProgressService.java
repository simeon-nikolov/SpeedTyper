package speedtyper.service;

import java.util.List;

import speedtyper.model.ProgressModel;

public interface ProgressService {
	public void update(ProgressModel progress);
	public ProgressModel getGameProgress(int userId, int roomId);
	public List<ProgressModel> getGamePregressesByRoom(int roomId);
}
