package ae.smartdubai.bookstore.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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
import ae.smartdubai.bookstore.model.Book;
import ae.smartdubai.bookstore.service.CheckoutService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CheckoutControllerTest extends BookStoreTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CheckoutService checkoutService;

	@Test
	public void givenBooksWithoutPromotion_whenCalculatingPrice_thenReturnTotalPriceWithoutDiscount() throws Exception {
		List<Book> books = new ArrayList<>();
		books.add(createDummyBookObject());

		Mockito.when(checkoutService.calculateTotalPrice(books, null)).thenReturn(BigDecimal.valueOf(100));

		mockMvc.perform(post("/v1/checkout/order").content(toJson(books)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void givenBooksWithPromotion_whenCalculatingPrice_thenReturnTotalPriceWithDiscount() throws Exception {

		List<Book> books = new ArrayList<>();
		books.add(createDummyBookObject());

		Mockito.when(checkoutService.calculateTotalPrice(books, "SALE50")).thenReturn(BigDecimal.valueOf(50));

		mockMvc.perform(post("/v1/checkout/order").content(toJson(books)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}
}
