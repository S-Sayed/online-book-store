package ae.smartdubai.bookstore.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "BOOK")
public class Book implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ISBN")
	@NotBlank
	private String isbn;

	@Column(name = "NAME")
	@NotBlank
	private String name;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "PRICE")
	@NotBlank
	private BigDecimal price;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "AUTHOR_ID")
	private Set<Author> authors = new HashSet<>();

	@ManyToOne
	@JoinColumn(name = "CLASSIFICATION_CODE")
	Classification classification = new Classification();

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Set<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(Set<Author> authors) {
		this.authors = authors;
	}

	public Classification getClassification() {
		return classification;
	}

	public void setClassification(Classification classification) {
		this.classification = classification;
	}

	@Override
	public String toString() {
		return "Book [isbn=" + isbn + ", name=" + name + ", description=" + description + ", price=" + price
				+ ", authors=" + authors + ", classification=" + classification + "]";
	}
}
