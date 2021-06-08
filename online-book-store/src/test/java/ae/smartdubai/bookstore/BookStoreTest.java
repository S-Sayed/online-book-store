package ae.smartdubai.bookstore;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.ObjectMapper;

import ae.smartdubai.bookstore.model.Book;

public class BookStoreTest {
	protected Book createDummyBookObject() {
		Book book = new Book();
		book.setIsbn("1-26325-387-456");
		book.setName("Java");
		book.setPrice(BigDecimal.valueOf(100));
		book.getClassification().setCode("PROGRAMMING");
		return book;
	}

	protected byte[] toJson(Object object) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsBytes(object);
	}
}
