package speedtyper.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import speedtyper.dao.TextDao;
import speedtyper.model.TextModel;

@Repository
public class TextDaoImpl implements TextDao {
	@Autowired
	private SessionFactory session;
	
	@Override
	public void add(TextModel text) {
		session.getCurrentSession().save(text);
	}

	@Override
	public void edit(TextModel text) {
		session.getCurrentSession().update(text);
	}

	@Override
	public void delete(int textId) {
		session.getCurrentSession().delete(this.getText(textId));
	}

	@Override
	public TextModel getText(int textId) {
		return (TextModel)session.getCurrentSession().get(TextModel.class, textId);
	}

	@Override
	public List<TextModel> getAllTexts() {
		return session.getCurrentSession().createQuery("from Text").list();
	}

}
