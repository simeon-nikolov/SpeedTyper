package speedtyper.model;

import javax.persistence.*;

@Entity
@Table(name="texts")
public class TextModel {
	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@Column
	private String text;
	@Column(name="words_count")
	private int wordsCount;
	
	public TextModel () {}
	public TextModel(String text, int wordsCount) {
		this.text = text;
		this.wordsCount = wordsCount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getWordsCount() {
		return wordsCount;
	}

	public void setWordsCount(int wordsCount) {
		this.wordsCount = wordsCount;
	}
	
}
