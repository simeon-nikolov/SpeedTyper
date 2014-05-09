package speedtyper.service;

import java.util.List;

import speedtyper.model.TextModel;

public interface TextService {
	public void add(TextModel text);
	public void update(TextModel text);
	public void delete(int textId);
	public TextModel getText(int textId);
	public List<TextModel> getAllTexts();
}
