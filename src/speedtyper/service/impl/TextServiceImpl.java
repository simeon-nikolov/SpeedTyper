package speedtyper.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import speedtyper.dao.TextDao;
import speedtyper.model.TextModel;
import speedtyper.service.TextService;

@Service
public class TextServiceImpl implements TextService {

	@Autowired
	private TextDao textDao;
	
	@Transactional
	public void add(TextModel text) {
		this.textDao.add(text);
	}

	@Transactional
	public void update(TextModel text) {
		this.textDao.update(text);
	}

	@Transactional
	public void delete(int textId) {
		this.textDao.delete(textId);
	}

	@Transactional
	public TextModel getText(int textId) {
		return this.textDao.getText(textId);
	}

	@Transactional
	public List<TextModel> getAllTexts() {
		return this.textDao.getAllTexts();
	}

}
