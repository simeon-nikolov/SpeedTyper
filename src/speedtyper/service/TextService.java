package speedtyper.service;

import java.util.List;

import speedtyper.model.Text;

public interface TextService {
	public void add(Text text);
	public void edit(Text text);
	public void delete(int textId);
	public Text getText(int textId);
	public List<Text> getAllTexts();
}
