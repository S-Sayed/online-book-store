package ae.smartdubai.bookstore.dao;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import ae.smartdubai.bookstore.BookStoreTest;
import ae.smartdubai.bookstore.model.Book;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BookRepositoryTest extends BookStoreTest {

	@Autowired
	private BookRepository bookRepository;

	@Before
	public void insertBooks() {
		List<Book> books = new ArrayList<>();
		books.add(createDummyBookObject());
		bookRepository.saveAll(books);
	}

	@Test
	public void givenNothing_whenGettingBooks_thenReturnAll() {
		List<Book> books = bookRepository.findAll();
		assertNotNull(books);
		assertFalse(books.isEmpty());
		assertEquals(1, books.size());
	}

	@Test
	public void givenValidIsbn_whenGettingBook_thenReturnOne() {
		Book book = bookRepository.findById("1-26325-387-456").orElse(null);
		assertNotNull(book);
		assertEquals(book.getName(), "Java");
	}

	@Test
	public void givenInvalidIsbn_whenGettingBook_thenReturnNull() {
		Book book = bookRepository.findById("123-654-89-xyz").orElse(null);
		assertNull(book);
	}

	@Test
	public void givenBook_whenAddingBook_thenReturnSavedBook() {
		Book book = createDummyBookObject();
		Book savedBook = bookRepository.save(book);
		Book returnedBook = bookRepository.findById(book.getIsbn()).orElse(null);
		assertNotNull(returnedBook);
		assertEquals(savedBook.getIsbn(), returnedBook.getIsbn());
	}

	@Test
	public void givenBook_whenUpdatingBook_thenReturnUpdatedBook() {
		Book book = createDummyBookObject();
		Book updatedBook = bookRepository.save(book);
		Book returnedBook = bookRepository.findById(book.getIsbn()).orElse(null);
		assertNotNull(returnedBook);
		assertEquals(updatedBook.getName(), returnedBook.getName());
	}

	@Test
	public void givenIsbn_whenDeletingBook_thenGetBookBySameIsbnAndReturnNull() {
		String isbn = "1-26325-387-456";
		bookRepository.deleteById(isbn);
		Book returnedBook = bookRepository.findById(isbn).orElse(null);
		assertNull(returnedBook);
	}
}