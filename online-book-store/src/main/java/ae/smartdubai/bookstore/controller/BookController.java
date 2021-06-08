package ae.smartdubai.bookstore.controller;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ae.smartdubai.bookstore.annotation.Log;
import ae.smartdubai.bookstore.exception.BadRequestException;
import ae.smartdubai.bookstore.exception.BookNotFoundException;
import ae.smartdubai.bookstore.model.Book;
import ae.smartdubai.bookstore.service.BookService;

@RestController
@CrossOrigin
@RequestMapping("/v1/book")
public class BookController {
	private static final Logger LOGGER = LoggerFactory.getLogger(BookController.class);

	@Autowired
	private BookService bookService;

	@Log
	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(value = HttpStatus.OK)
	public List<Book> getAllBook() {
		return bookService.getAllBooks();
	}

	@Log
	@GetMapping(value = "/{isbn}", produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(value = HttpStatus.OK)
	public Book getBookByIsbn(@PathVariable(required = true) String isbn) {
		LOGGER.info("getBookByIsbn - isbn <{}>", isbn);
		Book book = bookService.getBookByIsbn(isbn).orElseThrow(() -> new BookNotFoundException(isbn));
		return book;
	}

	@Log
	@PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(value = HttpStatus.CREATED)
	public Book addBook(@RequestBody Book book) throws URISyntaxException {
		LOGGER.info("addBook - book <{}>", book);
		Optional.ofNullable(book).orElseThrow(BadRequestException::new);
		return bookService.addBook(book);
	}

	@Log
	@PutMapping(value = "/{isbn}", produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(value = HttpStatus.OK)
	public Book updateBook(@PathVariable(required = true) String isbn, @RequestBody Book book) {
		LOGGER.info("updateBook - isbn <{}>, book <{}>", isbn, book);
		Optional.ofNullable(book).orElseThrow(BadRequestException::new);
		book.setIsbn(isbn);
		return bookService.updateBook(book);
	}

	@Log
	@DeleteMapping(value = "/{isbn}", produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deleteBook(@PathVariable(required = true) String isbn) {
		LOGGER.info("deleteBook - isbn <{}>", isbn);
		bookService.deleteBook(isbn);
	}
}