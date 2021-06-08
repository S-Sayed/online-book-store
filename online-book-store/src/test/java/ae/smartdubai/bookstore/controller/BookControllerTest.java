package ae.smartdubai.bookstore.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import ae.smartdubai.bookstore.BookStoreTest;
import ae.smartdubai.bookstore.exception.BookNotFoundException;
import ae.smartdubai.bookstore.model.Book;
import ae.smartdubai.bookstore.service.BookService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerTest extends BookStoreTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private BookService bookService;

	@Test
	public void givenNoIsbn_whenGettingBooks_thenReturnAll() throws Exception {
		List<Book> books = new ArrayList<>();
		books.add(createDummyBookObject());

		Mockito.when(bookService.getAllBooks()).thenReturn(books);
		String expectedIsbn = "1-26325-387-456";

		mockMvc.perform(get("/v1/book").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(1))).andExpect(jsonPath("$[0].isbn", is(expectedIsbn)));
	}

	@Test
	public void givenValidIsbn_whenGettingBooks_thenReturnOneBook() throws Exception {
		Book book = createDummyBookObject();
		Mockito.when(bookService.getBookByIsbn(book.getIsbn())).thenReturn(Optional.ofNullable(book));

		mockMvc.perform(get("/v1/book/" + book.getIsbn()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$.isbn", is(book.getIsbn())));
	}

	@Test
	public void givenInvalidIsbn_whenGettingBooks_thenThrowException() throws Exception {
		String isbn = "1-26325-387-456-xyz";
		Mockito.doThrow(new BookNotFoundException(isbn)).when(bookService).getBookByIsbn(isbn);
		mockMvc.perform(get("/v1/book/" + isbn).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}

	@Test
	public void givenBook_whenSaving_thenReturn201() throws Exception {
		Book book = createDummyBookObject();
		Mockito.when(bookService.addBook(book)).thenReturn(book);
		mockMvc.perform(post("/v1/book").content(toJson(book)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
	}

	@Test
	public void givenBook_whenUpdating_thenReturn200AndBook() throws Exception {
		Book book = createDummyBookObject();
		Mockito.when(bookService.updateBook(book)).thenReturn(book);
		mockMvc.perform(put("/v1/book/" + book.getIsbn()).contentType(MediaType.APPLICATION_JSON).content(toJson(book)))
				.andExpect(status().isOk());
	}

	@Test
	public void givenIsbn_whenDeleting_thenReturn204() throws Exception {
		String isbn = "1-26325-387-456";
		Mockito.doNothing().when(bookService).deleteBook(isbn);
		mockMvc.perform(delete("/v1/book/" + isbn).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent());
	}
}
