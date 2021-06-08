package ae.smartdubai.bookstore.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BOOK_CLASSIFICATION")
public class Classification implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "CODE")
	private String code;

	@Column(name = "NAME")
	private String name;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Classification [code=" + code + ", name=" + name + "]";
	}
}