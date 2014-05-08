package speedtyper.service;

import java.util.List;

import speedtyper.model.HighscoreModel;

public interface HighscoreService {
	public void add(HighscoreModel highscore);
	public void edit(HighscoreModel highscore);
	public void delete(int id);
	public HighscoreModel getHighscoreByUserId(int userId);
	public HighscoreModel getHighscoreById(int id);
	public List<HighscoreModel> getAll();
}
