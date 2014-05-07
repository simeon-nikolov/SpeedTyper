package speedtyper.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import speedtyper.dao.TextDao;
import speedtyper.model.Text;
import speedtyper.service.TextService;

@Service
public class TextServiceImpl implements TextService {

	@Autowired
	private TextDao textDao;
	
	@Transactional
	public void add(Text text) {
		this.textDao.add(text);
	}

	@Transactional
	public void edit(Text text) {
		this.textDao.edit(text);
	}

	@Transactional
	public void delete(int textId) {
		this.textDao.delete(textId);
	}

	@Transactional
	public Text getText(int textId) {
		return this.textDao.getText(textId);
	}

	@Transactional
	public List<Text> getAllTexts() {
		return this.textDao.getAllTexts();
	}

}
