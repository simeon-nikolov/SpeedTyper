package speedtyper.service;

import java.util.List;

import speedtyper.model.Highscore;

public interface HighscoreService {
	public void add(Highscore highscore);
	public void edit(Highscore highscore);
	public void delete(int id);
	public Highscore getHighscoreByUserId(int userId);
	public Highscore getHighscoreById(int id);
	public List<Highscore> getAll();
}
