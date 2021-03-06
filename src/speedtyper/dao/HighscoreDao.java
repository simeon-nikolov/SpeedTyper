package speedtyper.dao;

import java.util.List;

import speedtyper.model.HighscoreModel;

public interface HighscoreDao {
	public void add(HighscoreModel highscore);
	public void update(HighscoreModel highscore);
	public void delete(int id);
	public HighscoreModel getHighscore(int userId, int roomId);
	public List<HighscoreModel> getHighscoreByUserId(int userId);
	public HighscoreModel  getHighscoreById(int id);
	public List<HighscoreModel> getTop100();
}
