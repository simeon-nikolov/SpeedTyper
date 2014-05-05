package speedtyper.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import speedtyper.dao.TextDao;
import speedtyper.model.Text;

@Repository
public class TextDaoImpl implements TextDao {
	@Autowired
	private SessionFactory session;
	
	@Override
	public void add(Text text) {
		session.getCurrentSession().save(text);
	}

	@Override
	public void edit(Text text) {
		session.getCurrentSession().update(text);
	}

	@Override
	public void delete(int textId) {
		session.getCurrentSession().delete(this.getText(textId));
	}

	@Override
	public Text getText(int textId) {
		return (Text)session.getCurrentSession().get(Text.class, textId);
	}

	@Override
	public List<Text> getAllTexts() {
		return session.getCurrentSession().createQuery("from Text").list();
	}

}
