package ae.smartdubai.bookstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ae.smartdubai.bookstore.dao.BookRepository;
import ae.smartdubai.bookstore.exception.BookNotFoundException;
import ae.smartdubai.bookstore.model.Book;

@Service
public class BookService {

	@Autowired
	private BookRepository bookRepository;

	public List<Book> getAllBooks() {
		return bookRepository.findAll();
	}

	public Optional<Book> getBookByIsbn(String isbn) {
		return bookRepository.findById(isbn);
	}

	public Book addBook(Book book) {
		return bookRepository.save(book);
	}

	public Book updateBook(Book book) {
		Optional.ofNullable(getBookByIsbn(book.getIsbn())).orElseThrow(() -> new BookNotFoundException(book.getIsbn()));
		return bookRepository.save(book);
	}

	public void deleteBook(String isbn) {
		Optional.ofNullable(getBookByIsbn(isbn)).orElseThrow(() -> new BookNotFoundException(isbn));
		bookRepository.deleteById(isbn);
	}
}