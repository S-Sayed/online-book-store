package ae.smartdubai.bookstore.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import ae.smartdubai.bookstore.BookStoreTest;
import ae.smartdubai.bookstore.dao.BookRepository;
import ae.smartdubai.bookstore.exception.BookNotFoundException;
import ae.smartdubai.bookstore.model.Book;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest extends BookStoreTest {

	@InjectMocks
	private BookService bookService;

	@Mock
	private BookRepository bookRepository;

	@Test
	public void givenNothing_whenGettingBooks_thenReturnAll() throws Exception {
		List<Book> books = new ArrayList<>();
		books.add(createDummyBookObject());

		Mockito.when(bookRepository.findAll()).thenReturn(books);
		List<Book> returnedBooks = bookService.getAllBooks();

		assertNotNull(returnedBooks);
		assertThat(returnedBooks.size()).isEqualTo(books.size());
		assertThat(returnedBooks.get(0).getIsbn()).isEqualTo(books.get(0).getIsbn());
		verify(bookRepository).findAll();
	}

	@Test
	public void givenValidIsbn_whenGettingBooks_thenReturnOne() throws Exception {
		Book book = createDummyBookObject();
		Mockito.when(bookRepository.findById(book.getIsbn())).thenReturn(Optional.ofNullable(book));

		Book returnedBook = bookService.getBookByIsbn(book.getIsbn()).get();

		assertNotNull(returnedBook);
		assertThat(returnedBook.getIsbn()).isEqualTo(book.getIsbn());
		verify(bookRepository).findById(book.getIsbn());
	}

	@Test
	public void givenInvalidIsbn_whenGettingBooks_thenReturnNull() throws Exception {
		String isbn = "1-26325-387-456-xyz";

		Mockito.when(bookRepository.findById(isbn)).thenReturn(Optional.ofNullable(null));
		Book returnedBook = bookService.getBookByIsbn(isbn).orElse(null);

		assertNull(returnedBook);
		verify(bookRepository).findById(isbn);
	}

	@Test
	public void givenValidBook_whenAdding_thenReturnIt() throws Exception {
		Book book = createDummyBookObject();
		Mockito.when(bookRepository.save(book)).thenReturn(book);

		Book returnedBook = bookService.addBook(book);

		assertNotNull(returnedBook);
		assertThat(returnedBook.getIsbn()).isEqualTo(book.getIsbn());
		verify(bookRepository).save(book);
	}

	@Test
	public void givenValidBookIsbn_whenUpdating_thenReturnIt() throws Exception {
		Book book = createDummyBookObject();
		Mockito.when(bookRepository.save(book)).thenReturn(book);

		Book returnedBook = bookService.updateBook(book);

		assertNotNull(returnedBook);
		assertThat(returnedBook.getIsbn()).isEqualTo(book.getIsbn());
		verify(bookRepository).save(book);
	}

	@Test(expected = BookNotFoundException.class)
	public void givenInvalidBookIsbn_whenUpdating_thenThrowException() throws Exception {
		Book book = new Book();
		book.setIsbn("1-26325-387-456-xyz");

		Mockito.when(bookService.getBookByIsbn(book.getIsbn())).thenThrow(new BookNotFoundException(book.getIsbn()));

		bookService.updateBook(book);
	}

	@Test
	public void givenValidBookIsbn_whenDeleting_thenDoNoting() throws Exception {
		String isbn = "1-26325-387-456";
		Mockito.doNothing().when(bookRepository).deleteById(isbn);
		bookService.deleteBook(isbn);
	}

	@Test(expected = BookNotFoundException.class)
	public void givenInvalidBookIsbn_whenDeleting_thenThrowException() throws Exception {
		String isbn = "1-26325-387-456";
		Mockito.when(bookService.getBookByIsbn(isbn)).thenThrow(new BookNotFoundException(isbn));
		bookService.deleteBook(isbn);
	}
}